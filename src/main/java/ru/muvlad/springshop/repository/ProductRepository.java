package ru.muvlad.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muvlad.springshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
