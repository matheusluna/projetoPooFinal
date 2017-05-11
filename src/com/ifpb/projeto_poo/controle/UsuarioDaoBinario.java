/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.projeto_poo.controle;

import com.ifpb.projeto_poo.modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus Moreira Luna
 */
public class UsuarioDaoBinario implements UsuarioDao {
    private File arquivo;
    
    public UsuarioDaoBinario(){
        arquivo = new File("Usuarios.bin");
        
        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Falha na conexão com o arquivo", "Menssagem de erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Usuario> list() throws FileNotFoundException, IOException, ClassNotFoundException{
        if(arquivo.length() > 0){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivo));
            return (List<Usuario>) input.readObject();
        }else{
            return new ArrayList<>();
        }
    }
    
    private void atualizarArquivo(List<Usuario> usuarios) throws FileNotFoundException, IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
        output.writeObject(usuarios);
        output.close();
    }
    @Override
    public boolean create(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        for(Usuario u : usuarios){
            if(u.getEmail().equals(usuario.getEmail())){
                return false;
            }
        }
        usuarios.add(usuario);
        atualizarArquivo(usuarios);
        return true;
    }
    
    @Override
    public Usuario read(String email) throws IOException, FileNotFoundException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    
    @Override
    public boolean update(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(usuario.getEmail())){
                usuarios.set(i, usuario);
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean delete(Usuario usuario) throws IOException, FileNotFoundException, ClassNotFoundException{
        List<Usuario> usuarios = list();
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getEmail().equals(usuario.getEmail())){
                usuarios.remove(i);
                atualizarArquivo(usuarios);
                return true;
            }
        }
        return false;
    }   
}
