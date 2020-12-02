package com.atguigu.spring.boot.scheduling.enums;

public enum SysJobStatus {

    SUSPEND(0,"暂停"),
    NORMAL(1,"正常");

    private final int value;
    private final String desrc;

    SysJobStatus(int value,String desrc){
        this.value = value;
        this.desrc = desrc;
    }

    public byte getValue(){
        return (byte)value;
    }

    public String getDesrc(){
        return desrc;
    }

    public boolean equalB(byte equalValue){
        return (byte) value == equalValue;
    }

    public boolean equalI(int equalValue){
        return value == equalValue;
    }

}
