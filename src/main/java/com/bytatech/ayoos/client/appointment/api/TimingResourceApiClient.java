package com.bytatech.ayoos.client.appointment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.appointment.ClientConfiguration;

@FeignClient(name="${appointment.name:appointment}", url="${appointment.url:34.68.84.96:8090/}", configuration = ClientConfiguration.class)
public interface TimingResourceApiClient extends TimingResourceApi {
}
