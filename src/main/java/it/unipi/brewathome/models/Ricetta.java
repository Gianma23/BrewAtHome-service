/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.brewathome.models;

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
public class Ricetta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="account_id")
    private String accountId;

    @Generated(GenerationTime.INSERT)
    @Column(name="nome")
    private String nome;
    
    @Column(name="autore")
    private String autore;
    
    @Column(name="tipo")
    @Generated(GenerationTime.INSERT)
    private String tipo;
    
    @Column(name="attrezzatura_id")
    @Generated(GenerationTime.INSERT)
    private int attrezzaturaId;
    
    @Column(name="stile_id")
    @Generated(GenerationTime.INSERT)
    private String stileId;
    
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

    public Ricetta(int id, String accountId, String nome, String autore, String tipo, int attrezzaturaId, String stileId, int abv, int og, int fg, int ebc, int ibu, Timestamp ultimaModifica) {
        this.id = id;
        this.accountId = accountId;
        this.nome = nome;
        this.autore = autore;
        this.tipo = tipo;
        this.attrezzaturaId = attrezzaturaId;
        this.stileId = stileId;
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

    public int getAttrezzaturaId() {
        return attrezzaturaId;
    }

    public void setAttrezzaturaId(int attrezzaturaId) {
        this.attrezzaturaId = attrezzaturaId;
    }

    public String getStileId() {
        return stileId;
    }

    public void setStileId(String stileId) {
        this.stileId = stileId;
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