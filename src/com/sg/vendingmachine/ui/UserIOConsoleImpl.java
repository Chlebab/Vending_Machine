package com.sg.vendingmachine.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserIOConsoleImpl implements UserIO{

    final private Scanner mcScan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return mcScan.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt));
            } catch (NumberFormatException e) {
                print("Input Error...");
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;
        do {
            input = readInt(prompt);
        } while (input < min || input > max);
        return input;
    }

    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readString(prompt));
            } catch (NumberFormatException e) {
                print("Input Error...");
            }
        }

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input;
        do {
            input = readDouble(prompt);
        } while (input < min || input > max);
        return input;
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(readString(prompt));
            } catch (NumberFormatException e) {
                print("Input Error...");
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input;
        do {
            input = readFloat(prompt);
        } while (input < min || input > max);
        return input;
    }

    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readString(prompt));
            } catch (NumberFormatException e) {
                print("Input Error...");
            }
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input;
        do {
            input = readLong(prompt);
        } while (input < min || input > max);
        return input;

    }
}
