package com.challenge.abdulbasit.spring.service;

import com.challenge.abdulbasit.spring.entity.Challenge;

import java.util.List;

public interface InterviewService {
    /**
     *      * Looks up all [Challenge] associated with
     *      * an interview identified by id and returns
     *      * as a List.
     *     
     */
    List<Challenge> getInterview(String id);
}
