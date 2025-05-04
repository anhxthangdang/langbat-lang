package com.langbat.lang.model;


import com.langbat.lang.model.bson.question.Media;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "exam")
public class Exam extends UserAudit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int nQuestions;
    private int minPoint;
    private int maxPoint;
    private String description;
    private String difficulty;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private boolean release;
    private int members;

    @ManyToOne
    @JoinColumn(name = "examCategory_id", nullable = false)
    private ExamCategory examCategory;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    private int examTime;

    @Column(name = "search_vector", columnDefinition = "tsvector")
    @ColumnTransformer(read = "search_vector", write = "to_tsvector('simple', ?)")
    private String searchVector;

    private int orderIndex;

    private boolean enable;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private Media media;
}
