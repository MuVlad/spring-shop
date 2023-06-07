package ru.muvlad.springshop.service;

import ru.muvlad.springshop.dto.BucketDTO;
import ru.muvlad.springshop.model.Bucket;
import ru.muvlad.springshop.model.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser(String name);
}
