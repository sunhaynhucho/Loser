<%-- 
    Document   : updateprocesswork
    Created on : Feb 5, 2015, 10:23:15 AM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.OmapConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String process = request.getParameter("p");
    String wid = request.getParameter("wid");
%>
<input type="hidden" value="<%=wid%>" id="hidWid" />
<table style="height: 200px;width: 100%;">
    <tr style="height: 50px;">
        <td align="center">
            <input class="easyui-slider" id="ruleProcess" style="width:300px" data-options="
                   showTip:true,
                   rule: [0,'|',25,'|',50,'|',75,'|',100]
                   " value="<%=process%>" />
        </td>

    </tr>
    <tr>
        <td style="text-align: center;">
            <a href="#" class="easyui-linkbutton" onclick="submitUpdateProcess()" iconCls="icon-ok">Ghi lại</a>
            <a href="#" class="easyui-linkbutton" onclick="javascript:$('#divProcess').dialog('close')" iconCls="icon-cancel">Đóng</a>
        </td>
    </tr>
</table>

