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
/**
 *
 * @author user
 */
public class ListaFuncionario extends Application {
  




    private Runnable acaoVoltar;

    // Construtor sem argumentos exigido pelo JavaFX
    public ListaFuncionario() {
        this.acaoVoltar = () -> System.out.println("Nenhuma ação de voltar definida.");
    }

    // Construtor com argumento para uso externo
    public ListaFuncionario(Runnable acaoVoltar) {
        this.acaoVoltar = acaoVoltar;
    }

    @Override
    public void start(Stage stage) {
        TableView<Funcionario> tabela = new TableView<>();
        

        TableColumn<Funcionario, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());

        TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> data.getValue().nomeProperty());

        TableColumn<Funcionario, String> colSexo = new TableColumn<>("Sexo");
        colSexo.setCellValueFactory(data -> data.getValue().sexoProperty());

        TableColumn<Funcionario, String> colEstado = new TableColumn<>("Estado Civil");
        colEstado.setCellValueFactory(data -> data.getValue().estadoCivilProperty());

        TableColumn<Funcionario, String> colCategoria = new TableColumn<>("Categoria");
        colCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());

        TableColumn<Funcionario, String> colAcesso = new TableColumn<>("Nível de Acesso");
        colAcesso.setCellValueFactory(data -> data.getValue().nivelAcessoProperty());

        tabela.getColumns().addAll(colCodigo, colNome, colSexo, colEstado, colCategoria, colAcesso);
    
        
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
              
      

   ObservableList<Funcionario> lista = FXCollections.observableArrayList();
try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
    String sql = "SELECT codigo, nome, sexo, estadoCivil, categoria, nivel FROM funcionarioFX";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
        // Pegando valores do banco
        String[] linha = new String[6];
        linha[0] = rs.getInt("codigo") + ""; // convertendo int para String
        linha[1] = rs.getString("nome");
        linha[2] = rs.getString("sexo");
        linha[3] = rs.getString("estadoCivil");
        linha[4] = rs.getString("categoria");
        linha[5] = rs.getString("nivel");

        // Criando objeto Funcionario com esses dados
        Funcionario f = new Funcionario(linha[0], linha[1], linha[2], linha[3], linha[4], linha[5]);
        lista.add(f);
    }

    rs.close();
    stmt.close();
    con.close();
} catch (SQLException e) {
    e.printStackTrace();
}
tabela.setItems(lista);
 
    

       Button btnVoltar = new Button("Voltar ao Menu");
        btnVoltar.setStyle("-fx-background-color: #2979ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 8; -fx-cursor: hand; -fx-padding: 10 20 10 20;");
        btnVoltar.setOnAction(e -> {
            stage.close();
             new MeT().start(new Stage());
        });

        VBox layout = new VBox(15, new Label("Lista de Funcionários"), tabela, btnVoltar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");
        VBox.setVgrow(tabela, Priority.ALWAYS);

        Scene scene = new Scene(layout, 700, 400);
        stage.setTitle("Lista de Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
     class Funcionario {
    private final StringProperty codigo;
    private final StringProperty nome;
    private final StringProperty sexo;
    private final StringProperty estadoCivil;
    private final StringProperty categoria;
    private final StringProperty nivelAcesso;

    public Funcionario(String codigo, String nome, String sexo, String estadoCivil, String categoria, String nivelAcesso) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nome = new SimpleStringProperty(nome);
        this.sexo = new SimpleStringProperty(sexo);
        this.estadoCivil = new SimpleStringProperty(estadoCivil);
        this.categoria = new SimpleStringProperty(categoria);
        this.nivelAcesso = new SimpleStringProperty(nivelAcesso);
    }

    public StringProperty codigoProperty() { return codigo; }
    public StringProperty nomeProperty() { return nome; }
    public StringProperty sexoProperty() { return sexo; }
    public StringProperty estadoCivilProperty() { return estadoCivil; }
    public StringProperty categoriaProperty() { return categoria; }
    public StringProperty nivelAcessoProperty() { return nivelAcesso; }
}
}