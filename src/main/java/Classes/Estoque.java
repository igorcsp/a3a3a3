package Classes;

import ConnectionFactory.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Estoque {

    public void mostrarEstoque(JTable table) {
        ConnectionFactory objetoConexao = new ConnectionFactory();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Código");
        modelo.addColumn("Produto");
        modelo.addColumn("Fornecedor");
        modelo.addColumn("Data de registro");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Unidade");

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
        String addMovimentacoes = "INSERT INTO tb_movimentacoes(tipoDeMovimentacao, produto, quantidade, funcionario, data_retirada ) VALUES (?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(adicionar);
            cs.setString(1, descricao.getText());
            cs.setInt(2, Integer.parseInt(fornecedor.getText()));

            Timestamp dataDeAgora = new Timestamp(System.currentTimeMillis());
            cs.setString(3, dataDeAgora.toString());
            cs.setString(4, preco.getText());
            cs.setInt(5, Integer.parseInt(quantidade.getText()));
            cs.setString(6, unidadeDeMedida.getText());

            // Adicionando às movimentações/histórico
            CallableStatement css = objConexao.obterConexao().prepareCall(addMovimentacoes);
            css.setString(1, "Entrada");
            css.setString(2, descricao.getText());
            css.setInt(3, Integer.parseInt(quantidade.getText()));
            css.setString(4, null);
            css.setString(5, dataDeAgora.toString());

            cs.execute();
            css.execute();

            JOptionPane.showMessageDialog(null, "Novo registro inserido corretamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir registro: " + e.toString());
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

    public void retirarMateriais(JTextField quantidade, JTextField id, JTextField produto) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_estoque SET quantidade=? WHERE codigo=?;";
        String addMovimentacoes = "INSERT INTO tb_movimentacoes(tipoDeMovimentacao, produto, quantidade, funcionario, data_retirada ) VALUES (?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            CallableStatement css = objConexao.obterConexao().prepareCall(addMovimentacoes);

            String input = JOptionPane.showInputDialog(null, "Digite a quantidade que deseja retirar:");
            String funcionario = JOptionPane.showInputDialog(null, "Digite o ID do funcionário que está retirando o material:");
            int retirado = Integer.parseInt(input);
            int updatedValue = Integer.parseInt(quantidade.getText()) - retirado;
            Timestamp dataDeAgora = new Timestamp(System.currentTimeMillis());

            cs.setInt(1, updatedValue);
            cs.setInt(2, Integer.parseInt(id.getText()));

            css.setString(1, "Retirada"); // tipo de movimento
            css.setString(2, produto.getText()); // produto
            css.setInt(3, retirado); //quantidade
            css.setString(4, funcionario); // funcionario
            css.setString(5, dataDeAgora.toString()); // data da retirada

            cs.execute();
            css.execute();

            cs.execute();
            JOptionPane.showMessageDialog(null, "Retirado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao retirar a quantidade: " + e.toString());
        }
    }
    
    public void acrescentarQuantidade(JTextField quantidade, JTextField id, JTextField produto) {
        ConnectionFactory objConexao = new ConnectionFactory();
        String modificar = "UPDATE tb_estoque SET quantidade=? WHERE codigo=?;";
        String addMovimentacoes = "INSERT INTO tb_movimentacoes(tipoDeMovimentacao, produto, quantidade, funcionario, data_retirada ) VALUES (?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = objConexao.obterConexao().prepareCall(modificar);
            CallableStatement css = objConexao.obterConexao().prepareCall(addMovimentacoes);

            String input = JOptionPane.showInputDialog(null, "Digite a quantidade que deseja adicionar:");
            int adicionado = Integer.parseInt(input);
            int updatedValue = Integer.parseInt(quantidade.getText()) + adicionado;
            Timestamp dataDeAgora = new Timestamp(System.currentTimeMillis());

            cs.setInt(1, updatedValue);
            cs.setInt(2, Integer.parseInt(id.getText()));

            css.setString(1, "Entrada"); // tipo de movimento
            css.setString(2, produto.getText()); // produto
            css.setInt(3, adicionado); //quantidade
            css.setString(4, null); // funcionario
            css.setString(5, dataDeAgora.toString()); // data da retirada

            cs.execute();
            css.execute();

            cs.execute();
            JOptionPane.showMessageDialog(null, "Acrescentado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao retirar a quantidade: " + e.toString());
        }
    }

    // fazer uma função de retirar e adionar itens aqui
    // tem ser JoptionPane da quantidade e insere nas movimentações 'Retirada' e o valor atualizado com a subtração 
    // um botao de adicionar atualizando as movimentações
}
