package ru.muvlad.springshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.muvlad.springshop.dto.UserDTO;
import ru.muvlad.springshop.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    void save(User user);

    List<UserDTO> getAll();

    User findByName(String name);

    void updateProfile(UserDTO userDTO);
}
