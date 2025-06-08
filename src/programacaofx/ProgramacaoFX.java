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
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ProgramacaoFX extends Application {
    
    @Override
    public void start(Stage stage) {
        Button btn = new Button("Bem-vindo ao Sistema");
        btn.setStyle(
            "-fx-background-color: #2979ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12 24 12 24;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bem-vindo ao Sistema");
                new Login().start(new Stage()); // chama a tela de Login
                stage.close(); // fecha a tela inicial
            }
        });

        StackPane root = new StackPane(btn);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Sistema de Gestão");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}