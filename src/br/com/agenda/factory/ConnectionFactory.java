package br.com.agenda.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    static Config config;

    static {
        try {
            config = new Config("project.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String USERNAME = config.getProperty("DB_USERNAME");
    private static final String PASSWORD = config.getProperty("DB_PASSWORD");
    private static final String DATABASE_URL = config.getProperty("DB_URL");

    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();
        if (con != null) {
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        }
    }
}
