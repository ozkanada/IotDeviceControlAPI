package com.eywa.iotdevicecontrolapi.repository;

import com.eywa.iotdevicecontrolapi.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceReposirtory<Integer> extends JpaRepository<Device,Integer> {

    @Query("SELECT u FROM Device u WHERE u.status = ?1")
    List<Device> getDeviceWaitingForActivation(Integer status);
}
