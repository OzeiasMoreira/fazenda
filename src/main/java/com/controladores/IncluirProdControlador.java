package com.controladores;

import com.App;
import com.modelo.Producao;
import com.modelo.Vaca;
import com.util.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class IncluirProdControlador {

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox<String> listar;

    @FXML
    private TextField litros;

    @FXML
    private void initialize() {
        data.setValue(LocalDate.now());

        Dao<Vaca> dao = new Dao(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()) {
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void registrar() {

        if (litros.getText().isEmpty() || listar.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Preencha os campos");
            alert.show();
            return;
        }

        Dao<Vaca> dao = new Dao(Vaca.class);
        Vaca vaca = dao.buscarPorChave("brinco", listar.getValue());

        Producao producao = new Producao(data.getValue().toString(), vaca, Double.valueOf(litros.getText()));
        Dao<Producao> daoprod = new Dao(Producao.class);
        daoprod.inserir(producao);

        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText("Sucesso");
        sucesso.setContentText("Produção registrada com sucesso!");
        sucesso.show();

        litros.setText("");
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
}
