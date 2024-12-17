package com.controladores;

import com.App;
import com.modelo.Vaca;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class NovaVacaControlador {

    @FXML
    private TextField nome;

    @FXML
    private TextField brinco;

    @FXML
    private TextField raca;

    @FXML
    public void cadastrar() {
        if (nome.getText().isEmpty() || brinco.getText().isEmpty() || raca.getText().isEmpty()) {
            mostrarErro("Erro!Preencha os campos.");
            return;
        }

        Dao<Vaca> dao = new Dao<Vaca>(Vaca.class);
        Vaca vaca = dao.buscarPorChave("brinco", brinco.getText());
        if (vaca == null) {

            vaca = new Vaca(brinco.getText(), nome.getText(), raca.getText());
            dao.inserir(vaca);

            mostrarSucesso("Vaca cadastrada com sucesso!");

            limparCampos();
        } else {
            mostrarErro("Erro!Já existe uma vaca com esse brinco!");
        }
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
        this.brinco.setText("");
        this.raca.setText("");
    }

}
