package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService pis = new ProductInfoService();
    private ReviewService rs = new ReviewService();
    ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(pis, rs);

    @Test
    void retrieveProductDetails() {
        String productId = "ABC123";
        Product product = pscf.retrieveProductDetails(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2() {
        String productId = "ABC123";
        Product product = pscf.retrieveProductDetails_approach2(productId).join();

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }
}