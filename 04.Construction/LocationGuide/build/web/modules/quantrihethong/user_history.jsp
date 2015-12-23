<%-- 
    Document   : bcgiamsattruynhapnguoisudung
    Created on : May 14, 2013, 2:44:53 PM
    Author     : Pham Quang Kiem
--%>
<%@page import="com.elcom.omap.common.Constant"%>
<%@page import="com.elcom.omap.common.OmapHistoryLogic"%>
<%@page import="com.elcom.omap.quantrihethong.QuanTriHeThongDataLogic"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="../js/jquantrihethong/juserhistory.js"></script>
<%
    String newDate = OmapUtils.getCurrentDate();
    List lCongViec = QuanTriHeThongDataLogic.listAction();
    String userName = OmapSessionUtils.getUserName(request);
    OmapHistoryLogic.insertUserHistory(Constant.ACTION_QTHT_USERHISTORY, "0", userName, "Truy vấn lịch sử truy nhập web", OmapUtils.getClientIpAddr(request));
%>

<div class="easyui-panel" title="Giám sát truy nhập người sử dụng" style="float: none;font:Arial">
    <div style="padding:10px 0 10px 60px">
        <table>
            <tr>  
                <td width="100px" class="lb">Từ ngày:</td>  
                <td width="250px">
                    <input id="txtFromDate" name="txtFromDate" type="text" class="easyui-datebox" 
                           value="<%=newDate%>" data-options="formatter:myformatter,parser:myparser,editable:false"></input>  
                </td>  
                <td width="100px" class="lb"> Đến ngày:</td>
                <td width="250px">
                    <input id="txtToDate" name="txtToDate" type="text" class="easyui-datebox" 
                           value="<%=newDate%>" data-options="formatter:myformatter,parser:myparser,editable:false"></input>  
                </td>  
            </tr>
            <tr>  
                <td class="lb">Công việc:</td>  
                <td>
                    <select style="width: 150px" name="cboCongViec" id="cboCongViec">
                        <option value="All">Tất cả</option>
                        <% for (Object o : lCongViec) {%>
                        <% Object[] oTT = (Object[]) o;%>
                        <option value="<%=oTT[0]%>"><%=oTT[0]%></option>
                        <% }%>
                    </select>
                </td>
                <td>Username:</td>  
                <td>
                    <input id="txtUserName" name="txtUserName" maxlength="50" type="text"/>
                </td>
            </tr>
            <tr>  
                <td class="lb"></td>  
                <td>
                </td>
                <td></td>  
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitTimKiem()">Báo cáo</a>
                </td>
            </tr>
        </table>  
    </div>
</div>
<div style="height: 5px"></div>
<table id="dgReport" class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:true,pageSize:10,autoRowHeight:false,pagination:true"
       title="Kết quả" style="  height:370px">  
    <thead>  
        <tr>  
            
            <th data-options="field:'username'" >UserName</th>
            <th data-options="field:'congviecthuchien'" >Công việc thực hiện</th>  
            <th data-options="field:'thoigian'">Thời gian</th>  
            <th data-options="field:'chitietcongviec'">Chi tiết công việc thực hiện</th>  
            <th data-options="field:'ketqua'">Kết quả</th>
            <th data-options="field:'diachiip'">Địa chỉ IP</th>
        </tr>  
    </thead>
</table>
