/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Utente
 */

@Entity
@Table(name="fermentabile")
public class Fermentabile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="ricetta_id")
    private int ricettaId;

    @Column(name="nome")
    private String nome;
    
    @Column(name="quantita")
    private int quantita;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="fornitore")
    private String fornitore;
    
    @Column(name="provenienza")
    private String provenienza;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="colore")
    private int colore;
    
    @Column(name="potenziale")
    private int potenziale;
    
    @Column(name="rendimento")
    private int rendimento;
    
    public Fermentabile() {
    }

    public Fermentabile(int id, int ricettaId, String nome, int quantita, String categoria, String fornitore, String provenienza, String tipo, int colore, int potenziale, int rendimento) {
        this.id = id;
        this.ricettaId = ricettaId;
        this.nome = nome;
        this.quantita = quantita;
        this.categoria = categoria;
        this.fornitore = fornitore;
        this.provenienza = provenienza;
        this.tipo = tipo;
        this.colore = colore;
        this.potenziale = potenziale;
        this.rendimento = rendimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRicettaId() {
        return ricettaId;
    }

    public void setRicettaId(int ricettaId) {
        this.ricettaId = ricettaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFornitore() {
        return fornitore;
    }

    public void setFornitore(String fornitore) {
        this.fornitore = fornitore;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getColore() {
        return colore;
    }

    public void setColore(int colore) {
        this.colore = colore;
    }

    public int getPotenziale() {
        return potenziale;
    }

    public void setPotenziale(int potenziale) {
        this.potenziale = potenziale;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }
}
