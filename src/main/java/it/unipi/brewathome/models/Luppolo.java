package it.unipi.brewathome.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="luppolo")
public class Luppolo implements Serializable {
    
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

    @Column(name="fornitore")
    private String fornitore;
    
    @Column(name="provenienza")
    private String provenienza;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="tempo")
    private int tempo;
    
    @Column(name="alpha")
    private double alpha;
    
    public Luppolo() {}

    public Luppolo(int id, int ricettaId, String nome, int quantita, String fornitore, String provenienza, String tipo, int tempo, double alpha) {
        this.id = id;
        this.ricettaId = ricettaId;
        this.nome = nome;
        this.quantita = quantita;
        this.fornitore = fornitore;
        this.provenienza = provenienza;
        this.tipo = tipo;
        this.tempo = tempo;
        this.alpha = alpha;
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

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
