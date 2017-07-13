/**
 * 当前页面全局变量
 */
var HBNodeAccess = {
	tbNodeAccess : undefined,
	selectNodes : [],
	orgs : [],
	hbNodes : [],
	hbTypes : [],
}
var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay
};
DataSourceTree.prototype = {
	data : function(options, callback) {
		setTimeout( function() {
			var farOrgId = 0;

			if ( options.id != null ) {
				farOrgId = options.id.replace( "org", "" );
			}

			$.ajax( {
				async : true,
				type : "POST",
				url : "hbNodeAccessController.refOrg",
				cache : false,
				data : ServerRequestPar( 1, {
					farOrgId : farOrgId
				} ),
				dataType : "json",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
				success : function(res) {
					if ( res.code != 0 ) {
						callback( {
							data : []
						} );
					} else {
						var treeData = [];

						$.each( res.data, function(index, value) {
							var item = {};

							item.id = "org" + value.orgId;
							item.name = value.orgName;
							if ( value.prgroup == "0" ) {
								item.type = 'item';
							} else {
								item.type = 'folder';
							}
							treeData.push( item );
						} );

						callback( {
							data : treeData
						} );

					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					callback( {
						data : []
					} );
				}
			} );
		}, this._delay )
	}
};

var treeDataNode = new DataSourceTree( {
	data : [],
	delay : 400
} );

jQuery( document ).ready( function() {
	// ******** 开始初始化组织列表 ********
	$( '#tree-org' ).tree( {
		cacheItems : true,
		selectable : true,
		multiSelect : false,
		dataSource : treeDataNode,
		loadingHTML : '<div class="tree-loading"><i class="fa fa-rotate-right fa-spin"></i></div>',
	} );
	// ******************** ==== ********************
	// ******** 查询组织信息 ********
	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeAccessController.refOrg",
		cache : false,
		data : ServerRequestPar( 1, {
			farOrgId : ''
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
			} else {
				HBNodeAccess.orgs = res.data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}

	} );
	// ******************** ==== ********************
	// ******** 查询站点信息 ********
	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeAccessController.refNode",
		cache : false,
		data : ServerRequestPar( 1, {
			nodeId : 0
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			if ( res.code != 0 ) {
			} else {
				HBNodeAccess.hbNodes = res.data;
				HBNodeAccess.hbTypes = res.subJoinResponseData.data;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}

	} );
	// ******************** ==== ********************
	// ******** 开始初始化站点权限表格 ********
	HBNodeAccess.tbNodeAccess = new CommDataTables( "#tbNodeAccess", "#tbNodeAccessHC", 18, callError );
	HBNodeAccess.tbNodeAccess.buttons = "EP";
	HBNodeAccess.tbNodeAccess.serverInfo.modifyUrl = "hbNodeAccessController.modifyNodeAccess";

	HBNodeAccess.tbNodeAccess.columns["resourceTypeB"].render = function(data, type, row) {
		var fixData = data;

		if ( type === 'display' ) {
			$.each( HBNodeAccess.orgs, function(index, org) {
				if ( org["orgId"] == data ) {
					fixData = org["orgName"];
				}
			} );
		}
		return fixData;
	}
	HBNodeAccess.tbNodeAccess.columns["resourceTypeC"].render = function(data, type, row) {
		var fixData = data;

		if ( type === 'display' ) {
			$.each( HBNodeAccess.hbNodes, function(index, node) {
				if ( node["nodeId"] == data ) {
					fixData = node["nodeName"];
				}
			} );
		}
		return fixData;
	}

	HBNodeAccess.tbNodeAccess.columns["resourceTypeD"].render = function(data, type, row) {
		var fixData = data;

		if ( type === 'display' ) {
			$.each( HBNodeAccess.hbTypes, function(index, type) {
				if ( type["typeId"] == data ) {
					fixData = type["typeName"];
				}
			} );
		}
		return fixData;
	}

	HBNodeAccess.tbNodeAccess.fields["resourceTypeB"].options = TransToOptions( HBNodeAccess.orgs, "orgId", "orgName" );
	HBNodeAccess.tbNodeAccess.fields["resourceTypeC"].options = TransToOptions( HBNodeAccess.hbNodes, "nodeId", "nodeName" );
	HBNodeAccess.tbNodeAccess.fields["resourceTypeD"].options = TransToOptions( HBNodeAccess.hbTypes, "typeId", "typeName" );

	HBNodeAccess.tbNodeAccess.create( null, dataTableAjax );
	// ******************** ==== ********************
} );
/*******************************************************************************************************************************************************************************************************
 * 查询数据
 ******************************************************************************************************************************************************************************************************/
function refData(click) {
	var selectNodes = $( '#tree-org' ).tree( 'selectedItems' );
	if ( selectNodes.length == 0 ) {

		if ( click ) {
			callError( 100, "请先选择一个站点...!!" );
		}
		return;
	}

	HBNodeAccess.tbNodeAccess.table.ajax.reload( null, false );
}
/*******************************************************************************************************************************************************************************************************
 * 向服务器请求数据
 ******************************************************************************************************************************************************************************************************/
function dataTableAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};

	var selectNodes = $( '#tree-org' ).tree( 'selectedItems' );
	if ( selectNodes.length == 0 ) {
		callback( tableData );
		return;
	}

	var orgId = selectNodes[0].id.replace( "org", "" );
	$.ajax( {
		async : false,
		type : "POST",
		url : "hbNodeAccessController.refNodeAccess",
		cache : false,
		data : ServerRequestPar( 1, {
			resourceTypeB : orgId,
			pageNumber : data.length == -1 ? 0 : data.start / data.length + 1,
			pageSize : data.length == -1 ? 0 : data.length
		} ),
		dataType : "json",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
		success : function(res) {
			var data = [];
			tableData.recordsTotal = HBNodeAccess.hbNodes.length;
			tableData.recordsFiltered = HBNodeAccess.hbNodes.length;
			if ( res.code == 0 ) {
				data = res.data;
			}
			$.each( HBNodeAccess.hbNodes, function(index, node) {
				var lineData = {};
				var value = "";

				lineData.DT_RowId = "_" + index;
				value = findColumnValue( data, node.nodeId, "resourceId" );
				lineData["resourceId"] = value == "" ? -1 : value;
				lineData["resourceTypeA"] = "NODE";
				lineData["resourceTypeB"] = orgId;
				lineData["resourceTypeC"] = node.nodeId;
				lineData["resourceTypeD"] = node.typeId;
				value = findColumnValue( data, node.nodeId, "prflag" );
				lineData["prflag"] = value == "" ? 0 : value;

				tableData.data.push( lineData );
			} );

			callback( tableData );
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError( -900, "操作未完成，向服务器请求失败..." );
			callback( tableData );
		}
	} );
}
/*******************************************************************************************************************************************************************************************************
 * 查询列值
 ******************************************************************************************************************************************************************************************************/
function findColumnValue(datas, nodeId, column) {
	var value = "";
	$.each( datas, function(index, data) {
		if ( data.resourceTypeC == nodeId ) {
			value = data[column];
		}
	} );

	return value;
}
/*******************************************************************************************************************************************************************************************************
 * 错误提示框
 ******************************************************************************************************************************************************************************************************/
function callError(code, message) {
	$( "#mwTitle" ).html( '<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>' );
	$( "#mwMessage" ).html( message );
	$( "#modal-warning" ).modal( "show" );
}