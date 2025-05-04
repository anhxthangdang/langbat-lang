package com.langbat.lang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "examLevel")
public class ExamLevel extends UserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "examCategoryID")
    private ExamCategory examCategory;

    private String description;
}
