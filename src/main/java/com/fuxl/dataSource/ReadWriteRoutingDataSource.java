package com.fuxl.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 代理数据源获取方式（extends AbstractRoutingDataSource 根据需求得知拿到主库还是从库 相当于代理）
 */
//@Component
public class ReadWriteRoutingDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getDataBaseType();
    }

}
