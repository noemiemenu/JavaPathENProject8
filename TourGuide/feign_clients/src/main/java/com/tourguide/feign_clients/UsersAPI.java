package com.tourguide.feign_clients;

import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import gpsUtil.location.VisitedLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;


import java.util.List;

@FeignClient(name = "users-API", url = "${usersAPI.service.url}")
public interface UsersAPI {

    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/user/{userName}")
    User getUser(@PathVariable String userName);

    @GetMapping("/rewards/{userName}")
    List<UserReward> getUserRewards(@PathVariable String userName);

    @PostMapping("/rewards/{userName}")
    void createUserReward(@PathVariable String userName, @RequestBody UserReward userReward);

    @PostMapping("/tripDeals/{userName}")
    void updateTripDeals(@PathVariable String userName, @RequestBody List<Provider> tripDeals);

    @PostMapping("/addVisitedLocation/{userName}")
    void createVisitedLocation(@PathVariable String userName, @RequestBody VisitedLocation visitedLocation);
}
