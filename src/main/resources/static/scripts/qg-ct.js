var ctListGrid = new ax5.ui.grid();
var ctDetailListGrid = new ax5.ui.grid();
var tempMachineId = null;
var tempProductId = null;
var tempProductName = null;
var tempData = null;
var dateArr = new Array();
var ctArr = new Array();
var scatterDataArr = new Array();
var ctReverseArr = new Array();

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["minCt"] = function() {
	
	if(this.value >= 1){
		var value = Math.floor(this.value);
		value = (value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		return value;
	}else{
		var value = this.value;
		return value;
	}
};

ax5.ui.grid.formatter["avgCt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["maxCt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["minCtDetail"] = function() {
	var tempValue = this.value * 0.05;
	tempValue = tempValue.toFixed(2);
	var value = (tempValue).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["avgCtDetail"] = function() {
	var tempValue = this.value * 0.05;
	tempValue = tempValue.toFixed(2);
	var value = (tempValue).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["maxCtDetail"] = function() {
	var tempValue = this.value * 0.05;
	tempValue = tempValue.toFixed(2);
	var value = (tempValue).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["distributionCt"] = function() {
	return "<div id='" + this.item.machineId + this.item.productId + "'></div>";
};

ax5.ui.grid.formatter["distributionDetailCt"] = function() {
	return "<div id='" + this.item.machineId + this.item.productId + this.item.sectionNumber + this.item.toolName + "'></div>";
};

ax5.ui.grid.formatter["userKey"] = function() {
	var value = this.item.standardCt - this.item.avgCt;
	value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["itemCnt"] = function() {
	var value = this.value;
	value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["doubleStandardCt"] = function() {
	var value = this.value;
	value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["doubleGapStandardAvg"] = function() {
	var value = this.value;
	value = value.toFixed(1);
	value = value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["modalBtn"] = function() {
	var machineId = this.item.machineId;
	var productId = this.item.productId;
	var productName = this.item.productName;
	var dataArr = this.item.ctArr;
	value = '<a class="btn btn-primary me-1 mb-1 btn-sm" onclick="openModal(\'' + machineId + "," + productId + "," + productName + '\')" style="height:50px;padding-left:5px;padding-right:5px;padding-top:12px;">상세보기</a>';
	value += '<a class="btn btn-warning me-1 mb-1 btn-sm" onclick="openBellModal(\'' + dataArr + '/' + this.item.productName + '/' + this.item.machineName + '\')" style="height:50px;padding-left:5px;padding-right:5px;padding-top:13px;">분포보기</a>';
	return value;
};

ax5.ui.grid.formatter["bellModal"] = function() {
	var dataArr = this.item.ctArr;
	var value = '<a class="btn btn-secondary me-1 mb-1 btn-sm" onclick="openBellModal(\'' + dataArr + '/' + this.item.productName + '/' + this.item.machineName + '\')" style="height:25px;">열기</a>';
	return value;
};

function initGrid() {
	ctListGrid.setConfig({
		target: $('[data-ax5grid="ctList-grid"]'),
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
			onDBLClick: function() {
			},
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 130, align: "left", formatter: "tooltipName" },
			{ key: "productName", label: "품명", width: 230, align: "left", formatter: "tooltipName" },
			{ key: "doubleStandardCt", label: "표준 CT(s)", width: 90, align: "right", formatter: "doubleStandardCt" },
			{
				key: undefined, label: "실제 CT(s)", columns: [
//					{ key: "doubleMinCt", label: "최소", width: 90, align: "right", formatter: "minCt" },
					{ key: "avgCt", label: "평균", width: 90, align: "right", formatter: "avgCt" },
					{ key: "maxCt", label: "최대", width: 90, align: "right", formatter: "maxCt" },
					{ key: "doubleGapStandardAvg", label: "표준-평균", width: 90, align: "right", formatter: "doubleGapStandardAvg"},
					{ key: "distributionCt", label: "분포", width: 250, align: "right", formatter: "distributionCt" }
				]
			},
			{ key: "itemCnt", label: "생산수량", width: 90, align: "right", formatter: "itemCnt"},
			{ key: "machineId", label: "상세정보", width: 160, formatter: "modalBtn" },
		]
	});
	ctListGrid.setColumnSort({
      "gapStandardAvg": {orderBy: "desc", seq: 0},
    });
}

function openModal(str) {
	var tempArr = str.split(',');
	var machineId = tempArr[0];
	var productId = tempArr[1];
	var productName = tempArr[2];
	tempMachineId = machineId;
	tempProductId = productId;
	tempProductName = productName;

	$('#myModal').modal('show');
}

function openBellModal(str) {
	
	var tempArr = str.split('/');
	var dataArr = new Array();
	dataArr = tempArr[0].split(',');
	var productName = tempArr[1];
	var machineName = tempArr[2];
	var innerDateArr = new Array();
	var innerCtArr = new Array();
	var hcArr = new Array();
	
	for(var i=0; i<dataArr.length; i++){
		if(((i+1)%2) == 0){
			dataArr[i] = parseInt(dataArr[i]); 
			innerCtArr.push(dataArr[i]);
		}else{
			dataArr[i] = parseInt(dataArr[i]); 
			innerDateArr.push(dataArr[i]);
		}
	}
	
	for(var j=0; j<innerDateArr.length; j++){
		var tempArr = Array();
		tempArr.push(innerDateArr[j]);
		tempArr.push(innerCtArr[j]);
		hcArr.push(tempArr);
	}
	
	dateArr = innerDateArr;
	ctArr = innerCtArr;
	for(var k=ctArr.length-1; k>=0; k--){
		ctReverseArr.push(ctArr[k]);
	}
	tempData = hcArr;

	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value;
	var element = document.getElementById("staticBackdropLabelBell");
	element.innerText = machineName + "  -  " + productName;
	var element2 = document.getElementById("selDateBell");
	element2.innerText = startDate + " ~ " + endDate;

	$('#bellCurveModal').modal('show');
}

function chartXaxisChange(value) {
	if(value === 'index'){
		var data = ctReverseArr;
		initIndexHighChart(data);
	}else{
		var data = tempData;
		initHighChart(data);
	}
}

function searchCtList() {

	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var productId = $('#selProduct option:selected').val();
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var standardMaxCt = $('#standardMaxCt').val();
	$('#yMax').val(standardMaxCt);

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
			url: '/qgCtApi/getCtViewList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				productId: productId,
				startDate: startDate,
				endDate: endDate,
				standardMaxCt: standardMaxCt
			},
			beforeSend: function() {
				swal({
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				ctListGrid.setData(data.qgCtViewList);
				swal.closeModal();
			}
		});
	}
}

function searchCtDetailList() {
	var machineId = tempMachineId;
	var productId = tempProductId;
	var productName = tempProductName;
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var endDate2 = document.getElementById("enddate").value;
	var element = document.getElementById("staticBackdropLabel");
	element.innerText = "CT 상세정보 (" + productName + ")";
	var element2 = document.getElementById("selDate");
	element2.innerText = startDate + " ~ " + endDate2;

	$.ajax({
		url: '/qgCtApi/getCtDetailViewList',
		type: 'POST',
		data: {
			machineId: machineId,
			productId: productId,
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
			swal.closeModal();
			document.getElementById('myModal').focus();
			ctDetailListGrid.setData(data.qgCtDetailViewList);
		}
	});
}

// 공구 교환 횟수 디테일 그리드 (Tool 별)
function initDetailGrid() {
	ctDetailListGrid.setConfig({
		target: $('[data-ax5grid="ctDetailList-grid"]'),
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
			align: "center",
			columnHeight: 105,
		},
		columns: [
			{ key: "toolName", label: "공구", width: 160, align: "left" },
			{
				key: undefined, label: "실제 CT(s)", columns: [
					{ key: "minCtDetail", label: "최소", width: 80, align: "right", formatter: "minCtDetail" },
					{ key: "avgCtDetail", label: "평균", width: 80, align: "right", formatter: "avgCtDetail" },
					{ key: "maxCtDetail", label: "최대", width: 80, align: "right", formatter: "maxCtDetail" },
					{ key: "distributionDetailCt", label: "분포", width: 250, formatter: "distributionDetailCt" }
				]
			},
		]
	});
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
			searchProductList();
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
			searchProductList();
		}
	});
}

function searchProductList() {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	$.ajax({
		url: '/qgMonitoringApi/getProductList',
		type: 'POST',
		data: {
			factoryId: factoryId,
			areaId: areaId,
			machineId: machineId
		},
		success: function(data) {
			var productOption;
			$.each(data, function(index, productList) {
				var productId;
				if (isNull(productList.productId)) productId = '';
				else productId = productList.productId;
				productOption += '<option value="' + productId + '">' + productList.productName + '</option>';
			});
			$('#selProduct').html(productOption);
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
			data = ctArr;
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
	
	Highcharts.setOptions({
		lang: {
			thousandsSep: ',',
			months: [
				'1월', '2월', '3월', '4월',
				'5월', '6월', '7월', '8월',
				'9월', '10월', '11월', '12월'
			],
			weekdays: [
				'월', '화', '수', '목',
				'금', '토', '일'
			]
		}
	})

	var hc_options = {
		chart: {
			renderTo: 'scatterChart',
			type: 'scatter',
			zoomType: 'xy'
		},
		boost: {
//			useGPUTranslations: true,
			usePreAllocated: true,
			seriesThreshold: 1
		},
		title: {
			text: ' '
		},
		xAxis: {
//			type: 'datetime',
			title: {
				enabled: true,
				text: '생산시간'
			},
			labels: {
				formatter: function() {
					return Highcharts.dateFormat('%y-%m-%d %H:%M', moment.unix(this.value));
				}
			},
			startOnTick: false,
			endOnTick: false,
			showLastLabel: true,
			tickInterval: 1,
		},
		yAxis: {
			title: {
				text: 'CT'
			},
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
					pointFormatter: function () { return '' + Highcharts.dateFormat('%y-%m-%d %H:%M:%S', moment.unix(this.x)) + '</b> , <b>' + (this.y).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '(s)</b>';  }
				}
			}
		},
		series: [{
//			boostThreshold: 5000,
			name: 'Cycle time',
			data: data,
			color: '#000000'
		}],
		credits: {
			enabled: false
		},
	}

	var chart = new Highcharts.Chart(hc_options);
	
	var yAxis = chart.yAxis[0];
	var standardMaxCt = $('#standardMaxCt').val();
	yAxis.options.startOnTick = true;
	yAxis.options.endOnTick = true;
	chart.yAxis[0].setExtremes(0, standardMaxCt);
	chart.showResetZoom();
	
	swal.closeModal();
}

function initIndexHighChart(data) {
	let xAxisMax  = data.length;
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
//			usePreAllocated: true,
			seriesThreshold: 1
		},
		title: {
			text: ' '
		},
		xAxis: {
//			type: 'datetime',
			title: {
				enabled: true,
				text: '생산순서'
			},
//			labels: {
//				formatter: function() {
//					return Highcharts.dateFormat('%y-%m-%d %H:%M', moment.unix(this.value));
//				}
//			},
			startOnTick: false,
			endOnTick: false,
			showLastLabel: true,
			tickInterval: 1,
		},
		yAxis: {
			title: {
				text: 'CT'
			}
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
					pointFormatter: function () { return '' + this.x + '</b> , <b>' + (this.y).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '(s)</b>';  }
				}
			}
		},
		series: [{
			name: 'Cycle time',
			data: data,
			color: '#000000'
		}],
		credits: {
			enabled: false
		},
	}

	var chart = new Highcharts.Chart(hc_options);
	
	var yAxis = chart.yAxis[0];
	var standardMaxCt = $('#standardMaxCt').val();
	yAxis.options.startOnTick = true;
	yAxis.options.endOnTick = true;
	chart.yAxis[0].setExtremes(0, standardMaxCt);
	chart.showResetZoom();
}

