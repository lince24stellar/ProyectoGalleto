/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.Dao;
import org.utl.idgs.conexion.ConexionDB;
import java.sql.*;
import java.text.SimpleDateFormat;
import org.apache.commons.codec.digest.DigestUtils;
import org.utl.idgs.CQRS.UserCQRS;
import org.utl.idgs.model.Users;


/**
 *
 * @author lluis
 */


public class UserDao {
    
  private final Connection conn;
    private final UserCQRS userCQRS; 

    public UserDao() throws SQLException {
        ConexionDB con = new ConexionDB();
        this.conn = con.open(); 
        this.userCQRS = new UserCQRS(); 
    }

    public Users log(Users user) throws SQLException {
        if (!userCQRS.validateUser(user)) {
            System.out.println("Usuario o contraseña no válidos.");
            return null;  
        }

        String sql = "SELECT * FROM users WHERE user = ? AND password = ?";
        Users usuarioResultante = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String fecha = new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
                    String token = user.getUsername() + "." + fecha;
                    String tokenizer = DigestUtils.sha512_256Hex(token);

                    System.out.println("Token generado: " + tokenizer);

                    String sqlToken = "UPDATE users SET lastToken = ? WHERE idUser = ?";
                    try (PreparedStatement stmtT = conn.prepareStatement(sqlToken)) {
                        stmtT.setString(1, tokenizer);
                        stmtT.setInt(2, rs.getInt("idUser"));
                        stmtT.executeUpdate();
                    }

                    usuarioResultante = fill(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al realizar la consulta.", e);
        }
        System.out.println("Usuario resultante: " + usuarioResultante);
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
