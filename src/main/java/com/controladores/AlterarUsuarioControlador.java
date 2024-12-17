package com.controladores;

import com.App;
import com.modelo.Usuario;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AlterarUsuarioControlador {

    @FXML
    TextField nome;

    @FXML
    TextField login;

    @FXML
    PasswordField senha;

    @FXML
    private ComboBox<String> listar;

    private Dao<Usuario> dao;

    @FXML
    private void initialize() {
        dao = new Dao<>(Usuario.class);
        carregarUsuarios();

        listar.setOnAction(event -> preencherCampos());
    }

    private void carregarUsuarios() {
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Usuario user : dao.listarTodos()) {
            listaTemp.add(user.getNome());
        }
        listar.getItems().setAll(listaTemp);
    }

    private void preencherCampos() {
        String usuarioSelecionado = listar.getValue();

        if (usuarioSelecionado != null) {
            Usuario usuario = dao.buscarPorChave("nome", usuarioSelecionado);
            if (usuario != null) {
                nome.setText(usuario.getNome());
                login.setText(usuario.getLogin());
                senha.setText(usuario.getSenha());
            }
        }
    }

    @FXML
    public void alterar() {
        if (nome.getText().isEmpty() || login.getText().isEmpty() || senha.getText().isEmpty() || listar.getValue() == null) {
            mostrarErro("Erro! Preencha os campos.");
            return;
        }

        Usuario usuario = new Usuario(nome.getText(), login.getText(), senha.getText());
        dao.alterar("nome", listar.getValue(), usuario);

        mostrarSucesso("Usuário alterado com sucesso!");

        limparCampos();
        listar.setValue(null);
        carregarUsuarios();
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

    private void limparCampos() {
        this.nome.setText("");
        this.login.setText("");
        this.senha.setText("");
    }
}
