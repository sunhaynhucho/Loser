<%-- 
    Document   : frmguimail
    Created on : Mar 30, 2015, 1:40:48 PM
    Author     : KiemPQ
--%>

<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.kiemanh.vn.cauhinhgame.GameSchedureDataLogic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String scid = request.getParameter("scid");
    if(scid == null){
        scid = "-1";
    }
    List lSchedures = GameSchedureDataLogic.listSchedure(scid);
    Object[] sc = null;
    if (lSchedures != null && lSchedures.size() > 0) {
        sc = (Object[]) lSchedures.get(0);
    } else {
        sc = new Object[20];
    }
%>
<input type="hidden" id="mailId" value="<%=scid%>"/>
<div style="padding:10px 60px 20px 60px">
    <table cellpadding="5">
        <tr>
            <td>Gửi tới:</td>
            <td><input class="easyui-textbox" type="text" name="name" id="mailName"
                       value="<%=OmapUtils.parserObjectToString(sc[2])%>"
                       data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input class="easyui-textbox" type="text" name="email" id="mailAddress" 
                       value="<%=OmapUtils.parserObjectToString(sc[4])%>"
                       data-options="required:true,validType:'email'"></input></td>
        </tr>
        <tr>
            <td>Tiêu đề:</td>
            <td><input class="easyui-textbox" type="text" id="mailSubject"
                       value="<%=OmapUtils.parserObjectToString(sc[14])%>"
                       name="subject" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Nội dung:</td>
            <td><input class="easyui-textbox" id="mailContent" name="message" 
                       value="Bạn đã đăng ký thành công game <%=OmapUtils.parserObjectToString(sc[14])%>. 
                       Bạn sẽ chơi vào <%=OmapUtils.parserObjectToString(sc[8])%> <%=OmapUtils.parserObjectToString(sc[18])%>. 
                       Mã vé của bạn là <%=OmapUtils.parserObjectToString(sc[15])%>. 
                       Bạn đăng ký có <%=OmapUtils.parserObjectToString(sc[9])%> người chơi"
                       data-options="multiline:true" style="height:60px"></input></td>
        </tr>
    </table>

    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormGuiMail()">Gửi</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>