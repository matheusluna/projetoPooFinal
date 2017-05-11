/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus Moreira Luna
 */
public class ConFactory {
    /**
     * Contrutor que implementa a conexão com o banco de dados.
     * @return retorna um <code>Connection</code>.
     * @throws ClassNotFoundException Exceção que trata a classe não existente.
     * @throws SQLException Exceção que trata as falhas de conexão.
     */
    public static Connection getConection() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/ProjetoPoo";
        String usuario = "postgres";
        String senha = "123456";
        
        return DriverManager.getConnection(url, usuario, senha);
    }
}
