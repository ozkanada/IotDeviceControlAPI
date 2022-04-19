package com.eywa.iotdevicecontrolapi.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id")
    private Integer simId;

    @OneToOne
    @JoinColumn(name = "operator_code", referencedColumnName = "operator_code")
    private Operator operatorCode;

    @OneToOne
    @JoinColumn(name = "country", referencedColumnName = "country_code")
    private Country country;

    @Column(name = "status")
    private Integer status;
}

