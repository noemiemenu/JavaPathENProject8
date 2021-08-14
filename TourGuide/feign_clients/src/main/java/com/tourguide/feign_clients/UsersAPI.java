package com.tourguide.feign_clients;

import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


}
