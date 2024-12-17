package com.controladores;

import com.App;
import com.modelo.Usuario;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NovoUsuarioControlador {

    @FXML
    private TextField nome;
    
    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    public void cadastrar() {
        if (nome.getText().isEmpty() || login.getText().isEmpty() || senha.getText().isEmpty()) {
            mostrarErro("Erro! Preencha os campos.");
            return;
        }
        
        Dao<Usuario> dao = new Dao<>(Usuario.class);
        Usuario user = dao.buscarPorChave("login", login.getText());

        if (user == null) {
            // Cria um novo usuário com a senha pura (sem hash)
            user = new Usuario(nome.getText(),login.getText(), senha.getText());
            dao.inserir(user);

            mostrarSucesso("Usuário cadastrado com sucesso!");
            limparCampos();
        } else {
            mostrarErro("Erro: Já existe um usuário com esse nome.");
        }
    }

    @FXML
    public void voltar() {
        try {
            App.trocarTela("login.fxml");
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

    private void limparCampos() {
        this.nome.setText("");
        this.login.setText("");
        this.senha.setText("");
    }
}
