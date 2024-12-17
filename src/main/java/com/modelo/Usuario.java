package com.modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Usuario {

    @BsonProperty(value="nome")
    private String nome;
    
    @BsonProperty(value="login")
    private String login;

    @BsonProperty(value="senha")
    private String senha;

    public Usuario(){}

    public Usuario(String nome,String login, String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
