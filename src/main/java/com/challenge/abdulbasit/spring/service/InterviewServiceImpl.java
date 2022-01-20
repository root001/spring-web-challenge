package com.challenge.abdulbasit.spring.service;

import com.challenge.abdulbasit.spring.entity.Challenge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Override
    public List<Challenge> getInterview(String id) {
        return getInterview(id) == null ? new ArrayList<>() : getInterview(id);
    }
}
