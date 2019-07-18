package com.bytatech.ayoos.client.patient.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.patient.PatientClientConfiguration;

@FeignClient(name="${patient.name:patient}", url="${patient.url}", configuration = PatientClientConfiguration.class)
public interface AddressLineResourceApiClient extends AddressLineResourceApi {
}