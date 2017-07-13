var tablePageShow, CombFormData = [];

jQuery(document).ready(function() {
	$("#pageId").select2({
		placeholder : "选择一个界面",
		allowClear : true,
		language : "zh-CN"
	});
	getFormComb();
	tablePageShow = new CommDataTables("#table-pageshow", "#table-pageshow-columns", 9, callError);
	tablePageShow.serverInfo.referUrl = "pageShow.referPageShow";
	tablePageShow.serverInfo.referControls.push(ControlPar("text", "pageId", "", $("#pageId")));
	tablePageShow.serverInfo.modifyUrl = "pageShow.modifyPageShow";

	// ***** Add information to Column *****
	tablePageShow.columns["pageId"].render = function(data, type, row) {
		var fixData = data;

		if (type === 'display') {
			$.each(CombFormData, function(index, value) {
				if (value["formId"] === data) {
					fixData = value["formName"];
				}
			});
		}

		return fixData;
	}
	tablePageShow.columns["columnAlign"].render = function(data, type, row) {
		if (type === 'display') {
			if (data === 2) {
				return '右对齐';
			} else if (data === 1) {
				return '居中';
			} else {
				return "左对齐";
			}
		}
		return data;
	};
	tablePageShow.columns["prtype"].render = function(data, type, row) {
		if (type === 'display') {
			if (data === "N") {
				return '数字';
			} else if (data === "D") {
				return '日期';
			} else {
				return "文本";
			}
		}
		return data;
	};
	// *********************************
	// ***** Add information to Field *****
	tablePageShow.fields["pageId"].options = TransToOptions(CombFormData, "formId", "formName");
	tablePageShow.fields["columnAlign"].options = [ {
		label : "左对齐",
		value : 0
	}, {
		label : "居中",
		value : 1
	}, {
		label : "右对齐",
		value : 2
	} ];
	tablePageShow.fields["columnType"].options = [ {
		label : "text",
		value : "text"
	}, {
		label : "checkbox ",
		value : "checkbox"
	}, {
		label : "date",
		value : "date"
	}, {
		label : "datetime",
		value : "datetime"
	}, {
		label : "radio",
		value : "radio"
	}, {
		label : "select",
		value : "select"
	}, {
		label : "textarea",
		value : "textarea"
	}, {
		label : "upload",
		value : "upload"
	}, {
		label : "uploadMany",
		value : "uploadMany"
	}, {
		label : "readonly",
		value : "readonly"
	}, {
		label : "password",
		value : "password"
	}, {
		label : "hidden",
		value : "hidden"
	} ];
	tablePageShow.fields["prtype"].options = [ {
		label : "文本",
		value : "T"
	}, {
		label : "数字",
		value : "N"
	}, {
		label : "日期",
		value : "D"
	} ];
	// *********************************
	tablePageShow.create();
});

function getFormComb() {
	$.ajax({
		async : false,
		type : "POST",
		url : "pageShow.referAllForms",
		cache : false,
		data : ServerRequestPar(0, {}),
		dataType : "json",
        headers : {
            'Content-Type' : 'application/json;charset=utf-8'
        },
		success : function(res) {
			if (res.code != 0) {
				callError(res.code, res.message);
			} else {
				CombFormData = res.data;

				var html = "", group = "";
				$.each(res.data, function(index, value) {
					if (!(group === value.prgroup)) {
						if (!(group === "")) {
							html += "</optgroup>";
						}
						group = value.prgroup;
						html += "<optgroup label='界面组别-" + group + "'>";
					}
					html += "<option value='" + value.formId + "'>" + value.formName + "</option>";
				});
				html += "</optgroup>";
				$("#pageId").append(html);
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