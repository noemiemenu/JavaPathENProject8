package com.tourguide.microservice.rewards;

import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.helper.InternalTestHelper;
import com.tourguide.library.helper.UsersHelper;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.rewards.service.RewardsService;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rewardCentral.RewardCentral;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestRewardsService {

    private User user;


    @BeforeEach
    public void setup() {
        String userName = "internalUser1";
        String phone = "000";
        String email = userName + "@tourGuide.com";
        this.user = new User(UUID.randomUUID(), userName, phone, email);
        UsersHelper.generateUserLocationHistory(this.user);
    }

	/*@Test
	public void userGetRewards() {
		Locale.setDefault(new Locale("en", "US"));
		GpsUtil gpsUtil = new GpsUtil();
		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());

		InternalTestHelper.setInternalUserNumber(0);
		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
		
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsUtil.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));
		tourGuideService.trackUserLocation(user);
		List<UserReward> userRewards = user.getUserRewards();
		tourGuideService.tracker.stopTracking();
		assertTrue(userRewards.size() == 1);
	}*/

    @Test
    public void isWithinAttractionProximity() {
        Locale.setDefault(new Locale("en", "US"));
        GpsUtil gpsUtil = new GpsUtil();
        RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
        Attraction attraction = gpsUtil.getAttractions().get(0);
        assertTrue(rewardsService.isWithinAttractionProximity(attraction, attraction));
    }


    @Test
    public void nearAllAttractions() {
        Locale.setDefault(new Locale("en", "US"));
        GpsUtil gpsUtil = new GpsUtil();

        RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
        rewardsService.setProximityBuffer(Integer.MAX_VALUE);

        InternalTestHelper.setInternalUserNumber(1);

        rewardsService.calculateRewards(user.getUserName());
        List<UserReward> userRewards = user.getUserRewards();

        assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
    }

}
