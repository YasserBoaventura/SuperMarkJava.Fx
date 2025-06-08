/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class CadastroFuncionario extends Application {
    
     Label lblCodigo = new Label("Código:");
        TextField txtCodigo = new TextField();
     

        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();
       

        Label lblSexo = new Label("Sexo:");
        ComboBox<String> cbSexo = new ComboBox<>();
      
  

        Label lblEstadoCivil = new Label("Estado Civil:");
        ComboBox<String> cbEstadoCivil = new ComboBox<>();
      
     

        Label lblCategoria = new Label("Categoria:");
        ComboBox<String> cbCategoria = new ComboBox<>();
       
       

        Label lblNivelAcesso = new Label("Nível de Acesso:");
        ComboBox<String> cbNivelAcesso = new ComboBox<>();
       
       

        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar ao Menu");

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(15);

        Label lblTitulo = new Label("Cadastro de Funcionário");
        lblTitulo.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 4, 0.5, 1, 1);" +
            "-fx-padding: 0 0 20 0;"
        );

        Label lblCodigo = new Label("Código:");
     
        txtCodigo.setPromptText("Digite o código");

        Label lblNome = new Label("Nome:");
      
        txtNome.setPromptText("Digite o nome");

        Label lblSexo = new Label("Sexo:");
       
        cbSexo.getItems().addAll("F", "M");
        cbSexo.setPromptText("Selecione");

        Label lblEstadoCivil = new Label("Estado Civil:");
       
        cbEstadoCivil.getItems().addAll("Casado", "Solteiro");
        cbEstadoCivil.setPromptText("Selecione");

        lblCategoria = new Label("Categoria:");
      
        cbCategoria.getItems().addAll("N1", "N2", "N3");
        cbCategoria.setPromptText("Selecione");

       lblNivelAcesso = new Label("Nível de Acesso:");
    
        cbNivelAcesso.getItems().addAll("Acesso Total", "Somente Vendas");
        cbNivelAcesso.setPromptText("Selecione");

        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar ao Menu");

        String buttonStyle =
            "-fx-background-color: #2979ff;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 10 20 10 20;";

        btnCadastrar.setStyle(buttonStyle);
        btnVoltar.setStyle(buttonStyle);

        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: 600;";
        lblCodigo.setStyle(labelStyle);
        lblNome.setStyle(labelStyle);
        lblSexo.setStyle(labelStyle);
        lblEstadoCivil.setStyle(labelStyle);
        lblCategoria.setStyle(labelStyle);
        lblNivelAcesso.setStyle(labelStyle);

        String inputStyle =
            "-fx-background-color: white;" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 6 10 6 10;" +
            "-fx-font-size: 14px;" +
            "-fx-border-color: transparent;" +
            "-fx-border-radius: 6;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 2, 0.5, 0, 1);";

        txtCodigo.setStyle(inputStyle);
        txtNome.setStyle(inputStyle);
        cbSexo.setStyle(inputStyle);
        cbEstadoCivil.setStyle(inputStyle);
        cbCategoria.setStyle(inputStyle);
        cbNivelAcesso.setStyle(inputStyle);

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(lblCodigo, 0, 1);
        grid.add(txtCodigo, 1, 1);
        grid.add(lblNome, 0, 2);
        grid.add(txtNome, 1, 2);
        grid.add(lblSexo, 0, 3);
        grid.add(cbSexo, 1, 3);
        grid.add(lblEstadoCivil, 0, 4);
        grid.add(cbEstadoCivil, 1, 4);
        grid.add(lblCategoria, 0, 5);
        grid.add(cbCategoria, 1, 5);
        grid.add(lblNivelAcesso, 0, 6);
        grid.add(cbNivelAcesso, 1, 6);

        HBox botoes = new HBox(10, btnCadastrar, btnVoltar);
        botoes.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setColumnSpan(botoes, 2);
        grid.add(botoes, 0, 7);

        btnCadastrar.setOnAction(e -> {
           registar();
            
               
           });
       
            
        

        btnVoltar.setOnAction(e -> {
            try {
        new MeT().start(new Stage()); // abre o menu principal
        stage.close(); // fecha a tela atual
    } catch (Exception ex) {
        ex.printStackTrace();
    }
});
        StackPane root = new StackPane(grid);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        Scene scene = new Scene(root, 500, 480);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Funcionário");
        stage.show();
        
    }

  public void registar(){
    
      int codigo=Integer.parseInt(txtCodigo.getText());
      String nome=txtNome.getText();
      String sexo=cbSexo.getValue();
      String eCivil=cbEstadoCivil.getValue();
      String  categoria=   cbCategoria.getValue();
      String nivel= cbNivelAcesso.getValue();
      if(!existe(codigo)){
      Connection con;
      try{
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
         String q="insert into funcionarioFX(codigo, nome, sexo, estadoCivil, categoria,nivel) values(?,?,?,?,?,?)";
         PreparedStatement ps=con.prepareStatement(q);
         ps.setInt(1, codigo);
         ps.setString(2, nome);
         ps.setString(3, sexo);
         ps.setString(4, eCivil);
         ps.setString(5, categoria);
         ps.setString(6, nivel);
         ps.execute();
         ps.close();
         con.close();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("sucesso");
                    alert.setHeaderText("Funcionario cadastrado com sucesso");
                    alert.show();
         
      }catch(SQLException ex){
         
      }
      }else{
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Ja existe um funcionario com esse codigo "+codigo);
                    alert.show();
      }
    
  }
    public boolean existe(int codigo){
        Connection con;
           boolean y=false;
        try{
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
        
        String q="select * from funcionarioFX where codigo="+codigo;
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