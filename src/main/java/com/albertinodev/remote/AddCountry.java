package com.albertinodev.remote;

import com.albertinodev.dao.CountryDAO;
import com.albertinodev.model.Country;
import com.albertinodev.services.Constants;
import com.albertinodev.services.Generator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Albertino Augusto
 */
@WebServlet("/add-country")
public class AddCountry extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Preparando o Escritor da resposta
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Adicionando controladores de requisição permitindo que todos possam requisitar
        response.setHeader("Access-Control-Allow-Origin", Constants.ALLOW_ALL);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        

        // Simplificação do objecto request
        HttpServletRequest R = request;
        
        // A mensagem a mostrar caso algum campo venha vázio
        String information = "";
        
        Country country = null;
        String jsonBody = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining("\n"));
        if (jsonBody == null || jsonBody.trim().length() == 0) {
            // Resposta ao cliente caso haja um campo vazio
            JSONObject finalResponse = new JSONObject();
            finalResponse.put("mensagem", "O corpo da requisição esta vázio");
            out.print(finalResponse.toString());
            out.flush();
        } else {
            
            JSONObject finalObject = new JSONObject(jsonBody);
            
            // ID gerado automaticamente
            String generatedId = new Generator().generateCustomId();
            country = new Country(generatedId, 
                    finalObject.has("name") == false ? "" : finalObject.get("name").toString(), 
                    finalObject.has("capital") == false ? "" : finalObject.get("capital").toString(), 
                    finalObject.has("region") == false ? "" : finalObject.get("region").toString(), 
                    finalObject.has("subRegion") == false ? "" : finalObject.get("subRegion").toString(), 
                    finalObject.has("area") == false ? "" : finalObject.get("area").toString());          
        
            
            // Certificanto-se de todos os campos estarem preenchidos
            if (country.getName().length() == 0) {
                information = "Por favor informe o Nome";
            }

            if (country.getCapital().length() == 0) {
                information = "Por favor informe a Capital";
            }

            if (country.getRegion().length() == 0) {
                information = "Por favor informe a Região";
            }

            if (country.getSubRegion().length() == 0) {
                information = "Por favor informe a Sub-Região";
            }

            if (country.getArea().length() == 0) {
                information = "Por favor informe a Área";
            }  
        
            
            if (information.length() > 0) {
                // Resposta ao cliente caso haja um campo vazio
                JSONObject finalResponse = new JSONObject();
                finalResponse.put("mensagem", information);
                out.print(finalResponse.toString());
                out.flush();
            } else {
                boolean wasSaved = new CountryDAO().saveCountry(country);
                if (wasSaved) {
                    // Resposta de confirmação para o cliente
                    JSONObject finalResponse = new JSONObject();
                    finalResponse.put("mensagem", "País adicionado com sucesso.");
                    out.print(finalResponse.toString());
                    out.flush();
                } else {
                    // Resposta de erro caso um erro ao gravar
                    JSONObject finalResponse = new JSONObject();
                    finalResponse.put("mensagem", "Ocorreu um erro ao gravar o país.");
                    out.print(finalResponse.toString());
                    out.flush();
                }
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
