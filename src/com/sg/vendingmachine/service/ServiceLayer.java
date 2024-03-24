package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ServiceLayer {

    // This returns a list of all items in file.
    // Access Item
    public Item accessItem(Item item);
    public Map<Item, Integer> getAll();

    // Removes an item if present and returns VendResult.
    public VendResult vendItem(Item item, BigDecimal money);


    //Calculates change
    public Map<Coins, Integer> calculateChange(Item item, BigDecimal money);
}
