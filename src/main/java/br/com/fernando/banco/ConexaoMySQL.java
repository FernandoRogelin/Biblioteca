package br.com.fernando.banco;
//Classes necessárias para uso de Banco de dados //
        import java.sql.Connection;

        import java.sql.DriverManager;

        import java.sql.SQLException;

//Início da classe de conexão//

public class ConexaoMySQL {

    public static String status = "Não conectou...";

    public ConexaoMySQL() {

    }

    //Método de Conexão//

    public static java.sql.Connection getConexaoMySQL() {

        Connection connection = null;
        try {

            // Carregando o JDBC Driver padrão

            String driverName = "com.mysql.jdbc.Driver";

            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//

            String serverName = "localhost";
            String mydatabase = "Biblioteca?usessl=false";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";
            String password = "admin";

            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
}
