/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacaofx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
/**
 *
 * @author user
 */
public class ProdutoDAo {
  //Funcao que traz a lista dos produtos no fromulario de vendas
 public static ObservableList<VenderP.ItemVenda> buscarProdutos() {
        ObservableList<VenderP.ItemVenda> lista = FXCollections.observableArrayList();
      Connection con;
        try  {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
            String sql = "SELECT nome, preco FROM ProdutosFX";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                lista.add(new VenderP.ItemVenda(nome, 1, preco));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
 //Actualiza quando Um Produto e vendido
    public static void atualizarEstoque(String nomeProduto, int quantidadeVendida) {
        
Connection con;
        try  {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
            String sql = "UPDATE ProdutosFX SET quantidade = quantidade - ? WHERE nome = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, quantidadeVendida);
            stmt.setString(2, nomeProduto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Busca a quantidade pra ser verificada se pode ser efectuada a venda
    public static int buscarQuantidade(String nomeProduto) {
    int quantidade = 0;
    Connection con;
    try  {
       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
        String sql = "SELECT quantidade FROM produtosFX WHERE nome = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nomeProduto);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            quantidade = rs.getInt("quantidade");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return quantidade;
}
    //Funcao que actualiza o estoque por nome de produto
    public static boolean atualizarEstoquePorCodigo(String nome, int novoEstoque) {
    
        Connection con;
    try { 
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
        String sql = "UPDATE produtosFX SET quantidade = quantidade + ? WHERE nome = ?";
         PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, novoEstoque);
        stmt.setString(2, nome);

        int afetados = stmt.executeUpdate();
        return afetados > 0; // true se atualizou
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
//Salvar relatorios na base de dados
    
    public static void salvarVenda(String nome, int quantidade, double preco, double subtotal) {
        
     Connection con;
        try {
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");
            String sql = "INSERT INTO relatorio (nome_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?)";
           
             PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setInt(2, quantidade);
            stmt.setDouble(3, preco);
            stmt.setDouble(4, subtotal);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Funcao que faz a busca As factura pra o formulario relatorios
    public static List<ListaFacutas.Fatura> buscarFaturasPorPeriodo(LocalDate inicio, LocalDate fim) {
    List<ListaFacutas.Fatura> lista = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura")) {

        String sql = "SELECT DATE(data_venda) AS data, SUM(subtotal) AS total " +
                     "FROM relatorio " +
                     "WHERE DATE(data_venda) BETWEEN ? AND ? " +
                     "GROUP BY DATE(data_venda) " +
                     "ORDER BY data";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, Date.valueOf(inicio));
        stmt.setDate(2, Date.valueOf(fim));

        ResultSet rs = stmt.executeQuery();
        int contador = 1;

        while (rs.next()) {
            LocalDate data = rs.getDate("data").toLocalDate();  // <-- "data" e não "data_venda"
            double total = rs.getDouble("total");
            String numero = String.format("F%03d", contador++);
            lista.add(new ListaFacutas.Fatura(numero, data, total));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
    //Funcao que faz a busca de produtos Vendidos de um certo periodo
   public static List<String> buscarProdutosEData(LocalDate inicio, LocalDate fim) {
        List<String> produtos = new ArrayList<>();
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVAFX", "root", "Boaventura");

            String sql = "SELECT nome_produto, quantidade,  preco_unitario, data_venda " +
                         "FROM relatorio " +
                         "WHERE data_venda BETWEEN ? AND ? " +
                         "ORDER BY data_venda";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(inicio.atStartOfDay()));
            stmt.setTimestamp(2, Timestamp.valueOf(fim.plusDays(1).atStartOfDay()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome_produto");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco_unitario");
                double total = quantidade * preco;

                produtos.add(nome + " - " + quantidade + " un. - Preço: MT" + preco + " - Total: MT " + total);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return produtos;
    }
}
    
