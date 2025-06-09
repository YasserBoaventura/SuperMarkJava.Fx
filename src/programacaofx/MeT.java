/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author user
 */
public class MeT extends Application {
  
 public void start(Stage stage) {
        VBox menuBox = new VBox(25);
        menuBox.setPadding(new Insets(40));
        menuBox.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Menu Principal");
        lblTitulo.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 5, 0.5, 2, 2);");

        // Criando botões Com icones simples
Button btnCadastroFuncionario = criarBotao("Func.", "👤", "yellow");
Button btnCadastroProduto = criarBotao("Prod.", "📦", "lightgreen");
Button btnAtualizarStock = criarBotao("Stock", "🔄", "orange");
Button btnLogin = criarBotao("Login", "🔐", "red");
Button btnVender = criarBotao("Venda", "🛒", "white");
Button btnListaFuncionarios = criarBotao("Lista Func.", "📋", "lightblue");
Button btnListaProdutos = criarBotao("Lista Prod.", "📦", "violet");
Button btnRelatorioFacturas = criarBotao("Faturas", "🧾", "white");



        // Definindo largura igual
        double larguraBotao = 180;
        btnCadastroFuncionario.setPrefWidth(larguraBotao);
        btnCadastroProduto.setPrefWidth(larguraBotao);
        btnAtualizarStock.setPrefWidth(larguraBotao);
        btnLogin.setPrefWidth(larguraBotao);
        btnVender.setPrefWidth(larguraBotao);
        btnListaFuncionarios.setPrefWidth(larguraBotao);
        btnListaProdutos.setPrefWidth(larguraBotao);
        btnRelatorioFacturas.setPrefWidth(larguraBotao);

        // Organizando os botões em linhas de 3
        HBox linha1 = new HBox(15, btnCadastroFuncionario, btnCadastroProduto, btnAtualizarStock);
        HBox linha2 = new HBox(15, btnLogin, btnVender, btnListaFuncionarios);
        HBox linha3 = new HBox(15, btnListaProdutos, btnRelatorioFacturas);
        linha1.setAlignment(Pos.CENTER);
        linha2.setAlignment(Pos.CENTER);
        linha3.setAlignment(Pos.CENTER);

        // Ações dos botões
        btnCadastroFuncionario.setOnAction(e -> abrirTela(new CadastroFuncionario(), stage));
        btnCadastroProduto.setOnAction(e -> abrirTela(new CadaProduto(), stage));
        btnAtualizarStock.setOnAction(e -> abrirTela(new ActualizarStock(), stage));
        btnLogin.setOnAction(e -> abrirTela(new Login(), stage));
        btnVender.setOnAction(e -> abrirTela(new VenderP(), stage));
        btnListaFuncionarios.setOnAction(e -> abrirTela(new ListaFuncionario(), stage));
        btnListaProdutos.setOnAction(e -> abrirTela(new ListaProduto(), stage));
        btnRelatorioFacturas.setOnAction(e -> abrirTela(new ListaFacutas(), stage));

        // Adicionando componentes
        menuBox.getChildren().addAll(lblTitulo, linha1, linha2, linha3);

        StackPane root = new StackPane(menuBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");
   Scene scene = new Scene(root, 800, 400);
       
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.show();
    }
private Button criarBotao(String texto, String emoji, String corEmoji) {
    Label icone = new Label(emoji);
    icone.setStyle("-fx-font-size: 18px; -fx-text-fill: " + corEmoji + ";");

    Button botao = new Button(texto, icone);
    botao.setStyle(
        "-fx-background-color: #2979ff; -fx-text-fill: white;" +
        "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 10;" +
        "-fx-cursor: hand; -fx-padding: 10 20 10 20;"
    );
    botao.setGraphicTextGap(10); // Espaço entre o ícone e o texto
    return botao;
}


    private void abrirTela(Application app, Stage atual) {
        try {
            app.start(new Stage());
            atual.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
