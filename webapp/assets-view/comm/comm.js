var sSysName = "XXXXXXX系统";

/**
 * AJAX请求XMLHttp
 * 
 * @returns
 */
function NewXMLHttpRequest() {
	var xmlHttp = null;

	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}

	return xmlHttp;
}

/**
 * 向服务器发送AJAX请求
 * 
 * @param xmlHttp
 *            AJAX请求
 * @param url
 *            请求地址
 * @param par
 *            请求参数
 * @param fcallback
 *            回调函数
 */
function AjaxRquest(xmlHttp, url, par, fcallback) {

	if (xmlHttp != null) {
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = fcallback;
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;text/html;charset=UTF-8");

		xmlHttp.send(par);
	} else {
		xmlHttp = null;
		$smsg("该浏览器不支持Ajax...!!", "E");
	}

}

/**
 * 将json数据转化为Tree数据格式。 json数据必须包含 id(结点)、fid(父结点)、text(结点名称)，可包含attributes。
 * 
 * @param data
 *            JSON数据
 * @param startrow
 *            读取JSON数据起始行
 */
function Json2Tree(data, key, farKey, text, root, start) {

	if (!data) {
		return [];
	} else {
		for (var i = start; i < data.length; i++) {
			if ("id" !== key) {
				data[i]["id"] = data[i][key];
			}
			data[i]["text"] = data[i][text];
		}
	}
	// 最终返回结果
	var _newData = [];
	// 记录一级节点
	var _treeArray = {};
	// 最顶层fid
	var _root = root;
	// 主键的键名
	var _idKey = key;
	// 父ID的键名
	var _fidKey = farKey;

	_getChildren(_root);
	function _getChildren($root) {
		var $children = [];
		for (var i = start; i < data.length; i++) {
			if ($root == data[i][_fidKey]) {
				data[i]["children"] = _getChildren(data[i][_idKey]);
				$children.push(data[i]);
			}
			// 只要一级节点
			if (_root == data[i][_fidKey] && !_treeArray[data[i][_idKey]]) {
				_treeArray[data[i][_idKey]] = data[i];
				_newData.push(data[i]);
			}
		}
		return $children;
	}

	return _newData;

}

/**
 * 设置指定单元格内容
 * 
 * @param irow
 * @param scol
 * @param svalue
 */
function SetCellValue(grd, irow, scol, svalue) {

	var row = null, rows = null;

	if (grd.crtrow != irow) {
		rows = grd.Grid.datagrid("getRows");
		for (var icnt = 0; icnt < rows.length; icnt++) {
			if (icnt == irow) {
				row = rows[icnt];
				break;
			}
		}
	} else {
		row = grd.Grid.datagrid('getSelected');
	}

	if (row != null) {
		row[scol] = svalue;
		grd.Grid.datagrid('updateRow', {
			index : irow,
			row : row
		});
	}
	row = null;
	rows = null;

};

/**
 * 取得指定cmb的填充数据
 * 
 * @param cmbdata
 *            数据列表集合
 * @param type
 *            对应数据列表DTYPE列匹配值
 * @param tag
 *            对应数据列表DTAG列匹配值
 * @returns {String}
 */
function GetCmbData(cmbdata, type, tag) {

	var bfind = false, bexist = false;
	var scmbdata = "";

	if (cmbdata == null) {
		return '[{"DCODE":"","DNAME":""}]';
	}
	for (icnt = 0; icnt < cmbdata.length; icnt++) {
		bfind = false;
		if (type == cmbdata[icnt].DTYPE) {
			if (tag != '') {
				if (tag == cmbdata[icnt].DTAG) {
					bfind = true;
				} else {
					bfind = false;
				}
			} else {
				bfind = true;
			}
		}
		if (bfind) {
			bexist = true;
			if (scmbdata == '') {
				scmbdata += '[{"DCODE":"' + cmbdata[icnt].DCODE + '","DNAME":"' + cmbdata[icnt].DNAME + '"}';
			} else {
				scmbdata += ',{"DCODE":"' + cmbdata[icnt].DCODE + '","DNAME":"' + cmbdata[icnt].DNAME + '"}';
			}
		}
	}
	scmbdata += ']';

	if (!bexist) {
		scmbdata = '[{"DCODE":"","DNAME":""}]';
	}
	return scmbdata;
}

