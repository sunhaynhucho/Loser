<%-- 
    Document   : frmxacnhan
    Created on : Mar 30, 2015, 1:40:09 PM
    Author     : KiemPQ
--%>

<%@page import="com.elcom.omap.util.AppUtils"%>
<%@page import="com.kiemanh.vn.cauhinhgame.GameSchedureDataLogic"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.kiemanh.vn.cauhinhgame.GameSettingDataLogic"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List lGames = GameSettingDataLogic.listGames("");
    List lGioChoi = GameSchedureDataLogic.listTimes();
    String scid = request.getParameter("scid");
    List lSchedures = GameSchedureDataLogic.listSchedure(scid);
    Object[] sc = null;
    if (lSchedures != null && lSchedures.size() > 0) {
        sc = (Object[]) lSchedures.get(0);
    } else {
        sc = new Object[20];
    }
%>
<div style="padding:10px 60px 20px 60px">
    <input type="hidden" id="txtSid" value="<%=scid%>" />
    <table cellpadding="5">
        <tr>
            <td>Tên thường gọi:</td>
            <td><input class="easyui-textbox" type="text" name="name" id="txtUserName"
                       value="<%=AppUtils.cObjectToString(sc[1])%>"
                       data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Họ và tên:</td>
            <td><input class="easyui-textbox" type="text" id="txtHoVaTen"
                       value="<%=AppUtils.cObjectToString(sc[2])%>"
                       name="name" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input class="easyui-textbox" type="text" id="txtEmail"
                       value="<%=AppUtils.cObjectToString(sc[4])%>"
                       name="email" data-options="required:true,validType:'email'"></input></td>
        </tr>
        <tr>
            <td>Điện thoại:</td>
            <td><input class="easyui-textbox" type="text" id="txtDienThoai"
                       value="<%=AppUtils.cObjectToString(sc[3])%>"
                       name="subject" data-options="required:true"></input></td>
        </tr>
        <tr>
            <td>Địa chỉ:</td>
            <td><input class="easyui-textbox" id="txtDiaChi"
                       value="<%=AppUtils.cObjectToString(sc[5])%>"
                       name="message" ></input></td>
        </tr>
        <tr>
            <td>Ngày chơi:</td>
            <td><input class="easyui-datebox" id="txtNgayChoi"
                       value="<%=AppUtils.cObjectToString(sc[8])%>"
                       data-options="formatter:myformatter,parser:myparser,prompt:'Ngày chơi'" style="width:110px">
                <input type="hidden" id="txtNgayChoiOld" value="<%=AppUtils.cObjectToString(sc[8])%>"/>
            </td>
        </tr>
        <tr>
            <td>Giờ chơi:</td>
            <td>
                <input type="hidden" id="txtGioChoiOld" value="<%=sc[17]%>"/>
                <select class="easyui-combobox" id="ccbGioChoi" name="language">
                    <%
                        for (Object oTime : lGioChoi) {
                            Object[] o = (Object[]) oTime;
                    %>
                    <option value="<%=OmapUtils.parserObjectToString(o[0])%>"><%=OmapUtils.parserObjectToString(o[1])%></option>
                    <%
                        }
                    %>
                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbGioChoi").length; i++) {
                        if (document.getElementById("ccbGioChoi").options[i].value == '<%=sc[17]%>') {
                            document.getElementById("ccbGioChoi").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
        <tr>
            <td>Game:</td>
            <td>
                <select class="easyui-combobox" id="ccbGame" name="language" style="width: 250px;">
                    <%
                        for (Object oGame : lGames) {
                            Object[] o = (Object[]) oGame;
                    %>
                    <option value="<%=OmapUtils.parserObjectToString(o[0])%>"><%=OmapUtils.parserObjectToString(o[1])%></option>
                    <%
                        }
                    %>

                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbGame").length; i++) {
                        if (document.getElementById("ccbGame").options[i].value == '<%=sc[13]%>') {
                            document.getElementById("ccbGame").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
        <tr>
            <td>Số người chơi:</td>
            <td>
                <input class="easyui-numberspinner" id="txtSoNguoiChoi" style="width:80px;" 
                       value="<%=OmapUtils.parserObjectToString(sc[9])%>"></input>
            </td>
        </tr>
        <tr>
            <td>Trạng thái:</td>
            <td>
                <select class="easyui-combobox" id="ccbStatus" name="ccbTrangThai">
                    <option value="0">Chưa xác nhận</option>
                    <option value="1">Xác nhận OK</option>
                    <option value="2">Hủy lịch</option>
                    <option value="3">Black list</option>
                </select>
                <script type="text/javascript">
                    for (i = 0; i < document.getElementById("ccbStatus").length; i++) {
                        if (document.getElementById("ccbStatus").options[i].value == '<%=sc[11]%>') {
                            document.getElementById("ccbStatus").selectedIndex = i;
                        }
                    }
                </script>
            </td>
        </tr>
    </table>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitXacNhan()">Xác nhận</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>