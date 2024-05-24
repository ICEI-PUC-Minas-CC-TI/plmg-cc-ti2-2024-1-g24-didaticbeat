package dao;

import java.sql.*;

public class DAO {
    protected Connection conexao;
    
    public DAO() {
        conexao = null;
    }
    
    public boolean conectar() {
        String driverName = "org.postgresql.Driver";                    
        // Modifique as informações de conexão para o PostgreSQL no Azure
        String serverName = "didaticbeat.postgres.database.azure.com";
        String mydatabase = "postgres";
        int porta = 5432; // A porta padrão para o PostgreSQL é 5432, mas no Azure pode ser diferente
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "didaticbeat"; // Substitua pelo seu nome de usuário no Azure
        String password = "Equipe24"; // Substitua pela sua senha no Azure
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null); // Verifica se a conexão foi bem-sucedida
            System.out.println("Conexão efetuada com o PostgreSQL no Azure!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o PostgreSQL no Azure -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL no Azure -- " + e.getMessage());
        }

        return status;
    }
    
    public boolean close() {
        boolean status = false;
        
        try {
            if (conexao != null)
                conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}
