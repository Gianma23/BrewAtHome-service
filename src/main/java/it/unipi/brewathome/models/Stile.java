package it.unipi.brewathome.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="stile")
public class Stile implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nome")
    private String nome;
    
    @Column(name="guida")
    private String guida;
    
    @Column(name="abv_min")
    private double abvMin;
    
    @Column(name="abv_max")
    private double abvMax;
    
    @Column(name="og_min")
    private double ogMin;
    
    @Column(name="og_max")
    private double ogMax;
    
    @Column(name="fg_min")
    private double fgMin;
    
    @Column(name="fg_max")
    private double fgMax;
    
    @Column(name="srm_min")
    private int srmMin;
    
    @Column(name="srm_max")
    private int srmMax;
    
    @Column(name="ibu_min")
    private int ibuMin;
    
    @Column(name="ibu_max")
    private int ibuMax;
    
    public Stile() {}

    public Stile(String nome, String guida, double abvMin, double abvMax, double ogMin, double ogMax, double fgMin, double fgMax, int srmMin, int srmMax, int ibuMin, int ibuMax) {
        this.nome = nome;
        this.guida = guida;
        this.abvMin = abvMin;
        this.abvMax = abvMax;
        this.ogMin = ogMin;
        this.ogMax = ogMax;
        this.fgMin = fgMin;
        this.fgMax = fgMax;
        this.srmMin = srmMin;
        this.srmMax = srmMax;
        this.ibuMin = ibuMin;
        this.ibuMax = ibuMax;
    }

    public String getGuida() {
        return guida;
    }

    public void setGuida(String guida) {
        this.guida = guida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAbvMin() {
        return abvMin;
    }

    public void setAbvMin(double abvMin) {
        this.abvMin = abvMin;
    }

    public double getAbvMax() {
        return abvMax;
    }

    public void setAbvMax(double abvMax) {
        this.abvMax = abvMax;
    }

    public double getOgMin() {
        return ogMin;
    }

    public void setOgMin(double ogMin) {
        this.ogMin = ogMin;
    }

    public double getOgMax() {
        return ogMax;
    }

    public void setOgMax(double ogMax) {
        this.ogMax = ogMax;
    }

    public double getFgMin() {
        return fgMin;
    }

    public void setFgMin(double fgMin) {
        this.fgMin = fgMin;
    }

    public double getFgMax() {
        return fgMax;
    }

    public void setFgMax(double fgMax) {
        this.fgMax = fgMax;
    }

    public int getSrmMin() {
        return srmMin;
    }

    public void setSrmMin(int srmMin) {
        this.srmMin = srmMin;
    }

    public int getSrmMax() {
        return srmMax;
    }

    public void setSrmMax(int srmMax) {
        this.srmMax = srmMax;
    }

    public int getIbuMin() {
        return ibuMin;
    }

    public void setIbuMin(int ibuMin) {
        this.ibuMin = ibuMin;
    }

    public int getIbuMax() {
        return ibuMax;
    }

    public void setIbuMax(int ibuMax) {
        this.ibuMax = ibuMax;
    }
}