/**
 * 返回格式化的行索引
 * 
 * @param index
 * @returns
 */
function fmtindex(index) {
	var val = null;

	if (index == null) {
		val = null;
	} else {
		val = "<" + index + ">";
	}

	return val;
}
/**
 * @param sstr
 * @param sfind
 * @returns
 */
function instr(sstr, sfind) {

	var val = -1;

	if (sstr != null) {
		val = sstr.indexOf(sfind);
	}
	return val;

}

/**
 * @param sstr
 * @param sfind
 * @returns
 */
function replace(sstr, sfind, sother) {
	var val = null;

	if (sstr != null) {
		val = sstr.replace(sfind, sother);
	}

	return val;
}
/**
 * 
 * @param str
 * @returns
 */
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * @param str
 */
function ltrim(str) {
	return str.replace(/(^\s*)/g, "");
};

/**
 * @param str
 */
function rtrim(str) {
	return str.replace(/(\s*$)/g, "");
};

/**
 * 弹出提示框
 * 
 * @param msg
 * @param code
 */
function $smsg(msg, type, code) {

	var show = null;

	if (code != null) {
		show = "<font size='2'>Code: " + code + "<br/>Message: " + msg + "</font>";
	} else {
		show = "<font size='2'>" + msg + "</font>";
	}
	switch (type) {
	case "I":
		$.messager.alert(sSysName, show, 'info');
		break;
	case "E":
		$.messager.alert(sSysName, show, 'error');
		break;
	case "Q":
		$.messager.alert(sSysName, show, 'question');
		break;
	case "W":
		$.messager.alert(sSysName, show, 'warning');
		break;
	default:
		$.messager.alert(sSysName, show);
	}
}

// *****************************
// 弹出确认框
// *****************************
function $cmsg(msg, callback) {
	$.messager.confirm(sSysName, "<font size='2'>" + msg + "</font>", callback);
}

// *****************************
// 弹出确认框
// *****************************
function $cwmsg(msg) {
	return window.confirm(msg, sSysName);
}

// *****************************
// 弹出输入框
// *****************************
function $input(msg, callback) {
	$.messager.prompt(sSysName, msg, callback);
	// return prompt(msg, dvalue);
}

// *****************************
// 通过ID返回对应的实例
// *****************************
function $obj(id) {
	return document.getElementById(id);
}

// *****************************
// 返回对象的value属性
// *****************************
function $value(id) {
	return $obj(id).value;
}

function ServerRequestPar(parCount, inPars) {
    var par = {
        parCount : parCount,
        inPar : inPars
    };

    return JSON.stringify(par).replace(/%/g, "%25").replace(/\&/g, "%26").replace(/\+/g, "%2B");
}

