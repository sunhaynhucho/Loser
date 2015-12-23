$(function() {
    $('#dgRole').datagrid({
        onSelect: function(rowIndex, rowData){
            reloadGridPage(rowData.roleId);
        }
    });
});
    
function reloadGridPage(pRoleId){
    var nodes = $('#tt').tree('getChecked');  
    for(var i=0; i<nodes.length; i++){  
        $('#tt').tree('uncheck', nodes[i].target);
    }  
    $.getJSON( requestUrl + "/QuanTriHeThongServices",
    {
        "options":"GetPagesByRoleId",
        "roleId": pRoleId
    },
    checkPages);
}
    
function checkPages(data){
    $.each(data, function(i, page) {
        var node = $('#tt').tree('find', page.pageId);
        if($('#tt').tree('isLeaf', node.target)){
            $('#tt').tree('check', node.target);
        }
    });
}
    
function saveRolePages(){
    var win = $.messager.progress({  
        title:'Xin đợi ',  
        msg:'Đang xử lý dữ liệu...'  
    }); 
    var row = $('#dgRole').datagrid('getSelected');
    var ids = [];
    var nodes = $('#tt').tree('getChecked');  
    for(var i=0; i<nodes.length; i++){
        var nodeCha = $('#tt').tree('getParent',nodes[i].target);
        if(nodeCha != null && $.inArray(nodeCha.id, ids)){
            ids.push(nodeCha.id);
            var nodeRoot = $('#tt').tree('getRoot',nodes[i].target);
            if(nodeRoot != null && $.inArray(nodeRoot.id, ids)){
                ids.push(nodeRoot.id);
            }
        }
        ids.push(nodes[i].id);
    }  
    
    $.ajax({
        url: requestUrl +"/QuanTriHeThongServices",
        type: "POST",
        cache: false,
        data: {
            options:"SaveRolePages", 
            roleId:row.roleId,
            pageIds:ids.join(',')
        },
        dataType: "html",
        success: function(html){
            $.messager.progress('close');
            $.messager.alert('Thông báo',html);              
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
    $('#tt').tree({
        url: requestUrl + '/QuanTriHeThongServices?options=GetListPages',
        loadFilter: function(rows){
            return convert(rows);
        }
    });
});