/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import org.utl.idgs.controller.ControllerUsers;
import org.utl.idgs.model.Users;

/**
 *
 * @author lluis
 */

@Path("login")
public class RESTSaludo {

       @Path("consumes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response login(Users usuario) {
        ControllerUsers loginController = new ControllerUsers();
        try {
            Users user = loginController.login(usuario); // Llamada al controlador
            Gson gson = new Gson();
            String json = gson.toJson(user);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error de servidor: " + e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Error de entrada: " + e.getMessage()).build();
        }
    }
    


}
