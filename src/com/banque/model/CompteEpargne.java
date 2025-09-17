package com.banque.model;

public class CompteEpargne extends Compte{

    private double tauxInteret ;

    @Override
    public void retirer(double montant){
        if(getSolde()>= montant){
            setSolde(getSolde()-montant);
            System.out.println("Operation de retrait est succes");
        }else{
            System.out.println("Fonds insuffisants pour effectuer cette transaction");
        }
    }

    @Override
    public  void afficherDetails(){
        System.out.println("Code de compte : " + getCode());
        System.out.println("Le solde du compte epargne est : " + getSolde());
        System.out.println("Taux d’intérêt est : " + tauxInteret);
    }

    @Override
    public double calculerInteret(){
        double interet = getSolde() * tauxInteret;
        return interet;
    }

    public double getTauxInteret(){
        return tauxInteret;
    }

    public void setTauxInteret(double newTauxInteret){
        this.tauxInteret = newTauxInteret;
    }
}
