package com.tourguide.feign_clients;

import com.tourguide.library.model.DistancesHolder;
import gpsUtil.location.Attraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "rewards-API", url = "http://localhost:8383")
public interface RewardsAPI {

    @PostMapping("/calculateRewards/{userName}")
    ResponseEntity<String> calculateRewards(@PathVariable String userName);

    @GetMapping("/getRewardPoints/{userName}")
    int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName);

    @GetMapping("/getDistance")
    double getDistance(@RequestBody DistancesHolder distancesHolder);
}
