package com.eywa.iotdevicecontrolapi;

import com.eywa.iotdevicecontrolapi.controller.DeviceController;
import com.eywa.iotdevicecontrolapi.model.Country;
import com.eywa.iotdevicecontrolapi.model.Device;
import com.eywa.iotdevicecontrolapi.model.Operator;
import com.eywa.iotdevicecontrolapi.repository.DeviceStutusEnum;
import com.eywa.iotdevicecontrolapi.request.DeviceRequest;
import com.eywa.iotdevicecontrolapi.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DeviceService deviceService;

    private DeviceRequest deviceSave;
    private DeviceRequest deviceUpdate;

    @Test
    public void listDevicesWaitingForActivation() throws Exception {
        when(deviceService.listDevicesWaitingForActivation()).thenReturn(
                Arrays.asList(new Device(1,new Operator(1),new Country(1),3),
                        new Device(2,new Operator(1),new Country(2),3))
        );

        RequestBuilder request= MockMvcRequestBuilders.get("/api/devices").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().json("[{\"simId\":1,\"operatorCode\":{\"operatorCode\":1,\"operatorName\":\"Vodafone\"},\"country\":{\"countryCode\":1,\"countryName\":\"England\"},\"status\":3},{\"simId\":2,\"operatorCode\":{\"operatorCode\":1,\"operatorName\":\"Vodafone\"},\"country\":{\"countryCode\":2,\"countryName\":\"Turkey\"},\"status\":3}]"))
                                .andReturn();

    }

    @Before
    public void setup() {
        deviceSave = new DeviceRequest();
        deviceSave.setSimId(1);
        deviceSave.setCountry(1);
        deviceSave.setOperatorCode(1);
        deviceSave.setStatus(1);

        deviceUpdate = new DeviceRequest();
        deviceUpdate.setStatus(DeviceStutusEnum.ACTIVE.getId());
    }
    @Test
    public void saveDevices() throws Exception {
        String json = mapper.writeValueAsString(deviceSave);
        RequestBuilder request= MockMvcRequestBuilders.post("/api/saveDevice")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(json)
                                    .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());

    }

    @Test
    public void updateDevicesStatus() throws Exception {
        String json = mapper.writeValueAsString(deviceUpdate);
        RequestBuilder request= MockMvcRequestBuilders.put("/api/updateDeviceStatus/{simId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());

    }

    @Test
    public void deleteDevices() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.delete("/api/deleteDevice/{simId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isOk());
    }
}
