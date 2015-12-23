package com.elcom.omap.quantrihethong;

import com.elcom.omap.common.Constant;
import com.elcom.omap.common.OmapHistoryLogic;
import com.elcom.omap.common.OmapSessionUtils;
import com.elcom.omap.login.OmapUser;
import com.elcom.omap.util.OmapUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadImageUserServices extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UploadImageUserServices.class);
    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String imageUrl = "0";
            String userName = OmapSessionUtils.getUserName(request);
            if (userName == null) {
                OmapUtils.showErrSessionTimeOut(out);
                return;
            }
            if (!ServletFileUpload.isMultipartContent(request)) {
                // if not, we stop here
                return;
            }

            // configures some settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(REQUEST_SIZE);

            // constructs the directory path to store upload file
            String uploadPath = getServletContext().getRealPath("") + File.separator + Constant.UPLOAD_DIRECTORY_USER;
            System.out.println(uploadPath);
            String filePath = "";
            String content = "";
            String fileUpload = request.getContextPath() + "/" + Constant.UPLOAD_WEB_USER;
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String title = "";
            FileItem filesave = null;
            File storeFile = null;
            try {
                // parses the request's content to extract file data
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();

                // iterates over form's fields

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        
                        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                        Date date = new Date();

                        String innerName = userName + dateFormat.format(date);
                        imageUrl = new File(item.getName()).getName();
                        imageUrl = innerName + imageUrl;
                        
                        if (imageUrl.length() > 0) {
                            filePath = uploadPath + imageUrl;
                            fileUpload += imageUrl;

                            storeFile = new File(filePath);
                            if (storeFile.exists()) {
                                JSONObject jObj = new JSONObject();
                                jObj.put("code", "1");
                                out.print(jObj);
                                out.flush();
                                return;
                            }
                            filesave = item;
                            if (filesave != null && storeFile != null) {
                                filesave.write(storeFile);

                                JSONObject jObj = new JSONObject();
                                jObj.put("code", "0");
                                jObj.put("fileName", imageUrl);

                                out.print(jObj);
                                out.flush();

                            }
                        }

                    }
                }

            } catch (Exception ex) {
                logger.error("UploadFile()==>", ex);
            }
        } finally {
            out.close();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
