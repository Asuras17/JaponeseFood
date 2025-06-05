package com.nassau.japanesefood.infrastructure.controller;




import com.nassau.japanesefood.application.dto.JapaneseFoodItemRequestDTO;
import com.nassau.japanesefood.application.dto.JapaneseFoodItemResponseDTO;
import com.nassau.japanesefood.application.service.JapaneseFoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/japanese-food")
@RequiredArgsConstructor
public class JapaneseFoodController {

    @Autowired
   JapaneseFoodService service;

    // GET - Buscar todos
    @GetMapping
    public ResponseEntity<List<JapaneseFoodItemResponseDTO>> getAllItems() {
        List<JapaneseFoodItemResponseDTO> items = service.findAll();
        return ResponseEntity.ok(items);
    }

    // GET BY ID - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<JapaneseFoodItemResponseDTO> getItemById(@PathVariable Long id) {
        JapaneseFoodItemResponseDTO item = service.findById(id);
        return ResponseEntity.ok(item);
    }

    // POST - Criar
    @PostMapping
    public ResponseEntity<JapaneseFoodItemResponseDTO> createItem(
            @Valid @RequestBody JapaneseFoodItemRequestDTO requestDTO) {
        JapaneseFoodItemResponseDTO createdItem = service.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // PUT - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<JapaneseFoodItemResponseDTO> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody JapaneseFoodItemRequestDTO requestDTO) {
        JapaneseFoodItemResponseDTO updatedItem = service.update(id, requestDTO);
        return ResponseEntity.ok(updatedItem);
    }

    // DELETE - Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET por categoria
    @GetMapping("/category/{category}")
    public ResponseEntity<List<JapaneseFoodItemResponseDTO>> getItemsByCategory(
            @PathVariable String category) {
        List<JapaneseFoodItemResponseDTO> items = service.findByCategory(category);
        return ResponseEntity.ok(items);
    }
}