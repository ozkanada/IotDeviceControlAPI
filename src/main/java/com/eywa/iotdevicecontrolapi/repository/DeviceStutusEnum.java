package com.eywa.iotdevicecontrolapi.repository;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum DeviceStutusEnum {
    ACTIVE(1,"Active"),
    BLOCKED(2,"Blocked"),
    WAITING(3,"Waiting"),
    DEACTIVATED(4,"Deactivated");

    @Getter
    private Integer id;
    @Getter
    private String value;

    DeviceStutusEnum(Integer id, String value){
        this.id = id;
        this.value = value;
    }

    private static final Map<Integer, DeviceStutusEnum> lookupWithId = new HashMap<>();
    private static final Map<String, DeviceStutusEnum> lookupWithValue = new HashMap<>();

    static{
        for (DeviceStutusEnum item: DeviceStutusEnum.values()){
            lookupWithId.put(item.getId(),item);
        }
    }

    static{
        for (DeviceStutusEnum item: DeviceStutusEnum.values()){
            lookupWithValue.put(item.getValue(),item);
        }
    }

    public static DeviceStutusEnum get(Integer id) {return lookupWithId.get(id);}

    public static DeviceStutusEnum get(String value) {return lookupWithId.get(value);}
}