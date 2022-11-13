var selectIdx;
var toolLifeListGrid = new ax5.ui.grid();
var tempData = null;
var standardLine = null;
var hcDataArr = new Array();
var dateArr = new Array();
var cntArr = new Array();
var maxCnt = 0;

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["minLife"] = function() {
	var minSection = document.getElementById("minSection").value;
	var nowPresetCnt = this.item.nowPresetCnt;
	if (nowPresetCnt === 0) {
		var sectionPreset = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var scetionCnt = (this.item.minSectionCnt).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var value = "<span style='line-height:40px'><br></span>" + sectionPreset + "(" + scetionCnt + ")";
		return value;
	} else {
		minSection = minSection * 0.01;
		var minGap = minSection * nowPresetCnt;
		minGap = Math.floor(minGap * 100) / 100;
		minGap = (minGap).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var sectionPreset = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var scetionCnt = (this.item.minSectionCnt).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var value = "<span style='line-height:30px'><br></span>" + sectionPreset + "(" + scetionCnt + ")" + "<br/>0~" + minGap;
		return value;
	}

};

ax5.ui.grid.formatter["avgLife"] = function() {
	var sectionPreset = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	var value = sectionPreset;
	return value;
};

ax5.ui.grid.formatter["maxLife"] = function() {
	var maxSection = document.getElementById("maxSection").value;
	var nowPresetCnt = this.item.nowPresetCnt;
	if (nowPresetCnt === 0) {
		var value = "<span style='line-height:40px'><br></span>" + "0(0)"
		return value;
	} else {
		maxSection = maxSection * 0.01;
		var maxGap = nowPresetCnt * (1 - maxSection);
		nowPresetCnt = (nowPresetCnt).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		maxGap = Math.floor(maxGap * 100) / 100;
		maxGap = (maxGap).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var sectionPreset = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var scetionCnt = (this.item.maxSectionCnt).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		var value = "<span style='line-height:30px'><br></span>" + sectionPreset + "(" + scetionCnt + ")" + "<br/>" + maxGap + "~" + nowPresetCnt;
		return value;
	}
};

ax5.ui.grid.formatter["distribution"] = function() {
	return "<div id='" + this.item.machineId + this.item.toolName + "'></div>";
};

ax5.ui.grid.formatter["nowPresetCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["userKey"] = function() {
	var value = this.item.nowPresetCnt - this.item.avgLife;
	value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["presetArr"] = function() {
	value = '<a class="btn btn-warning mb-1 btn-sm" onclick="openModal(\'' + this.value + '/' + this.item.toolName + '/' + this.item.machineName + '/' + this.item.nowPresetCnt + '\')" style="height:50px;padding-left:5px;padding-right:5px;padding-top:13px;">분포보기</a>';
	return value;
};

ax5.ui.grid.formatter["sumUseCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["gapStandardAvg"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

function initGrid() {
	toolLifeListGrid.setConfig({
		target: $('[data-ax5grid="toolLifeList-grid"]'),
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: true,
		multiSort: true,
		scroller: { size: 20 },
		header: {
			align: "center",
			columnHeight: 32
		},
		body: {
			mergeCells: ["areaName", "machineName"],
			align: "center",
			columnHeight: 105,
			onClick: function() {
			},
			onDBLClick: function() {
			},
			onDataChanged: function() {
				this.self.repaint();
			},
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 150, align: "left", formatter: "tooltipName" },
			{ key: "toolName", label: "공구", width: 80, align: "left", formatter: "tooltipName" },
			{ key: "nowPresetCnt", label: "관리수명(EA)", width: 110, align: "right", formatter: "nowPresetCnt", editor: {type:"number"} },
			{
				key: undefined, label: "실제 공구 수명(EA)", columns: [
					{ key: "minLife", label: "최소", width: 120, align: "right", formatter: "minLife", multiLine: true },
					{ key: "avgLife", label: "평균", width: 120, align: "right", formatter: "avgLife" },
					{ key: "maxLife", label: "최대", width: 120, align: "right", formatter: "maxLife", multiLine: true },
					{ key: "gapStandardAvg", label: "관리-평균", width: 120, align: "right", formatter: "gapStandardAvg"},
					{ key: "arrDistributionCnt", label: "분포", width: 250, formatter: "distribution" }
				]
			},
			{ key: "sumUseCnt", label: "생산수량", width: 100, align: "right", formatter: "sumUseCnt"},
			{ key: "presetArr", label: "분포도", width: 80, formatter: "presetArr" },
		]
	});
	toolLifeListGrid.setColumnSort({
      "gapStandardAvg": {orderBy: "desc", seq: 0},
    });
}

// 공구수명 리스트
function searchToolLifeList() {

	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var minSection = document.getElementById("minSection").value;
	var maxSection = document.getElementById("maxSection").value;
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";

	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	} else if (((minSection < 0 || minSection > 50) || (maxSection < 0 || maxSection > 50)) || (!minSection || !maxSection)) {
		if (alertTrigger) {
			swal("최소, 최대 구간을 올바르게 입력해주세요. (최소 0~50, 최대 0~50)", "", "warning");
		}
	} else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	} else {
		$.ajax({
			url: '/qgToolLifeApi/getToolLifeViewList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				minSection: minSection,
				maxSection: maxSection,
				startDate: startDate,
				endDate: endDate
			},
			beforeSend: function() {
				swal({
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				toolLifeListGrid.setData(data.qgToolLifeViewList);
				swal.closeModal();
			}
		});
	}
}

