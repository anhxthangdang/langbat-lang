package com.langbat.lang.model;



import com.langbat.lang.model.bson.answer.Answer;
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
@Table(name = "userAnswer")
public class UserAnswer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<Answer> answers= new ArrayList<>();

    private Integer score =0;
    // DONE // DOING // IDLE
    private String toDo;
    private Integer userTime;
}