const start = +new Date(),
	tds = Array.from(document.querySelectorAll('td[data-sparkline]')),
	fullLen = tds.length;

let n = 0;

function doChunk() {
	const time = +new Date(),
		len = tds.length;

	for (let i = 0; i < len; i += 1) {
		const td = tds[i];
		const stringdata = td.dataset.sparkline;
		const arr = stringdata.split('; ');
		const data = arr[0].split(', ').map(parseFloat);
		const chart = {};

		if (arr[1]) {
			chart.type = arr[1];
		}

		Highcharts.SparkLine(td, {
			title:{
				text: ' '
			},
			series: [{
				data: data,
				pointStart: 1
			}],
			tooltip: {
				headerFormat: '<span style="font-size: 10px">' + td.parentElement.querySelector('th').innerText + ', Q{point.x}:</span><br/>',
				pointFormat: '<b>{point.y}.000</b> USD'
			},
			chart: chart
		});

		n += 1;

		// If the process takes too much time, run a timeout to allow interaction with the browser
		if (new Date() - time > 500) {
			tds.splice(0, i + 1);
			setTimeout(doChunk, 0);
			break;
		}

		// Print a feedback on the performance
		if (n === fullLen) {
			//            document.getElementById('result').innerHTML = 'Generated ' + fullLen + ' sparklines in ' + (new Date() - start) + ' ms';
		}
	}
}

$(document).ready(function() {
	
	$(':focus').blur();  

	initGrid();
	searchAreaList();
	searchMachineList();
	searchProductList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);

	$('#myModal').on('hidden.bs.modal', function(e) {
		ctDetailListGrid.destroy();
		var element = document.getElementById("staticBackdropLabel");
		element.innerText = "공구별 교환 횟수";
		var element2 = document.getElementById("selDate");
		element2.innerText = "";
	});

	$('#myModal').on('shown.bs.modal', function(e) {
		initDetailGrid();
		searchCtDetailList();
	});

	$('#bellCurveModal').on('hidden.bs.modal', function(e) {
		var chartTitleElem = document.getElementById("scatterChart");
		chartTitleElem.innerText = "";
		var chartDateElem = document.getElementById("selDateBell");
		chartDateElem.innerText = "";
		ctReverseArr = [];
		$("input[id='hcDateOption']").prop("checked", true);
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
				style: {
					zIndex: 100
				}
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
				},
				tooltip:{
					style: {
						zIndex: 100
					}
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
        ctListGrid.exportExcel("cycletime_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});