function openModal(arr) {
	var tempArr = arr.split('/');
	var dataArr = new Array();
	dataArr = tempArr[0].split(',');
	var toolName = tempArr[1];
	var machineName = tempArr[2];
	var nowPresetCnt = tempArr[3];
	var innerDateArr = new Array();
	var innerCntArr = new Array();
	var HcArr = new Array();
	var tempMaxCnt = 0;
	standardLine = nowPresetCnt;
	
	for(var i=0; i<dataArr.length; i++){
		if(((i+1)%2) == 0){
			dataArr[i] = parseInt(dataArr[i]); 
			innerCntArr.push(dataArr[i]);
		}else{
			dataArr[i] = parseInt(dataArr[i]); 
			innerDateArr.push(dataArr[i]);
		}
	}
	
	dateArr = innerDateArr;
	cntArr = innerCntArr;
	
	for(var j=0; j<innerDateArr.length; j++){
		var tempArr = Array();
		if(tempMaxCnt < innerCntArr[j]){
			tempMaxCnt = innerCntArr[j];
		}
		tempArr.push(innerDateArr[j]);
		tempArr.push(innerCntArr[j]);
		HcArr.push(tempArr);
	}
	
	maxCnt = tempMaxCnt;
	tempData = HcArr;
	
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value;
	var element = document.getElementById("staticBackdropLabelBell");
	element.innerText = machineName + "  -  " + toolName;
	var element2 = document.getElementById("selDateBell");
	element2.innerText = startDate + " ~ " + endDate;
	
	$('#bellCurveModal').modal('show');
}

