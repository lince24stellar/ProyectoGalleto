/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.CQRS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.utl.idgs.model.Users;
import java.sql.*;
import org.utl.idgs.conexion.ConexionDB;

/**
 *
 * @author lluis
 */
public class UserCQRS {
    
    private final Connection conn;

    public UserCQRS() throws SQLException {
        ConexionDB con = new ConexionDB();
        this.conn = con.open(); 
    }
    
    public boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return false;
        }
        
        if (username.length() < 3 || username.length() > 20) {
            System.out.println("El nombre de usuario debe tener entre 3 y 20 caracteres.");
            return false;
        }
        
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        
        if (!matcher.matches()) {
            System.out.println("El nombre de usuario solo puede contener letras, números y guiones bajos.");
            return false;
        }

        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            System.out.println("La contraseña no puede estar vacía.");
            return false;
        }

        if (password.length() < 4) {
            System.out.println("La contraseña debe tener al menos 4 caracteres.");
            return false;
        }

     

        return true;
    }

    // Método para validar usuario y contraseña
    public boolean validateUser(Users user) {
        if (!validateUsername(user.getUsername())) {
            return false;
        }
        
        if (!validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }

    // Método para iniciar sesión con validaciones
    public Users log(Users user) throws SQLException {
        if (!validateUser(user)) {
            System.out.println("Usuario o contraseña no válidos.");
            return null;  // No proceder si las validaciones fallan
        }

        // Si las validaciones son correctas, entonces realizamos el inicio de sesión (consulta a la base de datos)
        String sql = "SELECT * FROM users WHERE user = ? AND password = ?";
        Users usuarioResultante = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Llamar al método para llenar el objeto usuario
                    usuarioResultante = fill(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al realizar la consulta.", e);
        }

        return usuarioResultante;
    }

    private static Users fill(ResultSet rs) throws SQLException {
        return new Users(
            rs.getInt("idUser"),
            rs.getString("user"),
            rs.getString("password"),
            rs.getBoolean("permiso"),
            rs.getString("lastToken")
        );
    }
    
}
