package com.langbat.lang.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "part")
public class Part extends UserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sub;

    @ManyToOne
    @JoinColumn(name = "exam_category_id")
    private ExamCategory examCategory;

    @OneToMany(mappedBy = "part")
    private List<Question> questions;
    private String description;

    private Integer duration;
    private Integer iindex;

    public Part(ExamCategory examCategory,String name, String sub, String description, Integer duration, Integer iindex) {
        this.examCategory =examCategory;
        this.name = name;
        this.sub = sub;
        this.description = description;
        this.duration = duration;
        this.iindex = iindex;
    }

    public Part(String name, String sub, String description, Integer duration, Integer iindex) {
        this.name = name;
        this.sub = sub;
        this.description = description;
        this.duration = duration;
        this.iindex = iindex;
    }

    public Part() {

    }

    public Part(Long id) {
        this.id = id;
    }
}
