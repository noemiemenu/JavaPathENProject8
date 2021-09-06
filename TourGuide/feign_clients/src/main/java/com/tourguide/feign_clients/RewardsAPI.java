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

/**
 * The interface Rewards api.
 */
@FeignClient(name = "rewards-API", url = "${rewardsAPI.service.url}")
public interface RewardsAPI {

    /**
     * Calculate rewards list.
     *
     * @param userName the user name
     * @return the list
     */
    @PostMapping("/calculateRewards/{userName}")
    List<UserReward> calculateRewards(@PathVariable String userName);

    /**
     * Gets reward points.
     *
     * @param attraction the attraction
     * @param userName   the user name
     * @return the reward points
     */
    @PostMapping("/getRewardPoints/{userName}")
    int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName);

    /**
     * Gets distance.
     *
     * @param distancesHolder the distances holder
     * @return the distance
     */
    @PostMapping("/getDistance")
    double getDistance(@RequestBody DistancesHolder distancesHolder);
}
