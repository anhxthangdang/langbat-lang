package com.langbat.lang.model;


import com.langbat.lang.model.xnum.FriendshipStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "friendships")
@Setter
@Getter
@NoArgsConstructor
public class Friendship extends UserAudit  {
    public Friendship(User user1, User user2, FriendshipStatus status, Long initiatorId) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
        this.initiatorId = initiatorId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FriendshipStatus status;

    @Column(name = "initiator_id")
    private Long initiatorId; // ID của người khởi tạo yêu cầu (chỉ dùng cho PENDING)

    @PrePersist
    public void normalize() {
        if (user1.getId() > user2.getId()) {
            User temp = user1;
            user1 = user2;
            user2 = temp;
            // Điều chỉnh initiatorId nếu hoán đổi
            if (initiatorId != null) {
                initiatorId = initiatorId.equals(user1.getId()) ? user2.getId() : user1.getId();
            }
        }
    }
}
