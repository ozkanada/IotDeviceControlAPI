package com.eywa.iotdevicecontrolapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "country")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class Country {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "country_name")
    private String countryName;

    @OneToOne(mappedBy = "country")
    private Device device;
}
