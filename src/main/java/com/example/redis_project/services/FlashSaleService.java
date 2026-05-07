package com.example.redis_project.services;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis_project.models.Product;
import com.example.redis_project.repo.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

// distributed.locking
@Slf4j
@Service
public class FlashSaleService {

    @Autowired
    private ProductRepository productRepository; // JPA Repository
    
    @Autowired
    private RedissonClient redissonClient;

    public String buyIPhone(String userId, Long productId) {
        RLock lock = redissonClient.getLock("lock:product:" + productId);
        try {
            if (lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                // MySQL se product uthayein
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product Not Found"));

                if (product.getStockCount() > 0) {
                    product.setStockCount(product.getStockCount() - 1);
                    productRepository.save(product); // DB Update
                    return "Success! iPhone booked for " + userId;
                } else {
                    return "Out of Stock!";
                }
            } else {
                return "Server Busy!";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        } finally {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }
}