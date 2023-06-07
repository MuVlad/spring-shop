package ru.muvlad.springshop.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.muvlad.springshop.dto.ProductDTO;
import ru.muvlad.springshop.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProduct(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOS);

    List<ProductDTO> fromProductList(List<Product> products);
}
