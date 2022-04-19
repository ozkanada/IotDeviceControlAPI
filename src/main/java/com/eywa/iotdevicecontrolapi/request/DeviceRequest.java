package com.eywa.iotdevicecontrolapi.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DeviceRequest {
    private Integer simId;
    private Integer operatorCode;
    private Integer country;
    private Integer status;
}
