package com.modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Producao {

    @BsonProperty(value="data")
    private String data;

    @BsonProperty(value="vaca")
    private Vaca vaca;

    @BsonProperty(value="quantidadeLitros")
    private Double quantidadeLitros;

    public Producao(){}

    public Producao(String data, Vaca vaca, Double quantidadeLitros) {
        this.data = data;
        this.vaca = vaca;
        this.quantidadeLitros = quantidadeLitros;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }

    public void setQuantidadeLitros(Double quantidadeLitros) {
        this.quantidadeLitros = quantidadeLitros;
    }

    public String getData(){
        return data;
    }

    public Vaca getVaca() {
        return vaca;
    }

    public Double getQuantidadeLitros() {
        return quantidadeLitros;
    }

}
