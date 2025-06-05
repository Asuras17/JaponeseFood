package com.nassau.japanesefood.application.port;



import com.nassau.japanesefood.domain.JapaneseFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JapaneseFoodRepository extends JpaRepository<JapaneseFoodItem, String> {

    List<JapaneseFoodItem> findAll();

    Optional<JapaneseFoodItem> findById(Long id);

    JapaneseFoodItem save(JapaneseFoodItem item);

    void deleteById(Long id);

    List<JapaneseFoodItem> findByCategory(String category);
}