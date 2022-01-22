package com.albertinodev.remote;

import com.albertinodev.dao.CountryDAO;
import com.albertinodev.services.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Albertino Augusto
 */
@WebServlet(name = "ShowCountries", urlPatterns = {"/get-countries"})

//@WebServlet("/get-countries")
public class ShowCountries extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Preparando o Escritor da resposta
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        
        // Adicionando controladores de requisição permitindo que todos possam requisitar
        response.setHeader("Access-Control-Allow-Origin", Constants.ALLOW_ALL);
        response.setHeader("Access-Control-Allow-Methods", "GET");
        
        
        // Simplificação do objecto request
        HttpServletRequest R = request;
        String order = R.getParameter("order") != null ? R.getParameter("order") : "";
        
        if (order.equals("name") == false && order.equals("capital") == false && order.equals("region") == false && order.equals("subRegion") == false && order.equals("area") == false) {
            // Pega todos paises
            List coutries = new CountryDAO().getCountriesList();
            if (coutries.isEmpty()) {
               JSONObject someResponse = new JSONObject();
               someResponse.put("mensagem", "Ainda sem países adicionados na base dados");
               out.print(someResponse.toString());
               out.flush();
            } else {
                JSONArray array = new JSONArray(coutries);
                //finalResponse.put("mensagem", information);
                out.print(array.toString());
                out.flush();
            }
        } else {
            System.out.println("Data: " + order);
            
            // Pegas todos os paises pela order escolhida pelo cliente
            List coutries = new CountryDAO().getOrderedCountriesList(order);
            if (coutries.isEmpty()) {
               JSONObject someResponse = new JSONObject();
               someResponse.put("mensagem", "Sem resultados encontrados");
               out.print(someResponse.toString());
               out.flush();
            } else {
                JSONArray array = new JSONArray(coutries);
                //finalResponse.put("mensagem", information);
                out.print(array.toString());
                out.flush();
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
