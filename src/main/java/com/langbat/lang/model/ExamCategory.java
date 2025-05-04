package com.langbat.lang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "examCategory")
public class ExamCategory extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "languageID")
    private Language language;
    private String description;
    private String ltype;

    @OneToMany(mappedBy = "examCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    public ExamCategory(String name, String description, String ltype) {
        this.name = name;
        this.description = description;
        this.ltype = ltype;
    }

    public ExamCategory() {
    }

    public ExamCategory(Long id) {
        this.id = id;
    }
}
