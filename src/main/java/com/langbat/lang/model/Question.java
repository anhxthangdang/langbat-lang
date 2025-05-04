package com.langbat.lang.model;


import com.langbat.lang.model.bson.QuestionAnswer;
import com.langbat.lang.model.bson.question.Media;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;


@Setter
@Getter
@Entity
@Table(name = "question")
public class Question  extends UserAudit {

    public Question(){
    }
    public Question(Exam exam,Long partId,long group,long numberQuestion,long indexQuestionGroup){
        this.exam = exam;
        this.part= new Part(partId);
        this.groupPart = group;
        this.numberQuestion = numberQuestion;
        this.iQuestion =indexQuestionGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", nullable = false) // nullable = false để đảm bảo dữ liệu liên kết
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;
    private long groupPart;
    private long iQuestion;
    private long numberQuestion;

    @Column(name = "contentGroup", columnDefinition = "TEXT")
    private String contentGroup;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    private String correct;
    private boolean type;
    private long score;

    private long estimate = 0;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<Media> mediaGroup = new ArrayList<>();


//    @Column(columnDefinition = "json")
//    @Type(JsonType.class)
//    @Convert(converter = JsonConverter.class)
//    private Media media;

    @Type(JsonType.class)
    private Media media;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Claim> claims;

    @Column(name = "explanation", columnDefinition = "TEXT")
    private String explanation;

    @Type(JsonType.class)
    private List<QuestionAnswer> answers = new ArrayList<>();

    @Column(name = "start_time")
    private long startTime;

    @Column(name = "end_time")
    private long endTime;
}
