package Classes;


import ConnectionFactory.ConnectionFactory;
import java.sql.CallableStatement;
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
}
