var tableTypeItem, combTypeData = [], combTypeItemData = [], combNodeData = [], selectTypeId = 0, selectNodeId = 0;
var DataSourceTree = function(options) {
	this._data = options.data;
	this._delay = options.delay
};

DataSourceTree.prototype = {
	data : function(options, callback) {
		setTimeout(function() {
			var url = "hbNodeConfig.referHbType", treeId = "";

			if (options.id != null) {
				if (options.type === "folder") {
					url = "hbNodeConfig.referHbNode";
					treeId = options.id.replace("type", "");

					$.ajax({
						async : false,
						type : "POST",
						url : url,
						cache : false,
						data : ServerRequestPar(1, {
							typeId : treeId
						}),
						dataType : "json",
						success : function(res) {
							if (res.code != 0) {
								callback({
									data : []
								});
							} else {
								var treeData = [];

								$.merge(combNodeData, res.data);
								$.each(res.data, function(index, value) {
									var item = {};

									item.id = "node" + value.nodeId;
									item.name = value.nodeName + "<div class='tree-actions'><i onclick=showNodeModal(" + value.typeId + "," + value.nodeId
											+ ",'U') class='glyphicon glyphicon-pencil blue' title='编辑站点'></i> <i onclick=deleteNode(" + value.nodeId + ",'" + value.nodeName
											+ "') class='glyphicon glyphicon-minus red' title='删除站点'></i> </div>";
									item.type = 'item';
									item.typeId = value.typeId;
									item.nodeId = value.nodeId;

									treeData.push(item);
								});

								callback({
									data : treeData
								});

							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							callback({
								data : []
							});
						}
					});

				} else {

				}
			} else {
				url = "hbNodeConfig.referHbType";

				$.ajax({
					async : false,
					type : "POST",
					url : url,
					cache : false,
					data : ServerRequestPar(0, {}),
					dataType : "json",
					success : function(res) {
						if (res.code != 0) {
							callback({
								data : []
							});
						} else {
							var treeData = [];

							combTypeData = res.data;
							if (!(res.subJoin === undefined || res.subJoin === null)) {
								if (!(res.subJoin["typeItems"] === undefined || res.subJoin["typeItems"] === null)) {
									combTypeItemData = res.subJoin["typeItems"];
								}
							}
							$.each(res.data, function(index, value) {
								var item = {};

								item.id = "type" + value.typeId;
								item.name = value.typeName + "<div class='tree-actions'><i onclick=showNodeModal(" + value.typeId
										+ ",'','I') class='glyphicon glyphicon-plus green' title='添加站点'></i></div>";
								item.type = 'folder';

								treeData.push(item);
							});

							callback({
								data : treeData
							});

						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						callback({
							data : []
						});
					}
				});
			}

		}, this._delay)
	}
};

var treeDataNode = new DataSourceTree({
	data : [],
	delay : 400
});

jQuery(document).ready(function() {
	$('#tree-node').tree({
		cacheItems : true,
		selectable : false,
		dataSource : treeDataNode,
		loadingHTML : '<div class="tree-loading"><i class="fa fa-rotate-right fa-spin"></i></div>'
	});

	tableTypeItem = new CommDataTables("#table-typeitem", "#table-typeitem-columns", 12, callError);
	tableTypeItem.serverInfo.referUrl = "hbTypeItemConfig.referHbTypeItem";
	tableTypeItem.serverInfo.modifyUrl = "hbTypeItemConfig.modifyHbTypeItem";
	tableTypeItem.lengthInfo = {
		lengthMenu : [ [ -1 ], [ "全部" ] ],
		pageLength : -1
	};
	tableTypeItem.scrollY = 75;
	tableTypeItem.buttons = "EP";
	
	// ***** Add information to Column *****
	// *********************************
	// ***** Add information to Field *****
	// *********************************

	tableTypeItem.create(null, dataTableAjax);
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
				combTypeData = res.data;

				var html = "";
				$.each(res.data, function(index, value) {
					html += "<option value='" + value.typeId + "'>" + value.typeName + "</option>";
				});

				$("#typeId").append(html);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callError(-900, "操作未完成，向服务器请求失败...");
		}
	});
}

