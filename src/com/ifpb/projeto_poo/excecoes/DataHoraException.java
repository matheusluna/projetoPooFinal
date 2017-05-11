/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.excecoes;

/**
 *
 * @author Matheus Moreira Luna
 */
public class DataHoraException extends Exception{
    /**
     * Exceção que trata os conjuntos de data e hora que forem consideradas inválidas.
     * @param msg deve ser passado a menssagem que deve ser apresentada se houver a exceção.
     */
    public DataHoraException(String msg){
        super(msg);
    }
}
