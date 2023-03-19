package com.example.demo;

import com.example.demo.entities.SessionAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionAttributesRepository extends JpaRepository<SessionAttribute, String> {

    List<SessionAttribute> findByAttributeName(String attributeName);
}
