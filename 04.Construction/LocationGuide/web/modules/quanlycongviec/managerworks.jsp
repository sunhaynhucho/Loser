<%-- 
    Document   : managerworks
    Created on : Mar 5, 2015, 1:32:22 PM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.login.OmapUser"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    table.tableNhanvien tr:hover td{
        background-color: #FC6;
    }
</style>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="../js/jquanlycongviec/jmanagerworks.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<%
    String userId = "-1";//OmapSessionUtils.getUserId(request);
    String posType = "-1";
    String posName = "";
    String fullName = "";
    OmapUser user = OmapSessionUtils.getOmapUserUserName(request);
    if (user != null) {
        userId = user.getUserId();
        posType = user.getPosType() + "";
        posName = user.getPosName();
        fullName = user.getFullName();
    }

    //user.get
%>
<input type="hidden" id="txtUserId" value="<%=userId%>"/>
<input type="hidden" id="txtFullName" value="<%=fullName%>"/>
<input type="hidden" id="txtPosType" value="<%=posType%>"/>
<input type="hidden" id="txtPosName" value="<%=posName%>"/>
<input type="hidden" id="txtUserIdWork" value=""/>
<div style="width: 844px;overflow: scroll;">
    <table height="100%" border="0">
        <tr>
            <td id="cot1" width="0px" height="100%" valign="top" style="visibility:hidden;border-right: #3399cc solid 1px;">
                <div id="divcot1" style="width:200px; height:100%; overflow:auto">
                    <table id="tblcot1" class="tableNhanvien" width="100%" style="cursor:pointer">

                    </table>
                </div>
            </td>

            <td id="cot2" width="0px" height="100%" valign="top" style="visibility:hidden;border-right: #3399cc solid 1px;">
                <div id="divcot2" style="width:200px; height:100%; overflow:auto">
                    <table id="tblcot2" width="100%" class="tableNhanvien" style="cursor:pointer">

                    </table>
                </div>
            </td>

            <td id="cot3" width="0px"  height="100%" valign="top" style="visibility:hidden;border-right: #3399cc solid 1px;">
                <div id="divcot3" style="width:200px; height:100%; overflow:auto">
                    <table id="tblcot3" width="100%" class="tableNhanvien" style="cursor:pointer">

                    </table>
                </div>
            </td>

            <td id="cot4" width="0px"  height="100%" valign="top" style="visibility:hidden;border-right: #3399cc solid 1px;">
                <div id="divcot4" style="width:200px; height:100%; overflow:auto;">
                    <table id="tblcot4" width="100%" class="tableNhanvien" style="cursor:pointer">   

                    </table>
                </div>

            </td>

            <td valign="top">
                <table id="dg" style="width:645px;height:540px"
                       title="Danh sách công việc"
                       data-options="singleSelect:true,
                       loadFilter: pagerFilter,
                       toolbar:'#tb',
                       pagination:true,
                       nowrap:false,
                       remoteSort:false,
                       multiSort:true,
                       pageSize:20"
                       >
                    <thead>
                        <tr>
                            <th field="wid" align="center">Mã</th>
                            <th field="wcontent" width="175px">Nội dung</th>
                            <th field="wcreatetime" align="center" sortable="true">Bắt đầu</th>
                            <th field="wdeadline" align="center" sortable="true">Kết thúc</th>
                            <th field="wprocess" align="center" sortable="true">Tiến độ</th>
                            <th field="wcomment" align="center">Góp ý</th>
                            <th field="wdocument" align="center">Tài liệu</th>
                            <th field="wketqua" align="center">Kết quả</th>
                        </tr>
                    </thead>
                </table>
            </td>
        </tr>
    </table>
</div>
<div id="tb" style="padding:2px 5px;">
    Từ ngày: <input class="easyui-datebox" id="startDate" data-options="formatter:myformatter,parser:myparser" style="width:110px">
    Đến ngày: <input class="easyui-datebox" id="endDate" data-options="formatter:myformatter,parser:myparser" style="width:110px">
    <a href="#" class="easyui-linkbutton" onclick="searchWork()" iconCls="icon-search">Tìm</a>
    <a href="#" class="easyui-linkbutton" onclick="clearSearchWork()" iconCls="icon-clear">Xóa</a>
    <br/>
    <a href="#" class="easyui-linkbutton" onclick="showAddWork()" iconCls="icon-add" plain="true">Tạo công việc</a>
    <a href="#" class="easyui-linkbutton" onclick="showEditWork()" iconCls="icon-edit" plain="true">Sửa</a>
    <a href="#" class="easyui-linkbutton" onclick="deleteWork()" iconCls="icon-no" plain="true">Xóa</a>
    <a href="#" class="easyui-linkbutton" onclick="showComboProcess()" iconCls="icon-process" plain="true">Tiến độ</a>
    <!--<a href="#" class="easyui-linkbutton" onclick="showDocument()" iconCls="icon-save" plain="true">Tài liệu</a>-->
    <a href="#" class="easyui-linkbutton" onclick="showAddComment()" iconCls="icon-man" plain="true">Góp ý</a>
</div>

<div id="ft" style="padding:2px 5px;">
</div>

<jsp:include page="../../formdialog/dquanlycongviec/editwork.jsp" />              
<jsp:include page="../../formdialog/dquanlycongviec/addwork.jsp" />              

<div id="dgABC"></div>
<div id="dgABCD"></div>
<div id="dgABCDE"></div>
<input id="txtUrlFileShow" type="hidden"/>