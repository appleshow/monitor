var tableTypeItem, CombTypeData = [];

jQuery(document).ready(function() {
	getHbTypeComb();
	tableTypeItem = new CommDataTables("#table-typeitem", "#table-typeitem-columns", 11, callError);
	tableTypeItem.serverInfo.referUrl = "hbTypeItemConfig.referHbTypeItem";
	tableTypeItem.serverInfo.referControls.push(ControlPar("text", "typeId", "", $("#typeId")));
	tableTypeItem.serverInfo.modifyUrl = "hbTypeItemConfig.modifyHbTypeItem";

	// ***** Add information to Column *****
	tableTypeItem.columns["typeId"].render = function(data, type, row) {
		var fixData = data;

		if (type === 'display') {
			$.each(CombTypeData, function(index, value) {
				if (value["typeId"] === data) {
					fixData = value["typeName"];
				}
			});
		}

		return fixData;
	}
	// *********************************
	// ***** Add information to Field *****
	tableTypeItem.fields["typeId"].options = TransToOptions(CombTypeData, "typeId", "typeName");
	// *********************************

	tableTypeItem.create();
})

function getHbTypeComb() {
	$.ajax({
		async : false,
		type : "POST",
		url : "hbTypeItemConfig.referHbType",
		cache : false,
		data : ServerRequestPar(0, {}),
		dataType : "json",
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				CombTypeData = res.data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError(-900, "操作未完成，向服务器请求失败...");
		}
	});
}

function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}