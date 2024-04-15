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
public class Fornecedores {
    public void mostrarFornecedores(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("CNPJ");
        modelo.addColumn("Razão Social");
        modelo.addColumn("Tipo de produto");

        table.setModel(modelo);

        sql = "SELECT * FROM tb_fornecedor;";

        String[] dados = new String[3];

        Statement st;

        try {
            st = objetoConexao.obterConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dados[0] = rs.getString(1);
                dados[1] = rs.getString(2);
                dados[2] = rs.getString(3);

                modelo.addRow(dados);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarFornecedores(JTable table, JTextField id, JTextField nome, JTextField tipo) {
        try {
            int linha = table.getSelectedRow();

            if (linha >= 0) {
                id.setText(table.getValueAt(linha, 0).toString());
                nome.setText(table.getValueAt(linha, 1).toString());
                tipo.setText(table.getValueAt(linha, 2).toString());
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

    public void alterarFornecedor(JTextField nome, JTextField endereco, JTextField cnpj) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_fornecedor SET cnpj=?, razao_social=?, endereco=? WHERE cnpj=?;";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            cs.setInt(1, Integer.parseInt(cnpj.getText()));
            cs.setString(2, nome.getText());
            cs.setString(3, endereco.getText());
            cs.setInt(4, Integer.parseInt(cnpj.getText()));
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alterar Erro: " + e.toString());
        }
    }

    public void excluirItemEstoque(JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String excluir = "DELETE FROM tb_fornecedor WHERE cnpj=?;";

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