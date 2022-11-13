var alarmListGrid = new ax5.ui.grid();
var machineListGrid = new ax5.ui.grid();
var chart = null;
var chartData = null;

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='" + this.value + "'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["barChart"] = function() {
	return "<div id='" + this.item.machineId + "'></div>";
};

ax5.ui.grid.formatter["machineHoldTime"] = function() {
	var hours = parseInt(this.value / 3600);
	if (hours < 10) {
		hours = '0' + hours;
	}
	var mins = parseInt((parseInt(this.value % 3600)) / 60);
	if (mins < 10) {
		mins = '0' + mins;
	}
	var secs = parseInt((parseInt(this.value % 3600)) % 60);
	if (secs < 10) {
		secs = '0' + secs;
	}

	return hours + ':' + mins + ':' + secs;
};

ax5.ui.grid.formatter["machineHoldCnt"] = function() {
	return this.value + ' 건';
};

ax5.ui.grid.formatter["sumTotal"] = function() {
	var hours = parseInt(this.value / 3600);
	if (hours < 10) {
		hours = '0' + hours;
	}
	var mins = parseInt((parseInt(this.value % 3600)) / 60);
	if (mins < 10) {
		mins = '0' + mins;
	}
	var secs = parseInt((parseInt(this.value % 3600)) % 60);
	if (secs < 10) {
		secs = '0' + secs;
	}


	return hours + ':' + mins + ':' + secs;
};

function initAlarmGrid() {
	let totalKey = 'machineHoldCnt';
	if ($('#inlineRadio1').prop('checked')) {
		totalKey = 'machineHoldTime';
	}

	let division = 'alarm';
	if ($('#division1').prop('checked')) {
		division = 'machine';
	}

	if (division === 'machine') {
		alarmListGrid.setConfig({
			target: $('[data-ax5grid="alarmList-grid"]'),
			showLineNumber: true,
			showRowSelector: false,
			multipleSelect: false,
			lineNumberColumnWidth: 40,
			rowSelectorColumnWidth: 28,
			sortable: true,
			multiSort: true,
			virtualScrollY: false,
			scroller: { size: 20 },
			header: {
				align: "center",
				columnHeight: 32
			},
			body: {
				align: "center",
				columnHeight: 32,
				mergeCells: ["areaName"],
				onDBLClick: function() {
				},
				trStyleClass: function() {
					return "grid-cell-white";
				}
			},
			columns: [
				{ key: "alarmType", label: "알람타입", width: 95, align: "left", formatter: "tooltipName" },
				{ key: "alarmMsg", label: "알람메시지", width: 150, align: "left", formatter: "tooltipName" },
				{ key: "sumTotal", label: "합계", width: 150, align: "left", formatter: totalKey },
			]
		});
		alarmListGrid.setColumnSort({
			"sumTotal": { orderBy: "desc", seq: 0 },
		});
	} else {
		alarmListGrid.setConfig({
			target: $('[data-ax5grid="alarmList-grid"]'),
			showLineNumber: true,
			showRowSelector: false,
			multipleSelect: false,
			lineNumberColumnWidth: 40,
			rowSelectorColumnWidth: 28,
			sortable: true,
			multiSort: true,
			virtualScrollY: false,
			scroller: { size: 20 },
			header: {
				align: "center",
				columnHeight: 32
			},
			body: {
				align: "center",
				columnHeight: 32,
				mergeCells: ["areaName"],
				onDBLClick: function() {
				},
				trStyleClass: function() {
					return "grid-cell-white";
				}
			},
			columns: [
				{ key: "machineName", label: "설비명", width: 150, align: "left", formatter: "tooltipName" },
				{ key: "sumTotal", label: "합계", width: 150, align: "left", formatter: totalKey },
			]
		});
		alarmListGrid.setColumnSort({
			"sumTotal": { orderBy: "desc", seq: 0 },
		});
	}
}

