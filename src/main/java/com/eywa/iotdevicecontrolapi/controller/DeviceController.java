package com.eywa.iotdevicecontrolapi.controller;

import com.eywa.iotdevicecontrolapi.request.DeviceRequest;
import com.eywa.iotdevicecontrolapi.dto.DeviceDto;
import com.eywa.iotdevicecontrolapi.model.Device;
import com.eywa.iotdevicecontrolapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    /**
     * Retrieve device list waiting for activation
     * @return List<Device>
     */
    @GetMapping(value = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> list() {
        return deviceService.listDevicesWaitingForActivation();
    }

    /**
     * save new device
     * @param deviceRequest
     */
    @PostMapping(value = "/saveDevice", produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody DeviceRequest deviceRequest) {
        try {
            DeviceDto deviceDto = mapperDeviceDto(deviceRequest);
            deviceService.saveDevice(deviceDto);
        }catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    /**
     * update device status
     * @param deviceRequest
     * @param simId
     * @return
     */
    @PutMapping(value = "/updateDeviceStatus/{simId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDeviceStatus(@RequestBody DeviceRequest deviceRequest, @PathVariable Integer simId) {
        try {
            DeviceDto deviceDto = mapperDeviceDto(deviceRequest);
            deviceDto.setSimId(simId);
            deviceService.updateDeviceStatus(deviceDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    /**
     * delete device
     * @param simId
     */
    @DeleteMapping("/deleteDevice/{simId}")
    public void deleteDevice(@PathVariable Integer simId) {
        deviceService.deleteDevice(simId);
    }

    /**
     * DeviceRequest to DeviceDto mapper
     * @param deviceRequest
     * @return
     */

    private DeviceDto mapperDeviceDto(DeviceRequest deviceRequest){
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setSimId(deviceRequest.getSimId());
        deviceDto.setCountry(deviceRequest.getCountry());
        deviceDto.setOperatorCode(deviceRequest.getOperatorCode());
        deviceDto.setStatus(deviceRequest.getStatus());
        return deviceDto;
    }
}
