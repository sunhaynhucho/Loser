<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.quantrihethong.PageLogic"%>
<%@page import="sun.awt.SunHints.Value"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<style >
    .messageok {
        border:1px solid green; 
        border-radius:5px;
        color: green;
    }

    .messageerr{
        border:1px solid red; 
        border-radius:5px;
        color: red;
    }
</style>

<%
    String pageId = "-1";
    if (request.getParameter("pageId") != null) {
        pageId = (String)request.getParameter("pageId");
    }
    List listPages = PageLogic.getPages();
    Object pageObj = null;
    for (Object o : listPages) {
        Object[] obj = (Object[]) o;
        String strPage = String.valueOf(obj[0]);
        if (pageId.equals(strPage)) {
            pageObj = o;
            break;
        }
    }
    if (pageObj == null) {
        return;
    }

    Object[] pageO = (Object[]) pageObj;
    
%>

    <div id="message"></div>
    <div style="padding:10px 0 10px 60px">  
        <form id="ffEdit" method="post" accept-charset="UTF-8">  
            <table> 
                <tr>  
                    <td>Định danh:</td>  
                    <td><input class="easyui-validatebox" type="text" id="editpageId" name="editpageId" disabled data-options="required:true" value="<%=pageO[0]%>"></input></td>  
                </tr>
                <tr>  
                    <td>Tên trang:</td>  
                    <td><input class="easyui-validatebox" type="text" id="editname" maxlength="50" name="editname" data-options="required:true" value="<%=pageO[1]%>"></input></td>  
                </tr>  
                <tr>  
                    <td>Link:</td>  
                    <td><input class="easyui-validatebox" type="text" id="editfriendlyurl" maxlength="100" name="editfriendlyurl" data-options="required:true" value="<%=pageO[2]%>"></input></td>  
                </tr>  
                <tr>  
                    <td>Trang gốc:</td>  
                    <td>  
                        <select name="editparent" id="editparent">
                            <option value="0">Trang gốc</option>
                            <% if (listPages != null && listPages.size() > 0) {%>
                            <% for (Object o : listPages) {%>
                            <% Object[] obj = (Object[]) o;
                                String pageIdO = String.valueOf(obj[0]);
                                String parentId = String.valueOf(pageO[4]);
                            %>
                            <% if (pageIdO.equals(parentId)) {%>
                            <option value="<%=obj[0]%>" selected="selected" ><%=obj[1]%></option>
                            <%} else if(!pageId.equals(pageIdO)){%>
                            <option value="<%=obj[0]%>"><%=obj[1]%></option>
                            <%}%>
                            <%}%>
                            <%}%>
                        </select>  
                    </td>  
                </tr>
                <tr>  
                    <td>Kiểu trang:</td>  
                    <td>
                        <% String sel1 = "";
                            String sel2 = "";
                            String sel3 = "";
                            String valueO = String.valueOf(pageO[5]);
                            if(valueO.equals("3")){
                                sel3 = "selected=\"selected\"";
                            }else if(valueO.equals("2")){
                                sel2 = "selected=\"selected\"";
                            }else{
                                sel1 = "selected=\"selected\"";
                            }
                        %>
                        <select name="editpagetype" id="editpagetype">
                            <option value="1" <%=sel1%> >Menu trang chủ</option>
                            <option value="2" <%=sel2%> >Menu trang Administrator</option>
                            <option value="3" <%=sel3%> >Trang thường</option>
                        </select>  
                    </td>  
                </tr>
                <tr>  
                    <td>Miêu tả:</td> 
                    <% String strMieuTa = String.valueOf(pageO[3]);
                        if(strMieuTa.equals("null")){
                            strMieuTa = "";
                        }
                    %>
                    <td><textarea name="editdescrition" id ="editdescrition" maxlength="200" style="height:60px;" ><%=strMieuTa%></textarea></td>  
                </tr>  

            </table>  
        </form>  
    </div>  
    <div style="text-align:center;padding:5px">  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">Đồng ý</a>  
    </div>  
<script>  
    
</script>  