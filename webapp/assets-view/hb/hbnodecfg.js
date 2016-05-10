var tableTypeItem, CombTypeData = [], CombNodeData = [];
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

								CombNodeData = res.data;
								$.each(res.data, function(index, value) {
									var item = {};

									item.id = "node" + value.nodeId;
									item.name = value.nodeName + "<div class='tree-actions'><i onclick=showNodeModal(" + value.typeId + "," + value.nodeId
											+ ",'U') class='glyphicon glyphicon-pencil blue' title='编辑站点'></i> <i onclick=deleteNode(" + value.nodeId
											+ ") class='glyphicon glyphicon-minus red' title='删除站点'></i> </div>";
									item.type = 'item';

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

							CombTypeData = res.data;
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

	$("#typeId").select2({
		placeholder : "选择一个类型",
		allowClear : true,
		language : "zh-CN"
	});

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

	$.each(CombTypeData, function(index, value) {
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
		$.each(CombNodeData, function(index, value) {
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
function deleteNode(nodeId) {

}

/**
 * 
 */
function modifyNode() {
	if ($("#node-name").val() === "") {
		$("#node-name").attr('placeholder', '此项不能为空');
		$("#node-name").focus();
	} else if ($("#node-mn").val() === "") {
		$("#node-mn").attr('placeholder', '此项不能为空');
		$("#node-mn").focus();
	} else if (isNaN(($("#node-lx").val()).replace(" ", "a"))) {
		$("#node-lx").val("");
		$("#node-lx").attr('placeholder', '此项必须为数字');
		$("#node-lx").focus();
	} else if (isNaN(($("#node-ly").val()).replace(" ", "a"))) {
		$("#node-ly").val("");
		$("#node-ly").attr('placeholder', '此项必须为数字');
		$("#node-ly").focus();
	} else {
		var nodeAtr = {
			lx : $("#node-lx").val(),
			ly : $("#node-ly").val()
		};

		$.ajax({
			async : false,
			type : "POST",
			url : "hbNodeConfig.modifyHbNode",
			cache : false,
			data : ServerRequestPar(1, [ {
				_type : $("#action-type").val(),
				typeId : $("#type-id").val(),
				nodeId : $("#node-id").val(),
				nodeName : $("#node-name").val(),
				nodeMn : $("#node-mn").val(),
				nodeAtr : JSON.stringify(nodeAtr)
			} ]),
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
}

function callError(code, message) {
	$("#mwTitle").html('<span class="glyphicon glyphicon-bullhorn" aria-hidden="true">&nbsp;警告</span>');
	$("#mwMessage").html(message);
	$("#modal-warning").modal("show");
}