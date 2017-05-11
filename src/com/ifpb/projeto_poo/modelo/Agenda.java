/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Moreira Luna
 */
public class Agenda implements Serializable {
    
    private String nome;
    private List<Compromisso> compromissos;
    /**
     * Construtor que cria Agendas.
     * @param nome deve ser passado o um <code>String</code> nome para criar agendas.
     */
    public Agenda(String nome){
        this.nome = nome;
        this.compromissos = new ArrayList<>();
    }
    /**
     * Método que retorna o nome da agenda.
     * @return retorna um <code>String</code> com o nome da agenda.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Método que retorna a lista de compromissos da agenda.
     * @return retorna um <code>List</code> com os compromissos da agenda.
     */
    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
    /**
     * Método que cria compromissos na agenda.
     * @param compromisso deve ser passado um <code>Compromisso</code> como parâmetro.
     * @return retorna um <code>boolean</code> informando se o compromisso foi criado ou não.
     */
    public boolean create(Compromisso compromisso){
        for(Compromisso c : compromissos){
            if(c.getDataHora().equals(compromisso.getDataHora())){
                return false;
            }
        }
        return compromissos.add(compromisso);
    }
    /**
     * Método que pesquisa um compromisso da agenda a partir do conjunto de data e hora.
     * @param dataHora deve ser passado um <code>LocalDateTime</code> com hora e data.
     * @return retorna um <code>Compromisso</code>.
     */
    public Compromisso read(LocalDateTime dataHora){
        for(Compromisso c : compromissos){
            if(dataHora.equals(c.getDataHora())){
                return c;
            }
        }
        return null;
    }
    /**
     * Método que atualiza o compromisso na agenda.
     * @param compromisso deve ser passado um <code>Compromisso</code> como parâmetro.
     * @return retorna um <code>boolean</code> informando se o compromisso foi atualizado ou não.
     */
    public boolean update(Compromisso compromisso){
        for(int i = 0; i < compromissos.size(); i ++){
            if(compromisso.getDataHora().equals(compromissos.get(i).getDataHora())){
                compromissos.set(i, compromisso);
                return true;
            }
        }
        return false;
    }
    /**
     * Método que deleta o compromisso da agenda.
     * @param compromisso deve ser passado um <code>Compromisso</code> como parâmetro.
     * @return retorna um <code>boolean</code> informando se o compromisso foi deletado ou não.
     */
    public boolean delete(Compromisso compromisso){
        for(int i = 0; i < compromissos.size(); i ++){
            if(compromissos.get(i).getDataHora().equals(compromisso.getDataHora())){
                compromissos.remove(i);
                return true;
            }
        }
        return false;
    }
}
