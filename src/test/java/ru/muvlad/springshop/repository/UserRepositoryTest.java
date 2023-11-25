package ru.muvlad.springshop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.muvlad.springshop.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void checkFindByName() {
        User user = new User();
        user.setName("TestName");
        user.setPassword("pass");
        user.setEmail("test@mail.ru");

        entityManager.persist(user);

        User actualUser = userRepository.findFirstByName("TestName");

        assertNotNull(actualUser);
        assertEquals(user.getName(), actualUser.getName());
        assertEquals(user.getPassword(), actualUser.getPassword());
        assertEquals(user.getEmail(), actualUser.getEmail());

    }
}