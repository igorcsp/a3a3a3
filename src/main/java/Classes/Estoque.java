package Classes;

import ConnectionFactory.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Estoque {

    public void mostrarEstoque(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("código");
        modelo.addColumn("descrição");
        modelo.addColumn("fornecedor");
        modelo.addColumn("data de registro");
        modelo.addColumn("preço");
        modelo.addColumn("quantidade");
        modelo.addColumn("unidade de medida");

        table.setModel(modelo);

        sql = "SELECT * FROM tb_estoque;";

        String[] dados = new String[7];

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
                dados[6] = rs.getString(7);

                modelo.addRow(dados);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarEstoque(JTable table, JTextField id, JTextField descricao,
            JTextField fornecedor, JTextField data, JTextField preco, JTextField quantidade, JTextField unidadeDeMedida) {
        try {
            int linha = table.getSelectedRow();

            if (linha >= 0) {
                id.setText(table.getValueAt(linha, 0).toString());
                descricao.setText(table.getValueAt(linha, 1).toString());
                fornecedor.setText(table.getValueAt(linha, 2).toString());
                data.setText(table.getValueAt(linha, 3).toString());
                preco.setText(table.getValueAt(linha, 4).toString());
                quantidade.setText(table.getValueAt(linha, 5).toString());
                unidadeDeMedida.setText(table.getValueAt(linha, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Não selecionou o registro. Erro: ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select Error: " + e.toString());
        }

    }

    public void adicionarItemEstoque(JTextField descricao, JTextField fornecedor, JTextField dataregistro, JTextField preco, JTextField quantidade, JTextField unidadeDeMedida) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String adicionar = "INSERT INTO tb_estoque (descricao , fornecedor , dataregistro, preco , quantidade, unidadeDeMedida) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(adicionar);
            cs.setString(1, descricao.getText());
            cs.setString(2, fornecedor.getText());
            cs.setString(3, dataregistro.getText());
            cs.setString(4, preco.getText());
            cs.setString(5, quantidade.getText());
            cs.setString(6, unidadeDeMedida.getText());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Novo registro inserido corretamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir Erro: " + e.toString());
        }
    }

    public void alterarItemEstoque(JTextField descricao, JTextField fornecedor, JTextField dataregistro, JTextField preco, JTextField quantidade, JTextField unidadeDeMedida, JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_estoque SET descricao=?, fornecedor=?, dataregistro=?, preco=?, quantidade=?, unidadeDeMedida=? WHERE codigo=?;";
        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            cs.setString(1, descricao.getText());
            cs.setString(2, fornecedor.getText());
            cs.setString(3, dataregistro.getText());
            cs.setDouble(4, Double.parseDouble(preco.getText()));
            cs.setInt(5, Integer.parseInt(quantidade.getText()));
            cs.setString(6, unidadeDeMedida.getText());
            cs.setInt(7, Integer.parseInt(id.getText()));

            cs.execute();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alterar Erro: " + e.toString());
        }
    }

    public void excluirItemEstoque(JTextField id) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String excluir = "DELETE FROM tb_estoque WHERE codigo=?;";

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
