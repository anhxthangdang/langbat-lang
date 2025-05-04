package com.langbat.lang.model;


import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vocabulary")
public class Vocabulary extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;
    private String pronunciation;
    private String meaning;
    private String level;

    @ManyToOne
    @JoinColumn(name = "exam_category_id")
    private ExamCategory examCategory;

    @ManyToOne
    @JoinColumn(name = "learning_resource_category_id")
    private LearningResourceCategory resourceCategory;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<String> synonyms = new ArrayList<>();

    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Example> examples = new ArrayList<>();


    private Double start;
    private Double stop;

    private boolean enabled = true;
}