package com.roteiroviagens.poo.dto;

import java.time.LocalDate;

public class HistoricoDTO {

    private Long id;
    private String destino;
    private LocalDate data;
    private String estilo;
    private int dias;


    public HistoricoDTO(Long id, String destino, LocalDate data, String estilo, int dias) {
        this.id = id;
        this.destino = destino;
        this.data = data;
        this.estilo = estilo;
        this.dias = dias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
