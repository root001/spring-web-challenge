package com.challenge.abdulbasit.spring.api;

import com.challenge.abdulbasit.spring.entity.Challenge;
import com.challenge.abdulbasit.spring.service.ChallengeService;
import com.challenge.abdulbasit.spring.service.InterviewService;
import com.challenge.abdulbasit.spring.service.config.WebClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoint to submit and query interviews.
 */
@Slf4j
@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    private static ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }
    @RequestMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void printReturn() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Running print class...");
        System.out.println((">> Get Users <<<<<= " + challengeService
                .getUsers().doOnNext(System.out::println).block()));
    }

}
