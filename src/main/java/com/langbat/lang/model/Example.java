package com.langbat.lang.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

@Setter
@Getter
@Entity
@Table(name = "example")
public class Example extends UserAudit  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String sentence;

    @Column(columnDefinition = "TEXT")
    private String translation;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id")
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "grammar_id")
    private Grammar grammar;

    @Column(name = "search_vector", columnDefinition = "tsvector")
    @ColumnTransformer(read = "search_vector", write = "to_tsvector('simple', ?)")
    private String searchVector;

    private boolean enabled = true;

    @PrePersist
    @PreUpdate
    private void validateLinks() {
        if ((vocabulary == null && grammar == null) || (vocabulary != null && grammar != null)) {
            throw new IllegalStateException("Example must link to exactly one of vocabulary or grammar.");
        }
    }
}