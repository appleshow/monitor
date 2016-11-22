//页面全局变量
var HB_NODE_STATUS = {
	// Grid表格
	tableHbNodeStatus : undefined,
}

jQuery( document ).ready( function() {
	$( "#hours" ).select2( {
		placeholder : "选择监测时间",
		allowClear : true,
		language : "zh-CN"
	} );

	HB_NODE_STATUS.tableHbNodeStatus = new CommDataTables( "#table-nodestatus", "#table-nodestatus-columns", 16, callError );
	HB_NODE_STATUS.tableHbNodeStatus.serverInfo.referUrl = "hbNodeStatusController.refNodeStatus";
	HB_NODE_STATUS.tableHbNodeStatus.serverInfo.referControls.push( ControlPar( "text", "hours", "", $( "#hours" ) ) );
	HB_NODE_STATUS.tableHbNodeStatus.buttons = "RP";
	// *********************************
	HB_NODE_STATUS.tableHbNodeStatus.create();
} );

function callError(code, message) {
	$( "#mwTitle" ).html( '<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>' );
	$( "#mwMessage" ).html( message );
	$( "#modal-warning" ).modal( "show" );
}