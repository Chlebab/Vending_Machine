package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String name;
    private BigDecimal cost;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return name + "::" + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return Objects.equals(name.toLowerCase(), item.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
