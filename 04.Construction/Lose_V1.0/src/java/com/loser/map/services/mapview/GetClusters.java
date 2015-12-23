package com.loser.map.services.mapview;

import com.loser.map.model.Cluster;
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
public class GetClusters extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LocationGetByAll.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            float minlat = Float.parseFloat(request.getParameter("minlat"));
            float maxlat = Float.parseFloat(request.getParameter("maxlat"));
            float minlon = Float.parseFloat(request.getParameter("minlon"));
            float maxlon = Float.parseFloat(request.getParameter("maxlon"));
            int row = Integer.parseInt(request.getParameter("row"));
            int column = Integer.parseInt(request.getParameter("column"));
            List<Cluster> clusters = SqlMapView.getCluster(-93, 90, -180, 180, row, column);
            List<Cluster> innerClusters = new LinkedList<>();
            List<Cluster> outerClusters = new LinkedList<>();
            clusters.stream().forEach((cluster) -> {
                if (cluster.contains(minlat, maxlat, minlon, maxlon)) {
                    innerClusters.add(cluster);
                } else {
                    outerClusters.add(cluster);
                }
            });
            JSONArray jsonArr = new JSONArray();
            jsonArr.put(innerClusters);
            jsonArr.put(outerClusters);
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
