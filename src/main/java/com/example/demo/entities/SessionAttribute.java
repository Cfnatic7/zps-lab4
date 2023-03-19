package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spring_session_attributes")
public class SessionAttribute {

    @Id
    @ManyToOne
    @JoinColumn(name = "session_primary_id")
    private String sessionPrimaryId;

    private String attributeName;

    private byte[] attributeBytes;
}
