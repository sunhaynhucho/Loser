<%-- 
    Document   : addpage
    Created on : Jan 21, 2013, 11:46:12 AM
    Author     : KiemPQ-PC
--%>

<%@page import="com.elcom.omap.quantrihethong.PageLogic"%>
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
    List listPages = PageLogic.getPages();
%>

    <div id="message"></div>
    <div style="padding:10px 0 10px 60px">  
        <form id="ff" method="post" accept-charset="UTF-8">  
            <table>  
                <tr>  
                    <td>Tên trang:</td>  
                    <td><input class="easyui-validatebox" type="text" id="name" name="name" maxlength="50" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>Link:</td>  
                    <td><input class="easyui-validatebox" type="text" id="friendlyurl" name="friendlyurl" maxlength="100" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>Trang gốc:</td>  
                    <td>  
                        <select name="parent" id="parent">
                            <option value="0">Trang gốc</option>
                            <% if (listPages != null && listPages.size() > 0) {%>
                            <% for (Object o : listPages) {%>
                            <% Object[] obj = (Object[]) o;%>
                            <option value="<%=obj[0]%>"><%=obj[1]%></option>
                            <%}%>
                            <%}%>
                        </select>  
                    </td>  
                </tr>  
                <tr>  
                    <td>Kiểu trang:</td>  
                    <td>  
                        <select name="pagetype" id="pagetype">
                            <option value="1">Menu trang chủ</option>
                            <option value="2">Menu trang Administrator</option>
                            <option value="3">Trang thường</option>
                        </select>  
                    </td>  
                </tr>
                <tr>  
                    <td>Miêu tả:</td>  
                    <td><textarea name="descrition" id ="descrition" style="height:60px;" maxlength="200"></textarea></td>  
                </tr>  

            </table>  
        </form>  
    </div>  
    <div style="text-align:center;padding:5px">  
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Đồng ý</a>  
    </div>  
<script>  
    
</script>  