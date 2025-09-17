package com.banque.model;

public class CompteCourant extends Compte {

    private double decouvert ;

    @Override
    public void retirer(double montant){

    }

    @Override
    public  void afficherDetails(){
        System.out.println("Le solde du compte courant est : " + getSolde());
        System.out.println("Solde negatif minimum autorisé : " + decouvert);

    }

    @Override
    public double calculerInteret(){
        return 0;
    }

    public double getDecouvert(){
        return decouvert;
    }
    public void setDecouvert(double Newdecouvert){
        this.decouvert = Newdecouvert;
    }
}
