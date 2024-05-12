/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import ConnectionFactory.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 823159742
 */
public class Movimentacoes {
    public void mostrarMovimentacoes(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("ID");
        modelo.addColumn("Tipo");
        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Funcionário");
        modelo.addColumn("Data da retirada");

        table.setModel(modelo);

        sql = "SELECT * FROM tb_movimentacoes;";

        String[] dados = new String[6];

        Statement st;

        try {
            st = objetoConexao.obterConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dados[0] = rs.getString(1);
                dados[1] = rs.getString(2);
                dados[2] = rs.getString(3);
                dados[3] = rs.getString(4);
                dados[4] = rs.getString(5);
                dados[5] = rs.getString(6);

                modelo.addRow(dados);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarMovimentacoes(JTable table, JTextField idMovimentacoes,
            JTextField tipoDeMovimentacao, JTextField produto, JTextField quantidade, JTextField funcionario, JTextField data_retirada) {
        try {
            int linha = table.getSelectedRow();

            if (linha >= 0) {
                idMovimentacoes.setText(table.getValueAt(linha, 0).toString());
                tipoDeMovimentacao.setText(table.getValueAt(linha, 1).toString());
                produto.setText(table.getValueAt(linha, 2).toString());
                quantidade.setText(table.getValueAt(linha, 3).toString());
                funcionario.setText(table.getValueAt(linha, 4).toString());
                data_retirada.setText(table.getValueAt(linha, 5).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Não selecionou o registro. Erro: ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select Error: " + e.toString());
        }

    }

    public void adicionarMovimentacoes(JTextField tipoDeMovimentacao, JTextField produto, JTextField quantidade, JTextField funcionario, JTextField dataRetirada) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String adicionar = "INSERT INTO tb_movimentacoes (tipoDeMovimentacao , produto, quantidade, funcionario, data_retirada) VALUES (?, ?, ?, ?, ?)";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(adicionar);
            cs.setString(1, tipoDeMovimentacao.getText());
            cs.setString(2, produto.getText());
            cs.setInt(3, Integer.parseInt(quantidade.getText()));
            cs.setString(4, funcionario.getText());
            cs.setString(5, dataRetirada.getText());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Novo registro inserido corretamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir Erro: " + e.toString());
        }
    }

    public void alterarMovimentacoes(JTextField tipoDeMovimentacao, JTextField produto,  JTextField quantidade, JTextField funcionario, JTextField data_retirada, JTextField idMovimentacoes) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_movimentacoes SET tipoDeMovimentacao=?, produto=?, quantidade=?, funcionario=?,  data_retirada=? WHERE idMovimentacoes=?;";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            cs.setString(1, tipoDeMovimentacao.getText());
            cs.setString(2, produto.getText());
            cs.setInt(3, Integer.parseInt(quantidade.getText()));
            cs.setString(4, funcionario.getText());
            cs.setString(5, data_retirada.getText());
            cs.setInt(6, Integer.parseInt(idMovimentacoes.getText()));

            cs.execute();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alterar Erro: " + e.toString());
        }
    }

    public void excluirMovimentacoes(JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String excluir = "DELETE FROM tb_movimentacoes WHERE idMovimentacoes=?;";

        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(excluir);
            cs.setInt(1, Integer.parseInt(id.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Linha excluída com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excluir Erro: " + e.toString());
        }

    }
    
    public void retirarMateriais() {
        System.out.println("teste");
    }
}
