package com.langbat.lang.model.bson.user;


import com.langbat.lang.model.xnum.AcademicMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Academic {
    private Long id;
    private AcademicMode mode;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private String grade;
    private String startDate;
    private String endDate;
    private String description;
    public Academic() {
        this.id = System.currentTimeMillis();
    }
}
