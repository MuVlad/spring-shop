package ru.muvlad.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muvlad.springshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
