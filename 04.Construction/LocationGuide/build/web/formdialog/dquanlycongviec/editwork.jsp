<%-- 
    Document   : editwork
    Created on : Feb 5, 2015, 10:22:49 AM
    Author     : elcom154
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
//    $(document).ready(function() {
//        $('#wcontentMaxlength').textbox();
//        $('#wcontentMaxlength').textbox('textbox').attr('maxlength', $('#wcontentMaxlength').attr("maxlength"));
//        $('#wcontentMaxlength').textbox('textbox').keyup(function() {
//            displayWordCounter();
//        });
//    });

    function displayWordCounter() {

        var getTextValue = document.frm.wcontent.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea2').innerHTML = getTextLength;
        document.getElementById('testTextarea3').innerHTML = 1000 - getTextLength;

    }
</script>

<div id="dlg" class="easyui-dialog" style="width:400px;height:240px;padding:10px 20px" modal="true" closed="true" buttons="#dlg-buttons">
    <form id="frmEditRow" name="frm" method="post" novalidate>
        <input type="hidden" name="wid">
        <div class="fitem">
            <table>
                <tr>
                    <td>
                        <label style="text-align: 100px; top: 100px">&nbsp;&nbsp;&nbsp;Nội dung:</label>         
                    </td>
                    <td>
                        <textarea id="wcontentMaxlength" name="wcontent"  onkeyup="displayWordCounter();" maxlength="1000" style="height:290px;width: 520px;border: #33ccff solid 1px"></textarea>
                    </td>
                </tr>
            </table>
            <!--<input id="wcontentMaxlength"  name="wcontent" data-options="multiline:true" maxlength="1000" style="height:280px;width: 550px" class="easyui-textbox" required="true">-->
        </div>
        <div class="fitem">
            <label>Hoàn thành:</label>
            <input name="wdeadline" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" required="true">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span >Số ký tự hiện tại :<strong> <span id="testTextarea2">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span>Số ký tự còn lại:<strong> <span id="testTextarea3">1000</span></strong></span>
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveEditData" iconCls="icon-ok" onclick="saveEditData()" style="width:90px">Sửa</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Đóng</a>
</div>

<style type="text/css">
    #frmEditRow{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }
</style>      