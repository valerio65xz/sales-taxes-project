package com.xpeppers.salestaxes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Loader {

    //Questi array mi consentono di individuare alcuni campi di prodotti
    private String path;
    private String[] foods = {"chocolate", "pasta", "fruit", "cheese", "water"};
    private String[] medicalProducts = {"pill", "medical patch", "ointment", "syringe"};
    private String[] books = {"book", "ebook"};

    Loader(String path){
        this.path = path;
    }

    //Metodo per processare l'input e creare l'array totale degli input.
    ArrayList<Input> loadInputs(){
        ArrayList<Input> inputs = new ArrayList<>();

        //Per ogni input, tokenizzo ogni riga creando un bene con i relativi attributi
        Scanner scanner;

        try {
            scanner = new Scanner(new File(path));

            //Salto la prima riga di "Input 1:"
            String line = scanner.nextLine();

            //Scorro finché ho righe da leggere
            while (scanner.hasNextLine()){

                //Sto attento a non processare la new line e "Input X:"
                line = scanner.nextLine();
                if ((!line.contains("Input")) && (!"".equals(line))){

                    //Per ogni input, creo una lista di beni da processare
                    ArrayList<Good> goods = new ArrayList<>();

                    //Devo ricontrollare che sono dentro un input, e che mi evito la stringa vuota
                    while ((!line.contains("Input")) && (!"".equals(line))){
                        Good good = createGood(line);
                        goods.add(good);

                        //Non potevo metterlo dentro il while di sopra, altrimenti l'ultima riga non sarebbe stata processata
                        if (scanner.hasNextLine())
                            line = scanner.nextLine();
                        else break;
                    }

                    //Creo il mio input dalla lista di beni, e lo aggiungo alla lista di input
                    Input input = new Input(goods);
                    inputs.add(input);
                }

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inputs;

    }

    //Mi crea un bene per aggiungerlo all'ArrayList dell'input
    private Good createGood(String line){

        //Inizialmente dichiaro il tokenizer e gli attributi del mio bene
        StringTokenizer stringTokenizer;
        int quantity = 0;
        String name = null;
        double price = 0;
        boolean isTaxFreeGood = false;
        boolean isImported = false;

        //A questo punto tokenizzo la riga, e scorro i vari token
        stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()){

            //Qui imposto la quantità del bene, il nome (fino a che non arrivo ad at) e il prezzo
            quantity = Integer.parseInt(stringTokenizer.nextToken());
            name = stringTokenizer.nextToken();
            if (name.equals("imported")) isImported=true;
            String subName = "";
            while (!subName.equals("at")){
                subName = stringTokenizer.nextToken();
                if (subName.equals("imported")) isImported=true;
                if (!subName.equals("at")) name=name.concat(" "+subName);
            }
            price = Double.parseDouble(stringTokenizer.nextToken());

            //In questo modo stabilisco, date 3 liste di elementi per cibi, medicine e libri, se è un bene esentasse
            for (String food : foods){
                if (name.contains(food)) {
                    isTaxFreeGood = true;
                    break;
                }
            }
            for (String medicalProduct : medicalProducts){
                if (name.contains(medicalProduct)) {
                    isTaxFreeGood = true;
                    break;
                }
            }
            for (String book : books){
                if (name.contains(book)) {
                    isTaxFreeGood = true;
                    break;
                }
            }

        }

        //Ritorno il bene creato
        return new Good(name, quantity, price, isTaxFreeGood, isImported);

    }

}
