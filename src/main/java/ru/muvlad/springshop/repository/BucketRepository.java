package ru.muvlad.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muvlad.springshop.model.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
