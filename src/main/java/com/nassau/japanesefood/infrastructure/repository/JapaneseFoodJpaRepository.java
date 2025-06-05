package com.nassau.japanesefood.infrastructure.repository;


import com.nassau.japanesefood.domain.JapaneseFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JapaneseFoodJpaRepository extends JpaRepository<JapaneseFoodItem, Long> {

    List<JapaneseFoodItem> findByCategoryIgnoreCase(String category);
}