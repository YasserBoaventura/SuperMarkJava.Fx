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
  import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;
import javax.swing.*;

/**
 *
 * @author user
 */
public class CadaProduto extends Application {
    
 


  // private Map<String, Produto> baseSimulada = new HashMap<>();

    TextField txtCodigo = new TextField();
    TextField txtNome = new TextField();
    ComboBox<String> cbClasse = new ComboBox<>();
    TextField txtQuantidade = new TextField();
    TextField txtPrecoUnitario = new TextField();

    @Override
    public void start(Stage stage) {
        System.out.println("Abrindo");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(15);

        Label lblTitulo = new Label("Gerenciamento de Produto");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 4, 0.5, 1, 1); -fx-padding: 0 0 20 0;");

        Label lblCodigo = new Label("Código:");
        Label lblNome = new Label("Nome:");
        Label lblClasse = new Label("Classe:");
        Label lblQuantidade = new Label("Quantidade:");
        Label lblPrecoUnitario = new Label("Preço Unitário:");

        txtCodigo.setPromptText("Digite o código");
        txtNome.setPromptText("Digite o nome");
        cbClasse.getItems().addAll("A", "B", "C");
        cbClasse.setPromptText("Selecione");
        txtQuantidade.setPromptText("Digite a quantidade");
        txtPrecoUnitario.setPromptText("Digite o preço");