function showNodeModal(typeId, nodeId, type) {
	canshow = false;

	$.each(combTypeData, function(index, value) {
		if (value["typeId"] === typeId) {
			$("#type-name").val(value["typeName"]);
		}
	});
	$("#type-id").val(typeId);
	$("#node-id").val(nodeId);
	$("#action-type").val(type);
	$("#node-message-label").hide();
	$("#node-name").val("");
	$("#node-name").attr('placeholder', '');
	$("#node-mn").val("");
	$("#node-mn").attr('placeholder', '');
	$("#node-lx").val("");
	$("#node-ly").val("");

	if (type === "U") {
		$("#node-modal-title").html("&nbsp;&nbsp;&nbsp;更新站点信息");
		$.each(combNodeData, function(index, value) {
			if (value["nodeId"] === nodeId) {
				$("#node-name").val(value["nodeName"]);
				$("#node-mn").val(value["nodeMn"]);
				if (!(value["nodeAtr"] === undefined || value["nodeAtr"] === null || value["nodeAtr"] === "")) {
					var nodeAtr = $.parseJSON(value["nodeAtr"]);

					$("#node-lx").val(nodeAtr["lx"]);
					$("#node-ly").val(nodeAtr["ly"]);
				}
			}
		});
	} else {
		$("#node-modal-title").html("&nbsp;&nbsp;&nbsp;新增一个站点");
	}
	$('#node-modal').modal({
		backdrop : 'static',
		keyboard : true
	});

	$('#node-modal').on('shown.bs.modal', function(e) {
		canshow = true;
		$("#node-name").focus();
	})

}

/**
 * 
 * @param nodeId
 */
function deleteNode(nodeId, nodeName) {
	canshow = false;

	$("#action-type").val("D");
	$("#node-id").val(nodeId);
	$("#labl-delete-body").html("&nbsp;确定要删除站点【" + nodeName + "】？");
	$('#node-modal-delete').modal({
		backdrop : 'static',
		keyboard : true
	});
	canshow = true;
}

/**
 * 
 */
function modifyNode() {
	var type = $("#action-type").val(), serverRequestPar;

	if (type === "D") {
		serverRequestPar = ServerRequestPar(1, [ {
			_type : type,
			nodeId : $("#node-id").val(),
		} ]);
	} else if (type === "I" || type == "U") {
		if ($("#node-name").val() === "") {
			$("#node-name").attr('placeholder', '此项不能为空');
			$("#node-name").focus();
			return;
		} else if ($("#node-mn").val() === "") {
			$("#node-mn").attr('placeholder', '此项不能为空');
			$("#node-mn").focus();
			return;
		} else if (isNaN(($("#node-lx").val()).replace(" ", "a"))) {
			$("#node-lx").val("");
			$("#node-lx").attr('placeholder', '此项必须为数字');
			$("#node-lx").focus();
			return;
		} else if (isNaN(($("#node-ly").val()).replace(" ", "a"))) {
			$("#node-ly").val("");
			$("#node-ly").attr('placeholder', '此项必须为数字');
			$("#node-ly").focus();
			return;
		} else {
			var nodeAtr = {
				lx : $("#node-lx").val(),
				ly : $("#node-ly").val()
			};

			serverRequestPar = ServerRequestPar(1, [ {
				_type : type,
				typeId : $("#type-id").val(),
				nodeId : $("#node-id").val(),
				nodeName : $("#node-name").val(),
				nodeMn : $("#node-mn").val(),
				nodeAtr : JSON.stringify(nodeAtr)
			} ]);
		}
	} else {
		return;
	}

	$.ajax({
		async : false,
		type : "POST",
		url : "hbNodeConfig.modifyHbNode",
		cache : false,
		data : serverRequestPar,
		dataType : "json",
		success : function(res) {
			if (res.code != 0) {
				$("#node-message").html(res.message);
				$("#node-message-label").show();
			} else {
				$('#node-modal').modal('hide');
				parent.refreshPage();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#node-message").html("服务器请求异常！");
			$("#node-message-label").show();
		}
	});
}

function dataTableAjax(data, callback, settings) {
	var tableData = {
		draw : settings.iDraw,
		recordsTotal : 0,
		recordsFiltered : 0,
		data : []
	};

	if (selectTypeId != 0 && selectNodeId != 0) {
		$.each(combTypeItemData, function(index, value) {
			if (value["typeId"] === selectTypeId) {
				value.DT_RowId = "_" + value.itemId;
				tableData.data.push(value);
				tableData.recordsTotal++;
				tableData.recordsFiltered++;
			}
		});
	}

	callback(tableData);
}

function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}