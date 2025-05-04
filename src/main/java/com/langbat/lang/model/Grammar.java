package com.langbat.lang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "grammar")
public class Grammar extends UserAudit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rule;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String level;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "exam_category_id")
    private ExamCategory examCategory;

    @ManyToOne
    @JoinColumn(name = "learning_resource_category_id")
    private LearningResourceCategory resourceCategory;

    @OneToMany(mappedBy = "grammar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Example> examples = new ArrayList<>();

    @Column(name = "search_vector", columnDefinition = "tsvector")
    @ColumnTransformer(read = "search_vector", write = "to_tsvector('simple', ?)")
    private String searchVector;

    private boolean enabled = true;
}