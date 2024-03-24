package com.sg.vendingmachine.test;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.DaoFileImpl;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.Coins;
import com.sg.vendingmachine.service.ServiceLayerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceLayerImpl {

    private ServiceLayerImpl serviceLayer;

    @BeforeEach
    public void setUp(){
        Dao dao = new DaoFileImpl();
        serviceLayer = new ServiceLayerImpl(dao);
    }

    @Test
    public void testCalculateChange(){
        Item item = new Item("Coke", new BigDecimal("1.20"));
        BigDecimal money = new BigDecimal("5.00");

        Map<Coins, Integer> expectedChange = Map.of(
                Coins.POUND, 3,
                Coins.FIFTY, 1,
                Coins.TWENTY, 1,
                Coins.TEN, 1,
                Coins.FIVE, 0,
                Coins.TWO, 0,
                Coins.ONE, 0
        );
        Map<Coins, Integer> actualChange = serviceLayer.calculateChange(item, money);

        assertEquals(expectedChange, actualChange);
    }
    @Test
    public void testCalculateChangeWithExactPayment() {
        Item item = new Item("Coke", new BigDecimal("1.20"));
        BigDecimal money = new BigDecimal("1.20");

        Map<Coins, Integer> expectedChange = Map.of(
                Coins.POUND, 0,
                Coins.FIFTY, 0,
                Coins.TWENTY, 0,
                Coins.TEN, 0,
                Coins.FIVE, 0,
                Coins.TWO, 0,
                Coins.ONE, 0
        );

        Map<Coins, Integer> actualChange = serviceLayer.calculateChange(item, money);

        assertEquals(expectedChange, actualChange);
    }

}
