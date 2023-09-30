package ru.muvlad.springshop.service;

import org.springframework.stereotype.Service;
import ru.muvlad.springshop.model.Order;
import ru.muvlad.springshop.repository.OrderRepository;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
