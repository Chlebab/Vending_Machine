package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface Dao {

    // Returns all items
    public Map<Item, Integer> getAll();

    // Checks if item is present and returns it, null if no item
    public Item itemAccess(Item item);

    // Gets price of item
    public BigDecimal getPrice(Item item);

    // Gets count of item
    public int getCount(Item item);

    // Remove item
    public void removeItem(Item item);
}
