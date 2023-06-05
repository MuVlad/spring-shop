package ru.muvlad.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muvlad.springshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
