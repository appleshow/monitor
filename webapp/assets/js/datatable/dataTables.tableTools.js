﻿/*! TableTools 2.2.3
 * 2009-2014 SpryMedia Ltd - datatables.net/license
 *
 * ZeroClipboard 1.0.4
 * Author: Joseph Huckaby - MIT licensed
 */
var TableTools;
(function(n, t, i) {
	var r = function(r, u) {
			"use strict";
			var f = {
				version: "1.0.4-TableTools2",
				clients: {},
				moviePath: "",
				nextId: 1,
				$: function(n) {
					return typeof n == "string" && (n = t.getElementById(n)), n.addClass || (n.hide = function() {
						this.style.display = "none"
					}, n.show = function() {
						this.style.display = ""
					}, n.addClass = function(n) {
						this.removeClass(n);
						this.className += " " + n
					}, n.removeClass = function(n) {
						this.className = this.className.replace(new RegExp("\\s*" + n + "\\s*"), " ").replace(/^\s+/, "").replace(/\s+$/, "")
					}, n.hasClass = function(n) {
						return !!this.className.match(new RegExp("\\s*" + n + "\\s*"))
					}), n
				},
				setMoviePath: function(n) {
					this.moviePath = n
				},
				dispatch: function(n, t, i) {
					var r = this.clients[n];
					r && r.receiveEvent(t, i)
				},
				register: function(n, t) {
					this.clients[n] = t
				},
				getDOMObjectPosition: function(n) {
					var t = {
						left: 0,
						top: 0,
						width: n.width ? n.width : n.offsetWidth,
						height: n.height ? n.height : n.offsetHeight
					};
					for (n.style.width !== "" && (t.width = n.style.width.replace("px", "")), n.style.height !== "" && (t.height = n.style.height.replace("px", "")); n;) t.left += n.offsetLeft, t.top += n.offsetTop, n = n.offsetParent;
					return t
				},
				Client: function(n) {
					this.handlers = {};
					this.id = f.nextId++;
					this.movieId = "ZeroClipboard_TableToolsMovie_" + this.id;
					f.register(this.id, this);
					n && this.glue(n)
				}
			};
			return f.Client.prototype = {
				id: 0,
				ready: !1,
				movie: null,
				clipText: "",
				fileName: "",
				action: "copy",
				handCursorEnabled: !0,
				cssEffects: !0,
				handlers: null,
				sized: !1,
				glue: function(n, i) {
					var e, r, u;
					this.domElement = f.$(n);
					e = 99;
					this.domElement.style.zIndex && (e = parseInt(this.domElement.style.zIndex, 10) + 1);
					r = f.getDOMObjectPosition(this.domElement);
					this.div = t.createElement("div");
					u = this.div.style;
					u.position = "absolute";
					u.left = "0px";
					u.top = "0px";
					u.width = r.width + "px";
					u.height = r.height + "px";
					u.zIndex = e;
					typeof i != "undefined" && i !== "" && (this.div.title = i);
					r.width !== 0 && r.height !== 0 && (this.sized = !0);
					this.domElement && (this.domElement.appendChild(this.div), this.div.innerHTML = this.getHTML(r.width, r.height).replace(/&/g, "&amp;"))
				},
				positionElement: function() {
					var n = f.getDOMObjectPosition(this.domElement),
						t = this.div.style,
						i;
					if (t.position = "absolute", t.width = n.width + "px", t.height = n.height + "px", n.width !== 0 && n.height !== 0) this.sized = !0;
					else return;
					i = this.div.childNodes[0];
					i.width = n.width;
					i.height = n.height
				},
				getHTML: function(n, t) {
					var i = "",
						r = "id=" + this.id + "&width=" + n + "&height=" + t,
						u;
					return navigator.userAgent.match(/MSIE/) ? (u = location.href.match(/^https/i) ? "https://" : "http://", i += '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="' + u + 'download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" width="' + n + '" height="' + t + '" id="' + this.movieId + '" align="middle"><param name="allowScriptAccess" value="always" /><param name="allowFullScreen" value="false" /><param name="movie" value="' + f.moviePath + '" /><param name="loop" value="false" /><param name="menu" value="false" /><param name="quality" value="best" /><param name="bgcolor" value="#ffffff" /><param name="flashvars" value="' + r + '"/><param name="wmode" value="transparent"/><\/object>') : i += '<embed id="' + this.movieId + '" src="' + f.moviePath + '" loop="false" menu="false" quality="best" bgcolor="#ffffff" width="' + n + '" height="' + t + '" name="' + this.movieId + '" align="middle" allowScriptAccess="always" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="' + r + '" wmode="transparent" />', i
				},
				hide: function() {
					this.div && (this.div.style.left = "-2000px")
				},
				show: function() {
					this.reposition()
				},
				destroy: function() {
					if (this.domElement && this.div) {
						this.hide();
						this.div.innerHTML = "";
						var n = t.getElementsByTagName("body")[0];
						try {
							n.removeChild(this.div)
						} catch (i) {}
						this.domElement = null;
						this.div = null
					}
				},
				reposition: function(n) {
					if (n && (this.domElement = f.$(n), this.domElement || this.hide()), this.domElement && this.div) {
						var t = f.getDOMObjectPosition(this.domElement),
							i = this.div.style;
						i.left = "" + t.left + "px";
						i.top = "" + t.top + "px"
					}
				},
				clearText: function() {
					this.clipText = "";
					this.ready && this.movie.clearText()
				},
				appendText: function(n) {
					this.clipText += n;
					this.ready && this.movie.appendText(n)
				},
				setText: function(n) {
					this.clipText = n;
					this.ready && this.movie.setText(n)
				},
				setCharSet: function(n) {
					this.charSet = n;
					this.ready && this.movie.setCharSet(n)
				},
				setBomInc: function(n) {
					this.incBom = n;
					this.ready && this.movie.setBomInc(n)
				},
				setFileName: function(n) {
					this.fileName = n;
					this.ready && this.movie.setFileName(n)
				},
				setAction: function(n) {
					this.action = n;
					this.ready && this.movie.setAction(n)
				},
				addEventListener: function(n, t) {
					n = n.toString().toLowerCase().replace(/^on/, "");
					this.handlers[n] || (this.handlers[n] = []);
					this.handlers[n].push(t)
				},
				setHandCursor: function(n) {
					this.handCursorEnabled = n;
					this.ready && this.movie.setHandCursor(n)
				},
				setCSSEffects: function(n) {
					this.cssEffects = !! n
				},
				receiveEvent: function(i, r) {
					var f, e, o, u;
					i = i.toString().toLowerCase().replace(/^on/, "");
					switch (i) {
					case "load":
						if (this.movie = t.getElementById(this.movieId), !this.movie) {
							f = this;
							setTimeout(function() {
								f.receiveEvent("load", null)
							}, 1);
							return
						}
						if (!this.ready && navigator.userAgent.match(/Firefox/) && navigator.userAgent.match(/Windows/)) {
							f = this;
							setTimeout(function() {
								f.receiveEvent("load", null)
							}, 100);
							this.ready = !0;
							return
						}
						this.ready = !0;
						this.movie.clearText();
						this.movie.appendText(this.clipText);
						this.movie.setFileName(this.fileName);
						this.movie.setAction(this.action);
						this.movie.setCharSet(this.charSet);
						this.movie.setBomInc(this.incBom);
						this.movie.setHandCursor(this.handCursorEnabled);
						break;
					case "mouseover":
						this.domElement && this.cssEffects && this.recoverActive && this.domElement.addClass("active");
						break;
					case "mouseout":
						this.domElement && this.cssEffects && (this.recoverActive = !1, this.domElement.hasClass("active") && (this.domElement.removeClass("active"), this.recoverActive = !0));
						break;
					case "mousedown":
						this.domElement && this.cssEffects && this.domElement.addClass("active");
						break;
					case "mouseup":
						this.domElement && this.cssEffects && (this.domElement.removeClass("active"), this.recoverActive = !1)
					}
					if (this.handlers[i]) for (e = 0, o = this.handlers[i].length; e < o; e++) u = this.handlers[i][e], typeof u == "function" ? u(this, r) : typeof u == "object" && u.length == 2 ? u[0][u[1]](this, r) : typeof u == "string" && n[u](this, r)
				}
			}, n.ZeroClipboard_TableTools = f, function(n, t, r) {
				TableTools = function(t, i) {
					!this instanceof TableTools && alert("Warning: TableTools must be initialised with the keyword 'new'");
					var r = n.fn.dataTable.Api ? new n.fn.dataTable.Api(t).settings()[0] : t.fnSettings();
					return this.s = {
						that: this,
						dt: r,
						print: {
							saveStart: -1,
							saveLength: -1,
							saveScroll: -1,
							funcEnd: function() {}
						},
						buttonCounter: 0,
						select: {
							type: "",
							selected: [],
							preRowSelect: null,
							postSelected: null,
							postDeselected: null,
							all: !1,
							selectedClass: ""
						},
						custom: {},
						swfPath: "",
						buttonSet: [],
						master: !1,
						tags: {}
					}, this.dom = {
						container: null,
						table: null,
						print: {
							hidden: [],
							message: null
						},
						collection: {
							collection: null,
							background: null
						}
					}, this.classes = n.extend(!0, {}, TableTools.classes), this.s.dt.bJUI && n.extend(!0, this.classes, TableTools.classes_themeroller), this.fnSettings = function() {
						return this.s
					}, typeof i == "undefined" && (i = {}), TableTools._aInstances.push(this), this._fnConstruct(i), this
				};
				TableTools.prototype = {
					fnGetSelected: function(n) {
						var u = [],
							i = this.s.dt.aoData,
							f = this.s.dt.aiDisplay,
							t, r;
						if (n) for (t = 0, r = f.length; t < r; t++) i[f[t]]._DTTT_selected && u.push(i[f[t]].nTr);
						else for (t = 0, r = i.length; t < r; t++) i[t]._DTTT_selected && u.push(i[t].nTr);
						return u
					},
					fnGetSelectedData: function() {
						for (var t = [], i = this.s.dt.aoData, n = 0, r = i.length; n < r; n++) i[n]._DTTT_selected && t.push(this.s.dt.oInstance.fnGetData(n));
						return t
					},
					fnGetSelectedIndexes: function(n) {
						var r = [],
							u = this.s.dt.aoData,
							f = this.s.dt.aiDisplay,
							t, i;
						if (n) for (t = 0, i = f.length; t < i; t++) u[f[t]]._DTTT_selected && r.push(f[t]);
						else for (t = 0, i = u.length; t < i; t++) u[t]._DTTT_selected && r.push(t);
						return r
					},
					fnIsSelected: function(n) {
						var t = this.s.dt.oInstance.fnGetPosition(n);
						return this.s.dt.aoData[t]._DTTT_selected === !0 ? !0 : !1
					},
					fnSelectAll: function(n) {
						this._fnRowSelect(n ? this.s.dt.aiDisplay : this.s.dt.aoData)
					},
					fnSelectNone: function(n) {
						this._fnRowDeselect(this.fnGetSelectedIndexes(n))
					},
					fnSelect: function(n) {
						this.s.select.type == "single" ? (this.fnSelectNone(), this._fnRowSelect(n)) : this._fnRowSelect(n)
					},
					fnDeselect: function(n) {
						this._fnRowDeselect(n)
					},
					fnGetTitle: function(n) {
						var t = "",
							i;
						return typeof n.sTitle != "undefined" && n.sTitle !== "" ? t = n.sTitle : (i = r.getElementsByTagName("title"), i.length > 0 && (t = i[0].innerHTML)), "¡".toString().length < 4 ? t.replace(/[^a-zA-Z0-9_¡-￿\.,\-_ !\(\)]/g, "") : t.replace(/[^a-zA-Z0-9_\.,\-_ !\(\)]/g, "")
					},
					fnCalcColRatios: function(n) {
						for (var o = this.s.dt.aoColumns, f = this._fnColumnTargets(n.mColumns), i = [], u = 0, e = 0, t = 0, r = f.length; t < r; t++) f[t] && (u = o[t].nTh.offsetWidth, e += u, i.push(u));
						for (t = 0, r = i.length; t < r; t++) i[t] = i[t] / e;
						return i.join("\t")
					},
					fnGetTableData: function(n) {
						if (this.s.dt) return this._fnGetDataTablesData(n)
					},
					fnSetText: function(n, t) {
						this._fnFlashSetText(n, t)
					},
					fnResizeButtons: function() {
						var t, n;
						for (t in f.clients) t && (n = f.clients[t], typeof n.domElement != "undefined" && n.domElement.parentNode && n.positionElement())
					},
					fnResizeRequired: function() {
						var t, n;
						for (t in f.clients) if (t && (n = f.clients[t], typeof n.domElement != "undefined" && n.domElement.parentNode == this.dom.container && n.sized === !1)) return !0;
						return !1
					},
					fnPrint: function(n, t) {
						t === i && (t = {});
						n === i || n ? this._fnPrintStart(t) : this._fnPrintEnd()
					},
					fnInfo: function(t, i) {
						var r = n("<div/>").addClass(this.classes.print.info).html(t).appendTo("body");
						setTimeout(function() {
							r.fadeOut("normal", function() {
								r.remove()
							})
						}, i)
					},
					fnContainer: function() {
						return this.dom.container
					},
					_fnConstruct: function(t) {
						var i = this;
						this._fnCustomiseSettings(t);
						this.dom.container = r.createElement(this.s.tags.container);
						this.dom.container.className = this.classes.container;
						this.s.select.type != "none" && this._fnRowSelectConfig();
						this._fnButtonDefinations(this.s.buttonSet, this.dom.container);
						this.s.dt.aoDestroyCallback.push({
							sName: "TableTools",
							fn: function() {
								n(i.s.dt.nTBody).off("click.DTTT_Select", "tr");
								n(i.dom.container).empty();
								var t = n.inArray(i, TableTools._aInstances);
								t !== -1 && TableTools._aInstances.splice(t, 1)
							}
						})
					},
					_fnCustomiseSettings: function(t) {
						typeof this.s.dt._TableToolsInit == "undefined" && (this.s.master = !0, this.s.dt._TableToolsInit = !0);
						this.dom.table = this.s.dt.nTable;
						this.s.custom = n.extend({}, TableTools.DEFAULTS, t);
						this.s.swfPath = this.s.custom.sSwfPath;
						typeof f != "undefined" && (f.moviePath = this.s.swfPath);
						this.s.select.type = this.s.custom.sRowSelect;
						this.s.select.preRowSelect = this.s.custom.fnPreRowSelect;
						this.s.select.postSelected = this.s.custom.fnRowSelected;
						this.s.select.postDeselected = this.s.custom.fnRowDeselected;
						this.s.custom.sSelectedClass && (this.classes.select.row = this.s.custom.sSelectedClass);
						this.s.tags = this.s.custom.oTags;
						this.s.buttonSet = this.s.custom.aButtons
					},
					_fnButtonDefinations: function(t, i) {
						for (var e, f, u, r = 0, o = t.length; r < o; r++) {
							if (typeof t[r] == "string") {
								if (typeof TableTools.BUTTONS[t[r]] == "undefined") {
									alert("TableTools: Warning - unknown button type: " + t[r]);
									continue
								}
								u = n.extend({}, TableTools.BUTTONS[t[r]], !0)
							} else {
								if (typeof TableTools.BUTTONS[t[r].sExtends] == "undefined") {
									alert("TableTools: Warning - unknown button type: " + t[r].sExtends);
									continue
								}
								e = n.extend({}, TableTools.BUTTONS[t[r].sExtends], !0);
								u = n.extend(e, t[r], !0)
							}
							f = this._fnCreateButton(u, n(i).hasClass(this.classes.collection.container));
							f && i.appendChild(f)
						}
					},
					_fnCreateButton: function(t, i) {
						var r = this._fnButtonBase(t, i);
						if (t.sAction.match(/flash/)) {
							if (!this._fnHasFlash()) return !1;
							this._fnFlashConfig(r, t)
						} else t.sAction == "text" ? this._fnTextConfig(r, t) : t.sAction == "div" ? this._fnTextConfig(r, t) : t.sAction == "collection" && (this._fnTextConfig(r, t), this._fnCollectionConfig(r, t));
						if (this.s.dt.iTabIndex !== -1) n(r).attr("tabindex", this.s.dt.iTabIndex).attr("aria-controls", this.s.dt.sTableId).on("keyup.DTTT", function(t) {
							t.keyCode === 13 && (t.stopPropagation(), n(this).trigger("click"))
						}).on("mousedown.DTTT", function(n) {
							t.sAction.match(/flash/) || n.preventDefault()
						});
						return r
					},
					_fnButtonBase: function(n, t) {
						var u, f, e;
						t ? (u = n.sTag && n.sTag !== "default" ? n.sTag : this.s.tags.collection.button, f = n.sLinerTag && n.sLinerTag !== "default" ? n.sLiner : this.s.tags.collection.liner, e = this.classes.collection.buttons.normal) : (u = n.sTag && n.sTag !== "default" ? n.sTag : this.s.tags.button, f = n.sLinerTag && n.sLinerTag !== "default" ? n.sLiner : this.s.tags.liner, e = this.classes.buttons.normal);
						var i = r.createElement(u),
							o = r.createElement(f),
							s = this._fnGetMasterSettings();
						return i.className = e + " " + n.sButtonClass, i.setAttribute("id", "ToolTables_" + this.s.dt.sInstance + "_" + s.buttonCounter), i.appendChild(o), o.innerHTML = n.sButtonText, s.buttonCounter++, i
					},
					_fnGetMasterSettings: function() {
						var t, n, i;
						if (this.s.master) return this.s;
						for (t = TableTools._aInstances, n = 0, i = t.length; n < i; n++) if (this.dom.table == t[n].s.dt.nTable) return t[n].s
					},
					_fnCollectionConfig: function(n, t) {
						var i = r.createElement(this.s.tags.collection.container);
						i.style.display = "none";
						i.className = this.classes.collection.container;
						t._collection = i;
						r.body.appendChild(i);
						this._fnButtonDefinations(t.aButtons, i)
					},
					_fnCollectionShow: function(i, u) {
						var a = this,
							v = n(i).offset(),
							f = u._collection,
							y = v.left,
							s = v.top + n(i).outerHeight(),
							p = n(t).height(),
							h = n(r).height(),
							w = n(t).width(),
							o = n(r).width(),
							e, c, l;
						f.style.position = "absolute";
						f.style.left = y + "px";
						f.style.top = s + "px";
						f.style.display = "block";
						n(f).css("opacity", 0);
						e = r.createElement("div");
						e.style.position = "absolute";
						e.style.left = "0px";
						e.style.top = "0px";
						e.style.height = (p > h ? p : h) + "px";
						e.style.width = (w > o ? w : o) + "px";
						e.className = this.classes.collection.background;
						n(e).css("opacity", 0);
						r.body.appendChild(e);
						r.body.appendChild(f);
						c = n(f).outerWidth();
						l = n(f).outerHeight();
						y + c > o && (f.style.left = o - c + "px");
						s + l > h && (f.style.top = s - l - n(i).outerHeight() + "px");
						this.dom.collection.collection = f;
						this.dom.collection.background = e;
						setTimeout(function() {
							n(f).animate({
								opacity: 1
							}, 500);
							n(e).animate({
								opacity: .25
							}, 500)
						}, 10);
						this.fnResizeButtons();
						n(e).click(function() {
							a._fnCollectionHide.call(a, null, null)
						})
					},
					_fnCollectionHide: function(t, i) {
						(i === null || i.sExtends != "collection") && this.dom.collection.collection !== null && (n(this.dom.collection.collection).animate({
							opacity: 0
						}, 500, function() {
							this.style.display = "none"
						}), n(this.dom.collection.background).animate({
							opacity: 0
						}, 500, function() {
							this.parentNode.removeChild(this)
						}), this.dom.collection.collection = null, this.dom.collection.background = null)
					},
					_fnRowSelectConfig: function() {
						if (this.s.master) {
							var t = this,
								i = this.s.dt,
								r = this.s.dt.aoOpenRows;
							if (n(i.nTable).addClass(this.classes.select.table), this.s.select.type === "os") {
								n(i.nTBody).on("mousedown.DTTT_Select", "tr", function(t) {
									if (t.shiftKey) n(i.nTBody).css("-moz-user-select", "none").one("selectstart.DTTT_Select", "tr", function() {
										return !1
									})
								});
								n(i.nTBody).on("mouseup.DTTT_Select", "tr", function() {
									n(i.nTBody).css("-moz-user-select", "")
								})
							}
							n(i.nTBody).on("click.DTTT_Select", this.s.custom.sRowSelector, function(r) {
								var u = this.nodeName.toLowerCase() === "tr" ? this : n(this).parents("tr")[0],
									e = t.s.select,
									s = t.s.dt.oInstance.fnGetPosition(u),
									c;
								if (u.parentNode == i.nTBody && i.oInstance.fnGetData(u) !== null) {
									if (e.type == "os") if (r.ctrlKey || r.metaKey) t.fnIsSelected(u) ? t._fnRowDeselect(u, r) : t._fnRowSelect(u, r);
									else if (r.shiftKey) {
										var f = t.s.dt.aiDisplay.slice(),
											o = n.inArray(e.lastRow, f),
											h = n.inArray(s, f);
										t.fnGetSelected().length === 0 || o === -1 ? f.splice(n.inArray(s, f) + 1, f.length) : (o > h && (c = h, h = o, o = c), f.splice(h + 1, f.length), f.splice(0, o));
										t.fnIsSelected(u) ? (f.splice(n.inArray(s, f), 1), t._fnRowDeselect(f, r)) : t._fnRowSelect(f, r)
									} else t.fnIsSelected(u) && t.fnGetSelected().length === 1 ? t._fnRowDeselect(u, r) : (t.fnSelectNone(), t._fnRowSelect(u, r));
									else t.fnIsSelected(u) ? t._fnRowDeselect(u, r) : e.type == "single" ? (t.fnSelectNone(), t._fnRowSelect(u, r)) : e.type == "multi" && t._fnRowSelect(u, r);
									e.lastRow = s
								}
							});
							i.oApi._fnCallbackReg(i, "aoRowCreatedCallback", function(r, u, f) {
								i.aoData[f]._DTTT_selected && n(r).addClass(t.classes.select.row)
							}, "TableTools-SelectAll")
						}
					},
					_fnRowSelect: function(t, i) {
						for (var o = this, u = this._fnSelectData(t), s = u.length === 0 ? null : u[0].nTr, f = [], r = 0, e = u.length; r < e; r++) u[r].nTr && f.push(u[r].nTr);
						if (this.s.select.preRowSelect === null || this.s.select.preRowSelect.call(this, i, f, !0)) {
							for (r = 0, e = u.length; r < e; r++) u[r]._DTTT_selected = !0, u[r].nTr && n(u[r].nTr).addClass(o.classes.select.row);
							this.s.select.postSelected !== null && this.s.select.postSelected.call(this, f);
							TableTools._fnEventDispatch(this, "select", f, !0)
						}
					},
					_fnRowDeselect: function(t, i) {
						for (var o = this, u = this._fnSelectData(t), s = u.length === 0 ? null : u[0].nTr, f = [], r = 0, e = u.length; r < e; r++) u[r].nTr && f.push(u[r].nTr);
						if (this.s.select.preRowSelect === null || this.s.select.preRowSelect.call(this, i, f, !1)) {
							for (r = 0, e = u.length; r < e; r++) u[r]._DTTT_selected = !1, u[r].nTr && n(u[r].nTr).removeClass(o.classes.select.row);
							this.s.select.postDeselected !== null && this.s.select.postDeselected.call(this, f);
							TableTools._fnEventDispatch(this, "select", f, !1)
						}
					},
					_fnSelectData: function(n) {
						var i = [],
							r, t, u;
						if (n.nodeName) r = this.s.dt.oInstance.fnGetPosition(n), i.push(this.s.dt.aoData[r]);
						else {
							if (typeof n.length != "undefined") {
								for (t = 0, u = n.length; t < u; t++) n[t].nodeName ? (r = this.s.dt.oInstance.fnGetPosition(n[t]), i.push(this.s.dt.aoData[r])) : typeof n[t] == "number" ? i.push(this.s.dt.aoData[n[t]]) : i.push(n[t]);
								return i
							}
							i.push(n)
						}
						return i
					},
					_fnTextConfig: function(t, i) {
						var r = this;
						i.fnInit !== null && i.fnInit.call(this, t, i);
						i.sToolTip !== "" && (t.title = i.sToolTip);
						n(t).hover(function() {
							i.fnMouseover !== null && i.fnMouseover.call(this, t, i, null)
						}, function() {
							i.fnMouseout !== null && i.fnMouseout.call(this, t, i, null)
						});
						i.fnSelect !== null && TableTools._fnEventListen(this, "select", function(n) {
							i.fnSelect.call(r, t, i, n)
						});
						n(t).click(function(n) {
							i.fnClick !== null && i.fnClick.call(r, t, i, null, n);
							i.fnComplete !== null && i.fnComplete.call(r, t, i, null, null);
							r._fnCollectionHide(t, i)
						})
					},
					_fnHasFlash: function() {
						try {
							var n = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
							if (n) return !0
						} catch (t) {
							if (navigator.mimeTypes && navigator.mimeTypes["application/x-shockwave-flash"] !== i && navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin) return !0
						}
						return !1
					},
					_fnFlashConfig: function(n, t) {
						var r = this,
							i = new f.Client;
						t.fnInit !== null && t.fnInit.call(this, n, t);
						i.setHandCursor(!0);
						t.sAction == "flash_save" ? (i.setAction("save"), i.setCharSet(t.sCharSet == "utf16le" ? "UTF16LE" : "UTF8"), i.setBomInc(t.bBomInc), i.setFileName(t.sFileName.replace("*", this.fnGetTitle(t)))) : t.sAction == "flash_pdf" ? (i.setAction("pdf"), i.setFileName(t.sFileName.replace("*", this.fnGetTitle(t)))) : i.setAction("copy");
						i.addEventListener("mouseOver", function() {
							t.fnMouseover !== null && t.fnMouseover.call(r, n, t, i)
						});
						i.addEventListener("mouseOut", function() {
							t.fnMouseout !== null && t.fnMouseout.call(r, n, t, i)
						});
						i.addEventListener("mouseDown", function() {
							t.fnClick !== null && t.fnClick.call(r, n, t, i)
						});
						i.addEventListener("complete", function(u, f) {
							t.fnComplete !== null && t.fnComplete.call(r, n, t, i, f);
							r._fnCollectionHide(n, t)
						});
						this._fnFlashGlue(i, n, t.sToolTip)
					},
					_fnFlashGlue: function(n, t, i) {
						var u = this,
							f = t.getAttribute("id");
						r.getElementById(f) ? n.glue(t, i) : setTimeout(function() {
							u._fnFlashGlue(n, t, i)
						}, 100)
					},
					_fnFlashSetText: function(n, t) {
						var r = this._fnChunkData(t, 8192),
							i, u;
						for (n.clearText(), i = 0, u = r.length; i < u; i++) n.appendText(r[i])
					},
					_fnColumnTargets: function(t) {
						var u = [],
							o = this.s.dt,
							i, r, e = o.aoColumns,
							f = e.length,
							s;
						if (typeof t == "function") for (s = t.call(this, o), i = 0, r = f; i < r; i++) u.push(n.inArray(i, s) !== -1 ? !0 : !1);
						else if (typeof t == "object") {
							for (i = 0, r = f; i < r; i++) u.push(!1);
							for (i = 0, r = t.length; i < r; i++) u[t[i]] = !0
						} else if (t == "visible") for (i = 0, r = f; i < r; i++) u.push(e[i].bVisible ? !0 : !1);
						else if (t == "hidden") for (i = 0, r = f; i < r; i++) u.push(e[i].bVisible ? !1 : !0);
						else if (t == "sortable") for (i = 0, r = f; i < r; i++) u.push(e[i].bSortable ? !0 : !1);
						else for (i = 0, r = f; i < r; i++) u.push(!0);
						return u
					},
					_fnNewline: function(n) {
						return n.sNewLine == "auto" ? navigator.userAgent.match(/Windows/) ? "\r\n" : "\n" : n.sNewLine
					},
					_fnGetDataTablesData: function(t) {
						var r, o, s, k, e, h = [],
							i = "",
							y, f = this.s.dt,
							p, a = new RegExp(t.sFieldBoundary, "g"),
							w = this._fnColumnTargets(t.mColumns),
							v = typeof t.bSelectedOnly != "undefined" ? t.bSelectedOnly : !1,
							c, b, l;
						if (t.bHeader) {
							for (e = [], r = 0, o = f.aoColumns.length; r < o; r++) w[r] && (i = f.aoColumns[r].sTitle.replace(/\n/g, " ").replace(/<.*?>/g, "").replace(/^\s+|\s+$/g, ""), i = this._fnHtmlDecode(i), e.push(this._fnBoundData(i, t.sFieldBoundary, a)));
							h.push(e.join(t.sFieldSeperator))
						}
						for (v = !0, b = this.fnGetSelectedIndexes(), v = this.s.select.type !== "none" && v && b.length !== 0, c = v ? b : u.Api ? new u.Api(f).rows(t.oSelectorOpts).indexes().flatten().toArray() : f.oInstance.$("tr", t.oSelectorOpts).map(function(n, t) {
							return f.oInstance.fnGetPosition(t)
						}).get(), s = 0, k = c.length; s < k; s++) {
							for (p = f.aoData[c[s]].nTr, e = [], r = 0, o = f.aoColumns.length; r < o; r++) w[r] && (l = f.oApi._fnGetCellData(f, c[s], r, "display"), t.fnCellRender ? i = t.fnCellRender(l, r, p, c[s]) + "" : typeof l == "string" ? (i = l.replace(/\n/g, " "), i = i.replace(/<img.*?\s+alt\s*=\s*(?:"([^"]+)"|'([^']+)'|([^\s>]+)).*?>/gi, "$1$2$3"), i = i.replace(/<.*?>/g, "")) : i = l + "", i = i.replace(/^\s+/, "").replace(/\s+$/, ""), i = this._fnHtmlDecode(i), e.push(this._fnBoundData(i, t.sFieldBoundary, a)));
							h.push(e.join(t.sFieldSeperator));
							t.bOpenRows && (y = n.grep(f.aoOpenRows, function(n) {
								return n.nParent === p
							}), y.length === 1 && (i = this._fnBoundData(n("td", y[0].nTr).html(), t.sFieldBoundary, a), h.push(i)))
						}
						if (t.bFooter && f.nTFoot !== null) {
							for (e = [], r = 0, o = f.aoColumns.length; r < o; r++) w[r] && f.aoColumns[r].nTf !== null && (i = f.aoColumns[r].nTf.innerHTML.replace(/\n/g, " ").replace(/<.*?>/g, ""), i = this._fnHtmlDecode(i), e.push(this._fnBoundData(i, t.sFieldBoundary, a)));
							h.push(e.join(t.sFieldSeperator))
						}
						return h.join(this._fnNewline(t))
					},
					_fnBoundData: function(n, t, i) {
						return t === "" ? n : t + n.replace(i, t + t) + t
					},
					_fnChunkData: function(n, t) {
						for (var r = [], u = n.length, i = 0; i < u; i += t) i + t < u ? r.push(n.substring(i, i + t)) : r.push(n.substring(i, u));
						return r
					},
					_fnHtmlDecode: function(n) {
						if (n.indexOf("&") === -1) return n;
						var t = r.createElement("div");
						return n.replace(/&([^\s]*?);/g, function(n, i) {
							return n.substr(1, 1) === "#" ? String.fromCharCode(Number(i.substr(1))) : (t.innerHTML = n, t.childNodes[0].nodeValue)
						})
					},
					_fnPrintStart: function(i) {
						var s = this,
							u = this.s.dt,
							e, f, o, h;
						this._fnPrintHideNodes(u.nTable);
						this.s.print.saveStart = u._iDisplayStart;
						this.s.print.saveLength = u._iDisplayLength;
						i.bShowAll && (u._iDisplayStart = 0, u._iDisplayLength = -1, u.oApi._fnCalculateEnd && u.oApi._fnCalculateEnd(u), u.oApi._fnDraw(u));
						(u.oScroll.sX !== "" || u.oScroll.sY !== "") && (this._fnPrintScrollStart(u), n(this.s.dt.nTable).bind("draw.DTTT_Print", function() {
							s._fnPrintScrollStart(u)
						}));
						e = u.aanFeatures;
						for (f in e) if (f != "i" && f != "t" && f.length == 1) for (o = 0, h = e[f].length; o < h; o++) this.dom.print.hidden.push({
							node: e[f][o],
							display: "block"
						}), e[f][o].style.display = "none";
						n(r.body).addClass(this.classes.print.body);
						i.sInfo !== "" && this.fnInfo(i.sInfo, 3e3);
						i.sMessage && n("<div/>").addClass(this.classes.print.message).html(i.sMessage).prependTo("body");
						this.s.print.saveScroll = n(t).scrollTop();
						t.scrollTo(0, 0);
						n(r).bind("keydown.DTTT", function(n) {
							n.keyCode == 27 && (n.preventDefault(), s._fnPrintEnd.call(s, n))
						})
					},
					_fnPrintEnd: function() {
						var f = this,
							i = this.s.dt,
							u = this.s.print,
							e = this.dom.print;
						this._fnPrintShowNodes();
						(i.oScroll.sX !== "" || i.oScroll.sY !== "") && (n(this.s.dt.nTable).unbind("draw.DTTT_Print"), this._fnPrintScrollEnd());
						t.scrollTo(0, u.saveScroll);
						n("div." + this.classes.print.message).remove();
						n(r.body).removeClass("DTTT_Print");
						i._iDisplayStart = u.saveStart;
						i._iDisplayLength = u.saveLength;
						i.oApi._fnCalculateEnd && i.oApi._fnCalculateEnd(i);
						i.oApi._fnDraw(i);
						n(r).unbind("keydown.DTTT")
					},
					_fnPrintScrollStart: function() {
						var t = this.s.dt,
							f = t.nScrollHead.getElementsByTagName("div")[0],
							e = f.getElementsByTagName("table")[0],
							u = t.nTable.parentNode,
							i, r;
						i = t.nTable.getElementsByTagName("thead");
						i.length > 0 && t.nTable.removeChild(i[0]);
						t.nTFoot !== null && (r = t.nTable.getElementsByTagName("tfoot"), r.length > 0 && t.nTable.removeChild(r[0]));
						i = t.nTHead.cloneNode(!0);
						t.nTable.insertBefore(i, t.nTable.childNodes[0]);
						t.nTFoot !== null && (r = t.nTFoot.cloneNode(!0), t.nTable.insertBefore(r, t.nTable.childNodes[1]));
						t.oScroll.sX !== "" && (t.nTable.style.width = n(t.nTable).outerWidth() + "px", u.style.width = n(t.nTable).outerWidth() + "px", u.style.overflow = "visible");
						t.oScroll.sY !== "" && (u.style.height = n(t.nTable).outerHeight() + "px", u.style.overflow = "visible")
					},
					_fnPrintScrollEnd: function() {
						var n = this.s.dt,
							t = n.nTable.parentNode;
						n.oScroll.sX !== "" && (t.style.width = n.oApi._fnStringToCss(n.oScroll.sX), t.style.overflow = "auto");
						n.oScroll.sY !== "" && (t.style.height = n.oApi._fnStringToCss(n.oScroll.sY), t.style.overflow = "auto")
					},
					_fnPrintShowNodes: function() {
						for (var n = this.dom.print.hidden, t = 0, i = n.length; t < i; t++) n[t].node.style.display = n[t].display;
						n.splice(0, n.length)
					},
					_fnPrintHideNodes: function(t) {
						for (var f, e = this.dom.print.hidden, u = t.parentNode, r = u.childNodes, i = 0, o = r.length; i < o; i++) r[i] != t && r[i].nodeType == 1 && (f = n(r[i]).css("display"), f != "none" && (e.push({
							node: r[i],
							display: f
						}), r[i].style.display = "none"));
						u.nodeName.toUpperCase() != "BODY" && this._fnPrintHideNodes(u)
					}
				};
				TableTools._aInstances = [];
				TableTools._aListeners = [];
				TableTools.fnGetMasters = function() {
					for (var t = [], n = 0, i = TableTools._aInstances.length; n < i; n++) TableTools._aInstances[n].s.master && t.push(TableTools._aInstances[n]);
					return t
				};
				TableTools.fnGetInstance = function(n) {
					typeof n != "object" && (n = r.getElementById(n));
					for (var t = 0, i = TableTools._aInstances.length; t < i; t++) if (TableTools._aInstances[t].s.master && TableTools._aInstances[t].dom.table == n) return TableTools._aInstances[t];
					return null
				};
				TableTools._fnEventListen = function(n, t, i) {
					TableTools._aListeners.push({
						that: n,
						type: t,
						fn: i
					})
				};
				TableTools._fnEventDispatch = function(n, t, i, r) {
					for (var f = TableTools._aListeners, u = 0, e = f.length; u < e; u++) n.dom.table == f[u].that.dom.table && f[u].type == t && f[u].fn(i, r)
				};
				TableTools.buttonBase = {
					sAction: "text",
					sTag: "default",
					sLinerTag: "default",
					sButtonClass: "DTTT_button_text",
					sButtonText: "Button text",
					sTitle: "",
					sToolTip: "",
					sCharSet: "utf8",
					bBomInc: !1,
					sFileName: "*.csv",
					sFieldBoundary: "",
					sFieldSeperator: "\t",
					sNewLine: "auto",
					mColumns: "all",
					bHeader: !0,
					bFooter: !0,
					bOpenRows: !1,
					bSelectedOnly: !1,
					oSelectorOpts: i,
					fnMouseover: null,
					fnMouseout: null,
					fnClick: null,
					fnSelect: null,
					fnComplete: null,
					fnInit: null,
					fnCellRender: null
				};
				TableTools.BUTTONS = {
					csv: n.extend({}, TableTools.buttonBase, {
						sAction: "flash_save",
						sButtonClass: "DTTT_button_csv",
						sButtonText: "CSV文件",
						sFieldBoundary: '"',
						sFieldSeperator: ",",
						fnClick: function(n, t, i) {
							this.fnSetText(i, this.fnGetTableData(t))
						}
					}),
					xls: n.extend({}, TableTools.buttonBase, {
						sAction: "flash_save",
						sCharSet: "utf16le",
						bBomInc: !0,
						sButtonClass: "DTTT_button_xls",
						sButtonText: "Excel文件",
						fnClick: function(n, t, i) {
							this.fnSetText(i, this.fnGetTableData(t))
						}
					}),
					copy: n.extend({}, TableTools.buttonBase, {
						sAction: "flash_copy",
						sButtonClass: "DTTT_button_copy",
						sButtonText: "复制",
						fnClick: function(n, t, i) {
							this.fnSetText(i, this.fnGetTableData(t))
						},
						fnComplete: function(n, t, i, r) {
							var u = r.split("\n").length,
								f;
							t.bHeader && u--;
							this.s.dt.nTFoot !== null && t.bFooter && u--;
							f = u == 1 ? "" : "s";
							this.fnInfo("<h6>Table copied<\/h6><p>Copied " + u + " row" + f + " to the clipboard.<\/p>", 1500)
						}
					}),
					pdf: n.extend({}, TableTools.buttonBase, {
						sAction: "flash_pdf",
						sNewLine: "\n",
						sFileName: "*.pdf",
						sButtonClass: "DTTT_button_pdf",
						sButtonText: "PDF文件",
						sPdfOrientation: "portrait",
						sPdfSize: "A4",
						sPdfMessage: "",
						fnClick: function(n, t, i) {
							this.fnSetText(i, "title:" + this.fnGetTitle(t) + "\nmessage:" + t.sPdfMessage + "\ncolWidth:" + this.fnCalcColRatios(t) + "\norientation:" + t.sPdfOrientation + "\nsize:" + t.sPdfSize + "\n--/TableToolsOpts--\n" + this.fnGetTableData(t))
						}
					}),
					print: n.extend({}, TableTools.buttonBase, {
						sInfo: "<h6>Print view<\/h6><p>Please use your browser's print function to print this table. Press escape when finished.<\/p>",
						sMessage: null,
						bShowAll: !0,
						sToolTip: "View print view",
						sButtonClass: "DTTT_button_print",
						sButtonText: "打印",
						fnClick: function(n, t) {
							this.fnPrint(!0, t)
						}
					}),
					text: n.extend({}, TableTools.buttonBase),
					select: n.extend({}, TableTools.buttonBase, {
						sButtonText: "Select button",
						fnSelect: function(t) {
							this.fnGetSelected().length !== 0 ? n(t).removeClass(this.classes.buttons.disabled) : n(t).addClass(this.classes.buttons.disabled)
						},
						fnInit: function(t) {
							n(t).addClass(this.classes.buttons.disabled)
						}
					}),
					select_single: n.extend({}, TableTools.buttonBase, {
						sButtonText: "Select button",
						fnSelect: function(t) {
							var i = this.fnGetSelected().length;
							i == 1 ? n(t).removeClass(this.classes.buttons.disabled) : n(t).addClass(this.classes.buttons.disabled)
						},
						fnInit: function(t) {
							n(t).addClass(this.classes.buttons.disabled)
						}
					}),
					select_all: n.extend({}, TableTools.buttonBase, {
						sButtonText: "Select all",
						fnClick: function() {
							this.fnSelectAll()
						},
						fnSelect: function(t) {
							this.fnGetSelected().length == this.s.dt.fnRecordsDisplay() ? n(t).addClass(this.classes.buttons.disabled) : n(t).removeClass(this.classes.buttons.disabled)
						}
					}),
					select_none: n.extend({}, TableTools.buttonBase, {
						sButtonText: "Deselect all",
						fnClick: function() {
							this.fnSelectNone()
						},
						fnSelect: function(t) {
							this.fnGetSelected().length !== 0 ? n(t).removeClass(this.classes.buttons.disabled) : n(t).addClass(this.classes.buttons.disabled)
						},
						fnInit: function(t) {
							n(t).addClass(this.classes.buttons.disabled)
						}
					}),
					ajax: n.extend({}, TableTools.buttonBase, {
						sAjaxUrl: "/xhr.php",
						sButtonText: "Ajax button",
						fnClick: function(t, i) {
							var r = this.fnGetTableData(i);
							n.ajax({
								url: i.sAjaxUrl,
								data: [{
									name: "tableData",
									value: r
								}],
								success: i.fnAjaxComplete,
								dataType: "json",
								type: "POST",
								cache: !1,
								error: function() {
									alert("Error detected when sending table data to server")
								}
							})
						},
						fnAjaxComplete: function() {
							alert("Ajax complete")
						}
					}),
					div: n.extend({}, TableTools.buttonBase, {
						sAction: "div",
						sTag: "div",
						sButtonClass: "DTTT_nonbutton",
						sButtonText: "Text button"
					}),
					collection: n.extend({}, TableTools.buttonBase, {
						sAction: "collection",
						sButtonClass: "DTTT_button_collection",
						sButtonText: "Collection",
						fnClick: function(n, t) {
							this._fnCollectionShow(n, t)
						}
					})
				};
				TableTools.buttons = TableTools.BUTTONS;
				TableTools.classes = {
					container: "DTTT_container",
					buttons: {
						normal: "DTTT_button",
						disabled: "DTTT_disabled"
					},
					collection: {
						container: "DTTT_collection",
						background: "DTTT_collection_background",
						buttons: {
							normal: "DTTT_button",
							disabled: "DTTT_disabled"
						}
					},
					select: {
						table: "DTTT_selectable",
						row: "DTTT_selected selected"
					},
					print: {
						body: "DTTT_Print",
						info: "DTTT_print_info",
						message: "DTTT_PrintMessage"
					}
				};
				TableTools.classes_themeroller = {
					container: "DTTT_container ui-buttonset ui-buttonset-multi",
					buttons: {
						normal: "DTTT_button ui-button ui-state-default"
					},
					collection: {
						container: "DTTT_collection ui-buttonset ui-buttonset-multi"
					}
				};
				TableTools.DEFAULTS = {
					sSwfPath: "../swf/copy_csv_xls_pdf.swf",
					sRowSelect: "none",
					sRowSelector: "tr",
					sSelectedClass: null,
					fnPreRowSelect: null,
					fnRowSelected: null,
					fnRowDeselected: null,
					aButtons: ["copy", "csv", "xls", "pdf", "print"],
					oTags: {
						container: "div",
						button: "a",
						liner: "span",
						collection: {
							container: "div",
							button: "a",
							liner: "span"
						}
					}
				};
				TableTools.defaults = TableTools.DEFAULTS;
				TableTools.prototype.CLASS = "TableTools";
				TableTools.version = "2.2.3";
				n.fn.dataTable.Api && n.fn.dataTable.Api.register("tabletools()", function() {
					var n = null;
					return this.context.length > 0 && (n = TableTools.fnGetInstance(this.context[0].nTable)), n
				});
				typeof n.fn.dataTable == "function" && typeof n.fn.dataTableExt.fnVersionCheck == "function" && n.fn.dataTableExt.fnVersionCheck("1.9.0") ? n.fn.dataTableExt.aoFeatures.push({
					fnInit: function(n) {
						var t = n.oInit,
							i = t ? t.tableTools || t.oTableTools || {} : {};
						return new TableTools(n.oInstance, i).dom.container
					},
					cFeature: "T",
					sFeature: "TableTools"
				}) : alert("Warning: TableTools requires DataTables 1.9.0 or newer - www.datatables.net/download");
				n.fn.DataTable.TableTools = TableTools
			}(jQuery, n, t), typeof r.fn.dataTable == "function" && typeof r.fn.dataTableExt.fnVersionCheck == "function" && r.fn.dataTableExt.fnVersionCheck("1.9.0") ? r.fn.dataTableExt.aoFeatures.push({
				fnInit: function(n) {
					var i = typeof n.oInit.oTableTools != "undefined" ? n.oInit.oTableTools : {},
						t = new TableTools(n.oInstance, i);
					return TableTools._aInstances.push(t), t.dom.container
				},
				cFeature: "T",
				sFeature: "TableTools"
			}) : alert("Warning: TableTools 2 requires DataTables 1.9.0 or newer - www.datatables.net/download"), r.fn.dataTable.TableTools = TableTools, r.fn.DataTable.TableTools = TableTools, TableTools
		};
	typeof define == "function" && define.amd ? define(["jquery", "datatables"], r) : typeof exports == "object" ? r(require("jquery"), require("datatables")) : jQuery && !jQuery.fn.dataTable.TableTools && r(jQuery, jQuery.fn.dataTable)
})(window, document);