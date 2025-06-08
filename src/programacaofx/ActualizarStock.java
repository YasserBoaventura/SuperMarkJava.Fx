/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ActualizarStock extends Application {
    
 

    private Map<String, Produto> baseSimulada = new HashMap<>();

    TextField txtCodigo = new TextField();
    TextField txtNovoStock = new TextField();

    @Override
    public void start(Stage stage) {
        baseSimulada.put("P001", new Produto("P001", "Mouse", "A", 10, 15.5));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Atualizar Stock do Produto");
        lblTitulo.setStyle(
                "-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 4, 0.5, 1, 1); -fx-padding: 0 0 20 0;"
        );

        Label lblCodigo = new Label("Código do Produto:");
        Label lblNovoStock = new Label("Novo Stock:");

        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 600;";
        lblCodigo.setStyle(labelStyle);
        lblNovoStock.setStyle(labelStyle);

        txtCodigo.setPromptText("Digite o código");
        txtNovoStock.setPromptText("Digite o novo stock");

        String inputStyle = "-fx-background-color: white; -fx-background-radius: 6;" +
                "-fx-padding: 6 10 6 10; -fx-font-size: 14px; -fx-border-color: transparent;" +
                "-fx-border-radius: 6; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 0, 1);";

        txtCodigo.setStyle(inputStyle);
        txtNovoStock.setStyle(inputStyle);

        Button btnAtualizar = new Button("Atualizar Stock");
        btnAtualizar.setStyle(
                "-fx-background-color: #2979ff; -fx-text-fill: white;" +
                        "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 8;" +
                        "-fx-cursor: hand; -fx-padding: 10 20 10 20;"
        );

        btnAtualizar.setOnAction(e -> atualizarStock());

        Button btnVoltar = new Button("Voltar ao Menu");
        btnVoltar.setStyle(
                "-fx-background-color: #2979ff; -fx-text-fill: white;" +
                        "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 8;" +
                        "-fx-cursor: hand; -fx-padding: 10 20 10 20;"
        );

        btnVoltar.setOnAction(e -> {
            stage.close();
            new MeT().start(new Stage()); // Substitua por sua classe de menu principal
        });

        VBox botoes = new VBox(10, btnAtualizar, btnVoltar);
        botoes.setAlignment(Pos.CENTER_RIGHT);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(lblCodigo, 0, 1); grid.add(txtCodigo, 1, 1);
        grid.add(lblNovoStock, 0, 2); grid.add(txtNovoStock, 1, 2);
        grid.add(botoes, 1, 3);
        GridPane.setMargin(botoes, new Insets(20, 0, 0, 0));

        StackPane root = new StackPane(grid);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        Scene scene = new Scene(root, 600, 450);
        stage.setScene(scene);
        stage.setTitle("Atualização de Stock");
        stage.show();
    }

    private void atualizarStock() {
    
    String codigo = txtCodigo.getText();
    String novoStockStr = txtNovoStock.getText();

    try {
        int novoStock = Integer.parseInt(novoStockStr);

        boolean atualizado = ProdutoDAo.atualizarEstoquePorCodigo(codigo, novoStock);
        if (atualizado) {
            showAlert("Sucesso", "Stock atualizado com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Erro", "Produto não encontrado ou erro ao atualizar.", Alert.AlertType.ERROR);
        }
    } catch (NumberFormatException e) {
        showAlert("Erro", "Stock deve ser um número inteiro.", Alert.AlertType.ERROR);
    }
}

    

    private void showAlert(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    static class Produto {
        String codigo;
        String nome;
        String classe;
        int quantidade;
        double preco;

        Produto(String codigo, String nome, String classe, int quantidade, double preco) {
            this.codigo = codigo;
            this.nome = nome;
            this.classe = classe;
            this.quantidade = quantidade;
            this.preco = preco;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}