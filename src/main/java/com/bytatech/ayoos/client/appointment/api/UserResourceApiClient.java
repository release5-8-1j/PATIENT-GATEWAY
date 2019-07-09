package com.bytatech.ayoos.client.appointment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.appointment.ClientConfiguration;

@FeignClient(name="${appointment.name:appointment}", url="${appointment.url:35.184.100.236:8090/}", configuration = ClientConfiguration.class)
public interface UserResourceApiClient extends UserResourceApi {
}