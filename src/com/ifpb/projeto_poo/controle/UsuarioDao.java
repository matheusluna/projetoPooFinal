/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.controle;

import com.ifpb.projeto_poo.modelo.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Matheus Moreira Luna
 */
public interface UsuarioDao {
    /**
     * método que cria e salva usuários tanto no banco quanto no arquivo.
     * @param usuario deve ser passado um <code>Usuario</code> com parâmetro.
     * @return retorna um <code>boolean</code> informando se o usuário foi crado ou não.
     * @throws IOException Exceção que trata as falhas de comunicação com arquivo.
     * @throws FileNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws ClassNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws SQLException Exceção que trata as falhas de comunicação com banco.
     */
    public boolean create(Usuario usuario)throws IOException, FileNotFoundException, ClassNotFoundException, SQLException;
    /**
     * método que lê se existe o usuário a partir do email e retorna o objeto <code>Usuario</code>.
     * @param email parâmetor que deve ser passado para realizar a pesquisa.
     * @return retorna um <code>Usuario</code> com as informações do usuário. Se não houver usuário será retornado <code>null</code>;
     * @throws IOException Exceção que trata as falhas de comunicação com arquivo.
     * @throws FileNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws ClassNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws SQLException Exceção que trata as falhas de comunicação com banco.
     */
    public Usuario read(String email) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException;
    /**
     * método que atualiza as informações do usuário.
     * @param usuario deve ser passado um <code>Usuario</code> como parâmetro.
     * @return retorna um <code>boolean</code> informando se o usuário foi atualizado ou não.
     * @throws IOException Exceção que trata as falhas de comunicação com arquivo.
     * @throws FileNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws ClassNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws SQLException Exceção que trata as falhas de comunicação com banco.
     */
    public boolean update(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException;
    /**
     * método que deleta o usuário que for passado.
     * @param usuario deve ser passado um <code>Usuario</code> como parâmetro.
     * @return retorna um <code>boolean</code> informando se o usuário foi deletado ou não.
     * @throws IOException Exceção que trata as falhas de comunicação com arquivo.
     * @throws FileNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws ClassNotFoundException Exceção que trata as falhas de comunicação com arquivo.
     * @throws SQLException Exceção que trata as falhas de comunicação com banco. 
     */
    public boolean delete(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException;
}
