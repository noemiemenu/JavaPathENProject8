package com.tourguide.feign_clients;

import com.tourguide.library.model.DistancesHolder;
import com.tourguide.library.user.UserReward;
import gpsUtil.location.Attraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "rewards-API", url = "${rewardsAPI.service.url}")
public interface RewardsAPI {

    @PostMapping("/calculateRewards/{userName}")
    List<UserReward> calculateRewards(@PathVariable String userName);

    @PostMapping("/getRewardPoints/{userName}")
    int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName);

    @PostMapping("/getDistance")
    double getDistance(@RequestBody DistancesHolder distancesHolder);
}
