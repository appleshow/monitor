// ************************
// 窗体 全局变量定义
// ************************
var modifytype = null;

/**
 * 窗体 启动 事件
 */
function pageLoad() {
	pageIni();
}

// 初始化 Grid
function pageIni() {

	// 初始化treeOrg
	$("#treeOrg").tree({
		animate : true,
		checkbox : false,
		cascadeCheck : false,
		lines : true,
		dnd : false
	});

}

/**
 * 查询组织架构
 */
function orgRef() {

	var url = "orgConfig.referOrg";
	var inf = {};

	$('#treeOrg').tree('loadData', []);

	// 加载ORG
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : ServerRequestPar(0, inf),
		dataType : "json",
		headers : {
			'Content-Type' : 'application/json;charset=utf-8'
		},
		success : function(res) {
			if (res.code != 0) {
				$smsg(res.message, "E", res.code);
			} else {
				for (var icnt = 0; icnt < res.data.length; icnt++) {
					if (res.data[icnt].farOrgId == 0) {
						$('#treeOrg').tree('append', {
							parent : null,
							data : [
								{
									id : 'node' + res.data[icnt].orgId,
									text : res.data[icnt].orgName,
									attributes : res.data[icnt].prflag,
								}
							]
						});
					} else {
						$('#treeOrg').tree('append', {
							parent : $('#treeOrg').tree("find", "node" + res.data[icnt].farOrgId).target,
							data : [
								{
									id : 'node' + res.data[icnt].orgId,
									text : res.data[icnt].orgName,
									attributes : res.data[icnt].prflag
								}
							]
						});
					}
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
		}
	});
}
/**
 * 编辑组织架构
 */
function orgEdit() {
	var selectnode = $('#treeOrg').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个组织架构结点...!!", "E", -100);
	} else {
		modifytype = "U";
		$obj("far_org_id").value = replace(selectnode.id, "node", "");
		$('#org_name').textbox('setValue', selectnode.text);
		$('#org_seq').textbox('setValue', selectnode.attributes);
		$("#modifyTitle").html("&nbsp;&nbsp;&nbsp;编辑组织架构：<" + selectnode.text + ">");
		$('#orgModiy').window('open');
		$("#org_name").textbox("textbox").focus();
		$("#org_name").textbox("textbox").select();
	}
}
/**
 * 新增组织架构
 */
function orgAdd() {
	var selectnode = $('#treeOrg').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个组织架构结点...!!", "E", -100);
	} else {
		modifytype = "I";
		$obj("far_org_id").value = replace(selectnode.id, "node", "");
		$('#org_name').textbox('setValue', "");
		$('#org_seq').textbox('setValue', "");
		$("#modifyTitle").html("&nbsp;&nbsp;&nbsp;新增组织架构到：<" + selectnode.text + ">");
		$('#orgModiy').window('open');
		$("#org_name").textbox("textbox").focus();
	}
}
/**
 * 删除组织架构
 */
function orgDel() {
	var selectnode = $('#treeOrg').tree('getSelected');

	if (selectnode == null) {
		$smsg("请选择一个组织架构结点...!!", "E", -100);
	} else {
		var orgid = replace(selectnode.id, "node", "");
		modifytype = "D";
		$cmsg("确定删除组织架构：" + selectnode.text + " ..??", function(bok) {
			if (bok) {
				var url = "orgConfig.modifyOrg";
				var inf = {
					parCount : 1,
					inPar : [
						{
							_type : modifytype,
							orgId : orgid
						}
					]
				};
				$.ajax({
					type : "POST",
					url : url,
					cache : false,
					data : JSON.stringify(inf),
					dataType : "json",
					headers : {
						'Content-Type' : 'application/json;charset=utf-8'
					},
					success : function(res) {
						if (res.code != 0) {
							$smsg(res.message, "E", res.code);
						} else {
							orgRef();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
					}
				});
			}
		});
	}
}

/**
 * 
 */
function selectOrgName() {
	$("#org_name").textbox("textbox").focus();
	$("#org_name").textbox("textbox").select();
}

/**
 * 
 */
function selectOrgSeq() {
	$("#org_seq").textbox("textbox").focus();
	$("#org_seq").textbox("textbox").select();
}

/**
 * 
 */
function modifyOK() {
	var selectnode = $('#treeOrg').tree('getSelected');
	var orgnameo = selectnode.text, orgseqo = selectnode.attributes;
	var orgnamen = $("#org_name").textbox("getValue"), orgseqn = $("#org_seq").textbox("getValue");
	var orgid = $obj("far_org_id").value;

	if (orgnameo != null || orgnameo != orgnamen || orgseqo != orgseqn) {
		var url = "orgConfig.modifyOrg";
		var inf = {};

		if (modifytype == "U" || modifytype == "D") {
			inf = {
				parCount : 1,
				inPar : [
					{
						_type : modifytype,
						orgId : orgid,
						orgName : orgnamen,
						prflag : orgseqn
					}
				]
			};
		} else {
			inf = {
				parCount : 1,
				inPar : [
					{
						_type : modifytype,
						farOrgId : orgid,
						orgName : orgnamen,
						prflag : orgseqn
					}
				]
			};
		}

		$.ajax({
			type : "POST",
			url : url,
			cache : false,
			data : JSON.stringify(inf),
			dataType : "json",
			headers : {
				'Content-Type' : 'application/json;charset=utf-8'
			},
			success : function(res) {
				if (res.code != 0) {
					$smsg(res.message, "E", res.code);
				} else {
					$('#orgModiy').window('close');
					orgRef();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$smsg(textStatus != null ? textStatus : errorThrown, "E", XMLHttpRequest.status);
			}
		});
	}
}

/**
 * 
 */
function modifyCancel() {
	$('#orgModiy').window('close');
}