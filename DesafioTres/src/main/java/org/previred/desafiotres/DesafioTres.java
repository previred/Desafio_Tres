package org.previred.desafiotres;

import org.previred.desafiotres.service.UFSerivce;
import org.previred.desafiotres.utils.Utils;

import java.io.IOException;

public class DesafioTres {


    public static void main(String[] args) {
        UFSerivce ufSerivce = new UFSerivce();
        System.out.println("Inicio ....");
        try {
            Utils.saveToFile(ufSerivce.getUfRange(), "./valores.json");
            System.out.println("Completado ....");
        } catch (IOException e) {
            System.err.println("Error almacenando Archivo ");
            e.printStackTrace();
        }
    }
}
