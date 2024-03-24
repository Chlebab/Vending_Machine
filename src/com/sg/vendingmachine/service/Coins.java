package com.sg.vendingmachine.service;

public enum Coins {
    POUND, FIFTY, TWENTY, TEN, FIVE, TWO, ONE;
    @Override
    public String toString() {
        switch (this) {
            case POUND:
                return "pound";
            case FIFTY:
                return "50p";
            case TWENTY:
                return "20p";
            case TEN:
                return "10p";
            case FIVE:
                return "5p";
            case TWO:
                return "2p";
            case ONE:
                return "1p";
            default:
                return null;
        }
    }
}
