package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DaoFileImpl implements Dao {

    private Map<Item, Integer> items;
    private final String filename = "vendingmachine.txt";
//    @Autowired
//    public DaoFileImpl(){
//        items = new HashMap<>();
//    }
    private void readFile() {
        Scanner scanner;

        // Create Scanner for reading the file
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(filename)));

            String currentLine;
            items = new HashMap<>();
            while (scanner.hasNextLine()) {
                String[] inputs = scanner.nextLine().split("::");
                items.put(new Item(inputs[0], new BigDecimal(inputs[1])), Integer.parseInt(inputs[2]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("-_- Could not load roster data into memory.");
        }

    }

    private void writeFile() {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(filename));

            for (Item item : items.keySet()) {
                out.println(item + "::" + items.get(item));
                out.flush();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("-_- Could not load roster data into memory.");
        }
    }

    @Override
    public Map<Item, Integer> getAll() {
        readFile();
        return items;
    }

    @Override
    public Item itemAccess(Item item) {
        readFile();
        List<Item> keys = items.keySet().stream().collect(Collectors.toList());
        int i = keys.indexOf(item);
        if (i == -1) return null;
        return keys.get(i);
    }

    @Override
    public BigDecimal getPrice(Item item) {
        readFile();
        Item foundItem = itemAccess(item);
        if (foundItem == null) {
            // Handle the case where the item is not found, e.g., throw an exception or return a default value
            throw new IllegalArgumentException("Item not found: " + item);
        }
        return foundItem.getCost();
    }

    @Override
    public int getCount(Item item) {
        readFile();
        return items.get(item);
    }

    @Override
    public void removeItem(Item item) {
        readFile();
        items.replace(item, items.get(item) - 1);
        writeFile();
    }


}
