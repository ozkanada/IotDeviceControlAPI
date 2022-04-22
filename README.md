#Technologies used :

 - Spring Boot 2.6.6.RELEASE
 - Spring 5.3.18.RELEASE
 - Maven 3.6.3
 - Java 8
 - Lombok
 - Mysql


#1. Database

Mysql database was used. Database script is in src/main/resurces/database folder.
File name is eywa.sql. Open this file in mysql editor and than run.

#2.Build Project

Clone project to local repository.

```
git clone https://github.com/ozkanada/IotDeviceControlAPI.git
cd IotDeviceControlAPI
git checkout test
mvn install
mvn spring-boot:run
```

#3.Test

You can use postman to test this api.  

###Save Device

Request uri : http://localhost:8080/api/saveDevice

```
Body: { 
        "operatorCode" : 2,
        "country" : 3,
        "status" :3  
       }
```

###Update Device Status
Request uri : http://localhost:8080/api/updateDeviceStatus/1

```
Body: { 
        "status" :3 
      }
```

###Delete Device
Request uri : http://localhost:8080/api/deleteDevice/1

###List Devices Waiting For Activation
Request uri : http://localhost:8080/api/devices
