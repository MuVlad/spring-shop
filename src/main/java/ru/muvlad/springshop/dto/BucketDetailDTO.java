package ru.muvlad.springshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.muvlad.springshop.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {
    private String title;
    private Long productId;
    private Double price;
    private Double amount;
    private Double sum;

    public BucketDetailDTO(Product product) {
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = 1.0;
        this.sum = Double.valueOf(product.getPrice().toString());

    }
}
