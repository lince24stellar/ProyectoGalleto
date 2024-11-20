/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author lluis
 */
public class ConexionDB {

    private Connection connection;

    public Connection open() {
        String user = "root";
        String password = "1234";
        final String url = "jdbc:mysql://localhost:3306/dongalleto?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8";

        try {
            // Asegurarte de que el driver de MySQL esté disponible
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer conexión
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Excepción controlada al cerrar la conexión.");
            }
        }
    }


    
}
