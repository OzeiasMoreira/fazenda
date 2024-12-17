package com.controladores;

import com.App;
import com.modelo.Usuario;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador {

    @FXML
    private TextField nome;
    
    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    public void entrar() {
        if (login.getText().isEmpty() || senha.getText().isEmpty()) {
            mostrarErro("Erro! Preencha os campos.");
            return;
        }

        Dao<Usuario> dao = new Dao<>(Usuario.class);
        Usuario user = dao.buscarPorChave("login", login.getText());

        if (user == null) {
            mostrarErro("Login incorreto!");
            return;
        }

        if (senha.getText().equals(user.getSenha())) {
            try {
                App.trocarTela("menu.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarErro("Senha incorreta!");
        }
    }

    @FXML
    public void cadastrar() {
        try {
            App.trocarTela("novoUsuario.fxml");
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
