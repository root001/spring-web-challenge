package com.challenge.abdulbasit.spring.api;

import com.challenge.abdulbasit.spring.entity.Challenge;
import com.challenge.abdulbasit.spring.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @RequestMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Challenge> get(@PathVariable String id) {
        return interviewService.getInterview(id);
    }

    public static void main(String[] args) {
        //get("3");
    }
}
