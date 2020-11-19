package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void no_Of_cores() {
        System.out.println("no of cores : " + Runtime.getRuntime().availableProcessors());
    }

    @Test
    void parallelism() {
        System.out.println("parallelism : " + ForkJoinPool.getCommonPoolParallelism());
    }

    @Test
    void checkout_6_items() {
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkout_25_items() {
        // [Test worker] - Total Time Taken : 4034
        // Because my Mac has 4-cores.
        Cart cart = DataSet.createCart(25);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void modify_parallelism() {
        // [Test worker] - Total Time Taken : 602
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "25");
        Cart cart = DataSet.createCart(25);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}