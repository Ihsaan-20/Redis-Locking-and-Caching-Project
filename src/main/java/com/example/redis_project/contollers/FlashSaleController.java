package com.example.redis_project.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis_project.services.FlashSaleService;

@RestController
@RequestMapping("/sale")
public class FlashSaleController {

    @Autowired
    private FlashSaleService flashSaleService;

    // Ab humne productId ko bhi path mein add kar diya hai
    // URL format: http://localhost:8080/sale/buy/1/user_101
    @PostMapping("/buy/{productId}/{userId}")
    public String buy(@PathVariable Long productId, @PathVariable String userId) {
        return flashSaleService.buyIPhone(userId, productId);
    }
}
