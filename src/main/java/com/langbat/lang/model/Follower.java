package com.langbat.lang.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "followers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "followed_user_id"}))
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Follower extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_user_id", nullable = false)
    private User followedUser;
}
