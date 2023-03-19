package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spring_session")
public class Session {

    @Id
    private String primaryId;

    private String sessionId;

    private LocalDateTime creationTime;

    private LocalDateTime lastAccessTime;

    private Integer maxInactiveInterval;

    private LocalDateTime expiryTime;

    private String principalName;

    @OneToMany(mappedBy = "sessionPrimaryId")
    private List<SessionAttribute> sessionAttributes;
}
