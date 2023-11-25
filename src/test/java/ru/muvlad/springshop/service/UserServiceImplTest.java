package ru.muvlad.springshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.muvlad.springshop.dto.UserDTO;
import ru.muvlad.springshop.model.User;
import ru.muvlad.springshop.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {

        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void checkFindByName() {

        String name = "petr";
        User expectedUser = User.builder().id(1l).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        User actualUser = userService.findByName(name);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void checkSaveIncorrectPassword() {

        UserDTO userDto = UserDTO.builder()
                .password("pass")
                .matchingPassword("another")
                .build();

        assertThrows(RuntimeException.class, () -> userService.save(userDto));
    }

    @Test
    void checkSave() {

        UserDTO userDto = UserDTO.builder()
                .username("name")
                .email("email")
                .password("pass")
                .matchingPassword("pass")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("pass");

        boolean result = userService.save(userDto);

        assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }
}