package com.banque.model;

public class CompteEpargne extends Compte{


    private final double TAUX_INTERET = 0.05;
    @Override
    public void retirer(double montant, String destination) {
        if(getSolde() >= montant) {
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
        System.out.println("Le solde du compte epargne est : " + getSolde());
        System.out.println("Taux d’intérêt est : " + TAUX_INTERET);
    }
    @Override
    public double calculerInteret() {
        return getSolde() * TAUX_INTERET;
    }

    public void appliquerInteret() {
        double interet = calculerInteret();
        setSolde(getSolde() + interet);
    }

    public double getTauxInteret(){
        return TAUX_INTERET;
    }

}
