package com.banque.model;

public class CompteCourant extends Compte {

    private double decouvert ;

    @Override
    public void retirer(double montant){
        if(getSolde() - montant >= -decouvert){
            setSolde(getSolde()-montant);
        }else {
            System.out.println("Fonds insuffisants pour effectuer cette transaction");
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
