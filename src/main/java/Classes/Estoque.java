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

    public void mostrarEstoque(JTable paramTableLivros) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Autor");
        modelo.addColumn("Disponível");
        modelo.addColumn("Reservado");
        modelo.addColumn("Emprestado para");
        modelo.addColumn("Emprestado ");

        paramTableLivros.setModel(modelo);

        sql = "SELECT * FROM tb_estoque;";

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
                dados[6] = rs.getString(7);

                modelo.addRow(dados);
            }

            paramTableLivros.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não mostrou o registro. Erro: " + e.toString());
        }
    }

    public void selecionarEstoque(JTable paramTableLivros, JTextField paramId, JTextField paramTitulo, JTextField paramAutor, JTextField paramDisponivel, JTextField paramReservado, JTextField paramEmprestadoPara, JTextField paramEmprestado) {
        try {
            int linha = paramTableLivros.getSelectedRow();

            if (linha >= 0) {
                paramId.setText(paramTableLivros.getValueAt(linha, 0).toString());
                paramTitulo.setText(paramTableLivros.getValueAt(linha, 1).toString());
                paramAutor.setText(paramTableLivros.getValueAt(linha, 2).toString());
                paramDisponivel.setText(paramTableLivros.getValueAt(linha, 3).toString());
                paramReservado.setText(paramTableLivros.getValueAt(linha, 4).toString());
                paramEmprestadoPara.setText(paramTableLivros.getValueAt(linha, 5).toString());
                paramEmprestado.setText(paramTableLivros.getValueAt(linha, 6).toString());
                // colocar mais um aqui

            } else {
                JOptionPane.showMessageDialog(null, "Não selecionou o registro. Erro: ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select Error: " + e.toString());
        }

    }
}
