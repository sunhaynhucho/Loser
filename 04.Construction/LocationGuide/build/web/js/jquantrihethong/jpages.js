/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var toolbar = [{  
    text:'Thêm',  
    iconCls:'icon-add',  
    handler:function(){
        showAddPage();
    }  
},'-',{  
    text:'Sửa',  
    iconCls:'icon-edit',  
    handler:function(){
        showEditPage();
    }  
},'-',{  
    text:'Xóa',  
    iconCls:'icon-remove',  
    handler:function(){
        deletePage();
    }  
}];

$(function() {
    $( "#dialog" ).dialog({
        autoOpen: false,
        title: 'Thêm trang',  
        width: 400,
        top:100,
        closed: true, 
        href: requestUrl +'/formdialog/frmquantrihethong/frmaddpage.jsp',  
        modal: true
    });
    
//    $('#dgFunctions').datagrid({
//        loadFilter:pagerFilter
//    }).datagrid('reload',{
//        options:'GetFunctions'
//    });
        
});
        
function showEditPage(){
    var nodes = $('#ttPage').tree('getSelected');
    if(nodes == null){
        $.messager.alert('Thông báo','Bạn chưa chọn chức năng nào. ','warning');  
        return;
    }
    $( "#editlog" ).dialog({
        autoOpen: false,
        title: 'Chỉnh sửa trang',  
        width: 400,
        top:100,
        closed: false, 
        href: requestUrl +'/formdialog/frmquantrihethong/frmeditpage.jsp?pageId=' + nodes.id,  
        modal: true
    });
}
    
function showAddPage(){
    $("#dialog").dialog("open");
    
}
    
function deletePage(){
    var nodes = $('#ttPage').tree('getSelected');
    if(nodes == null){
        $.messager.alert('Thông báo','Bạn chưa chọn trang nào. ','warning');  
        return;
    }
    $.messager.confirm('Xác nhận', 'Bạn có chắc muốn xóa trang này ?', function(r){
        if (r){
            $.ajax({
                url: requestUrl + "/QuanTriHeThongServices",
                type: "POST",
                cache: false,
                data: {
                    options:"DeletePage",
                    pageId:nodes.id
                },
                dataType: "json",
                success: function(data){
                    if(data.code == "0"){
                        reloadGridFunctions();
                        $.messager.alert('Thông báo',data.detail);
                    }else{
                        $.messager.alert('Thông báo',data.detail,'error');  
                    }
                },
                error: function(data){
                    $.messager.alert('Thông báo','Lỗi không kết nối được tới server','error');  
                }
            });
        }
    });
}

function submitForm(){
    var strName = $("#name").val();
    var strUrl = $("#friendlyurl").val();
    var strParent  = $("#parent").val();
    var strDesc = $("#descrition").val();
    var strPageType = $("#pagetype").val();
    //alert(strParent);
        
    $.ajax({
        url: requestUrl +"/QuanTriHeThongServices",
        type: "POST",
        cache: false,
        data: {
            options:"AddPage",
            name:strName, 
            friendlyurl:strUrl, 
            parent:strParent,
            descrition:strDesc,
            pagetype:strPageType
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                reloadGridFunctions();
                $( "#dialog" ).dialog('close');
                $.messager.alert('Thông báo',data.detail);
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
            
        }
    });
}
function clearForm(){  
    $('#ff').form('clear');  
}  


function submitFormEdit(){
    var strName = $("#editname").val();
    var strUrl = $("#editfriendlyurl").val();
    var strParent  = $("#editparent").val();
    var strDesc = $("#editdescrition").val();
    var strPageId = $("#editpageId").val();
    var strPageType = $("#editpagetype").val();
        
    $.ajax({
        url: requestUrl + "/QuanTriHeThongServices",
        type: "POST",
        cache: false,
        data: {
            options:"EditPage",
            pageId:strPageId,
            name:strName, 
            friendlyurl:strUrl, 
            parent:strParent,
            descrition:strDesc,
            pagetype:strPageType
        },
        dataType: "json",
        success: function(data){
            if(data.code == "0"){
                reloadGridFunctions();
                $( "#editlog" ).dialog('close');
                $.messager.alert('Thông báo',data.detail);
            }else{
                $.messager.alert('Thông báo',data.detail,'error');  
            }
        }
    }); 
}
function clearFormEdit(){  
    $('#ffEdit').form('clear');  
}  


function reloadGridFunctions(){
    //    $('#dgFunctions').datagrid({
    //        loadFilter:pagerFilter
    //    }).datagrid('reload',{
    //        options:'GetFunctions'
    //    });
    $('#ttPage').tree({
        url: requestUrl + '/QuanTriHeThongServices?options=GetListPages',
        loadFilter: function(rows){
            return convert(rows);
        }
    });
}

function convert(rows){
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
			
    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
            nodes.push({
                id:row.id,
                text:row.name
            });
        }
    }
			
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();	// the parent node
        // get the children nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
                var child = {
                    id:row.id,
                    text:row.name
                };
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}
		
$(function(){
    $('#ttPage').tree({
        url: requestUrl + '/QuanTriHeThongServices?options=GetListPages',
        loadFilter: function(rows){
            return convert(rows);
        }
    });
});

function append(){  
    var t = $('#ttPage');  
    var node = t.tree('getSelected');  
    t.tree('append', {  
        parent: (node?node.target:null),  
        data: [{  
            text: 'new item1'  
        },{  
            text: 'new item2'  
        }]  
    });  
}  
function remove(){  
    var node = $('#ttPage').tree('getSelected');  
    $('#ttPage').tree('remove', node.target);  
}  
function collapse(){  
    var node = $('#ttPage').tree('getSelected');  
    $('#ttPage').tree('collapse',node.target);  
}  
function expand(){  
    var node = $('#ttPage').tree('getSelected');  
    $('#ttPage').tree('expand',node.target);  
}

