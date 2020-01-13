package com.fuxl.dataSource;

public class DataBaseContextHolder {

    public enum DataBaseType {
        MASTER, SLAVE;
    }

    public static final ThreadLocal<DataBaseType> dataType = new ThreadLocal<DataBaseType>();


    public static DataBaseType getDataBaseType() {
        return dataType.get() == null ? DataBaseType.MASTER : dataType.get();
    }

    public static void setDataBasicType(DataBaseType dataBasicType) {
        if (dataBasicType == null) throw new NullPointerException();
        dataType.set(dataBasicType);
    }

    public static void clearDataBasicType() {
        dataType.remove();
    }
}
