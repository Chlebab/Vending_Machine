package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sg.vendingmachine.service.VendResult.*;
@Component
public class ServiceLayerImpl implements ServiceLayer {

    private Dao dao;

    @Autowired
    public ServiceLayerImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Map<Item, Integer> getAll() {
        return dao.getAll();
    }

    @Override
    public VendResult vendItem(Item item, BigDecimal money) {
        if(item == null){
            return INVALIDITEM;
        }
        else if(dao.getCount(item) == 0){
            return NOMOREITEMS;
        }else if(money.compareTo(dao.getPrice(item)) < 0){
            return INVALIDFUNDS;
        }
        dao.removeItem(item);
        return BOUGHT;
    }

    @Override
    public Item accessItem(Item item) {
        return dao.itemAccess(item);
    }

    @Override
    public Map<Coins, Integer> calculateChange(Item item, BigDecimal money) {
        int pounds;
        if(item.getCost().scale() > 0) {
            if(item.getCost().equals(money)) {
                pounds = 0;
            } else {
                pounds = money.intValue() - item.getCost().intValue() - 1;
            }
        } else {
            pounds = money.intValue() - item.getCost().intValue();
        }
        Map<Coins, Integer> coins = new HashMap<>(Map.of(Coins.POUND, pounds, Coins.FIFTY, 0, Coins.TWENTY, 0,
                Coins.TEN, 0, Coins.FIVE, 0, Coins.TWO, 0, Coins.ONE, 0));
        money = money.subtract(dao.getPrice(item));
        BigDecimal rem = money.remainder(BigDecimal.valueOf(1));

        while (rem.compareTo(BigDecimal.valueOf(0)) > 0) {
            if (rem.compareTo(BigDecimal.valueOf(0.5)) >= 0) {
                coins.replace(Coins.FIFTY, coins.get(Coins.FIFTY) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.5));
                continue;
            }
            if (rem.compareTo(BigDecimal.valueOf(0.2)) >= 0) {
                coins.replace(Coins.TWENTY, coins.get(Coins.TWENTY) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.2));
                continue;
            }
            if (rem.compareTo(BigDecimal.valueOf(0.1)) >= 0) {
                coins.replace(Coins.TEN, coins.get(Coins.TEN) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.1));
                continue;
            }
            if (rem.compareTo(BigDecimal.valueOf(0.05)) >= 0) {
                coins.replace(Coins.FIVE, coins.get(Coins.FIVE) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.05));
                continue;
            }
            if (rem.compareTo(BigDecimal.valueOf(0.02)) >= 0) {
                coins.replace(Coins.TWO, coins.get(Coins.TWO) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.02));
                continue;
            }
            if (rem.compareTo(BigDecimal.valueOf(0.01)) >= 0) {
                coins.replace(Coins.ONE, coins.get(Coins.ONE) + 1);
                rem = rem.subtract(BigDecimal.valueOf(0.01));
            }
        }
        return coins;
    }
}

