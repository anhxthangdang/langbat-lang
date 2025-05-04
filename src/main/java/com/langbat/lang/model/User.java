package com.langbat.lang.model;



import com.langbat.lang.model.bson.user.Academic;
import com.langbat.lang.model.bson.user.Address;
import com.langbat.lang.model.bson.user.WorkHistory;
import com.langbat.lang.model.xnum.AuthProvider;
import com.vladmihalcea.hibernate.type.json.JsonType;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String cover;
    private String avatarLargeUrl;
    private String coverLargeUrl;

    @Column(name = "clone", nullable = false)
    private boolean clone = false;

    @Column()
    private String url;

    private String password;
    private Instant lastUpdatePass = Instant.now();

    private String tel;
    private Instant confirmTelAt;

    @Column(nullable = false, unique = true)
    private String email;
    private boolean emailVerified = false;
    private Instant nextIssue;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private String otp;
    private Instant otpExp= Instant.now();
    int verifyCount=0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Column(nullable = true)
    private boolean emailVerifiedRequire = false;

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Claim> claims;  // Added relation to claims
    public boolean isBlocked(){
        return !(accountNonExpired & accountNonLocked & credentialsNonExpired & enabled);
    }

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<Academic> academics = new ArrayList<>();

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<WorkHistory> works = new ArrayList<>();

    @Type(JsonType.class) // ✅ Correct for Hibernate 6+
    @Column(columnDefinition = "jsonb") // json/jsonb depending on DB (Postgres = jsonb)
    private List<Address> addresses= new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follower> following;


    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follower> followers;

    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Friendship> friendshipsAsUser1 = new HashSet<>();

    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Friendship> friendshipsAsUser2 = new HashSet<>();


}
