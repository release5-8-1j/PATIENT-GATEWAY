package com.bytatech.ayoos.client.patient.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.patient.PatientClientConfiguration;

@FeignClient(name="${patient.name:patient}", url="${patient.url:35.184.100.236:8081/}", configuration = PatientClientConfiguration.class)
public interface UserResourceApiClient extends UserResourceApi {
}