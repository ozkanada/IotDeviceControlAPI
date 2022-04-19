package com.eywa.iotdevicecontrolapi.service;

import com.eywa.iotdevicecontrolapi.constant.Message;
import com.eywa.iotdevicecontrolapi.dto.DeviceDto;
import com.eywa.iotdevicecontrolapi.model.Country;
import com.eywa.iotdevicecontrolapi.model.Device;
import com.eywa.iotdevicecontrolapi.model.Operator;
import com.eywa.iotdevicecontrolapi.repository.DeviceReposirtory;
import com.eywa.iotdevicecontrolapi.repository.DeviceStutusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DeviceService {
    @Autowired
    private DeviceReposirtory deviceReposirtory;

    public List<Device> listDevicesWaitingForActivation(){
        return deviceReposirtory.getDeviceWaitingForActivation(DeviceStutusEnum.WAITING.getId());
    }

    public Device getDevice(Integer simId) { return (Device) deviceReposirtory.findById(simId).get(); }

    public void saveDevice(DeviceDto deviceDto){
        if(deviceDto.getOperatorCode() == null){
            throw new NullPointerException(Message.OPERATOR_CODE_NOT_NULL);
        }
        if(deviceDto.getCountry() == null){
            throw new NullPointerException(Message.COUNTRY_CODE_NOT_NULL);
        }
        if(deviceDto.getStatus() == null){
            throw new NullPointerException(Message.STATUS_NOT_NULL);
        }
        Device device = new Device();
        device.setOperatorCode(new Operator(deviceDto.getOperatorCode()));
        device.setCountry(new Country(deviceDto.getCountry()));
        device.setStatus(deviceDto.getStatus());
        deviceReposirtory.save(device);
    }

    public void updateDeviceStatus(DeviceDto deviceDto){
        if(deviceDto.getSimId() == null){
            throw new NullPointerException(Message.SIM_ID_NOT_NULL);
        }
        Device device = getDevice(deviceDto.getSimId());
        if(device == null){
            throw new NoSuchElementException(Message.DEVICE_NO_FOUND);
        }
        if(deviceDto.getStatus() == null){
            throw new NullPointerException(Message.STATUS_NOT_NULL);
        }
        device.setStatus(deviceDto.getStatus());
        deviceReposirtory.save(device);
    }

    public void deleteDevice(Integer id){
        deviceReposirtory.deleteById(id);
    }
}
