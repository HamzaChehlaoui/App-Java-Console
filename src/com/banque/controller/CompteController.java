package com.banque.controller;

import com.banque.model.*;

import java.util.*;

public class CompteController {

    private Scanner sc = new Scanner(System.in);
    private Map<String, Compte> comptes = new HashMap<>();

    public void startMenu() {
        int choix;
        do {
            System.out.println("\n=== Menu Banque ===");
            System.out.println("1. Créer un compte");
            System.out.println("2. Verser");
            System.out.println("3. Retirer");
            System.out.println("4. Consulter solde");
            System.out.println("5. Consulter liste des opérations");
            System.out.println("6. Virement");
            System.out.println("7. Quitter");
            System.out.print("Choix : ");

            try {
                choix = Integer.parseInt(sc.nextLine().trim());
                switch (choix) {
                    case 1: creerCompte(); break;
                    case 2: verser(); break;
                    case 3: retirer(); break;
                    case 4: consulterSolde(); break;
                    case 5: consulterOperations(); break;
                    case 6: virement(); break;
                    case 7: System.out.println("Au revoir !"); break;
                    default: System.out.println("Choix invalide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide !");
                choix = 0;
            }
        } while (choix != 6);
    }

    private boolean isValidCode(String code) {
        return code.matches("CPT-\\d{5}");
    }

    private boolean isMontantValide(double montant) {
        return montant > 0;
    }

    private Compte getCompteOrFail(String code) {
        Compte c = comptes.get(code);
        if (c == null) System.out.println("Compte introuvable !");
        return c;
    }

    private void creerCompte() {
        try {
            System.out.print("Type de compte (1 = Courant, 2 = Epargne) : ");
            int type = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Code du compte (CPT-XXXXX) : ");
            String code = sc.nextLine().trim();
            if (!isValidCode(code)) { System.out.println("Code invalide !"); return; }
            if (comptes.containsKey(code)) { System.out.println("Ce compte existe déjà !"); return; }

            System.out.print("Solde initial : ");
            double solde = Double.parseDouble(sc.nextLine().trim());
            if (solde < 0) { System.out.println("Solde invalide !"); return; }

            if (type == 1) {
                System.out.print("Découvert autorisé : ");
                double decouvert = Double.parseDouble(sc.nextLine().trim());
                CompteCourant cc = new CompteCourant();
                cc.setCode(code); cc.setSolde(solde); cc.setDecouvert(decouvert);
                comptes.put(code, cc);
                System.out.println("Compte courant créé !");
            } else if (type == 2) {
                System.out.print("Taux d'intérêt (ex: 0.05) : ");
                double taux = Double.parseDouble(sc.nextLine().trim());
                CompteEpargne ce = new CompteEpargne();
                ce.setCode(code); ce.setSolde(solde); ce.setTauxInteret(taux);
                comptes.put(code, ce);
                System.out.println("Compte épargne créé !");
            } else {
                System.out.println("Type invalide !");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
        }
    }

    private void verser() {
        System.out.print("Code du compte : ");
        String code = sc.nextLine().trim();
        Compte c = getCompteOrFail(code);
        if (c == null) return;

        try {
            System.out.print("Montant à verser : ");
            double montant = Double.parseDouble(sc.nextLine().trim());
            if (!isMontantValide(montant)) { System.out.println("Montant invalide !"); return; }

            System.out.print("Source (ex: Salaire, Virement) : ");
            String source = sc.nextLine().trim();

            c.verser(montant, source);
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide !");
        }
    }

    private void retirer() {
        System.out.print("Code du compte : ");
        String code = sc.nextLine().trim();
        Compte c = getCompteOrFail(code);
        if (c == null) return;

        try {
            System.out.print("Montant à retirer : ");
            double montant = Double.parseDouble(sc.nextLine().trim());
            if (!isMontantValide(montant)) { System.out.println("Montant invalide !"); return; }

            System.out.print("Destination (ex: Distributeur ATM) : ");
            String destination = sc.nextLine().trim();

            c.retirer(montant, destination);
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide !");
        }
    }
    private void virement() {
        System.out.print("Code du compte source : ");
        String codeSource = sc.nextLine().trim();
        Compte compteSource = getCompteOrFail(codeSource);
        if (compteSource == null) return;

        System.out.print("Code du compte destination : ");
        String codeDest = sc.nextLine().trim();
        Compte compteDest = getCompteOrFail(codeDest);
        if (compteDest == null) return;

        if (codeSource.equals(codeDest)) {
            System.out.println("Erreur : Le compte source et destination sont identiques !");
            return;
        }

        try {
            System.out.print("Montant à transférer : ");
            double montant = Double.parseDouble(sc.nextLine().trim());
            if (!isMontantValide(montant)) {
                System.out.println("Montant invalide !");
                return;
            }

            compteSource.retirer(montant, "Virement vers " + codeDest);

            compteDest.verser(montant, "Virement depuis " + codeSource);

            System.out.println("Virement effectué avec succès !");
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide !");
        }
    }

    private void consulterSolde() {
        System.out.print("Code du compte : ");
        String code = sc.nextLine().trim();
        Compte c = getCompteOrFail(code);
        if (c == null) return;
        System.out.println("Solde : " + c.getSolde());
    }

    private void consulterOperations() {
        System.out.print("Code du compte : ");
        String code = sc.nextLine().trim();
        Compte c = getCompteOrFail(code);
        if (c == null) return;

        List<Operation> ops = c.getListOperations();
        if (ops.isEmpty()) { System.out.println("Aucune opération !"); return; }

        for (Operation op : ops) {
            if (op instanceof Versement) {
                System.out.println("Versement de " + op.getMontant() + " | Source: " + ((Versement)op).getSource() + " | Date: " + op.getDate());
            } else if (op instanceof Retrait) {
                System.out.println("Retrait de " + op.getMontant() + " | Destination: " + ((Retrait)op).getDestination() + " | Date: " + op.getDate());
            }
        }
    }

}
