<%-- 
    Document   : home
    Created on : Feb 3, 2015, 4:56:10 PM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/jhome.js"></script>
<%
    String userId = OmapSessionUtils.getUserId(request);
%>
<input type="hidden" id="txtUserId" value="<%=userId%>"/>
<input type="hidden" id="txtUserIdWork"/>
<table width='100%' boder='0' cellspacing='0'>
    <tr>
        <td align="left" style="vertical-align: top;">
            <div class="easyui-panel" title="Danh sách thành viên" style="padding:5px;width: 250px;height:550px;">
                <ul class="easyui-tree" data-options="
                    url:'<%=request.getContextPath()%>/QuanLyCongViecServices?options=GetListTree',
                    method:'get',
                    animate:true,

                    loadFilter: function(rows){
                    return convert(rows);
                    },onClick: function(node){
                    loadGridWork(node.id);
                    }
                    ">
                </ul>
            </div>

        </td>
        <td align="right" style="vertical-align: top;">
            <table id="dg" style="width:585px;height:550px"
                   title="Danh sách công việc"
                   data-options="singleSelect:true,toolbar:'#tb',footer:'#ft',pagination:true,nowrap:false"
                   >
                <thead>
                    <tr>
                        <th field="wid" align="center">Mã</th>
                        <th field="wcontent" width="175px">Nội dung</th>
                        <!-- <th field="wcreatetime">Ngày tạo</th>-->
                        <th field="wcreatetime" align="center">Bắt đầu</th>
                        <th field="wdeadline" align="center">Kết thúc</th>
                        <th field="wprocess" align="center">Tiến độ</th>
                        <th field="wcomment" align="center">Góp ý</th>
                        <th field="wdocument" align="center">Tài liệu</th>
                        <!-- <th field="wedit"></th>
                             <th field="wdelete"></th>-->
                    </tr>
                </thead>
            </table>
        </td>
    </tr>
</table>

<div id="divProcess"></div>                    

<div id="tb" style="padding:2px 5px;">
    Từ ngày: <input class="easyui-datebox" id="startDate" data-options="formatter:myformatter,parser:myparser" style="width:110px">
    Đến ngày: <input class="easyui-datebox" id="endDate" data-options="formatter:myformatter,parser:myparser" style="width:110px">
    <a href="#" class="easyui-linkbutton" onclick="searchWork()" iconCls="icon-search">Tìm</a>
</div>

<div id="ft" style="padding:2px 5px;">
    <a href="#" class="easyui-linkbutton" onclick="showAddWork()" iconCls="icon-add" plain="true">Thêm</a>
    <a href="#" class="easyui-linkbutton" onclick="showEditWork()" iconCls="icon-edit" plain="true">Sửa</a>
    <a href="#" class="easyui-linkbutton" onclick="deleteWork()" iconCls="icon-no" plain="true">Xóa</a>
    <a href="#" class="easyui-linkbutton" onclick="showUpdateProcessWork()" iconCls="icon-process" plain="true">Tiến độ</a>
    <a href="#" class="easyui-linkbutton" onclick="showDocument()" iconCls="icon-save" plain="true">Tài liệu</a>
    <a href="#" class="easyui-linkbutton" onclick="showAddComment()" iconCls="icon-man" plain="true">Comment</a>


</div>
<jsp:include page="../../formdialog/dquanlycongviec/editwork.jsp" />              
<jsp:include page="../../formdialog/dquanlycongviec/addwork.jsp" />              

<div id="dgABC"></div>
