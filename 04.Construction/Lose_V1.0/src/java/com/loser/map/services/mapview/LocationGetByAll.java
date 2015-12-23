package com.loser.map.services.mapview;

import com.loser.map.model.Location;
import com.loser.map.sql.SqlMapView;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author VinhNV
 */
public class LocationGetByAll extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LocationGetByAll.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            double minlat = Double.parseDouble(request.getParameter("minlat"));
            double maxlat = Double.parseDouble(request.getParameter("maxlat"));
            double minlon = Double.parseDouble(request.getParameter("minlon"));
            double maxlon = Double.parseDouble(request.getParameter("maxlon"));
            List<Location> locations = SqlMapView.locationGetByAll();
            List<Location> innerPlace = new LinkedList<>();
            List<Location> outerPlace = new LinkedList<>();
            locations.stream().forEach((Location place) -> {
                if (place.contains(minlat, maxlat, minlon, maxlon)) {
                    innerPlace.add(place);
                } else {
                    outerPlace.add(place);
                }
            });
            JSONArray jsonArr = new JSONArray();
            jsonArr.put(innerPlace);
            jsonArr.put(outerPlace);
            out.print(jsonArr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
