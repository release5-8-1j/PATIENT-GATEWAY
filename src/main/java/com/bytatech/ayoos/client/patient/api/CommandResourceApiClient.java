package com.bytatech.ayoos.client.patient.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.patient.ClientConfiguration;

@FeignClient(name="${patient.name:patient}", url="${patient.url:35.243.137.225:8081/}", configuration = ClientConfiguration.class)
public interface CommandResourceApiClient extends CommandResourceApi {
}