function chartXaxisChange(value){
	if(value === 'index'){
		var data = cntArr;
		initIndexHighChart(data);
	}else{
		var data = tempData;
		initHighChart(data);
	}
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

function isNull(obj) {
	return (typeof obj != "undefined" && obj != null && obj != "") ? false : true;
}

function changeYaxis(){
	var yMin = $('#yMin').val();
	var yMax = $('#yMax').val();
	var data = null;
	if(yMin > yMax){
		swal("구간 설정을 올바르게 해주세요.", "", "warning");
	}else{
		if($('#hcIndexOption').prop("checked")){
			data = cntArr;
			initIndexHighChart(data);
		}else{
			data = tempData;
			initHighChart(data);
		}
		
		var chart = $('#scatterChart').highcharts();
		var yAxis = chart.yAxis[0];
	    yAxis.options.startOnTick = true;
	    yAxis.options.endOnTick = true;
	    chart.yAxis[0].setExtremes(yMin, yMax);
		chart.showResetZoom();
	}
}

function initHighChart(data) {
	var plotLine = 0;
	if(maxCnt > standardLine){
		plotLine = maxCnt;
	}else{
		plotLine = standardLine;
	}
	$('input[id=yMax]').attr('value',plotLine);
	var hc_options = {
		chart: {
			renderTo: 'scatterChart',
			type: 'scatter',
			zoomType: 'xy'
		},
		boost: {
			useGPUTranslations: true,
			seriesThreshold: 1
		},
		title: {
			text: ' '
		},
		xAxis: {
			type: 'datetime',
			title: {
				enabled: true,
				text: '교체시간'
			},
			labels: {
				formatter: function() {
					return Highcharts.dateFormat('%y-%m-%d %H:%M', moment.unix(this.value));
				}
			},
			startOnTick: false,
			endOnTick: false,
			showLastLabel: true
		},
		yAxis: {
			title: {
				text: '사용횟수'
			},
		    max: plotLine,
			plotLines: [{//기준점 사용 옵션.
			   color: '#337ab7',
			   width: 2,
			   value: standardLine,
			   dashStyle: 'shortdash',//라인 스타일 지정 옵션
			   zIndex: 5,
			   label: {
			     text: '' + standardLine,
			     align: 'right',
			     x: -10,
			     y: -6,
			     style: {
			     	color:'#337ab7',
			     }
			   }
			 },
			]
		},
		plotOptions: {
			scatter: {
				marker: {
					radius: 2,
					states: {
						hover: {
							enabled: true,
							lineColor: 'rgb(100,100,100)'
						}
					}
				},
				states: {
					hover: {
						marker: {
							enabled: false
						}
					}
				},
				tooltip: {
					pointFormatter: function () { return '' + Highcharts.dateFormat('%y-%m-%d %H:%M:%S', moment.unix(this.x)) + '</b> , <b>' + (this.y).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '</b>';  }
				}
			}
		},
		series: [{
			name: '공구교체',
			data: data,
			color: '#000000'
		}],
		credits: {
			enabled: false
		},
	}
	var chart = new Highcharts.Chart(hc_options);
}

function initIndexHighChart(data) {
	var plotLine = 0;
	if(maxCnt > standardLine){
		plotLine = maxCnt;
	}else{
		plotLine = standardLine;
	}
	$('input[id=yMax]').attr('value',plotLine);
	Highcharts.setOptions({
		lang: {
			thousandsSep: ',',
		}
	})
	var hc_options = {
		chart: {
			renderTo: 'scatterChart',
			type: 'scatter',
			zoomType: 'xy'
		},
		boost: {
			useGPUTranslations: true,
			seriesThreshold: 1
		},
		title: {
			text: ' '
		},
		xAxis: {
			title: {
				enabled: true,
				text: '교체순서'
			},
			startOnTick: false,
			endOnTick: false,
			showLastLabel: true,
			tickInterval: 1
		},
		yAxis: {
			title: {
				text: '사용횟수'
			},
			max: plotLine,
			plotLines: [{//기준점 사용 옵션.
			   color: '#337ab7',
			   width: 2,
			   value: standardLine,
			   dashStyle: 'shortdash',//라인 스타일 지정 옵션
			   zIndex: 5,
			   label: {
			     text: '' + standardLine,
			     align: 'right',
			     x: -10,
			     y: -6,
			     style: {
			     	color:'#337ab7',
			     }
			   }
			 },
			]
		},
		plotOptions: {
			scatter: {
				marker: {
					radius: 2,
					states: {
						hover: {
							enabled: true,
							lineColor: 'rgb(100,100,100)'
						}
					}
				},
				states: {
					hover: {
						marker: {
							enabled: false
						}
					}
				},
				tooltip: {
					pointFormatter: function () { return '' + this.x + '</b> , <b>' + (this.y).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '</b>';  }
				}
			}
		},
		series: [{
			name: '공구교체',
			data: data,
			color: '#000000'
		}],
		credits: {
			enabled: false
		},
	}

	var chart = new Highcharts.Chart(hc_options);
}

$(document).ready(function() {

	initGrid();
	searchAreaList();
	searchMachineList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);

	$('#bellCurveModal').on('hidden.bs.modal', function(e) {
		//		Highcharts.charts[0] && Highcharts.charts[0].destroy();
		var chartTitleElem = document.getElementById("scatterChart");
		chartTitleElem.innerText = "";
		var chartDateElem = document.getElementById("selDateBell");
		chartDateElem.innerText = "";
		$("input[id='hcDateOption']").prop("checked", true);
		$('input[id=yMax]').attr('value',0);
	});

	$('#bellCurveModal').on('shown.bs.modal', function(e) {
		var data = tempData;
		initHighChart(data);
	});

	/**
	 * Create a constructor for sparklines that takes some sensible defaults and merges in the individual
	 * chart options. This function is also available from the jQuery plugin as $(element).highcharts('SparkLine').
	 */
	Highcharts.SparkLine = function(a, b, c) {
		const hasRenderToArg = typeof a === 'string' || a.nodeName;
		let options = arguments[hasRenderToArg ? 1 : 0];
		const defaultOptions = {
			chart: {
				renderTo: (options.chart && options.chart.renderTo) || (hasRenderToArg && a),
				backgroundColor: null,
				borderWidth: 0,
				type: 'area',
				margin: [2, 0, 2, 0],
				width: 120,
				height: 20,
				style: {
					overflow: 'visible'
				},
				// small optimalization, saves 1-2 ms each sparkline
				skipClone: true
			},
			boost: {
				useGPUTranslations: true
			},
			title: {
				text: ' '
			},
			credits: {
				enabled: false
			},
			xAxis: {
				labels: {
					enabled: false
				},
				title: {
					text: null
				},
				startOnTick: false,
				endOnTick: false,
				tickPositions: []
			},
			yAxis: {
				endOnTick: false,
				startOnTick: false,
				labels: {
					enabled: false
				},
				title: {
					text: null
				},
				tickPositions: [0]
			},
			legend: {
				enabled: false
			},
			tooltip: {
				hideDelay: 0,
				outside: false,
				shared: true,
			},
			plotOptions: {
				series: {
					animation: false,
					lineWidth: 1,
					shadow: false,
					states: {
						hover: {
							lineWidth: 1
						}
					},
					marker: {
						radius: 1,
						states: {
							hover: {
								radius: 2
							}
						}
					},
					fillOpacity: 0.25
				},
				column: {
					negativeColor: '#910000',
					borderColor: 'silver'
				}
			}
		};

		options = Highcharts.merge(defaultOptions, options);

		return hasRenderToArg ?
			new Highcharts.Chart(a, options, c) :
			new Highcharts.Chart(options, b);
	};

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
		]
	});
	
	$('[data-grid-control]').click(function () {
        toolLifeListGrid.exportExcel("toolLife_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});