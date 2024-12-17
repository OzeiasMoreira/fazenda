package com.controladores;

import com.App;
import com.modelo.Vaca;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AlterarVacaControlador {

    @FXML
    private TextField nome;

    @FXML
    private TextField brinco;

    @FXML
    private TextField raca;

    @FXML
    private ComboBox<String> listar;

    private Dao<Vaca> dao;

    @FXML
    private void initialize() {
        dao = new Dao<>(Vaca.class);
        carregarVacas();

        listar.setOnAction(event -> preencherCampos());
    }

    private void carregarVacas() {
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()) {
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }

    private void preencherCampos() {
        String brincoSelecionado = listar.getValue();

        if (brincoSelecionado != null) {
            Vaca vaca = dao.buscarPorChave("brinco", brincoSelecionado);
            if (vaca != null) {
                brinco.setText(vaca.getBrinco());
                nome.setText(vaca.getNome());
                raca.setText(vaca.getRaca());
            }
        }
    }

    @FXML
    public void alterar() {
        if (nome.getText().isEmpty() || raca.getText().isEmpty() || listar.getValue() == null) {
            mostrarErro("Erro! Preencha os campos!");
            return;
        }

        Vaca vaca = new Vaca(brinco.getText(), nome.getText(), raca.getText());
        dao.alterar("brinco", listar.getValue(), vaca);

        mostrarSucesso("Vaca alterada com sucesso!");

        limparCampos();
        listar.setValue(null);
        carregarVacas(); 
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
