package com.roteiroviagens.poo.dto;

public class RoteiroResponseDTO {
    private Long roteiroId;
    private String roteiroTexto;

    public RoteiroResponseDTO() {}

    public RoteiroResponseDTO(Long roteiroId, String roteiroTexto) {
        this.roteiroId = roteiroId;
        this.roteiroTexto = roteiroTexto;
    }

    public Long getRoteiroId() {
        return roteiroId;
    }
    public void setRoteiroId(Long roteiroId) {
        this.roteiroId = roteiroId;
    }
    public String getRoteiroTexto() {
        return roteiroTexto;
    }
    public void setRoteiroTexto(String roteiroTexto) {
        this.roteiroTexto = roteiroTexto;
    }
}
