var tableType;

jQuery(document).ready(function() {
	tableType = new CommDataTables("#table-type", "#table-type-columns", 10, callError);
	tableType.serverInfo.referUrl = "hbTypeConfig.referHbType";
	tableType.serverInfo.modifyUrl = "hbTypeConfig.modifyHbType";

	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************
	tableType.create();
});

function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}