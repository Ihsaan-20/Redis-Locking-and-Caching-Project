package com.example.redis_project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@SpringBootApplication
public class RedisProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisProjectApplication.class, args);
		
//		// Ek chota sa delay taaki server fully start ho jaye
//        try { Thread.sleep(5000); } catch (Exception e) {}
//
//        ExecutorService executor = Executors.newFixedThreadPool(100); 
//        RestTemplate restTemplate = new RestTemplate();
//        
//        // URL change: Product ID '1' ke liye (MySQL mein product ID 1 hona chahiye)
//        String url = "http://localhost:8080/sale/buy/1/";
//
//        for (int i = 1; i <= 20; i++) {
//            final int userId = i;
//            executor.execute(() -> {
//                try {
//                    // API hit: /sale/buy/1/User_1, /sale/buy/1/User_2 ...
//                    String response = restTemplate.postForObject(url + "User_" + userId, null, String.class);
//                    System.out.println("Request " + userId + ": " + response);
//                } catch (Exception e) {
//                    System.err.println("Error for User_" + userId + ": " + e.getMessage());
//                }
//            });
//        }
//        executor.shutdown();

	
	}

}
