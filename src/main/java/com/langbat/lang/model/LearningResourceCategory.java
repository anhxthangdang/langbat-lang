package com.langbat.lang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "learning_resource_category")
public class LearningResourceCategory extends UserAudit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "exam_category_id")
    private ExamCategory examCategory;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private LearningResourceCategory parentCategory;

    private String parentCategoryName;

    @OneToMany(mappedBy = "resourceCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vocabulary> vocabularies = new ArrayList<>();

    @OneToMany(mappedBy = "resourceCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grammar> grammars = new ArrayList<>();

    private boolean enabled = true;
}