package com.tourguide.microservice.rewards.controllers;


import com.tourguide.microservice.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RewardsController {
    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService){
        this.rewardsService = rewardsService;
    }

    @PostMapping("/calculateRewards/{userName}")
    public ResponseEntity<String> calculateRewards(@PathVariable String userName) {
        rewardsService.calculateRewards(userName);
        return ResponseEntity.ok().build();
    }
}
