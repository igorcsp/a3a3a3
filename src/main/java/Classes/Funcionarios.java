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
public class Funcionarios {
    public void mostrarFuncionarios(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Código");
        modelo.addColumn("Nome");

        table.setModel(modelo);

        sql = "SELECT * FROM tb_funcionarios;";

        String[] dados = new String[2];

        Statement st;

        try {
            st = objetoConexao.obterConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dados[0] = rs.getString(1);
                dados[1] = rs.getString(2);

                modelo.addRow(dados);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarFuncionarios(JTable table, JTextField id, JTextField funcionario) {
        try {
            int linha = table.getSelectedRow();

            if (linha >= 0) {
                id.setText(table.getValueAt(linha, 0).toString());
                funcionario.setText(table.getValueAt(linha, 1).toString());
                
            } else {
                JOptionPane.showMessageDialog(null, "Não selecionou o registro. Erro: ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select Error: " + e.toString());
        }

    }
    
    public void adicionarFuncionario(JTextField funcionario) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String adicionar = "INSERT INTO tb_funcionarios (nome) VALUES (?)";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(adicionar);
            cs.setString(1, funcionario.getText());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Novo registro inserido corretamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir Erro: " + e.toString());
        }
    }
    
    public void alterarFuncionario(JTextField nome, JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_funcionarios SET nome=? WHERE id=?;";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            cs.setString(1, nome.getText());
            cs.setInt(2, Integer.parseInt(id.getText()));

            cs.execute();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alterar Erro: " + e.toString());
        }
    }
    
    public void excluirFuncionario(JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String excluir = "DELETE FROM tb_funcionarios WHERE id=?;";

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
