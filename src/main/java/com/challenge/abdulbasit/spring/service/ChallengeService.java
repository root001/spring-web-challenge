package com.challenge.abdulbasit.spring.service;

import com.challenge.abdulbasit.spring.entity.Challenge;
import com.challenge.abdulbasit.spring.service.config.WebClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class ChallengeService {

    @Autowired
    private WebClientImpl webClientImpl;

    public ChallengeService(WebClientImpl webClientImpl) {
        this.webClientImpl = webClientImpl;
    }

    //Call for web client(reactive spring)
    public Mono<String> getUsers() {
        Mono<String> response = webClientImpl.simpleClient().get()
                .uri("https://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToMono(String.class);

        System.out.println("gotten :");
        return response;
    }

    public boolean isPassing(List<Challenge> challenges) {
        AtomicInteger counter = new AtomicInteger();
        if (challenges != null && challenges.size() > 0) {
            challenges.forEach(challenge -> {
                if (challenge.isCorrect()) {
                    counter.getAndIncrement();
                }
            });
    //        log.info("success size {} : counter {}", challenges.size(), counter.get());
            double success = (double)counter.get() / challenges.size();
    //        log.info("success rate is {}", success);
            return success > 0.875;
        }
        return false;
    }

    public boolean isEven(int number){
        return number % 2 == 0;
    }

}
