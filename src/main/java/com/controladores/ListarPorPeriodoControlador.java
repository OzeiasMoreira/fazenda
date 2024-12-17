package com.controladores;

import com.App;
import com.modelo.Producao;
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

import java.time.LocalDate;
import java.util.ArrayList;

public class ListarPorPeriodoControlador {

    @FXML
    private ComboBox<String> listar;

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colunaBrinco;

    @FXML
    private TableColumn<Producao, Double> colunaProducao;

    private final ObservableList<Producao> dados = FXCollections.observableArrayList();

    String tipoListagem;

    @FXML
    public void listarPorDia(){
        Dao<Producao> dao = new Dao(Producao.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Producao prod : dao.listarTodos()){
            LocalDate data = LocalDate.parse(prod.getData());
            String dia_mes_ano =  data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
            if (!listaTemp.contains(dia_mes_ano)){
                listaTemp.add(dia_mes_ano);
            }
        }
        listar.getItems().setAll(listaTemp);
        tipoListagem = "dia";
    }

    @FXML
    public void listarPorMes(){
        Dao<Producao> dao = new Dao(Producao.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Producao prod : dao.listarTodos()){
            LocalDate data = LocalDate.parse(prod.getData());
            String mes_ano = data.getMonthValue() + "/" + data.getYear();
            if (!listaTemp.contains(mes_ano)){
                listaTemp.add(mes_ano);
            }
        }
        listar.getItems().setAll(listaTemp);
        tipoListagem = "mes";
    }

    @FXML
    public void mostrar() {

        colunaBrinco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVaca().getBrinco()));
        colunaProducao.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getQuantidadeLitros()).asObject());

        dados.clear();

        Dao<Producao> dao = new Dao(Producao.class);
        if (tipoListagem.equals("dia")) {
            for (Producao prod : dao.listarTodos()){
                LocalDate data = LocalDate.parse(prod.getData());
                String dia_mes_ano =  data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear();
                if (dia_mes_ano.equals(listar.getValue())){
                    dados.add(prod);
                }
            }
        }
        else{
            for (Producao prod : dao.listarTodos()){
                LocalDate data = LocalDate.parse(prod.getData());
                String mes_ano = data.getMonthValue() + "/" + data.getYear();
                if (mes_ano.equals(listar.getValue())){
                    dados.add(prod);
                }
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
