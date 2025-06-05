package com.nassau.japanesefood.application.service;


import com.nassau.japanesefood.application.dto.JapaneseFoodItemRequestDTO;
import com.nassau.japanesefood.application.dto.JapaneseFoodItemResponseDTO;
import com.nassau.japanesefood.application.port.JapaneseFoodRepository;
import com.nassau.japanesefood.domain.JapaneseFoodItem;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JapaneseFoodService {

    @Autowired
     JapaneseFoodRepository repository;

    // GET - Buscar todos
    public List<JapaneseFoodItemResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // GET BY ID - Buscar por ID
    public JapaneseFoodItemResponseDTO findById(Long id) {
        JapaneseFoodItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));
        return toResponseDTO(item);
    }

    // POST - Criar
    public JapaneseFoodItemResponseDTO create(JapaneseFoodItemRequestDTO requestDTO) {
        JapaneseFoodItem item = toEntity(requestDTO);
        JapaneseFoodItem savedItem = repository.save(item);
        return toResponseDTO(savedItem);
    }

    // PUT - Atualizar
    public JapaneseFoodItemResponseDTO update(Long id, JapaneseFoodItemRequestDTO requestDTO) {
        JapaneseFoodItem existingItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + id));

        updateEntity(existingItem, requestDTO);
        JapaneseFoodItem updatedItem = repository.save(existingItem);
        return toResponseDTO(updatedItem);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new RuntimeException("Item não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Buscar por categoria
    public List<JapaneseFoodItemResponseDTO> findByCategory(String category) {
        return repository.findByCategory(category)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Conversores
    private JapaneseFoodItem toEntity(JapaneseFoodItemRequestDTO dto) {
        JapaneseFoodItem item = new JapaneseFoodItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setPrice(dto.getPrice());
        item.setAvailable(dto.getAvailable());
        item.setIngredients(dto.getIngredients());
        return item;
    }

    private void updateEntity(JapaneseFoodItem item, JapaneseFoodItemRequestDTO dto) {
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setCategory(dto.getCategory());
        item.setPrice(dto.getPrice());
        item.setAvailable(dto.getAvailable());
        item.setIngredients(dto.getIngredients());
    }

    private JapaneseFoodItemResponseDTO toResponseDTO(JapaneseFoodItem item) {
        JapaneseFoodItemResponseDTO dto = new JapaneseFoodItemResponseDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setCategory(item.getCategory());
        dto.setPrice(item.getPrice());
        dto.setAvailable(item.getAvailable());
        dto.setIngredients(item.getIngredients());
        return dto;
    }
}