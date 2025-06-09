/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
  import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.sql.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
/**

/**
 *
 * @author user
 */
public class Login extends Application {
    
    private TextField usuarioField;
    private PasswordField senhaField;

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Login");
        titulo.setFont(new Font("Segoe UI", 24));
        titulo.setStyle("-fx-text-fill: white;");

        usuarioField = new TextField();
        usuarioField.setPromptText("Usuário");
        usuarioField.setMaxWidth(250);
        usuarioField.setStyle(
            "-fx-background-radius: 10;" +
            "-fx-padding: 10;" +
            "-fx-font-size: 14px;"
        );

        senhaField = new PasswordField();
        senhaField.setPromptText("Senha");
        senhaField.setMaxWidth(250);
        senhaField.setStyle(
            "-fx-background-radius: 10;" +
            "-fx-padding: 10;" +
            "-fx-font-size: 14px;"
        );

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setFont(new Font("Segoe UI", 14));
        btnEntrar.setStyle(
            "-fx-background-color: #2979ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 10 20;" +
            "-fx-cursor: hand;"
        );

        btnEntrar.setOnAction(e -> realizarLogin(stage));

        VBox layout = new VBox(15, titulo, usuarioField, senhaField, btnEntrar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        // Emoji de usuário com opacidade visível
        Label iconeFundo = new Label("👤");
        iconeFundo.setStyle("-fx-font-size: 350px; -fx-opacity: 0.3;");

        StackPane root = new StackPane(iconeFundo, layout);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        Scene cena = new Scene(root, 500, 400);
        stage.setTitle("Tela de Login");
        stage.setScene(cena);
        stage.show();
    }

    public void realizarLogin(Stage stage) {
        String nome = usuarioField.getText().trim();
        String codigo = senhaField.getText().trim();

        if (nome.isEmpty() || codigo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro de Autenticação");
            alert.setHeaderText("Por favor, preencha todos os campos");
            alert.show();
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
             PreparedStatement pst = con.prepareStatement(
                     "SELECT nome, nivel FROM funcionarioFX WHERE nome=? AND codigo=?")) {

            pst.setString(1, nome);
            pst.setInt(2, Integer.parseInt(codigo));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nomeBanco = rs.getString("nome");
                String nivel = rs.getString("nivel");

                stage.close();

                if (nivel.equalsIgnoreCase("Acesso Total")) {
                    new MeT().start(new Stage());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Bem-vindo ao Sistema " + nomeBanco + " - " + nivel);
                    alert.show();
                } else if (nivel.equalsIgnoreCase("Somente Vendas")) {
                    new VenderP().start(new Stage());
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Tipo de autenticação desconhecida");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro de Autenticação");
                alert.setHeaderText("Nome e senha desconhecidos");
                alert.show();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro "+ex);
                    alert.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}