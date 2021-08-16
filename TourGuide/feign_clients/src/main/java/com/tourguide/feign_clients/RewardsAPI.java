package com.tourguide.feign_clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "rewards-API", url = "http://localhost:8383")
public interface RewardsAPI {

    @PostMapping("/calculateRewards/{userName}")
    public ResponseEntity<String> calculateRewards(@PathVariable String userName);
}
