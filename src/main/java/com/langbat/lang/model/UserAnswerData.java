package com.langbat.lang.model;


import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerData {
    private Long questionId;
    private String answer;
    private Long timeSpent; // Time spent on the question in seconds
}

