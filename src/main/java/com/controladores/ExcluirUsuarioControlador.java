package com.controladores;

import com.App;
import com.modelo.Usuario;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ExcluirUsuarioControlador {

    @FXML
    private ComboBox<String> listar;

    @FXML
    private void initialize() {
        Dao<Usuario> dao = new Dao(Usuario.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Usuario user : dao.listarTodos()) {
            listaTemp.add(user.getNome());
        }
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void excluir() {
        if (listar.getValue() == null) {
            mostrarErro("Erro!Preencha os campos.");
            return;
        }
        Dao<Usuario> dao = new Dao(Usuario.class);
        dao.excluir("nome", listar.getValue());

        mostrarSucesso("Usuario excluido com sucesso!");

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
