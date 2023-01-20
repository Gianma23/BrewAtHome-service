/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.models;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Generated;
/**
 *
 * @author Utente
 */

@Entity
@Table(name="ricetta")
public class Ricetta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="account_id")
    private String accountId;

    @Generated(GenerationTime.INSERT)
    @Column(name="nome")
    private String nome;
    
    @Column(name="descrizione")
    private String descrizione;
    
    @Column(name="autore")
    private String autore;
    
    @Column(name="tipo")
    @Generated(GenerationTime.INSERT)
    private String tipo;
    
    @Column(name="stile_id")
    @Generated(GenerationTime.INSERT)
    private String stileId;
    
    @Column(name="volume")
    @Generated(GenerationTime.INSERT)
    private double volume;
    
    @Column(name="rendimento")
    @Generated(GenerationTime.INSERT)
    private double rendimento;
        
    @Column(name="abv")
    private int abv;
    
    @Column(name="og")
    private int og;
    
    @Column(name="fg")
    private int fg;
    
    @Column(name="ebc")
    private int ebc;
    
    @Column(name="ibu")
    private int ibu;
    
    @Column(name="ultima_modifica")
    @Generated(GenerationTime.INSERT)
    private Timestamp ultimaModifica;
    
    public Ricetta() {}

    public Ricetta(int id, String accountId, String nome, String descrizione, String autore, String tipo, String stileId, double volume, double rendimento, int abv, int og, int fg, int ebc, int ibu, Timestamp ultimaModifica) {
        this.id = id;
        this.accountId = accountId;
        this.nome = nome;
        this.descrizione = descrizione;
        this.autore = autore;
        this.tipo = tipo;
        this.stileId = stileId;
        this.volume = volume;
        this.rendimento = rendimento;
        this.abv = abv;
        this.og = og;
        this.fg = fg;
        this.ebc = ebc;
        this.ibu = ibu;
        this.ultimaModifica = ultimaModifica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStileId() {
        return stileId;
    }

    public void setStileId(String stileId) {
        this.stileId = stileId;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public int getAbv() {
        return abv;
    }

    public void setAbv(int abv) {
        this.abv = abv;
    }

    public int getOg() {
        return og;
    }

    public void setOg(int og) {
        this.og = og;
    }

    public int getFg() {
        return fg;
    }

    public void setFg(int fg) {
        this.fg = fg;
    }

    public int getEbc() {
        return ebc;
    }

    public void setEbc(int ebc) {
        this.ebc = ebc;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public Timestamp getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(Timestamp ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }
}
