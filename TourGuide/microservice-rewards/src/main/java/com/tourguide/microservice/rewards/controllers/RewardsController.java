package com.tourguide.microservice.rewards.controllers;


import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.model.DistancesHolder;

import com.tourguide.microservice.rewards.service.RewardsService;

import gpsUtil.location.Attraction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RewardsController {
    private final RewardsService rewardsService;
    private final UsersAPI usersAPI;

    public RewardsController(RewardsService rewardsService, UsersAPI usersAPI){
        this.rewardsService = rewardsService;
        this.usersAPI = usersAPI;
    }

    @PostMapping("/calculateRewards/{userName}")
    public ResponseEntity<String> calculateRewards(@PathVariable String userName) {
        rewardsService.calculateRewards(userName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getRewardPoints/{userName}")
    public int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName) {
        return rewardsService.getRewardPoints(attraction, usersAPI.getUser(userName));
    }

    @PostMapping("/getDistance")
    public double getDistance(@RequestBody DistancesHolder distancesHolder){
        return rewardsService.getDistance(distancesHolder.getLocation1(), distancesHolder.getLocation2());
    }
}
