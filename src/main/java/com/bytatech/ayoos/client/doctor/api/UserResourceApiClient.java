package com.bytatech.ayoos.client.doctor.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.doctor.DoctorClientConfiguration;

@FeignClient(name="${doctor.name:doctor}", url="${doctor.url}", configuration = DoctorClientConfiguration.class)
public interface UserResourceApiClient extends UserResourceApi {
}