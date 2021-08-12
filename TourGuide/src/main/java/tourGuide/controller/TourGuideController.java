package tourGuide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;

import gpsUtil.location.VisitedLocation;
import tourGuide.model.UserPreferencesRequest;
import tourGuide.service.TourGuideService;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserPreferences;
import tripPricer.Provider;

import javax.validation.Valid;

@RestController
public class TourGuideController {

	@Autowired
	TourGuideService tourGuideService;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }
    
    @RequestMapping("/getLocation") 
    public String getLocation(@RequestParam String userName) {
    	VisitedLocation visitedLocation = tourGuideService.getUserLocation(getUser(userName));
		return JsonStream.serialize(visitedLocation.location);
    }
    
    //  Finish = Change this method to no longer return a List of Attractions.
    @RequestMapping("/getNearbyAttractions") 
    public String getNearbyAttractions(@RequestParam String userName) {
        User user = getUser(userName);
    	VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
    	return JsonStream.serialize(tourGuideService.getNearByAttractions(visitedLocation, user));
    }
    
    @RequestMapping("/getRewards") 
    public String getRewards(@RequestParam String userName) {
    	return JsonStream.serialize(tourGuideService.getUserRewards(getUser(userName)));
    }

    // finish = Get a list of every user's most recent location as JSON
    @GetMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() {
    	return JsonStream.serialize(tourGuideService.getAllCurrentLocations());
    }


    @RequestMapping("/getTripDeals")
    public String getTripDeals(@RequestParam String userName, @Valid UserPreferencesRequest userPreferencesRequest) {
        User user = getUser(userName);
        UserPreferences userPreferences = user.getUserPreferences();
        userPreferences.setCurrency(userPreferencesRequest.getCurrency());
        userPreferences.setAttractionProximity(userPreferencesRequest.getAttractionProximity());
        userPreferences.setHighPricePoint(userPreferencesRequest.getHighPricePoint());
        userPreferences.setLowerPricePoint(userPreferencesRequest.getLowerPricePoint());
        userPreferences.setTripDuration(userPreferencesRequest.getTripDuration());
        userPreferences.setTicketQuantity(userPreferencesRequest.getTicketQuantity());
        userPreferences.setNumberOfAdults(userPreferencesRequest.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesRequest.getNumberOfChildren());
    	List<Provider> providers = tourGuideService.getTripDeals(user);
    	return JsonStream.serialize(providers);
    }

    @RequestMapping("/users")
    public List<User> getUsers(){
        return tourGuideService.getAllUsers();
    }


    private User getUser(String userName) {
    	return tourGuideService.getUser(userName);
    }
   

}