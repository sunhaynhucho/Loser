<%-- 
    Document   : addwork
    Created on : Feb 5, 2015, 10:22:41 AM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.database.DBActions"%>
<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int userId = OmapUtils.parserInteger(OmapSessionUtils.getUserId(request));
    List lUser = DBActions.getListUserForTree(userId);
%>
<!--<script type="text/javascript" src="../js/jquanlycongviec/readExcel/angular.min.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/readExcel/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/readExcel/lodash.min.js"></script>

<script type="text/javascript" src="../js/jquanlycongviec/readExcel/jszip.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/readExcel/xlsx.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/readExcel/xlsx-reader.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#wAddcontentMaxlength').textbox();
        $('#wAddcontentMaxlength').textbox('textbox').attr('maxlength', $('#wAddcontentMaxlength').attr("maxlength"));

        var xlf = document.getElementById('xlf');
        $("#fileCV").change(function(event) {
            alert('ok');
            var file = this.files[0];
            var sheets;
            alert(file.toString());
            XLSXReader(file, true, false, function(data) {
                sheets = data.sheets;
                alert("Ben tren==>" + sheets["Sheet1"]);
                var dulieu = JSON.stringify(sheets["Sheet1"], null, 2);
                alert("dulieu==>" + dulieu);
                var obj = JSON.parse(dulieu);
                alert(obj.data[1][0] + " " + obj.data[1][1]);
            });
        });
    });

</script>-->


<script type="text/javascript">
//    $(document).ready(function() {
//        $('#wcontentMaxlength').textbox();
//        $('#wcontentMaxlength').textbox('textbox').attr('maxlength', $('#wcontentMaxlength').attr("maxlength"));
//        $('#wcontentMaxlength').textbox('textbox').keyup(function() {
//            displayWordCounter();
//        });
//    });

    function displayWordCounterAdd() {

        var getTextValue = document.frm2.wAddcontent.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea22').innerHTML = getTextLength;
        document.getElementById('testTextarea33').innerHTML = 1000 - getTextLength;

    }
</script>
<input type="hidden" id="userIDWork" value="<%=userId%>" />
<div id="dlgAdd" class="easyui-dialog" style="width:600px;height:360px;padding:10px 20px" modal="true" closed="true" buttons="#dlgAdd-buttons">
    <form id="frmAddRow" name="frm2" method="post" novalidate>
        <div class="fitem">
            <table>
                <tr>
                    <td>
                        <label style="text-align: 100px; top: 100px">&nbsp;&nbsp;&nbsp;Nội dung:</label>         
                    </td>
                    <td>
                        <textarea id="wAddcontentMaxlength" name="wAddcontent" placeholder="Nội dung công việc"  onkeyup="displayWordCounterAdd();" maxlength="1000" style="height:300px; width: 550px;border: #33ccff solid 1px"></textarea>
                    </td>
                </tr>
            </table>

            <!--            <label>Nội dung:</label>
                        <input id="wAddcontentMaxlength" name="wAddcontent" class="easyui-textbox" data-options="multiline:true" maxlength="2000" style="height:300px; width: 550px" prompt="Nội dung công việc" />-->
        </div>
        <div class="fitem">
            <label>Hoàn thành:</label>
            <input id="wAdddeadline" name="wAdddeadline" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,prompt:'Ngày hoàn thành ...'" required="true" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >Số ký tự hiện tại :<strong> <span id="testTextarea22">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span>Số ký tự còn lại:<strong> <span id="testTextarea33">1000</span></strong></span>
        </div>
        <div class="fitem">
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Chọn:</label>
            <select class="easyui-combobox" id="wAdduser" name="wAdduser" data-options="prompt:'Chọn ...',multiple:true" style="width: 165px">
                <% if (lUser != null) {%>
                <% for (Object os : lUser) {%>
                <%  Object[] o = (Object[]) os;%>
                <option value="<%=o[0]%>"><%=o[2]%></option>
                <% }
                    }%>
            </select>
        </div>
    </form>
    <form id="frmAddCV" method="post" enctype="multipart/form-data" acceptcharset="UTF-8" novalidate>
        <input type="hidden" id="userIDWorkFile" name="userIDWorkFile" value="<%=userId%>" />
        <div class="fitem">
            <label style="width: 110px;">&nbsp;&nbsp;&nbsp;&nbsp; File công việc:</label>
            <input class="easyui-filebox" id="fileCongviec" name="file[]" data-options="prompt:'Choose a file...'" style="width: 250px;">
            <label style="width: 150px; color: red">(Chỉ đính kèm file Excel)</label>
        </div>
    </form>

</div>

<div id="dlgAdd-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveAddData" iconCls="icon-ok" onclick="saveAddData()" style="width:90px">Thêm</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearFormClose()" style="width:90px">Đóng</a>
</div>
<style type="text/css">
    #frmAddRow{
        margin:0;
        padding:10px 30px;
    }
</style>
<script type="text/javascript">
    function clearFormClose() {
        $('#dlgAdd').dialog('close');
        $('#frmAddRow').form('clear');
        $('#frmAddCV').form('clear');
    }
</script>