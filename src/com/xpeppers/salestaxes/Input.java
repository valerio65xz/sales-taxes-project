package com.xpeppers.salestaxes;

import java.util.ArrayList;

public class Input {

    private ArrayList<Good> goods;
    private double totalTaxes;
    private double inputTotal;

    public Input(ArrayList<Good> goods) {
        this.goods = goods;

        //Calcolo le tasse totali e il prezzo totale di ogni input
        for (Good good: goods) {
            totalTaxes += good.getTax();
            inputTotal += good.getQuantity()*good.getPrice()+good.getTax();
        }

        //Correggo i double come ho fatto per i beni (Good.calculateTax())
        totalTaxes = Math.round(totalTaxes * 100);
        totalTaxes = totalTaxes / 100;
        inputTotal = Math.round(inputTotal * 100);
        inputTotal = inputTotal / 100;
    }

    ArrayList<Good> getGoods() {
        return goods;
    }

    double getTotalTaxes() {
        return totalTaxes;
    }

    double getInputTotal() {
        return inputTotal;
    }

}