function initMachineGrid() {

	let formKey = 'machineHoldCnt';
	if ($('#inlineRadio1').prop('checked')) {
		formKey = 'machineHoldTime';
	}

	let division = 'alarm';
	if ($('#division1').prop('checked')) {
		division = 'machine';
	}

	if (division === 'machine') {
		machineListGrid.setConfig({
			target: $('[data-ax5grid="machineList-grid"]'),
			showLineNumber: true,
			showRowSelector: false,
			multipleSelect: false,
			lineNumberColumnWidth: 40,
			rowSelectorColumnWidth: 28,
			sortable: true,
			multiSort: true,
			virtualScrollY: false,
			scroller: { size: 20 },
			header: {
				align: "center",
				columnHeight: 32
			},
			body: {
				align: "center",
				columnHeight: 32,
				mergeCells: ["areaName"],
				onClick: function() {
					if (this.colIndex === 0) {
						const map = new Map();
						$.each(this.list, function(index, row) {
							map.set(row.machineId, index);
						});
						const index = map.get(this.item.machineId);
						changeAlarmList(this.item.machineId);
					}
				},
				onDBLClick: function() {
				},
				trStyleClass: function() {
					return "grid-cell-white";
				}
			},
			columns: [
				{ key: "machineName", label: "설비명", width: 150, align: "left", formatter: "tooltipName" },
				{ key: formKey, label: "합계", width: 100, align: "left", formatter: formKey },
			]
		});
	} else {
		machineListGrid.setConfig({
			target: $('[data-ax5grid="machineList-grid"]'),
			showLineNumber: true,
			showRowSelector: false,
			multipleSelect: false,
			lineNumberColumnWidth: 40,
			rowSelectorColumnWidth: 28,
			sortable: true,
			multiSort: true,
			virtualScrollY: false,
			scroller: { size: 20 },
			header: {
				align: "center",
				columnHeight: 32
			},
			body: {
				align: "center",
				columnHeight: 32,
				mergeCells: ["areaName"],
				onClick: function() {
					if (this.colIndex === 0) {
						const map = new Map();
						$.each(this.list, function(index, row) {
							map.set(row.alarmId, index);
						});
						const index = map.get(this.item.alarmId);
						changeAlarmDivisionList(this.item.alarmId);
					}
				},
				onDBLClick: function() {
				},
				trStyleClass: function() {
					return "grid-cell-white";
				}
			},
			columns: [
				{ key: "alarmMsg", label: "알람명", width: 150, align: "left", formatter: "tooltipName" },
				{ key: formKey, label: "합계", width: 100, align: "left", formatter: formKey },
			]
		});
	}
}

function searchAlarmList() {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var filter = $('#filter option:selected').val();
	var alarmTypeId = $('#selAlarmType option:selected').val();
	var startDate = document.getElementById("startdate").value + " 00:00:00";
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var formKey = 'machineHoldCnt';
	var filter = 'cnt';
	if ($('#inlineRadio1').prop('checked')) {
		formKey = 'machineHoldTime';
		filter = 'time';
	}
	var division = 'alarm';
	if ($('#division1').prop('checked')) {
		division = 'machine';
	}

	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	} else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	} else {
		$.ajax({
			url: '/qgAlarmApi/getAlarmList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
				filter: filter,
				division: division,
				alarmTypeId: alarmTypeId
			},
			beforeSend: function() {
				swal({
					//                title: '데이터를 가져오는 중입니다..',
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				chartData = data.qgAlarmChartDataList;
				chartData.levelOne.sort(function(a, b) { // 내림차순
					return b['y'] - a['y'];
				});

				chartData.levelTwo.sort(function(a, b) { // 내림차순
					return b['y'] - a['y'];
				});

				alarmListGrid = new ax5.ui.grid();
				machineListGrid = new ax5.ui.grid();
				initAlarmGrid();
				initMachineGrid();
				initAlarmChart(chartData);
				swal.closeModal();
				$.each(data.dayList, function(index, row) {
					alarmListGrid.addColumn({ key: row, label: row, formatter: formKey });
				})
				alarmListGrid.setData(data.qgAlarmDetailViewList);
				machineListGrid.setData(data.qgAlarmViewList);
			},
			complete: function() {
			}
		});
	}
}

