package ru.muvlad.springshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.muvlad.springshop.dto.BucketDTO;
import ru.muvlad.springshop.dto.BucketDetailDTO;
import ru.muvlad.springshop.model.Bucket;
import ru.muvlad.springshop.model.Product;
import ru.muvlad.springshop.model.User;
import ru.muvlad.springshop.repository.BucketRepository;
import ru.muvlad.springshop.repository.ProductRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                // getOne вытаскивает ссылку на объект, а не сам объект
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }

        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for (Product product : products) {
            BucketDetailDTO bucketDetailDTO = mapByProductId.get(product.getId());
            if (bucketDetailDTO == null) {
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            } else {
                bucketDetailDTO.setAmount(bucketDetailDTO.getAmount() + 1.0);
                bucketDetailDTO.setSum(bucketDetailDTO.getSum() + product.getPrice());
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }
}
