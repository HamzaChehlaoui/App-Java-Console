# 🚀 Application de gestion de comptes bancaires (Java 8)

##  Description
Application console permettant de gérer des **comptes bancaires** (courant & épargne) et leurs opérations :
- Création de compte
- Versement
- Retrait
- Virement
- Consultation du solde & historique des opérations

---


## 🔑 Règles principales
- Code compte au format : **CPT-XXXXX** (ex: CPT-12345)
- Compte courant : découvert autorisé
- Compte épargne : intérêts calculés avec `tauxInteret`
- Opérations enregistrées avec `UUID` + date (`Java Time API`)
- Montants > 0 obligatoires

---

## ▶️ Utilisation
Compiler et exécuter :
```bash
javac -d out src/**/*.java
java -cp out com.maBanque.Main
