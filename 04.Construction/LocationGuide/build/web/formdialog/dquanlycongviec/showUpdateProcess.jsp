<%-- 
    Document   : updateprocesswork
    Created on : Feb 5, 2015, 10:23:15 AM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.OmapConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String wid = request.getParameter("wid");
%>
<input type="hidden" value="<%=wid%>" id="hidWid" />
<table style="height: 100px;width: 100%;">
    <tr>
        <td align="center">
            <select class="easyui-combobox" id="comboTiendo" name="state" style="width: 130px">
                <option value="0">Chưa thực hiện</option>
                <option value="2">Đang thực hiện</option>
                <option value="1">Hoàn thành</option>
            </select>
        </td>
    </tr>
    <tr>
        <td style="text-align: center;">
            <a href="#" class="easyui-linkbutton" id="submitUpdateProcess" onclick="submitUpdateProcess()" iconCls="icon-ok">Ghi lại</a>
            <a href="#" class="easyui-linkbutton" onclick="javascript:$('#dgABC').dialog('close')" iconCls="icon-cancel">Đóng</a>
        </td>
    </tr>
</table>

