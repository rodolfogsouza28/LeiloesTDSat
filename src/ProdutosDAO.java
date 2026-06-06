/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conn = new conectaDAO().connectDB();

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar produto: " + e.getMessage());
        }

    }
    
    public void venderProduto (Integer id){
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            conn = new conectaDAO().connectDB();

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar produto: " + e.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try {         
            conn = new conectaDAO().connectDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
        
            ArrayList<ProdutosDTO> lista = new ArrayList<>();
            
            while (rs.next()) { 
                ProdutosDTO produto = new ProdutosDTO();
                        
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                        
                lista.add(produto);    
            }
            
          
            return lista;
            
        } catch (SQLException sqle ) {
            return null;
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        String sql = "SELECT * FROM produtos Where status = 'Vendido'";
        
        try {         
            conn = new conectaDAO().connectDB();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
        
            ArrayList<ProdutosDTO> lista = new ArrayList<>();
            
            while (rs.next()) { 
                ProdutosDTO produto = new ProdutosDTO();
                        
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                        
                lista.add(produto);    
            }
            
          
            return lista;
            
        } catch (SQLException sqle ) {
            return null;
        }
        
        
    }

    
    
    
        
}

