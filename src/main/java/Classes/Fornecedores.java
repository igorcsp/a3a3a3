/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import ConnectionFactory.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 823159742
 */
public class Fornecedores { // refazer essa porra, ta dando erro porr causa do big int e do int do java acho
    public void mostrarFornecedores(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("ID");
        modelo.addColumn("CNPJ");
        modelo.addColumn("Razão Social");
        modelo.addColumn("Endereço");

        table.setModel(modelo);

        sql = "SELECT * FROM tb_fornecedor;";

        String[] dados = new String[4];

        Statement st;

        try {
            st = objetoConexao.obterConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dados[0] = rs.getString(1);
                dados[1] = rs.getString(2);
                dados[2] = rs.getString(3);
                dados[3] = rs.getString(4);

                modelo.addRow(dados);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarFornecedores(JTable table, JTextField id, JTextField cnpj, JTextField nome, JTextField endereco) {
        try {
            int linha = table.getSelectedRow();

            if (linha >= 0) {
                id.setText(table.getValueAt(linha, 0).toString());
                cnpj.setText(table.getValueAt(linha, 1).toString());
                nome.setText(table.getValueAt(linha, 2).toString());
                endereco.setText(table.getValueAt(linha, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Não selecionou o registro. Erro: ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select Error: " + e.toString());
        }

    }

    public void adicionarFornecedor(JTextField cnpj, JTextField razao_social, JTextField endereco) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String adicionar = "INSERT INTO tb_fornecedor (cnpj , razao_social , endereco) VALUES (?, ?, ?)";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(adicionar);
            cs.setString(1, cnpj.getText());
            cs.setString(2, razao_social.getText());
            cs.setString(3, endereco.getText());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Novo registro inserido corretamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir Erro: " + e.toString());
        }
    }

    public void alterarFornecedor(JTextField cnpj, JTextField nome, JTextField endereco, JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_fornecedor SET cnpj=?, razao_social=?, endereco=? WHERE id=?;";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            cs.setString(1, cnpj.getText());
            cs.setString(2, nome.getText());
            cs.setString(3, endereco.getText());
            cs.setInt(4, Integer.parseInt(id.getText()));
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alterar Erro: " + e.toString());
        }
    }
    
    public void excluirItemEstoque(JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String excluir = "DELETE FROM tb_fornecedor WHERE id=?;";

        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(excluir);
            cs.setInt(1, Integer.parseInt(id.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Linha excluída com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excluir Erro: " + e.toString());
        }

    }
}
