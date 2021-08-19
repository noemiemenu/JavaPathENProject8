package com.tourguide.feign_clients;

import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;


import java.util.List;

@FeignClient(name = "users-API", url = "http://localhost:8282")
public interface UsersAPI {

    @GetMapping("/users")
    List<User> getUsers();

    @GetMapping("/user/{userName}")
    User getUser(@PathVariable String userName);

    @GetMapping("/rewards/{userName}")
    List<UserReward> getUserRewards(@PathVariable String userName);

    @PostMapping("/rewards/{userName}")
    void createUserReward(UserReward userReward, @PathVariable String userName);

    @PatchMapping("/tripDeals/{userName}")
    void updateTripDeals(@PathVariable String userName, @RequestBody List<Provider> tripDeals);

}
