
package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class ConnectionFactory {
    Connection conectar;
    private static final Dotenv dotenv = Dotenv.configure()
            .filename("DB-config.env")
            .load();

    private static final String usuario = dotenv.get("DB_USERNAME");
    private static final String senha = dotenv.get("DB_PASSWORD");
    private static final String bd = dotenv.get("DB_DATABASE");
    private static final String host = dotenv.get("DB_HOST");
    private static final String porta = dotenv.get("DB_PORT");
    private static final String url = "jdbc:mysql://" + host + ":" + porta + "/" + bd;
    
    
    public Connection obterConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problema de conexão: " + e.toString());
        }
        return conectar;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getSenha() {
        return senha;
    }
    public static String getUrl() {
        return url;
    }
}