/**
 * json2.js 2012-10-08
 * 
 * Public Domain.
 * 
 * NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.
 * 
 * See http://www.JSON.org/js.html
 * 
 * 
 * This code should be minified before deployment. See http://javascript.crockford.com/jsmin.html
 * 
 * USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO NOT CONTROL.
 * 
 * 
 * This file creates a global JSON object containing two methods: stringify and parse.
 * 
 * JSON.stringify(value, replacer, space) value any JavaScript value, usually an object or array.
 * 
 * replacer an optional parameter that determines how object values are stringified for objects. It can be a function or an array of strings.
 * 
 * space an optional parameter that specifies the indentation of nested structures. If it is omitted, the text will be packed without extra whitespace. If it is a number, it will specify the number of
 * spaces to indent at each level. If it is a string (such as '\t' or '&nbsp;'), it contains the characters used to indent at each level.
 * 
 * This method produces a JSON text from a JavaScript value.
 * 
 * When an object value is found, if the object contains a toJSON method, its toJSON method will be called and the result will be stringified. A toJSON method does not serialize: it returns the value
 * represented by the name/value pair that should be serialized, or undefined if nothing should be serialized. The toJSON method will be passed the key associated with the value, and this will be
 * bound to the value
 * 
 * For example, this would serialize Dates as ISO strings.
 * 
 * Date.prototype.toJSON = function (key) { function f(n) { // Format integers to have at least two digits. return n < 10 ? '0' + n : n; }
 * 
 * return this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':' + f(this.getUTCMinutes()) + ':' + f(this.getUTCSeconds()) + 'Z'; };
 * 
 * You can provide an optional replacer method. It will be passed the key and value of each member, with this bound to the containing object. The value that is returned from your method will be
 * serialized. If your method returns undefined, then the member will be excluded from the serialization.
 * 
 * If the replacer parameter is an array of strings, then it will be used to select the members to be serialized. It filters the results such that only members with keys listed in the replacer array
 * are stringified.
 * 
 * Values that do not have JSON representations, such as undefined or functions, will not be serialized. Such values in objects will be dropped; in arrays they will be replaced with null. You can use
 * a replacer function to replace those with JSON values. JSON.stringify(undefined) returns undefined.
 * 
 * The optional space parameter produces a stringification of the value that is filled with line breaks and indentation to make it easier to read.
 * 
 * If the space parameter is a non-empty string, then that string will be used for indentation. If the space parameter is a number, then the indentation will be that many spaces.
 * 
 * Example:
 * 
 * text = JSON.stringify(['e', {pluribus: 'unum'}]); // text is '["e",{"pluribus":"unum"}]'
 * 
 * 
 * text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t'); // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'
 * 
 * text = JSON.stringify([new Date()], function (key, value) { return this[key] instanceof Date ? 'Date(' + this[key] + ')' : value; }); // text is '["Date(---current time---)"]'
 * 
 * 
 * JSON.parse(text, reviver) This method parses a JSON text to produce an object or array. It can throw a SyntaxError exception.
 * 
 * The optional reviver parameter is a function that can filter and transform the results. It receives each of the keys and values, and its return value is used instead of the original value. If it
 * returns what it received, then the structure is not modified. If it returns undefined then the member is deleted.
 * 
 * Example: // Parse the text. Values that look like ISO date strings will // be converted to Date objects.
 * 
 * myData = JSON.parse(text, function (key, value) { var a; if (typeof value === 'string') { a = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value); if (a) { return new
 * Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4], +a[5], +a[6])); } } return value; });
 * 
 * myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) { var d; if (typeof value === 'string' && value.slice(0, 5) === 'Date(' && value.slice(-1) === ')') { d = new Date(value.slice(5,
 * -1)); if (d) { return d; } } return value; });
 * 
 * 
 * This is a reference implementation. You are free to copy, modify, or redistribute.
 * 
 * jslint evil: true, regexp: true members "", "\b", "\t", "\n", "\f", "\r", "\"", JSON, "\\", apply, call, charCodeAt, getUTCDate, getUTCFullYear, getUTCHours, getUTCMinutes, getUTCMonth,
 * getUTCSeconds, hasOwnProperty, join, lastIndex, length, parse, prototype, push, replace, slice, stringify, test, toJSON, toString, valueOf
 */

// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.
if (typeof JSON !== 'object') {
	JSON = {};
}

