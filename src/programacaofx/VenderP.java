/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
 
public class VenderP extends Application {
    
   

    private TableView<ItemVenda> tabela = new TableView<>();
    private ObservableList<ItemVenda> itens = FXCollections.observableArrayList();

    private Label lblTotal = new Label("0.00 MZN");
    private TextField txtValorRecebido = new TextField();
    private Label lblTroco = new Label("0.00 MZN");

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Caixa de Supermercado");
        titulo.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");



    ObservableList<ItemVenda> produtosDisponiveis = ProdutoDAo.buscarProdutos();
     



        TextField campoPesquisa = new TextField();
        campoPesquisa.setPromptText("Pesquisar produto...");

        ListView<ItemVenda> produtoListView = new ListView<>(produtosDisponiveis);
        produtoListView.setPrefWidth(200);

        campoPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
            String filtro = newVal.toLowerCase();
            produtoListView.setItems(produtosDisponiveis.filtered(p ->
                p.getNome().toLowerCase().contains(filtro)));
        });

        produtoListView.setOnMouseClicked(e -> {
      
    ItemVenda selecionado = produtoListView.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Quantidade do Produto");
        dialog.setHeaderText("Informe a quantidade para: " + selecionado.getNome());
        dialog.setContentText("Quantidade:");
 
        dialog.showAndWait().ifPresent(qtdStr -> {       // Condicao que verifica se a qunatidade a ser vendida e maior que 0
            try {
                int quantidade = Integer.parseInt(qtdStr);
                if (quantidade <= 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "A quantidade deve ser maior que zero.");
                    alert.show();
                    return;
                }

                int estoqueAtual = ProdutoDAo.buscarQuantidade(selecionado.getNome());
                if (estoqueAtual < quantidade) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Estoque insuficiente! Disponível: " + estoqueAtual);
                    alert.show();
                    return;
                }

                itens.add(new ItemVenda(selecionado.getNome(), quantidade, selecionado.getPreco()));
                atualizarTotal();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Quantidade inválida.");
                alert.show();
            }
        });
    }
});

    

        VBox listaProdutosBox = new VBox(10, campoPesquisa, produtoListView);
        listaProdutosBox.setPadding(new Insets(10));
        listaProdutosBox.setPrefWidth(220);

        TableColumn<ItemVenda, String> colNome = new TableColumn<>("Produto");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<ItemVenda, Integer> colQtd = new TableColumn<>("Qtd");
        colQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        TableColumn<ItemVenda, Double> colPreco = new TableColumn<>("Preço Unit.");
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<ItemVenda, Double> colSubtotal = new TableColumn<>("Subtotal");
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
      
colSubtotal.setCellValueFactory(cellData -> 
    new ReadOnlyObjectWrapper<>(cellData.getValue().getSubtotal())
);

TableColumn<ItemVenda, Void> colRemover = new TableColumn<>("❌");
colRemover.setCellFactory(col -> new TableCell<ItemVenda, Void>() {
    private final Button btn = new Button("X");

    {
        btn.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 1 4 1 4;");
        btn.setOnAction(e -> {
            ItemVenda item = getTableView().getItems().get(getIndex());
            itens.remove(item);
            atualizarTotal();
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : btn);
    }
});

        tabela.getColumns().addAll(colNome, colQtd, colPreco, colSubtotal, colRemover);
        tabela.setItems(itens);
        tabela.setPrefHeight(200);

        GridPane pagamentoPane = new GridPane();
        pagamentoPane.setHgap(10);
        pagamentoPane.setVgap(10);

        pagamentoPane.add(new Label("Total (com IVA 17%):"), 0, 0);
        pagamentoPane.add(lblTotal, 1, 0);
        pagamentoPane.add(new Label("Valor Recebido:"), 0, 1);
        pagamentoPane.add(txtValorRecebido, 1, 1);
        pagamentoPane.add(new Label("Troco:"), 0, 2);
        pagamentoPane.add(lblTroco, 1, 2);

        Button btnConcluir = new Button("Concluir Venda");// vvvvvv
        btnConcluir.setStyle(btnEstilo());
        btnConcluir.setOnAction(e -> concluirVenda());

        Button btnVoltar = new Button("Voltar ao Menu");
        btnVoltar.setStyle(btnEstilo());
        btnVoltar.setOnAction(e -> {
            stage.close();
       
             new Login().start(new Stage());
        });

        HBox botoesBox = new HBox(20, btnVoltar, btnConcluir);
        botoesBox.setAlignment(Pos.CENTER);

        VBox caixaLayout = new VBox(15, titulo, tabela, pagamentoPane, botoesBox);
        caixaLayout.setPadding(new Insets(25));
        caixaLayout.setAlignment(Pos.TOP_CENTER);

        HBox layoutFinal = new HBox(20, listaProdutosBox, caixaLayout);
        layoutFinal.setPadding(new Insets(20));

        StackPane root = new StackPane(layoutFinal);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #4a90e2, #50e3c2);");

        Scene scene = new Scene(root, 900, 550);
        stage.setScene(scene);
        stage.setTitle("Caixa de Vendas");
        stage.show();
    }

    private void atualizarTotal() {
        double total = itens.stream().mapToDouble(ItemVenda::getSubtotal).sum();
        double totalComIVA = total * 1.17;
        lblTotal.setText(String.format("%.2f MZN", totalComIVA));
    }

    private void concluirVenda() {
        try {
            double total = Double.parseDouble(lblTotal.getText().replace(" MZN", ""));
            double recebido = Double.parseDouble(txtValorRecebido.getText());

            double troco = recebido - total;
            lblTroco.setText(String.format("%.2f MZN", troco));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SUPERMERCADO "+ "\n" +
                                 "Alto Mae-Maputo"+ "\n" 
                          );
            alert.setContentText("Total: " + lblTotal.getText() + "\n" +
                                 "Recebido: " + recebido + "\n" +
                                 "Troco: " + lblTroco.getText()+ "\n" +
                                   "----------------------------"+ "\n" +
                                   "Obrigado volte sempre");
            alert.show();

         
            // Atualiza estoque no banco de dados
for (ItemVenda item : itens) {
    ProdutoDAo.atualizarEstoque(item.getNome(), item.getQuantidade());
    
    //Metodo pra salvar venda na tabela relatorios
     ProdutoDAo.salvarVenda(
        item.getNome(),
        item.getQuantidade(),
        item.getPreco(),
        item.getSubtotal()
    );
}

// Atualiza UI
itens.clear();
atualizarTotal();
txtValorRecebido.clear();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Valor recebido inválido.");
            alert.show();
        }
    }

    private String btnEstilo() {
        return "-fx-background-color: #2979ff; -fx-text-fill: white;" +
               "-fx-font-weight: bold; -fx-font-size: 14px;" +
               "-fx-background-radius: 8; -fx-cursor: hand;" +
               "-fx-padding: 10 20 10 20;";
    }

    public static class ItemVenda {
        private String nome;
        private int quantidade;
        private double preco;

        public ItemVenda(String nome, int quantidade, double preco) {
            this.nome = nome;
            this.quantidade = quantidade;
            this.preco = preco;
        }

        public String getNome() { return nome; }
        public int getQuantidade() { return quantidade; }
        public double getPreco() { return preco; }
        public double getSubtotal() { return quantidade * preco; }

        @Override
        public String toString() {
            return nome + " - " + preco + " MZN";
        }
    }

    public static void main(String[] args) {
        launch();
    }
}