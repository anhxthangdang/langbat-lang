package com.langbat.lang.model;


import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "kafka_outbox")
public class KafkaOutbox extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private String payload;

    @Column(name = "processed", nullable = false)
    private boolean processed = false;

    @Column(name = "processed_at")
    private Instant processedAt;
}
