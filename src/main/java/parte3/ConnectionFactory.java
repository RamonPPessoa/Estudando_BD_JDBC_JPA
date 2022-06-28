package parte3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    //Construtor sendo declarado como privado para com isso evitar de  criar instâncias de fábrica.
    private ConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {

        // sempre importante baixar o drive do banco de dados que vai ser utilizado

        // 1 - Declararando objeto conexão (irá receber uma conexão após executar as etapas abaixo)
        Connection connection = null;

        // 2 - Carregando arquivo de propriedades para pegar os parâmetros necessários para se comunicar com o banco de dados
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

            // 3 - Definir parâmetros para se conectar ao banco de dados MySQL.
            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            // 4 - Construção  string de conexão.
            StringBuilder sb = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName);

            String connectionUrl = sb.toString(); //sb.toString() == "jdbc:postgresql://localhost/digital_innovation_one"

            // 5 - Criando conexão usando o DriverManager, passando como parâmetros a string de conexão, usuário e senha do usuário.
            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e) {
                System.out.println("Erro ao tentar criar conexão");
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("Erro ao tentar carregar aquivos de propriedades");
            e.printStackTrace();
        }

        return connection;
    }
}