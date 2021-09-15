package com.tourguide.microservice.users.controllers;

import com.tourguide.library.model.UserPreferencesRequest;
import com.tourguide.library.model.UsersLocations;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserPreferences;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.users.services.UserService;
import gpsUtil.location.VisitedLocation;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get users list.
     *
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
     * Gets user rewards.
     *
     * @param userName the username
     * @return list of rewards of this user
     */
    @GetMapping("/rewards/{userName}")
    public List<UserReward> getUserRewards(@PathVariable String userName) {
        return userService.getUserRewards(getUser(userName));
    }

    /**
     * Create user reward.
     *
     * @param userName   the username
     * @param userReward add the reward for this user
     */
    @PostMapping("/rewards/{userName}")
    public void createUserReward(@PathVariable String userName, @RequestBody UserReward userReward){
        userService.addUserReward(userName, userReward);
    }

    /**
     * Update trip deals.
     *
     * @param userName  the username
     * @param tripDeals Update trip deals for this user
     */
    @PostMapping("/tripDeals/{userName}")
    public void updateTripDeals(@PathVariable String userName, @RequestBody List<Provider> tripDeals){
        userService.updateTripDeals(userName, tripDeals);
    }

    /**
     * Get all current locations list.
     *
     * @return all current locations
     */
    @GetMapping("/getAllCurrentLocations")
    public List<UsersLocations> getAllCurrentLocations(){
        return userService.getAllCurrentLocations();
    }

    /**
     * Create visited location.
     *
     * @param userName        the username
     * @param visitedLocation Add the visited location for this user
     */
    @PostMapping("/addVisitedLocation/{userName}")
    public void createVisitedLocation(@PathVariable String userName, @RequestBody VisitedLocation visitedLocation){
        userService.addVisitedLocation(userName, visitedLocation);
    }

    @PostMapping("/userPreferences/{userName}")
    public void userPreferences(@PathVariable String userName, @RequestBody UserPreferences userPreferences){
        userService.UpdateUserPreferences(userName, userPreferences);
    }
}
