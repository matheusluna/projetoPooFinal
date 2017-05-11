/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.modelo;

import com.ifpb.projeto_poo.excecoes.DataHoraException;
import java.io.Serializable;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

/**
 *
 * @author Matheus Moreira Luna
 */
public class Compromisso implements Serializable {
    private LocalDateTime dataHora;
    private String descricao;
    private String local;
    /**
     * Construtor que cria Compromissos.
     * @param dataHora deve ser passado um <code>LocalDateTime</code> como parâmetro para hora e data do compromisso.
     * @param descricao deve ser passado um <code>String</code> como parâmetro para descrição do compromisso.
     * @param local deve ser passado um <code>String</code> como parâmetro para local do compromisso.
     * @throws DataHoraException Exceção que trata as datas e horas consideradas inválidas.
     */
    public Compromisso(LocalDateTime dataHora, String descricao, String local) throws DataHoraException{
        if(dataHora.isBefore(now())) throw new DataHoraException("A data e hora já passaram.");
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.local = local;
    }
    /**
     * Método que retorna o conjuto da data e hora do compromisso.
     * @return retorna um <code>LocalDateTime</code>.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    /**
     * Mátodo que altera o conjuto da data e hora do compromisso. 
     * @param dataHora deve ser passado o novo conjunto de data e hora.
     * @throws DataHoraException Exceção que trata os conjutos de datas e horas inválidas.
     */
    public void setDataHora(LocalDateTime dataHora) throws DataHoraException {
        if(dataHora.isBefore(now())) throw new DataHoraException("A data e hora já passaram.");
        this.dataHora = dataHora;
    }
    /**
     * Método que retorna a descrição do compromisso.
     * @return retorna um <code>String</code>.
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * Método que modifica a descrição do compromisso.
     * @param descricao deve ser passado a string com a nova descrição.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /**
     * Método que retorna o local do compromisso.
     * @return retorna um <code>String</code>.
     */
    public String getLocal() {
        return local;
    }
    /**
     * Método que modifica o local do compromisso.
     * @param local deve ser passado um <code>String</code> com o novo local.
     */
    public void setLocal(String local) {
        this.local = local;
    }
    
    
}
