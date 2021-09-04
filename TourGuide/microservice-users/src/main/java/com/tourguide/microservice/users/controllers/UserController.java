package com.tourguide.microservice.users.controllers;

import com.tourguide.library.model.UsersLocations;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.users.services.UserService;
import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return list of all users
     */
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    /**
     * @param userName
     * @return return a user with this name
     */
    @GetMapping("/user/{userName}")
    private User getUser(@PathVariable String userName) {
        return userService.getUser(userName);
    }

    /**
     * @param userName
     * @return list of rewards of this user
     */
    @GetMapping("/rewards/{userName}")
    public List<UserReward> getUserRewards(@PathVariable String userName) {
        return userService.getUserRewards(getUser(userName));
    }

    /**
     * @param userName
     * @param userReward
     * add the reward for this user
     */
    @PostMapping("/rewards/{userName}")
    public void createUserReward(@PathVariable String userName, @RequestBody UserReward userReward){
        userService.addUserReward(userName, userReward);
    }

    /**
     * @param userName
     * @param tripDeals
     * Update trip deals for this user
     */
    @PostMapping("/tripDeals/{userName}")
    public void updateTripDeals(@PathVariable String userName, @RequestBody List<Provider> tripDeals){
        userService.updateTripDeals(userName, tripDeals);
    }

    /**
     * @return all current locations
     */
    @GetMapping("/getAllCurrentLocations")
    public List<UsersLocations> getAllCurrentLocations(){
        return userService.getAllCurrentLocations();
    }

    /**
     * @param userName
     * @param visitedLocation
     * Add the visited location for this user
     */
    @PostMapping("/addVisitedLocation/{userName}")
    public void createVisitedLocation(@PathVariable String userName, @RequestBody VisitedLocation visitedLocation){
        userService.addVisitedLocation(userName, visitedLocation);
    }
}