function changeAlarmList(machineId) {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var filter = $('#filter option:selected').val();
	var alarmTypeId = $('#selAlarmType option:selected').val();
	var startDate = document.getElementById("startdate").value + " 00:00:00";
	var endDate = document.getElementById("enddate").value + " 23:59:59";

	var formKey = 'machineHoldCnt';
	var filter = 'cnt';
	if ($('#inlineRadio1').prop('checked')) {
		formKey = 'machineHoldTime';
		filter = 'time';
	}
	var division = 'alarm';
	if ($('#division1').prop('checked')) {
		division = 'machine';
	}

	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	} else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	} else {
		$.ajax({
			url: '/qgAlarmApi/getAlarmList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
				filter: filter,
				division: division,
				alarmTypeId: alarmTypeId
			},
			beforeSend: function() {
				swal({
					//                title: '데이터를 가져오는 중입니다..',
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				alarmListGrid = new ax5.ui.grid();
				initAlarmGrid();
				swal.closeModal();
				$.each(data.dayList, function(index, row) {
					alarmListGrid.addColumn({ key: row, label: row, formatter: formKey });
				})
				alarmListGrid.setData(data.qgAlarmDetailViewList);
			},
			complete: function() {
			}
		});
	}
}

function changeAlarmDivisionList(alarmId) {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var filter = $('#filter option:selected').val();
	var alarmTypeId = $('#selAlarmType option:selected').val();
	var startDate = document.getElementById("startdate").value + " 00:00:00";
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var machineId = null;
	var formKey = 'machineHoldCnt';
	var filter = 'cnt';
	if ($('#inlineRadio1').prop('checked')) {
		formKey = 'machineHoldTime';
		filter = 'time';
	}
	var division = 'alarm';
	if ($('#division1').prop('checked')) {
		division = 'machine';
	}

	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	} else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	} else {
		$.ajax({
			url: '/qgAlarmApi/getAlarmList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
				filter: filter,
				division: division,
				alarmTypeId: alarmTypeId,
				alarmId: alarmId
			},
			beforeSend: function() {
				swal({
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				alarmListGrid = new ax5.ui.grid();
				initAlarmGrid();
				swal.closeModal();
				$.each(data.dayList, function(index, row) {
					alarmListGrid.addColumn({ key: row, label: row, formatter: formKey });
				})
				alarmListGrid.setData(data.qgAlarmDetailViewList);
			},
			complete: function() {
			}
		});
	}
}

var alertPlaceholder = document.getElementById('liveAlertPlaceholder');
var alertTrigger = document.getElementById('liveAlertBtn');

function alert(message, type) {
	var wrapper = document.createElement('div')
	wrapper.innerHTML = '<div class="alert alert-' + type + ' border-2 d-flex align-items-center" role="alert"><div class="bg-danger me-3 icon-item"><span class="fas fa-times-circle text-white fs-3"></span></div><p class="mb-0 flex-1">' + message + '</p><button class="btn-close" type="button" data-bs-dismiss="alert" aria-label="Close"></button></div>'
	alertPlaceholder.append(wrapper)
}

// 오늘 날짜 구하기 0000-00-00
function getToday() {
	var today = new Date();
	var todayYear = today.getFullYear();
	var todayMonth = today.getMonth() + 1;
	var todayDate = today.getDate();
	if (todayMonth < 10) {
		todayMonth = "0" + todayMonth;
	}
	if (todayDate < 10) {
		todayDate = "0" + todayDate;
	}
	today = todayYear + '-' + todayMonth + '-' + todayDate;
	return today;
}

function searchAreaList() {
	var factoryId = $('#selFactory option:selected').val();
	$.ajax({
		url: '/qgMonitoringApi/getAreaList',
		type: 'POST',
		data: {
			factoryId: factoryId
		},
		dataType: 'json',
		success: function(data) {
			var areaOption;
			$.each(data, function(index, areaList) {
				var areaId;
				if (isNull(areaList.areaId)) areaId = '';
				else areaId = areaList.areaId;
				areaOption += '<option value="' + areaId + '">' + areaList.areaName + '</option>';
			});
			$('#selArea').html(areaOption);
			searchMachineList();
		}
	});
}

