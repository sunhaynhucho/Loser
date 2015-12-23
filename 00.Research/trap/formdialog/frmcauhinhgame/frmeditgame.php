<?php

?>
<div style="padding:10px 10px 20px 60px">
    <form id="frmGame" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
        <table cellpadding="5">
            <tr>
                <td>Mã game:</td>
                <td><input class="easyui-textbox"disabled="true" 
                           value="<%=gid%>" type="text" maxlength="50"></input>
                    <input type="hidden" id="txtMaGame" name='txtMaGame' value="<%=gid%>" />
                </td>
            </tr>
            <tr>
                <td>Tên game(EN):</td>
                <td><input class="easyui-textbox" id="txtTenGame" 
                          value="<%=g.getGameName()%>" name="txtTenGame" type="text" maxlength="100"></input></td>
            </tr>
            <tr>
                <td>Tên game(VN):</td>
                <td><input class="easyui-textbox" id="txtTenGameVN" 
                          value="<%=g.getGameNameVN()%>" name="txtTenGameVN" type="text" maxlength="100"></input></td>
            </tr>
            <tr>
                <td>Giá:</td>
                <td><input class="easyui-numberbox" id="txtGiaTien" 
                           value="<%=g.getGamePrice()%>" name="txtGiaTien" data-options="precision:0,groupSeparator:',',suffix:' vnd'"></input></td>
            </tr>
            <tr>
                <td>Độ khó:</td>
                <td><input class="easyui-numberbox" id="txtDoKho" name="txtDoKho" 
                           value="<%=g.getGameDifficulty()%>" data-options="suffix:' sao'"></input></td>
            </tr>
            <tr>
                <td>Số lượng:</td>
                <td><input class="easyui-numberbox" id="txtMin" name="txtMin" 
                           value="<%=g.getGameMin()%>" style="width: 30%;"></input> đến 
                    <input class="easyui-numberbox" id="txtMax" name="txtMax" 
                           value="<%=g.getGameMax()%>" style="width: 30%;"></input></td>
            </tr>
            <tr>
                <td>Nội dung(EN):</td>
                <td><input class="easyui-textbox" name="txtNoiDung" id="txtNoiDung" 
                           value="<%=g.getGameContentVN()%>" maxlength="1000" data-options="multiline:true" style="height:60px"></input></td>
            </tr>
            <tr>
                <td>Nội dung(VN):</td>
                <td><input class="easyui-textbox" name="txtNoiDungVN" id="txtNoiDungVN" 
                           value="<%=g.getGameContent()%>" maxlength="1000" data-options="multiline:true" style="height:60px"></input></td>
            </tr>
            <tr>
                <td>Thời gian:</td>
                <td><input class="easyui-numberbox" id="txtSoPhut" name="txtSoPhut" 
                           value="<%=g.getGameTime()%>" data-options="suffix:' phút'"></input></td>
            </tr>
            <tr>
                <td>Mã vé:</td>
                <td><input class="easyui-textbox" id="txtMaVe" name="txtMaVe" maxlength="50" 
                           value="<%=g.getGameTicket()%>" type="text"></input></td>
            </tr>
            <tr>
                <td>Ảnh:</td>
                <td><input class="easyui-filebox" id="fAnh" name="fAnh" data-options="prompt:'Choose a file...'"></td>
            </tr>
            <tr>
                <td>Url Game:</td>
                <td><input class="easyui-textbox" id="txtGameUrl" name="txtGameUrl" 
                           value="<%=g.getGameUrl()%>" maxlength="100" type="text"></input></td>
            </tr>
            <tr>
                <td>Khuyến mãi(EN):</td>
                <td><input class="easyui-textbox" name="txtPromotions" 
                           value="<%=g.getGpromotions()%>"
                           id="txtPromotions" maxlength="1000" data-options="multiline:true" style="height:60px"></input></td>
            </tr>
            <tr>
                <td>Khuyến mãi(VN):</td>
                <td><input class="easyui-textbox" name="txtPromotionsVN" value="<%=g.getGpromotionsVN()%>"
                           id="txtPromotionsVN" maxlength="1000" data-options="multiline:true" style="height:60px"></input></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <div id="divErrMsg" style="color: red;"></div>
                </td>
            </tr>
        </table>
        <input type="hidden" id="txtTypeCommand" name="txtTypeCommand" value="EDIT" />
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditGame()">Đồng ý</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Xóa</a>
    </div>
</div>