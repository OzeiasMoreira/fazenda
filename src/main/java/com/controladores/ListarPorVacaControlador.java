package com.controladores;

import com.App;
import com.modelo.Producao;
import com.modelo.Vaca;
import com.util.Dao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;


public class ListarPorVacaControlador {

    @FXML
    private ComboBox<String> listar;

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colunaData;

    @FXML
    private TableColumn<Producao, Double> colunaProducao;

    private final ObservableList<Producao> dados = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Dao<Vaca> dao = new Dao(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()){
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void mostrar() {
   
        colunaData.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getData()));
        colunaProducao.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getQuantidadeLitros()).asObject());

        dados.clear();

        Dao<Producao> dao = new Dao<>(Producao.class);
        for (Producao prod : dao.listarTodos()){
            if(prod.getVaca().getBrinco().equals(listar.getValue())){
                dados.add(prod);
            }
        }

        tabelaProducao.setItems(dados);
    }

    @FXML
    public void voltar(){
        try{
            App.trocarTela("menu.fxml");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
