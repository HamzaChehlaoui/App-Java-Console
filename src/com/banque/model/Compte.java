package com.banque.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte {

    private String code ;
    private double solde ;
    private List<Operation> listOperations = new ArrayList<>();

    public abstract void retirer(double montant, String destination);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

    public void verser(double montant, String source) {
        if(montant <= 0) {
            System.out.println("Montant invalide !");
            return;
        }
        setSolde(getSolde() + montant);
        Versement v = new Versement(montant, source);
        getListOperations().add(v);
        System.out.println("Versement effectué : " + montant);
    }

    public void setCode(String newCode){
        this.code = newCode;

    }
    public String getCode(){
        return code;
    }
    public void setSolde(double newSolde){
        this.solde = newSolde;
    }
    public double getSolde(){
        return solde;
    }
    public List<Operation> getListOperations(){return listOperations;}
}
