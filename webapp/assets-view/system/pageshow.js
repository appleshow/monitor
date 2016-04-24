var tablePageShow;

jQuery(document).ready(function() {
	tablePageShow = new CommDataTables("#table-pageshow", "#table-pageshow-columns", 9, callError);
	tablePageShow.serverInfo.referUrl = "pageShow.referPageShow";
	tablePageShow.serverInfo.referControls.push(ControlPar("text", "pageId", "", $("#pageId")));
	tablePageShow.serverInfo.modifyUrl = "pageShow.modifyPageShow";

	// ***** Add information to Column *****
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

function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}