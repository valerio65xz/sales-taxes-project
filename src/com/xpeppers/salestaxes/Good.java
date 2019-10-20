package com.xpeppers.salestaxes;

class Good {

    private String name;
    private int quantity;
    private double price;
    private boolean isImported;
    private boolean isTaxFreeGood;
    private double tax;

    Good(String name, int quantity, double price, boolean isTaxFreeGood, boolean isImported) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isTaxFreeGood = isTaxFreeGood;
        this.isImported = isImported;
        calculateTax();
    }

    //Metodo per calcolare le tasse a seconda dei 4 casi possibili
    private void calculateTax(){
        if (isTaxFreeGood){
            if (isImported){
                tax = customRound(0.05*price)*quantity;
            }
            else
                tax = 0;
        }
        else{
            if (isImported)
                tax = quantity*customRound(0.15*price);
            else
                tax = quantity*customRound(0.10*price);
        }

        //Questi due aggiustamenti sono fatti perché operazioni matematiche fra double e int in java creano dei problemi
        tax = Math.round(tax * 100);
        tax = tax / 100;
    }

    //Approssima un valore double allo 0.05 più vicino in eccesso
    private double customRound(double n){
        if (Math.round(n*10) > n*10)
            n = Math.round(n*10) / (double)10;
        else if (Math.round(n*10) < n*10)
            n = (Math.round(n*10)+0.5)/(double)10;
        return n;
    }

    String getName() {
        return name;
    }

    int getQuantity() {
        return quantity;
    }

    double getPrice() {
        return price;
    }

    double getTax() {
        return tax;
    }

    boolean isImported() {
        return isImported;
    }

    boolean isTaxFreeGood() {
        return isTaxFreeGood;
    }

}