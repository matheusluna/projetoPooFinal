/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matheus Moreira Luna
 */
public class CompromissoTableModel extends AbstractTableModel {
    private List<Compromisso> listaCompromissos = new ArrayList<>();
    private String[] colunas  = {"Data", "Hora", "Descrição", "Local"};
    
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return listaCompromissos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listaCompromissos.get(rowIndex).getDataHora().toLocalDate();
            case 1:
                return listaCompromissos.get(rowIndex).getDataHora().toLocalTime();
            case 2:
                return listaCompromissos.get(rowIndex).getDescricao();
            case 3:
                return listaCompromissos.get(rowIndex).getLocal();
        }
        return null;
    }
    
    public void addRow(Compromisso compromisso){
        Collections.sort (listaCompromissos, new Comparator() {
            public int compare(Object o1, Object o2) {
                Compromisso p1 = (Compromisso) o1;
                Compromisso p2 = (Compromisso) o2;
                return p1.getDataHora().isBefore(p2.getDataHora()) ? -1 : (p2.getDataHora().isBefore(p1.getDataHora()) ? +1 : 0);
            }
        });
        this.listaCompromissos.add(compromisso);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int linha){
        this.listaCompromissos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
}
