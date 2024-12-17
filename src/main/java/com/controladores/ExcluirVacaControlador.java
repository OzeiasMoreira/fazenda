package com.controladores;

import com.App;
import com.modelo.Vaca;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ExcluirVacaControlador {

    @FXML
    private ComboBox<String> listar;

    @FXML
    private void initialize() {
        Dao<Vaca> dao = new Dao(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()) {
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void excluir() {
        if (listar.getValue() == null) {
            mostrarErro("Erro!Preencha os campos.");
            return;
        }
        Dao<Vaca> dao = new Dao(Vaca.class);
        dao.excluir("brinco", listar.getValue());

        mostrarSucesso("Vaca excluida com sucesso!");

        listar.setValue(null);
    }

    @FXML
    public void voltar() {
        try {
            App.trocarTela("menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }

}
