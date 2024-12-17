package com.controladores;

import com.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuControlador {

    @FXML
    public void novaVaca() {
        try {
            App.trocarTela("novaVaca.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void alterarVaca() {
        try {
            App.trocarTela("alterarVaca.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void excluirVaca() {
        try {
            App.trocarTela("excluirVaca.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void novoUsuario() {
        try {
            App.trocarTela("novoUsuario.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void alterarUsuario() {
        try {
            App.trocarTela("alterarUsuario.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void excluirUsuario() {
        try {
            App.trocarTela("excluirUsuario.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void incluirProducao() {
        try {
            App.trocarTela("incluirProducao.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void listarPorPeriodo() {
        try {
            App.trocarTela("listarPorPeriodo.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarPorVaca() {
        try {
            App.trocarTela("listarPorVaca.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sair() {
        try {
            App.trocarTela("login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
