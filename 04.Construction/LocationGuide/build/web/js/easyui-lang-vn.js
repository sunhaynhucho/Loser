if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Trang';
	$.fn.pagination.defaults.afterPageText = 'của {pages}';
	$.fn.pagination.defaults.displayMsg = 'Hiển thị từ {from} đến {to} của {total} phần tử';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Đang loading, xin đợi trong giây lát ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Ok';
	$.messager.defaults.cancel = 'Cancel';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = 'Trường này không được để trống.';
	$.fn.validatebox.defaults.rules.email.message = 'Phải điền đúng định dạng email.';
	$.fn.validatebox.defaults.rules.url.message = 'Điền đúng định dạnh url.';
	$.fn.validatebox.defaults.rules.length.message = 'Điền giá trị từ {0} đến {1}.';
	$.fn.validatebox.defaults.rules.remote.message = 'Sửa lại trường này.';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = 'Trường này không được để trống.';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = 'Trường này không được để trống.';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = 'Trường này không được để trống.';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = 'Trường này không được để trống.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['CN','T2','T3','T4','T5','T6','t7'];
	$.fn.calendar.defaults.months = ['TH-1', 'TH-2', 'TH-3', 'TH-4', 'TH-5', 'TH-6', 'TH-7', 'TH-8', 'TH-9', 'TH-10', 'TH-11', 'TH-12'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Hiện tại';
	$.fn.datebox.defaults.closeText = 'Đóng';
	$.fn.datebox.defaults.okText = 'Ok';
	$.fn.datebox.defaults.missingMessage = 'Trường này không được để trống.';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
