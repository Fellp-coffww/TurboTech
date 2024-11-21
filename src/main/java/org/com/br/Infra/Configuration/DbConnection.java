package org.com.br.Infra.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection conexao = null;
    private DbConnection(){}
    public static Connection getConnection() throws Exception{
        try{
            if(conexao == null){
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user = "postgres";
                String password = "aluno";

                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
            }

        }
        catch(ClassNotFoundException erro){
            throw new Exception("Drive: "+erro.getMessage());
        }
        catch(SQLException erro){
            throw new Exception("Banco: " + erro.getMessage());
        }
        return conexao;

    }


}
