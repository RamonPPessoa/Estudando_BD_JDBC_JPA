package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args) {

        String urlConection = "jdbc:postgresql://localhost/digital_innovation_one";


        try {Connection conn = DriverManager.getConnection(urlConection, "postgres", "110747");

            System.out.println("Sucesso");
        } catch (Exception e) {
            System.out.println("Erro");

        }
    }
}