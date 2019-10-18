package com.xpeppers.salestaxes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesTaxes {

    private SalesTaxes(){

        //Creo l'ArrayList dei beni che processerò dall'input
        ArrayList<Input> inputs;

        //Carico il file di input, lo tokenizzo, e ritorno l'input tokenizzato
        Loader loader = new Loader(chooseInputFile());
        inputs = loader.loadInputs();
        System.out.println();

        //Genero gli output (uso un Locale.US per stampare il punto invece che la , per i decimali)
        for(int i=0; i<inputs.size(); i++){
            Input input = inputs.get(i);
            System.out.println("Output "+(i+1)+":");
            for (Good good : input.getGoods()){
                double finalPrice = (good.getQuantity()*good.getPrice()+good.getTax());

                //Faccio sempre quel famoso aggiustamento (vedere Good.calculateTax())
                finalPrice = Math.round(finalPrice * 100);
                finalPrice = finalPrice / 100;
                System.out.print(String.format(java.util.Locale.US, "%d %s: %.2f\n",good.getQuantity(), good.getName(), finalPrice));
            }
            System.out.print(String.format(java.util.Locale.US, "Sales Taxes: %.2f\n", input.getTotalTaxes()));
            System.out.print(String.format(java.util.Locale.US, "Total: %.2f\n", input.getInputTotal()));
            System.out.println();
        }
    }

    //Menu per selezionare i file di test
    private String chooseInputFile(){
        List<String> fileList;
        String[] files = null;
        System.out.println("Scegliere il file da caricare: ");
        System.out.println();
        try (Stream<Path> walk = Files.walk(Paths.get("input"))) {

            fileList = walk.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
            files = new String[fileList.size()];
            for (int i=0; i<fileList.size(); i++){
                String fileName = fileList.get(i);
                files[i] = fileName;
                System.out.println(i+") "+fileName.substring(fileName.indexOf('\\')+1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner myInput = new Scanner( System.in );
        int choose = myInput.nextInt();
        assert files != null : "Error when creating array file paths";
        return files[choose];
    }

    public static void main (String[] args){
        new SalesTaxes();
    }

}
