package com.eywa.iotdevicecontrolapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "operator")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Operator {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_code")
    private Integer operatorCode;

    @Column(name = "operator_name")
    private String operatorName;

    @OneToOne(mappedBy = "operatorCode")
    private Device device;
}
