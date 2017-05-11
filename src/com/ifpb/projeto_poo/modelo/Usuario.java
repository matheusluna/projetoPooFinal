/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.modelo;

import com.ifpb.projeto_poo.excecoes.DataInvalidaException;
import com.ifpb.projeto_poo.excecoes.EmailInvalidoException;
import com.ifpb.projeto_poo.excecoes.NomeInvalidoException;
import com.ifpb.projeto_poo.excecoes.SenhaInvalidaException;
import java.io.Serializable;
import java.time.LocalDate;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Matheus Moreira Luna
 */
public class Usuario implements Serializable {
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String email;
    private String senha;   
    private List<Agenda> agendas;
    /**
     * Construtor que cria novos usuários.
     * @param nome deve ser passado o nome do usuário.
     * @param dataNascimento deve ser passado a data de nascimento do usuário.
     * @param sexo deve ser passado o sexo do usuário.
     * @param email deve ser passado o email do usuário.
     * @param senha deve ser passado a senha do usuário.
     * @throws DataInvalidaException Exceção que trata as datas inválidas.
     * @throws EmailInvalidoException Exceção que trata os E-mails inválidos.
     * @throws SenhaInvalidaException Exceção que trata as senhas inválidas.
     * @throws NomeInvalidoException Exceção que trata os nomes inválidos.
     */
    public Usuario(String nome, LocalDate dataNascimento, String sexo, String email, String senha) throws DataInvalidaException, EmailInvalidoException, SenhaInvalidaException, NomeInvalidoException{
        if(nome.equals("") || nome == null) throw new NomeInvalidoException("O nome não pode ser nulo!");
        this.nome = nome;
        if(dataNascimento.isAfter(LocalDate.now())) throw new DataInvalidaException("Data invílida!");
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        if(email.equals("") || email == null) throw new EmailInvalidoException("O email não pode ser nulo!");
        this.email = email;
        if(senha.equals("") || senha == null) throw new SenhaInvalidaException("A senha não pode ser nula!");
        this.senha = senha;
        this.agendas = new ArrayList<>();
    }
    /**
     * Método que retorna o nome do usuário.
     * @return retorna uma <code>String</code> com o nome.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Método que retorna a data de nascimento do usuário.
     * @return retorna um <code>LocalDate</code> com a data.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    /**
     * Método que retorna o sexo do usuário.
     * @return retorna um <code>String</code> com o sexo.
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * Método que retorna o E-mail do usuário
     * @return retorna um <code>String</code> com o E-mail.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Método que retorna a senha do usuário.
     * @return retorna um <code>String</code> com a senha.
     */
    public String getSenha(){
        return senha;
    }
    /**
     * Método que retorna a lista de agendas do usuário.
     * @return retorna um <code>List</code> com as agendas.
     */
    public List<Agenda> getAgendas() {
        return agendas;
    }
    /**
     * Método que modifica o nome do usuário.
     * @param nome deve ser passado um <code>String</code> com o novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Método que modifica a data de nascimento do usuário.
     * @param dataNascimento deve ser passado um <code>LocalDate</code> com a nova data.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    /**
     * Método que modifica o sexo do usuário.
     * @param sexo deve ser passado um <code>String</code> com o novo sexo.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    /**
     * Método que autentica o usuário passando seu E-mail e senha. Esse método serve para o login.
     * @param emailVerifica deve ser passado um <code>String</code> com o E-mail.
     * @param senhaVerifica deve ser passado um <code>String</code> com a senha.
     * @return retorna <code>true</code> ou <code>false</code> que caracterisa se a senha e E-mail pertencem ao usuário.
     */
    public boolean autenticar(String emailVerifica, String senhaVerifica){
        if(emailVerifica.equals(email) && senhaVerifica.equals(senha)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Método que cria novas agendas para o usuário.
     * @param agenda deve ser passado um <code>Agenda</code> com as informações da nova agenda.
     * @return retorna um <code>boolean</code> que informa se a agenda foi criada ou não.
     */
    public boolean create(Agenda agenda){
        for(Agenda a : agendas){
            if(a.getNome().equals(agenda.getNome())){
                return false;
            }
        }
        return agendas.add(agenda);
    }
    /**
     * Método que pesquisa agendas a partir do nome.
     * @param nome deve ser passado um <code>String</code> com o nome da agenda
     * @return retorna uma <code>Agenda</code>.
     */
    public Agenda read(String nome){
        for(Agenda a : agendas){
            if(nome.equals(a.getNome())){
                return a;
            }
        }
        return null;
    }
    /**
     * Método que atualiza a agenda.
     * @param agenda deve ser passada a agenda que vai ser atualizada.
     * @return retorna um <code>boolean</code> que informa se a agenda foi atualizada ou não.
     */
    public boolean update(Agenda agenda){
        for(int i = 0; i < agendas.size(); i++){
            if(agendas.get(i).getNome().equals(agenda.getNome())){
                agendas.set(i, agenda);
                return true;
            }
        }
        return false; 
    }
    /**
     * Método que deleta a agenda do usuário.
     * @param agenda deve ser passada a agenda que vai ser deletada.
     * @return retorna um <code>boolean</code> que informa se a agenda foi deletada ou não.
     */
    public boolean delete(Agenda agenda){
        for(int i = 0; i < agendas.size(); i++){
            if(agendas.get(i).getNome().equals(agenda.getNome())){
                agendas.remove(i);
                return true;
            }
        }
        return false;
    }
}