function searchMachineList() {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	$.ajax({
		url: '/qgMonitoringApi/getMachineList',
		type: 'POST',
		data: {
			factoryId: factoryId,
			areaId: areaId
		},
		success: function(data) {
			var machineOption;
			$.each(data, function(index, machineList) {
				var machineId;
				if (isNull(machineList.machineId)) machineId = '';
				else machineId = machineList.machineId;
				machineOption += '<option value="' + machineId + '">' + machineList.machineName + '</option>';
			});
			$('#selMachine').html(machineOption);
		}
	});
}

function searchAlarmTypeList() {
	var factoryId = $('#selFactory option:selected').val();
	if (!isNull(factoryId)) {
		$.ajax({
			url: '/qgAlarmHistoryApi/getAlarmTypeList',
			type: 'POST',
			data: {
				factoryId: factoryId
			},
			success: function(data) {
				var alarmTypeOption;
				$.each(data, function(index, alarmTypeList) {
					var alarmTypeId;
					if (isNull(alarmTypeList.alarmTypeId)) alarmTypeId = '';
					else alarmTypeId = alarmTypeList.alarmTypeId;
					alarmTypeOption += '<option value="' + alarmTypeId + '">' + alarmTypeList.alarmType + '</option>';
				});
				$('#selAlarmType').html(alarmTypeOption);
			}
		});
	}

}

function isNull(obj) {
	return (typeof obj != "undefined" && obj != null && obj != "") ? false : true;
}

