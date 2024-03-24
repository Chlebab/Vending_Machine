package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
@Component
public class View {
    private UserIO io;

    @Autowired
    public View(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayVendingMachineBanner() {
        io.print("\n=== Vending Machine ===\n");
    }

    // Display All Items
    public void displayItemsList(Map<Item, Integer> itemList) {
        displayVendingMachineBanner();
        for (Map.Entry<Item, Integer> entry : itemList.entrySet()) {
            Item currentItem = entry.getKey();
            int quantity = entry.getValue();
            String itemInfo = String.format("%s - £%s (Available x%s)",
                    currentItem.getName(),
                    currentItem.getCost(),
                    quantity);
            io.print(itemInfo);
        }
        io.print("---------------------");
    }

    // Prompt for money
    public BigDecimal enterMoney() {
        return new BigDecimal(io.readString("Add money: "));
    }

    // Prompt an item
    public String selectItem() {
        return io.readString("Select item: ");
    }

    // Confirm the transaction is a success
    public void confirmTransaction(Item item) {
        io.print("You got "+ item.getName() +" for £"+ item.getCost() + "." );
    }

    // Display change
    public void giveChange(Map<Coins, Integer> change){
        io.print("Your change is:");
        change.keySet().stream().filter(key -> change.get(key) != 0).forEach(key -> {io.print(change.get(key).toString() +
                " x " + key);});
    }

    // Invalid selection
    public void invalidSelection(Item item){
        io.print("Item not in the vending machine, try again...");
    }


    public void runoutSelection(Item item){
        io.print("Vending machine has run out of " + item.getName());
    }

    // Lack of funds message
    public void notEnoughFunds(BigDecimal money){
        io.print("Insufficient funds, balance: £" + money + ".");
    }

    // Exiting
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

}
