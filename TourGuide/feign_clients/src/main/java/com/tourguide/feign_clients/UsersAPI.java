package com.tourguide.feign_clients;

import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import gpsUtil.location.VisitedLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;


import java.util.List;

/**
 * The interface Users api.
 */
@FeignClient(name = "users-API", url = "${usersAPI.service.url}")
public interface UsersAPI {

    /**
     * Gets users.
     *
     * @return the users
     */
    @GetMapping("/users")
    List<User> getUsers();

    /**
     * Gets user.
     *
     * @param userName the user name
     * @return the user
     */
    @GetMapping("/user/{userName}")
    User getUser(@PathVariable String userName);

    /**
     * Gets user rewards.
     *
     * @param userName the user name
     * @return the user rewards
     */
    @GetMapping("/rewards/{userName}")
    List<UserReward> getUserRewards(@PathVariable String userName);

    /**
     * Create user reward.
     *
     * @param userName   the user name
     * @param userReward the user reward
     */
    @PostMapping("/rewards/{userName}")
    void createUserReward(@PathVariable String userName, @RequestBody UserReward userReward);

    /**
     * Update trip deals.
     *
     * @param userName  the user name
     * @param tripDeals the trip deals
     */
    @PostMapping("/tripDeals/{userName}")
    void updateTripDeals(@PathVariable String userName, @RequestBody List<Provider> tripDeals);

    /**
     * Create visited location.
     *
     * @param userName        the user name
     * @param visitedLocation the visited location
     */
    @PostMapping("/addVisitedLocation/{userName}")
    void createVisitedLocation(@PathVariable String userName, @RequestBody VisitedLocation visitedLocation);
}