function initAlarmChart(data) {
	var name = '건수';
	var pFormat = 'cnt';
	var tickInterval = 10;
	if ($('#inlineRadio1').prop('checked')) {
		filter = 'time';
		name = '시간';
		pFormat = 'time';
		tickInterval = 1800;
	}
	var options = {
		title: {
			text: ' '
		},
		chart: {
			type: 'bar',
			events: {
				drilldown: function(e) {
				},
				selection: function() {
					var ch = this;
					zoomButton = ch.renderer.button('Reset zoom', null, null, function() {
						ch.xAxis[0].setExtremes(null, null);
					}, {
						zIndex: 20
					}).attr({
						id: 'resetZoom',
						align: 'right',
						title: 'Reset zoom level 1:1'
					}).add().align({
						align: 'right',
						x: -45,
						y: 350
					}, false, null);
				},
				drillup: function(e) {
				}
			},
			zoomType: 'x',
			resetZoomButton: {
				theme: {
					display: 'none'
				}
			},
			height: 500,
		},
		plotOptions: {
			series: {
				cropThreshold: 300,
				dataLabels: {
					enabled: true,
					inside: false,
					overflow: 'none',
					crop: false,
					style: {
						fontFamily: 'Helvetica, sans-serif',
						fontSize: '12px',
						fontWeight: 'normal',
						textShadow: 'none'
					},
					formatter: function() {
						let cnt = 0;
						let time = 0;
						let hours = 0;
						let mins = 0;
						let secs = 0;
						if (pFormat === 'time') {
							cnt = this.point.cnt;
							time = this.y;
							hours = parseInt(time / 3600);
							if (hours < 10) {
								hours = '0' + hours;
							}
							mins = parseInt((parseInt(time % 3600)) / 60);
							if (mins < 10) {
								mins = '0' + mins;
							}
							secs = parseInt((parseInt(time % 3600)) % 60);
							if (secs < 10) {
								secs = '0' + secs;
							}
							
							if(cnt === 0 || cnt === undefined || cnt === null){
								cnt = 0;
								return hours + ":" + mins + ":" + secs + " / " + cnt + "건";
							}else{
								return hours + ":" + mins + ":" + secs + " / " + cnt + "건";
							}
						} else {
							time = this.point.time;
							hours = parseInt(time / 3600);
							if (hours < 10) {
								hours = '0' + hours;
							}
							mins = parseInt((parseInt(time % 3600)) / 60);
							if (mins < 10) {
								mins = '0' + mins;
							}
							secs = parseInt((parseInt(time % 3600)) % 60);
							if (secs < 10) {
								secs = '0' + secs;
							}
							cnt = this.y;
							
							if(cnt === 0 || cnt === undefined || cnt === null){
								return cnt + " 건" + " / " + "00" + ":" + "00" + ":" + "00";
							}else{
								return cnt + " 건" + " / " + hours + ":" + mins + ":" + secs;
							}
						}
					}
				}
			}
		},
		xAxis: {
			type: 'category',
			labels: {
				style: {
					fontSize: '10px',
					width: '200px',
					'min-width': '200px',
					textOverflow: 'ellipsis',
					whiteSpace: 'nowrap',
				},
			},
			tickInterval: 1,
		},
		scrollbar: {
			enabled: true
		},
		yAxis: {
			title: {
				text: ''
			},
			labels: {
				formatter: function() {
					if (pFormat === 'time') {
						var time = this.value;
						var hours = parseInt(time / 3600);
						if (hours < 10) {
							hours = '0' + hours;
						}
						var mins = parseInt((parseInt(time % 3600)) / 60);
						if (mins < 10) {
							mins = '0' + mins;
						}
						var secs = parseInt((parseInt(time % 3600)) % 60);
						if (secs < 10) {
							secs = '0' + secs;
						}
						return hours + ":" + mins + ":" + secs;
					} else {
						var cnt = this.value;
						return cnt + " 건";
					}
				}
			},
			tickInterval: tickInterval,
		},
		series: [{
			name: name,
			data: data.levelOne,
		}],
		drilldown: {
			series: data.levelTwo,
		},
		credits: {
			enabled: false
		},
		tooltip: {
			enabled: true,
			formatter: function() {
				let cnt = 0;
				let time = 0;
				let hours = 0;
				let mins = 0;
				let secs = 0;
				if (pFormat === 'time') {
					cnt = this.point.cnt;
					time = this.y;
					hours = parseInt(time / 3600);
					if (hours < 10) {
						hours = '0' + hours;
					}
					mins = parseInt((parseInt(time % 3600)) / 60);
					if (mins < 10) {
						mins = '0' + mins;
					}
					secs = parseInt((parseInt(time % 3600)) % 60);
					if (secs < 10) {
						secs = '0' + secs;
					}
					if(cnt === 0 || cnt === undefined || cnt === null){
						cnt = 0;
						return hours + ":" + mins + ":" + secs + " / " + cnt + "건";
					}else{
						return hours + ":" + mins + ":" + secs + " / " + cnt + "건";
					}
				} else {
					time = this.point.time;
					hours = parseInt(time / 3600);
					if (hours < 10) {
						hours = '0' + hours;
					}
					mins = parseInt((parseInt(time % 3600)) / 60);
					if (mins < 10) {
						mins = '0' + mins;
					}
					secs = parseInt((parseInt(time % 3600)) % 60);
					if (secs < 10) {
						secs = '0' + secs;
					}
					cnt = this.y;
					if(cnt === 0 || cnt === undefined || cnt === null){
						return cnt + " 건" + " / " + "00" + ":" + "00" + ":" + "00";
					}else{
						return cnt + " 건" + " / " + hours + ":" + mins + ":" + secs;
					}
				}
			}
		},
	}

	options.chart.renderTo = 'alarmChart';
	options.chart.type = 'bar';
	var alarmChart = new Highcharts.Chart(options);
	chart = alarmChart;
}

function searchAreaListAndAlarmTypeList() {
	searchAreaList();
	searchAlarmTypeList();
}

$(document).ready(function() {

	initAlarmGrid();
	initMachineGrid();
	searchAreaList();
	searchMachineList();
	searchAlarmTypeList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);

	flatpickr("#startdate, #enddate", {
		plugins: [
			ShortcutButtonsPlugin({
				button: [
					{
						label: "Today"
					},
				],
				onClick: (index, fp) => {
					let date;
					date = new Date();
					fp.setDate(date);
				}
			})
		],
	});

	$('[data-grid-control]').click(function() {
		machineListGrid.exportExcel("machine_" + $('#startdate').val() + "~" + $('#enddate').val() + ".xls");
		alarmListGrid.exportExcel("alarm_" + $('#startdate').val() + "~" + $('#enddate').val() + ".xls");
	});

});