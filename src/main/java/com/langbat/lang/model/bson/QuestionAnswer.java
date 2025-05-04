package com.langbat.lang.model.bson;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswer {
    private String label; // Nhãn đại diện (A, B, C, D)
    private String text;
    private boolean correct;
}
