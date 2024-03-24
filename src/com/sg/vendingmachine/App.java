package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.Controller;
import com.sg.vendingmachine.dao.Dao;
import com.sg.vendingmachine.dao.DaoFileImpl;
import com.sg.vendingmachine.service.ServiceLayer;
import com.sg.vendingmachine.service.ServiceLayerImpl;
import com.sg.vendingmachine.ui.View;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        /*
        UserIO myIo = new UserIOConsoleImpl();
        View myView = new View(myIo);
        Dao myDao = new DaoFileImpl();
        ServiceLayer myService = new ServiceLayerImpl(myDao);

        Controller controller =
                new Controller(myService, myView);
         */
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.vendingmachine");
        appContext.refresh();

        Controller controller = appContext.getBean("controller", Controller.class);
        controller.run();
    }
}
