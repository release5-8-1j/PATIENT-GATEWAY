package com.bytatech.ayoos.client.payment.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.client.payment.PaymentClientConfiguration;

@FeignClient(name="${payment.name:payment}", url="${payment.url:35.237.4.7:9090/}", configuration = PaymentClientConfiguration.class)
public interface RazorpayCommandResourceApiClient extends RazorpayCommandResourceApi {
}