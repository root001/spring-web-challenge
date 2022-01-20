package com.challenge.abdulbasit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Challenge {
    private String id;
    private String question;
    private String answer;
    private boolean correct;
}
