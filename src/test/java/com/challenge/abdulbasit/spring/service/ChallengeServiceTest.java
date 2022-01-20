package com.challenge.abdulbasit.spring.service;

import com.challenge.abdulbasit.spring.api.ChallengeController;
import com.challenge.abdulbasit.spring.api.InterviewController;
import com.challenge.abdulbasit.spring.entity.Challenge;
import com.challenge.abdulbasit.spring.service.config.WebClientImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeServiceTest {

    private InterviewController interviewController = new InterviewController();

    WebClientImpl webClientImpl = new WebClientImpl();
    private ChallengeService challengeService = new ChallengeService(webClientImpl);
    private ChallengeController challengeController = new ChallengeController(challengeService);

    @Test
    void webFluxTest() {
        challengeController.printReturn();
    }

    @Test
    void interviewTest() {
        interviewController.get("2");
    }

    @Test
    void isPassing() {
        List<Challenge> challenges = Arrays.asList(new Challenge("1", "xyz", "yes", true),
                new Challenge("2", "await", "no", true),
                new Challenge("3", "oop concepts", "no", true),
                new Challenge("4", "abc into xyz", "no", true),
                new Challenge("5", "asyncawait", "no", false),
                new Challenge("6", "asserts true", "no", false));
        boolean result = challengeService.isPassing(challenges);
        assertFalse(result);
    }

    @Test
    @DisplayName("UserStory3 - TC12 - Asserting for a True scenario")
    void assertsTrue() {
        List<Challenge> challenges = Arrays.asList(new Challenge("1", "xyz", "yes", true),
                new Challenge("2", "await", "no", true),
                new Challenge("3", "oop concepts", "no", true),
                new Challenge("4", "abc into xyz", "no", true),
                new Challenge("5", "asyncawait", "no", true));
        boolean result = challengeService.isPassing(challenges);
        assertTrue(result);
    }

    @Test
    void assertsFalse() {
        List<Challenge> challenges = null;
        boolean result = challengeService.isPassing(challenges);
        assertFalse(result);
    }
}