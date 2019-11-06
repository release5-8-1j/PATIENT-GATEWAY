package com.bytatech.ayoos.client.appointment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.appointment.ClientConfiguration;

@FeignClient(name="${appointment.name:appointment}", url="${appointment.url:35.229.27.47:8090/}", configuration = ClientConfiguration.class)
public interface StatusResourceApiClient extends StatusResourceApi {
}