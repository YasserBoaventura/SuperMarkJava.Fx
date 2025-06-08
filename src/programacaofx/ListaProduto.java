/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ListaProduto extends Application {
 



    private TableView<Produto> table = new TableView<>();
    private ObservableList<Produto> lista = FXCollections.observableArrayList();

    public class Produto {
        private String codigo;
        private String nome;
        private String classe;
        private int quantidade;
        private double preco;

        public Produto(String codigo, String nome, String classe, int quantidade, double preco) {
            this.codigo = codigo;
            this.nome = nome;
            this.classe = classe;
            this.quantidade = quantidade;
            this.preco = preco;
        }

        public String getCodigo() { return codigo; }
        public String getNome() { return nome; }
        public String getClasse() { return classe; }
        public int getQuantidade() { return quantidade; }
        public double getPreco() { return preco; }
    }

    @Override
    public void start(Stage stage) {
        // Definir colunas da tabela
        TableColumn<Produto, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Produto, String> colClasse = new TableColumn<>("Classe");
        colClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));

        TableColumn<Produto, Integer> colQuantidade = new TableColumn<>("Quantidade");
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        TableColumn<Produto, Double> colPreco = new TableColumn<>("Preço Unitário");
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        table.getColumns().addAll(colCodigo, colNome, colClasse, colQuantidade, colPreco);
        table.setItems(lista);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(400);

        Label titulo = new Label("Lista de Produtos");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        Button btnPreencher = new Button("Preencher Produtos");
        Button btnVoltar = new Button("Voltar ao Menu");

        String btnStyle =
            "-fx-background-color: #2979ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 8 20;";

        btnPreencher.setStyle(btnStyle);
        btnVoltar.setStyle(btnStyle);

        btnPreencher.setOnAction(e -> preencher());

        btnVoltar.setOnAction(e -> {
            stage.close();
            try {
                new MeT().start(new Stage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao abrir o menu: " + ex.getMessage());
            }
        });

        HBox botoes = new HBox(10, btnPreencher, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, titulo, botoes, table);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        StackPane root = new StackPane(vbox);
        Scene scene = new Scene(root, 720, 500);

        stage.setTitle("Lista de Produtos");
        stage.setScene(scene);
        stage.show();
    }

    public void preencher() {
        lista.clear(); // limpa a lista para evitar duplicação
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT codigo, nome, classe, quantidade, preco FROM ProdutosFx")) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("codigo") + "",
                    rs.getString("nome"),
                    rs.getString("classe"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
