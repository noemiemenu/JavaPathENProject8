package com.tourguide.microservice.rewards.controllers;


import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.model.DistancesHolder;

import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.rewards.service.RewardsService;

import gpsUtil.location.Attraction;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Rewards controller.
 */
@RestController
public class RewardsController {
    private final RewardsService rewardsService;
    private final UsersAPI usersAPI;

    /**
     * Instantiates a new Rewards controller.
     *
     * @param rewardsService the rewards service
     * @param usersAPI       the users api
     */
    public RewardsController(RewardsService rewardsService, UsersAPI usersAPI){
        this.rewardsService = rewardsService;
        this.usersAPI = usersAPI;
    }

    /**
     * Calculate rewards list.
     *
     * @param userName the user name
     * @return the list
     */
    @PostMapping("/calculateRewards/{userName}")
    public List<UserReward> calculateRewards(@PathVariable String userName) {
        return rewardsService.calculateRewards(userName);
    }

    /**
     * Gets reward points.
     *
     * @param attraction the attraction
     * @param userName   the user name
     * @return the reward points
     */
    @PostMapping("/getRewardPoints/{userName}")
    public int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName) {
        return rewardsService.getRewardPoints(attraction, usersAPI.getUser(userName));
    }

    /**
     * Get distance double.
     *
     * @param distancesHolder the distances holder
     * @return the double
     */
    @PostMapping("/getDistance")
    public double getDistance(@RequestBody DistancesHolder distancesHolder){
        return rewardsService.getDistance(distancesHolder.getLocation1(), distancesHolder.getLocation2());
    }
}
