package ru.muvlad.springshop.service;

import ru.muvlad.springshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    void addToUserBucket(Long productId, String username);

    ProductDTO getById(Long id);
}
