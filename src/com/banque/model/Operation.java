package com.banque.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Operation {

    private double montant;
    private UUID numero;
    private LocalDate date;

    public Operation(double montant){
        this.montant = montant;
        this.numero = UUID.randomUUID();
        this.date = LocalDate.now();
    }
    public void setMontant(double NewMontant){
        this.montant = NewMontant;
    }
    public double getMontant(){
        return montant;
    }
    public UUID getNumero(){
        return  numero;
    }
    public LocalDate getDate(){
        return date;
    }
}
