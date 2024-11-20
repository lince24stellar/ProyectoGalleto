/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.utl.idgs.conexion.ConexionDB;
import org.utl.idgs.model.Users;
import org.apache.commons.codec.digest.DigestUtils;
import org.utl.idgs.Dao.UserDao;

/**
 *
 * @author lluis
 */

public class ControllerUsers {
    public Users login(Users usuario) throws SQLException {
        UserDao dao = new UserDao();
        return dao.log(usuario);
    }
   

}
