/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.controle;

import com.ifpb.projeto_poo.banco.ConFactory;
import com.ifpb.projeto_poo.excecoes.DataHoraException;
import com.ifpb.projeto_poo.excecoes.DataInvalidaException;
import com.ifpb.projeto_poo.excecoes.EmailInvalidoException;
import com.ifpb.projeto_poo.excecoes.NomeInvalidoException;
import com.ifpb.projeto_poo.excecoes.SenhaInvalidaException;
import com.ifpb.projeto_poo.modelo.Agenda;
import com.ifpb.projeto_poo.modelo.Compromisso;
import com.ifpb.projeto_poo.modelo.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Moreira Luna
 */
public class UsuarioDaoBanco implements UsuarioDao{

    @Override
    public boolean create(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (nome, dataNascimento, sexo, email, senha) VALUES(?,?,?,?,?)");
        stmt.setString(1, usuario.getNome());
        stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
        stmt.setString(3, usuario.getSexo());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getSenha());
        boolean retorno =  stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    @Override
    public Usuario read(String email) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        
        List<Usuario> usuarios = null;
        try {
            usuarios = list();
        } catch (DataInvalidaException ex) {
            Logger.getLogger(UsuarioDaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmailInvalidoException ex) {
            Logger.getLogger(UsuarioDaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SenhaInvalidaException ex) {
            Logger.getLogger(UsuarioDaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NomeInvalidoException ex) {
            Logger.getLogger(UsuarioDaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataHoraException ex) {
            Logger.getLogger(UsuarioDaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
        
        
        
    }

    @Override
    public boolean update(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConection();
        PreparedStatement stmt = con.prepareStatement("UPDATE usuario SET (nome, dataNascimento, sexo, senha) = (?,?,?,?) WHERE email = ?");
        stmt.setString(1, usuario.getNome());
        stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
        stmt.setString(3, usuario.getSexo());
        stmt.setString(4, usuario.getSenha());
        stmt.setString(5, usuario.getEmail());
        stmt.executeUpdate();
        PreparedStatement stmtAgenda = con.prepareStatement("DELETE FROM agenda WHERE emailUsuario = ?");
        stmtAgenda.setString(1, usuario.getEmail());
        stmtAgenda.executeUpdate();
        PreparedStatement stmtCompromisso = con.prepareStatement("DELETE FROM compromisso WHERE emailUsuario = ?");
        stmtCompromisso.setString(1, usuario.getEmail());
        stmtCompromisso.executeUpdate();
        for(int i = 0; i < usuario.getAgendas().size(); i++){
            PreparedStatement stmtInsAgenda = con.prepareStatement("INSERT INTO agenda (nome, emailUsuario) VALUES(?,?)");
            stmtInsAgenda.setString(1, usuario.getAgendas().get(i).getNome());
            stmtInsAgenda.setString(2, usuario.getEmail());
            stmtInsAgenda.executeUpdate();
            for(int j = 0; j < usuario.getAgendas().get(i).getCompromissos().size(); j++){
                PreparedStatement stmtInsCompromisso = con.prepareStatement("INSERT INTO compromisso (dataHora, descricao, local, emailUsuario) VALUES(?,?,?,?)");
                stmtInsCompromisso.setTimestamp(1, Timestamp.valueOf(usuario.getAgendas().get(i).getCompromissos().get(j).getDataHora()));
                stmtInsCompromisso.setString(2, usuario.getAgendas().get(i).getCompromissos().get(j).getDescricao());
                stmtInsCompromisso.setString(3, usuario.getAgendas().get(i).getCompromissos().get(j).getLocal());
                stmtInsCompromisso.setString(4, usuario.getEmail());
                stmtInsCompromisso.executeUpdate();
            }
        }
        boolean retorno =  stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public boolean delete(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConection();
        PreparedStatement stmt = con.prepareStatement("DELETE FROM usuario WHERE email = ?");
        stmt.setString(1, usuario.getEmail());
        stmt = con.prepareStatement("DELETE FROM agenda WHERE emailUsuario = ?");
        stmt.setString(2, usuario.getEmail());
        stmt = con.prepareStatement("DELETE FROM compromisso WHERE emailUsuario = ?");
        stmt.setString(3, usuario.getEmail());
        boolean retorno =  stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    
    public List<Usuario> list() throws ClassNotFoundException, SQLException, DataInvalidaException, EmailInvalidoException, SenhaInvalidaException, NomeInvalidoException, DataHoraException{
        
        Connection con = ConFactory.getConection();
        
        PreparedStatement stmtUsuario = con.prepareStatement("SELECT * FROM usuario");
        ResultSet usuariosBanco = stmtUsuario.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();
        
        while(usuariosBanco.next()){
            Usuario usuario = new Usuario(usuariosBanco.getString("nome"), usuariosBanco.getDate("dataNascimento").toLocalDate(), usuariosBanco.getString("sexo"), usuariosBanco.getString("email"), usuariosBanco.getString("senha"));
            
            PreparedStatement stmtAgenda = con.prepareStatement("SELECT * FROM agenda WHERE emailUsuario = ?");
            stmtAgenda.setString(1, usuario.getEmail());
            ResultSet agendaBanco = stmtAgenda.executeQuery();
            while(agendaBanco.next()){
                Agenda agenda = new Agenda(agendaBanco.getString("nome"));
                PreparedStatement stmtCompromisso = con.prepareStatement("SELECT * FROM compromisso WHERE emailUsuario = ? AND nomeAgenda = ?");
                stmtCompromisso.setString(1, usuario.getEmail());
                stmtCompromisso.setString(2, agenda.getNome());
                ResultSet compromissoBanco = stmtCompromisso.executeQuery();
                while(compromissoBanco.next()){
                    Compromisso compromisso = new Compromisso(compromissoBanco.getTimestamp("dataHora").toLocalDateTime(), compromissoBanco.getString("descricao"), compromissoBanco.getString("local"));
                    agenda.create(compromisso);
                }
                usuario.create(agenda);
            }
            usuarios.add(usuario);
        }
        con.close();
        return usuarios;
    }
}
