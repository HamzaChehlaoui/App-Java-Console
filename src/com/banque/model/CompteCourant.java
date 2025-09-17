package com.banque.model;

public class CompteCourant extends Compte {

    private double decouvert ;

    @Override
    public void retirer(double montant, String destination) {
        if(getSolde() - montant >= -decouvert) {
            setSolde(getSolde() - montant);
            Retrait r = new Retrait(montant, destination);
            getListOperations().add(r);
            System.out.println("Retrait effectué : " + montant);
        } else {
            System.out.println("Fonds insuffisants !");
        }
    }


    @Override
    public  void afficherDetails(){
        System.out.println("Code de compte : " + getCode());
        System.out.println("Le solde du compte courant est : " + getSolde());
        System.out.println("Solde negatif minimum autorisé : - " + decouvert);
    }

    @Override
    public double calculerInteret(){
        return 0;
    }

    public double getDecouvert(){
        return decouvert;
    }

    public void setDecouvert(double newDecouvert){
        this.decouvert = newDecouvert;
    }
}
