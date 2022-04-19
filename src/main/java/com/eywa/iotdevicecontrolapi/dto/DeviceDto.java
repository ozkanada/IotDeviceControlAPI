package com.eywa.iotdevicecontrolapi.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DeviceDto {
    private Integer simId;
    private Integer operatorCode;
    private Integer country;
    private Integer status;
}
