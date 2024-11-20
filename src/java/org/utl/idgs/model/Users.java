/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.model;

/**
 *
 * @author lluis
 */
public class Users {
    
    private int idUser;
    private String username;
    private String password;
    private boolean permiso;
    private String lastToken;

    public Users(String username, String password, boolean permiso) {
        this.username = username;
        this.password = password;
        this.permiso = permiso;
    }

    
    public Users(int idUser, String username, String password, boolean permiso, String lastToken) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.permiso = permiso;
        this.lastToken = lastToken;
    }
    
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users() {
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getPermiso() {
        return permiso;
    }

    public void setPermiso(boolean permiso) {
        this.permiso = permiso;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String lastToken) {
        this.lastToken = lastToken;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUser + ", username=" + username + ", password=" + password + ", permiso=" + permiso + '}';
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}