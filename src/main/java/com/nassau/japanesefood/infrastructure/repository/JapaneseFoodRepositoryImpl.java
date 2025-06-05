package com.nassau.japanesefood.infrastructure.repository;


import com.nassau.japanesefood.application.port.JapaneseFoodRepository;
import com.nassau.japanesefood.domain.JapaneseFoodItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public abstract class JapaneseFoodRepositoryImpl implements JapaneseFoodRepository {

    @Autowired
    private final JapaneseFoodJpaRepository jpaRepository;

    @Override
    public List<JapaneseFoodItem> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<JapaneseFoodItem> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public JapaneseFoodItem save(JapaneseFoodItem item) {
        return jpaRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<JapaneseFoodItem> findByCategory(String category) {
        return jpaRepository.findByCategoryIgnoreCase(category);
    }
}