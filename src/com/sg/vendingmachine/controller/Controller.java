package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.DataValidationException;
import com.sg.vendingmachine.service.DuplicateIdException;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.ui.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class Controller {

    private final View view;
    private ServiceLayer service;
    @Autowired
    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepRunning = true;
        while (keepRunning) {
            view.displayItemsList(service.getAll());
            BigDecimal money = view.enterMoney();
            Item item = service.accessItem(new Item(view.selectItem()));
            switch (service.vendItem(item, money)) {
                case BOUGHT:
                    view.confirmTransaction(item);
                    view.giveChange(service.calculateChange(item, money));
                    break;
                case INVALIDITEM:
                    view.invalidSelection(item);
                    break;
                case INVALIDFUNDS:
                    view.notEnoughFunds(money);
                    break;
                case NOMOREITEMS:
                    view.runoutSelection(item);
                    break;
                default:
                    view.displayUnknownCommandBanner();
                    view.displayExitBanner();
                    keepRunning = false;
                    break;
            }
        }

    }

}
