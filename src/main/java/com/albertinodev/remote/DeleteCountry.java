package com.albertinodev.remote;

import com.albertinodev.dao.CountryDAO;
import com.albertinodev.services.Constants;
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
@WebServlet("/delete-country")
public class DeleteCountry extends HttpServlet {


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Preparando o Escritor da resposta
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Adicionando controladores de requisição permitindo que todos possam requisitar
        response.setHeader("Access-Control-Allow-Origin", Constants.ALLOW_ALL);
        response.setHeader("Access-Control-Allow-Methods", "DELETE");
        

        // Simplificação do objecto request
        HttpServletRequest R = request;
        
        // A mensagem a mostrar caso algum campo venha vázio
        String information = "";
        String jsonBody = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining("\n"));
        if (jsonBody == null || jsonBody.trim().length() == 0) {
            // Resposta ao cliente caso haja um campo vazio
            JSONObject finalResponse = new JSONObject();
            finalResponse.put("mensagem", "Obrigatório mandar o id do pais que pretendi eliminar");
            out.print(finalResponse.toString());
            out.flush();
        } else {
            
            JSONObject finalObject = new JSONObject(jsonBody);
            
            // Lista de campos que predendi actualizar
            String id = finalObject.get("id").toString();

            // Certificanto-se de todos os campos estarem preenchidos
            if (id.length() == 0) {
                information = "Obrigatório mandar o id do pais que pretendi eliminar";
            }

            if (information.length() > 0) {
                // Resposta ao cliente caso haja um campo vazio
                JSONObject finalResponse = new JSONObject();
                finalResponse.put("mensagem", information);
                out.print(finalResponse.toString());
                out.flush();
            } else {
                boolean wasDeleted = new CountryDAO().deleteCountry(id);
                if (wasDeleted) {
                    // Resposta de confirmação
                    JSONObject finalResponse = new JSONObject();
                    finalResponse.put("mensagem", "País eliminado com sucesso com sucesso.");
                    out.print(finalResponse.toString());
                    out.flush();
                } else {
                    // Resposta de erro caso ocorra um erro ao actualizar
                    JSONObject finalResponse = new JSONObject();
                    finalResponse.put("mensagem", "Ocorreu um erro ao eliminar o pais.");
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
