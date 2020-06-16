package com.fuxl.dataSource;
/**
 * 主从分离设计步骤：
 * 1、主从数据源配置；
 * 2、主从数据源转换为对象@Value({*.*.*})
 * 3、mybatis管理数据源sqlSessionFactory (MyMybatisAutoConfiguration extends MybatisAutoConfiguration)
 * 4、代理数据源获取方式（ReadWriteRoutingDataSource extends AbstractRoutingDataSource 根据需求得知拿到主库还是从库 相当于代理）
 * 5、新建注解@ReadonlyConnection
 * 6、给注解添加AOP代理@Around("@annotation(readonlyConnection)")
 */

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 主从数据源转换为对象
 * druid页面配置及过滤
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${druid.type}")//注入druid.type配置
    private Class<? extends DataSource> dataSourceType;

    /**
     * 主库
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary //优先选择
    @ConfigurationProperties(prefix = "druid.master")//注入以druid.master为前缀的配置
    public DataSource masterDataSource() {
        DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        LOGGER.info("======master:{}======", masterDataSource);
        return masterDataSource;
    }

    /**
     * 从库
     * @return
     */
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource() {
        DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        LOGGER.info("======slave:{}======", slaveDataSource);
        return slaveDataSource;
    }

    /**
     * druid 展示页面的servlet
     * http://localhost:8080/druid
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
//        servletRegistrationBean.addInitParameter("allow","localhost");
//        servletRegistrationBean.addInitParameter("deny","/deny");
//        servletRegistrationBean.addInitParameter("loginUsername","fuxl"); //账号
//        servletRegistrationBean.addInitParameter("loginPassword","fuxl"); //密码
        LOGGER.info("druid console manager init :{}", servletRegistrationBean);
        return servletRegistrationBean;
    }

    /**
     * 创建过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        LOGGER.info("druid filter register :{}", filterRegistrationBean);
        return filterRegistrationBean;
    }
}
