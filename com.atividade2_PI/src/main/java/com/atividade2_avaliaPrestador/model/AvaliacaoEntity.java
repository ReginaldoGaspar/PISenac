package com.atividade2_avaliaPrestador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Reginaldo Gaspar
 */
@Data
@Entity

@Table(name = "Avaliacao")

public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "Prestador.id")
    private PrestadorEntity prestador;
    private String avaliacao;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrestadorEntity getPrestador() {
        return prestador;
    }

    public void setPrestador(PrestadorEntity prestador) {
        this.prestador = prestador;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

}
