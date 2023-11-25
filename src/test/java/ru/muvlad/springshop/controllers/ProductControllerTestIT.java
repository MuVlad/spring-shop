package ru.muvlad.springshop.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.muvlad.springshop.dto.ProductDTO;
import ru.muvlad.springshop.service.ProductService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTestIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    private final ProductDTO expectedProduct = new ProductDTO(100L, "Test", new BigDecimal("100.00"));

    @BeforeEach
    void setUp() {
        given(productService.getById(expectedProduct.getId())).willReturn(expectedProduct);
    }

    @Test
    void checkProductById() {
        ResponseEntity<ProductDTO> entity = restTemplate
                .getForEntity("/products/" + expectedProduct.getId(), ProductDTO.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        ProductDTO actualProduct = entity.getBody();
        assertEquals(expectedProduct, actualProduct);
    }
}