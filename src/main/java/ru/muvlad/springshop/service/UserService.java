package ru.muvlad.springshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.muvlad.springshop.dto.UserDTO;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}
