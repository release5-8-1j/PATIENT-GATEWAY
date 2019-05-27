package com.bytatech.ayoos.client.doctor.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.doctor.DoctorClientConfiguration;

@FeignClient(name="${doctor.name:doctor}", url="${doctor.url:localhost:8083/}", configuration = DoctorClientConfiguration.class)
public interface ReplyResourceApiClient extends ReplyResourceApi {
}