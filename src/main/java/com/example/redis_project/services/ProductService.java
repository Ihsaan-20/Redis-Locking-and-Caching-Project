package com.example.redis_project.services;

import java.time.Duration;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis_project.models.Product;
import com.example.redis_project.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "productCache::";
    private static final String ALL_PRODUCTS_CACHE_KEY = "products::all";
    
    @SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
        // 1. Pehle Redis mein poori list check karein

		List<Product> cachedList = (List<Product>) redisTemplate.opsForValue().get(ALL_PRODUCTS_CACHE_KEY);

        if (cachedList != null) {
            System.out.println(">>> [REDIS HIT] Fetching ALL products from Cache");
            return cachedList;
        }

        // 2. Agar Redis khali hai, toh DB se saara data layein
        System.out.println(">>> [DB MISS] Fetching ALL products from MySQL");
        List<Product> productList = productRepository.findAll();

        // 3. Redis mein save karein (e.g., 10 minute ke liye)
        if (!productList.isEmpty()) {
            redisTemplate.opsForValue().set(ALL_PRODUCTS_CACHE_KEY, productList, Duration.ofMinutes(5));
        }

        return productList;
    }
    
    public Product getProductDetails(Long productId) {
        String key = CACHE_KEY_PREFIX + productId;

        // 1. Redis se data nikalne ki koshish karein
        Product cachedProduct = (Product) redisTemplate.opsForValue().get(key);

        if (cachedProduct != null) {
            // YEAH! Ab aapko ye log dikhega
            System.out.println(">>> [REDIS HIT] Fetching from Cache for ID: " + productId);
            return cachedProduct;
        }

        // 2. Agar Redis khali hai
        System.out.println(">>> [DB MISS] Fetching from MySQL for ID: " + productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 3. Redis mein save karein
        redisTemplate.opsForValue().set(key, product, Duration.ofMinutes(5));

        return product;
    }
   
    
    @CacheEvict(value = "productCache", key = "#product.id")
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
    
    public Product saveProduct(Product product) {
        Product saved = productRepository.save(product);
        // Naya product aate hi 'all' wali list ko delete kar do taaki agli baar fresh list bane
        redisTemplate.delete(ALL_PRODUCTS_CACHE_KEY); 
        return saved;
    }
}