(function() {
	'use strict';

	function f(n) {
		// Format integers to have at least two digits.
		return n < 10 ? '0' + n : n;
	}

	if (typeof Date.prototype.toJSON !== 'function') {

		Date.prototype.toJSON = function(key) {

			return isFinite(this.valueOf()) ? this.getUTCFullYear() + '-' + f(this.getUTCMonth() + 1) + '-' + f(this.getUTCDate()) + 'T' + f(this.getUTCHours()) + ':' + f(this.getUTCMinutes()) + ':'
					+ f(this.getUTCSeconds()) + 'Z' : null;
		};

		String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function(key) {
			return this.valueOf();
		};
	}

	var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, gap, indent, meta = { // table
		// of
		// character
		// substitutions
		'\b' : '\\b',
		'\t' : '\\t',
		'\n' : '\\n',
		'\f' : '\\f',
		'\r' : '\\r',
		'"' : '\\"',
		'\\' : '\\\\'
	}, rep;

	function quote(string) {

		// If the string contains no control characters, no quote characters,
		// and no
		// backslash characters, then we can safely slap some quotes around it.
		// Otherwise we must also replace the offending characters with safe
		// escape
		// sequences.

		escapable.lastIndex = 0;
		return escapable.test(string) ? '"' + string.replace(escapable, function(a) {
			var c = meta[a];
			return typeof c === 'string' ? c : '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
		}) + '"' : '"' + string + '"';
	}

	function str(key, holder) {

		// Produce a string from holder[key].

		var i, // The loop counter.
		k, // The member key.
		v, // The member value.
		length, mind = gap, partial, value = holder[key];

		// If the value has a toJSON method, call it to obtain a replacement
		// value.

		if (value && typeof value === 'object' && typeof value.toJSON === 'function') {
			value = value.toJSON(key);
		}

		// If we were called with a replacer function, then call the replacer to
		// obtain a replacement value.

		if (typeof rep === 'function') {
			value = rep.call(holder, key, value);
		}

		// What happens next depends on the value's type.

		switch (typeof value) {
		case 'string':
			return quote(value);

		case 'number':

			// JSON numbers must be finite. Encode non-finite numbers as null.

			return isFinite(value) ? String(value) : 'null';

		case 'boolean':
		case 'null':

			// If the value is a boolean or null, convert it to a string. Note:
			// typeof null does not produce 'null'. The case is included here in
			// the remote chance that this gets fixed someday.

			return String(value);

			// If the type is 'object', we might be dealing with an object or an
			// array or
			// null.

		case 'object':

			// Due to a specification blunder in ECMAScript, typeof null is
			// 'object',
			// so watch out for that case.

			if (!value) {
				return 'null';
			}

			// Make an array to hold the partial results of stringifying this
			// object value.

			gap += indent;
			partial = [];

			// Is the value an array?

			if (Object.prototype.toString.apply(value) === '[object Array]') {

				// The value is an array. Stringify every element. Use null as a
				// placeholder
				// for non-JSON values.

				length = value.length;
				for (i = 0; i < length; i += 1) {
					partial[i] = str(i, value) || 'null';
				}

				// Join all of the elements together, separated with commas, and
				// wrap them in
				// brackets.

				v = partial.length === 0 ? '[]' : gap ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' : '[' + partial.join(',') + ']';
				gap = mind;
				return v;
			}

			// If the replacer is an array, use it to select the members to be
			// stringified.

			if (rep && typeof rep === 'object') {
				length = rep.length;
				for (i = 0; i < length; i += 1) {
					if (typeof rep[i] === 'string') {
						k = rep[i];
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ': ' : ':') + v);
						}
					}
				}
			} else {

				// Otherwise, iterate through all of the keys in the object.

				for (k in value) {
					if (Object.prototype.hasOwnProperty.call(value, k)) {
						v = str(k, value);
						if (v) {
							partial.push(quote(k) + (gap ? ': ' : ':') + v);
						}
					}
				}
			}

			// Join all of the member texts together, separated with commas,
			// and wrap them in braces.

			v = partial.length === 0 ? '{}' : gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' : '{' + partial.join(',') + '}';
			gap = mind;
			return v;
		}
	}

	// If the JSON object does not yet have a stringify method, give it one.

	if (typeof JSON.stringify !== 'function') {
		JSON.stringify = function(value, replacer, space) {

			// The stringify method takes a value and an optional replacer, and
			// an optional
			// space parameter, and returns a JSON text. The replacer can be a
			// function
			// that can replace values, or an array of strings that will select
			// the keys.
			// A default replacer method can be provided. Use of the space
			// parameter can
			// produce text that is more easily readable.

			var i;
			gap = '';
			indent = '';

			// If the space parameter is a number, make an indent string
			// containing that
			// many spaces.

			if (typeof space === 'number') {
				for (i = 0; i < space; i += 1) {
					indent += ' ';
				}

				// If the space parameter is a string, it will be used as the
				// indent string.

			} else if (typeof space === 'string') {
				indent = space;
			}

			// If there is a replacer, it must be a function or an array.
			// Otherwise, throw an error.

			rep = replacer;
			if (replacer && typeof replacer !== 'function' && (typeof replacer !== 'object' || typeof replacer.length !== 'number')) {
				throw new Error('JSON.stringify');
			}

			// Make a fake root object containing our value under the key of ''.
			// Return the result of stringifying the value.

			return str('', {
				'' : value
			});
		};
	}

	// If the JSON object does not yet have a parse method, give it one.

	if (typeof JSON.parse !== 'function') {
		JSON.parse = function(text, reviver) {

			// The parse method takes a text and an optional reviver function,
			// and returns
			// a JavaScript value if the text is a valid JSON text.

			var j;

			function walk(holder, key) {

				// The walk method is used to recursively walk the resulting
				// structure so
				// that modifications can be made.

				var k, v, value = holder[key];
				if (value && typeof value === 'object') {
					for (k in value) {
						if (Object.prototype.hasOwnProperty.call(value, k)) {
							v = walk(value, k);
							if (v !== undefined) {
								value[k] = v;
							} else {
								delete value[k];
							}
						}
					}
				}
				return reviver.call(holder, key, value);
			}

			// Parsing happens in four stages. In the first stage, we replace
			// certain
			// Unicode characters with escape sequences. JavaScript handles many
			// characters
			// incorrectly, either silently deleting them, or treating them as
			// line endings.

			text = String(text);
			cx.lastIndex = 0;
			if (cx.test(text)) {
				text = text.replace(cx, function(a) {
					return '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
				});
			}

			// In the second stage, we run the text against regular expressions
			// that look
			// for non-JSON patterns. We are especially concerned with '()' and
			// 'new'
			// because they can cause invocation, and '=' because it can cause
			// mutation.
			// But just to be safe, we want to reject all unexpected forms.

			// We split the second stage into 4 regexp operations in order to
			// work around
			// crippling inefficiencies in IE's and Safari's regexp engines.
			// First we
			// replace the JSON backslash pairs with '@' (a non-JSON character).
			// Second, we
			// replace all simple value tokens with ']' characters. Third, we
			// delete all
			// open brackets that follow a colon or comma or that begin the
			// text. Finally,
			// we look to see that the remaining characters are only whitespace
			// or ']' or
			// ',' or ':' or '{' or '}'. If that is so, then the text is safe
			// for eval.

			if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(
					/(?:^|:|,)(?:\s*\[)+/g, ''))) {

				// In the third stage we use the eval function to compile the
				// text into a
				// JavaScript structure. The '{' operator is subject to a
				// syntactic ambiguity
				// in JavaScript: it can begin a block or an object literal. We
				// wrap the text
				// in parens to eliminate the ambiguity.

				j = eval('(' + text + ')');

				// In the optional fourth stage, we recursively walk the new
				// structure, passing
				// each name/value pair to a reviver function for possible
				// transformation.

				return typeof reviver === 'function' ? walk({
					'' : j
				}, '') : j;
			}

			// If the text is not JSON parseable, then a SyntaxError is thrown.

			throw new SyntaxError('JSON.parse');
		};
	}
}());
