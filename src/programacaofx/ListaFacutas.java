/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.lang.StringBuilder;
/**
 *
 * @author user
 */
public class ListaFacutas extends Application {
    
    
     DatePicker dpInicio = new DatePicker();
    private DatePicker dpFim = new DatePicker();
    private TextArea areaResultados = new TextArea();
    private List<Fatura> faturas = new ArrayList<>();
  
    private TextArea areaProdutosVendidos = new TextArea();

    @Override
   public void start(Stage stage) {

        preencherFaturasSimuladas();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(15);

        Label lblTitulo = new Label("Relatório de Faturas por Período");
        lblTitulo.setStyle("-fx-font-size: 22px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lblInicio = new Label("Data Início:");
        Label lblFim = new Label("Data Fim:");
        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 600;";
        lblInicio.setStyle(labelStyle);
        lblFim.setStyle(labelStyle);

        dpInicio.setPromptText("Início");
        dpFim.setPromptText("Fim");

        Button btnEmitir = new Button("Emitir Lista");
        Button btnVoltar = new Button("Voltar ao Menu");

        String btnStyle =
            "-fx-background-color: #2979ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 10 20 10 20;";
        btnEmitir.setStyle(btnStyle);
        btnVoltar.setStyle(btnStyle);

        btnEmitir.setOnAction(e -> emitirLista());

        btnVoltar.setOnAction(e -> {
            stage.close();
            new MeT().start(new Stage()); // Substitua por sua classe de menu principal
        });

        HBox botoes = new HBox(15, btnEmitir, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        areaResultados.setEditable(false);
        areaResultados.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");
        areaResultados.setPrefHeight(200);
        areaResultados.setPrefWidth(350);

        areaProdutosVendidos.setEditable(false);
        areaProdutosVendidos.setStyle("-fx-font-family: monospace; -fx-font-size: 13px;");
        areaProdutosVendidos.setPrefHeight(200);
        areaProdutosVendidos.setPrefWidth(420);

        // HBox para colocar as duas áreas lado a lado
        HBox conteudoAreas = new HBox(15, areaResultados, areaProdutosVendidos);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(lblInicio, 0, 1); grid.add(dpInicio, 1, 1);
        grid.add(lblFim, 0, 2); grid.add(dpFim, 1, 2);
        grid.add(botoes, 0, 3, 2, 1);
        grid.add(conteudoAreas, 0, 4, 2, 1);

        GridPane.setMargin(conteudoAreas, new Insets(10, 0, 0, 0));

        StackPane root = new StackPane(grid);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

         Scene scene = new Scene(root, 750, 450);
        stage.setTitle("Relatório de Faturas");
        stage.setScene(scene);
        stage.show();
    }
   
    private void emitirLista() {
        LocalDate inicio = dpInicio.getValue();
        LocalDate fim = dpFim.getValue();

        if (inicio == null || fim == null || fim.isBefore(inicio)) {
            areaResultados.setText("⚠️ Por favor selecione um intervalo de datas válido.");
            areaProdutosVendidos.clear();
            return;
        }

        List<Fatura> faturas = ProdutoDAo.buscarFaturasPorPeriodo(inicio, fim);
        if (faturas.isEmpty()) {
            areaResultados.setText("Nenhuma fatura encontrada nesse intervalo.");
            areaProdutosVendidos.clear();
            return;
        }

       StringBuilder sb = new StringBuilder();
        StringBuilder sbProdutos = new StringBuilder();
        double totalGeral = 0;

        sb.append("Faturas emitidas de ").append(inicio).append(" a ").append(fim).append(":\n\n");
        sbProdutos.append("📦 Produtos Vendidos:\n\n");

        for (Fatura f : faturas) {
            sb.append(" - Nº: ").append(f.numero)
              .append(" | Data: ").append(f.data)
              .append(" | Total: ").append(String.format("MT %.2f", f.total))
              .append("\n");

          // Busca de produtod do certo periodo
      List<String> produtos = ProdutoDAo.buscarProdutosEData(inicio, fim);
      
   // sbProdutos.append("📦 Produtos Vendidos:\n\n");
    for (String p : produtos) {
        sbProdutos.append("• ").append(p).append("\n");
    }
    areaProdutosVendidos.setText(sbProdutos.toString());
            totalGeral += f.total;
        }

        sb.append("\nTotal Geral: ").append(String.format("MT %.2f", totalGeral));
        areaResultados.setText(sb.toString());
        areaProdutosVendidos.setText(sbProdutos.toString());
    }

    private void preencherFaturasSimuladas() {
        faturas.add(new Fatura("F001", LocalDate.of(2025, 5, 10), 10500));
        faturas.add(new Fatura("F002", LocalDate.of(2025, 5, 15), 7800));
        faturas.add(new Fatura("F003", LocalDate.of(2025, 5, 18), 15900));
        faturas.add(new Fatura("F004", LocalDate.of(2025, 5, 20), 6200));
        faturas.add(new Fatura("F005", LocalDate.of(2025, 5, 28), 9200));
    }

    static class Fatura {
        String numero;
        LocalDate data;
        double total;

        public Fatura(String numero, LocalDate data, double total) {
            this.numero = numero;
            this.data = data;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}