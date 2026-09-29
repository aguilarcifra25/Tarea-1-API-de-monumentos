package com.salesianos.dam.apimonumentos.apimonumentos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument {

    @Id @GeneratedValue
    private Long id;

    private String countryCode;
    private String countryName;
    private String cityName;
    private Double latitude;
    private Double longitude;
    private String name;
    private String desc;
    private String photoURL;


}
