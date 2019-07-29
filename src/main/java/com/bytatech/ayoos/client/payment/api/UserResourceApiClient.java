package com.bytatech.ayoos.client.payment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.payment.ClientConfiguration;

@FeignClient(name="${payment.name:payment}", url="${payment.url:35.237.4.7:9090/}", configuration = ClientConfiguration.class)
public interface UserResourceApiClient extends UserResourceApi {
}