        Button btnCadastrar = new Button("Cadastrar");
        Button btnPesquisar = new Button("Pesquisar");
        Button btnAtualizar = new Button("Atualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnVoltar = new Button("Voltar ao Menu");

        String btnStyle = "-fx-background-color: #2979ff; -fx-text-fill: white;" +
                "-fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 8;" +
                "-fx-cursor: hand; -fx-padding: 10 20 10 20;";

        btnCadastrar.setStyle(btnStyle);
        btnPesquisar.setStyle(btnStyle);
        btnAtualizar.setStyle(btnStyle);
        btnEliminar.setStyle(btnStyle);
        btnVoltar.setStyle(btnStyle);

        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 600;";
        lblCodigo.setStyle(labelStyle);
        lblNome.setStyle(labelStyle);
        lblClasse.setStyle(labelStyle);
        lblQuantidade.setStyle(labelStyle);
        lblPrecoUnitario.setStyle(labelStyle);

        String inputStyle = "-fx-background-color: white; -fx-background-radius: 6;" +
                "-fx-padding: 6 10 6 10; -fx-font-size: 14px; -fx-border-color: transparent;" +
                "-fx-border-radius: 6; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 0, 1);";

        txtCodigo.setStyle(inputStyle);
        txtNome.setStyle(inputStyle);
        cbClasse.setStyle(inputStyle);
        txtQuantidade.setStyle(inputStyle);
        txtPrecoUnitario.setStyle(inputStyle);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(lblCodigo, 0, 1); grid.add(txtCodigo, 1, 1);
        grid.add(lblNome, 0, 2); grid.add(txtNome, 1, 2);
        grid.add(lblClasse, 0, 3); grid.add(cbClasse, 1, 3);
        grid.add(lblQuantidade, 0, 4); grid.add(txtQuantidade, 1, 4);
        grid.add(lblPrecoUnitario, 0, 5); grid.add(txtPrecoUnitario, 1, 5);

        HBox botoes = new HBox(10, btnCadastrar, btnPesquisar, btnAtualizar, btnEliminar);
        botoes.setAlignment(Pos.CENTER_RIGHT);
        grid.add(botoes, 1, 6);
        GridPane.setMargin(botoes, new Insets(20, 0, 0, 0));
            btnCadastrar.setOnAction(e -> {
            try {
               
               Registar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    btnAtualizar.setOnAction(e -> {
            try {
               
              Actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
   btnVoltar.setOnAction(e -> {
            try {
               
               new MeT().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
  btnEliminar.setOnAction(e -> {
            try {
               
             Apagar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
  btnPesquisar.setOnAction(e -> {
            try {
               
               Procurar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Botão voltar abaixo dos outros, alinhado à direita
        HBox boxVoltar = new HBox(btnVoltar);
        boxVoltar.setAlignment(Pos.CENTER_RIGHT);
        grid.add(boxVoltar, 1, 7);
        GridPane.setMargin(boxVoltar, new Insets(10, 0, 0, 0));

        StackPane root = new StackPane(grid);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");
   

        Scene scene = new Scene(root, 500, 500); // <--- ESTA PARTE ESTAVA FALTANDO
        stage.setScene(scene);
        stage.setTitle("Cadastro de Produto");
        stage.show();

    }
    public void Procurar(){
        int cod=Integer.parseInt(txtCodigo.getText());
        Connection con;
        if(existe(cod)){
        try{
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
             String q="select * from ProdutosFX where codigo="+cod;
             Statement smt=con.createStatement();
             ResultSet rs=smt.executeQuery(q);
             if(rs.next()){
                 txtNome.setText(rs.getString(2));
               String classeDoBanco = rs.getString("classe");
               cbClasse.getSelectionModel().select(classeDoBanco);
                 txtQuantidade.setText(rs.getInt(4)+"");
                 txtPrecoUnitario.setText(rs.getDouble(5)+"");
             }
             
        }catch(SQLException ex){
            
        }}else{
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Nao existe produto com esse codigo");
                    alert.show();
        }
    }
    public void Apagar(){
        int cod=Integer.parseInt(txtCodigo.getText());
        Connection con;
        try{
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura"); 
        String q="delete from ProdutosFX where codigo="+cod;
        Statement smt=con.createStatement();
        smt.executeUpdate(q);
        smt.close();
        con.close();
        JOptionPane.showMessageDialog(null, "Produto iliminado com sucesso");
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Nao existe produto com esse codigo");
                    alert.show();
        }
        
    }
    public void Actualizar(){
        int codigo=Integer.parseInt(txtCodigo.getText());
        String nome=txtNome.getText();
        String Class=(String) cbClasse.getValue();
        int quantidade=Integer.parseInt(txtQuantidade.getText());
        double preco=Double.parseDouble(txtPrecoUnitario.getText());
        
        Connection con;
        if(existe(codigo)){
        try{
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
            String q="update ProdutosFX set codigo=?, nome=?, classe=?, quantidade=?, preco=? where codigo="+codigo;
             PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, codigo);
        ps.setString(2, nome);
        ps.setString(3, Class);
        ps.setInt(4, quantidade);
        ps.setDouble(5, preco);
     

        ps.executeUpdate();
        ps.close();
        con.close();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Produto actualizado com sucesso");
                    alert.show();

        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Erro ao actualizar produto"+ex);
                    alert.show();
        } 
        } else{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Nao existe produto com esse codigo");
                    alert.show();
    }
 
    }
public void Registar(){

        int codigo=Integer.parseInt(txtCodigo.getText());
        String nome=txtNome.getText();
        String Class=(String) cbClasse.getValue();
        int quantidade=Integer.parseInt(txtQuantidade.getText());
        double preco=Double.parseDouble(txtPrecoUnitario.getText());
        
        Connection con;
        if(!existe(codigo)){
        try{
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
            String q="INSERT INTO ProdutosFX(codigo, nome, classe, quantidade, preco) VALUES (?, ?, ?, ?, ?)";
             PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, codigo);
        ps.setString(2, nome);
        ps.setString(3, Class);
        ps.setInt(4, quantidade);
        ps.setDouble(5, preco);
      

        ps.execute();
        ps.close();
        con.close();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText("Produto adicionado com sucesso");
                    alert.show();
        }catch(SQLException ex){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Errro"+ex);
                    alert.show();   
        } 
        } else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Ja existe um produto com esse codigo atribua outro PorFavor");
                    alert.show();
    }
 
        
 }
//Funcao que controla a repiticao de codigo
        public boolean existe(int codigo){
        Connection con;
           boolean y=false;
        try{
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
        
        String q="select * from ProdutosFX where codigo="+codigo;
        Statement smt=con.createStatement();
        ResultSet rs=smt.executeQuery(q);
        if(rs.next()){
            y=true;
        }
         }catch(SQLException ex){
                    
                
                }
        return y;
    }
  

    public static void main(String[] args) {
        launch();
    }
}