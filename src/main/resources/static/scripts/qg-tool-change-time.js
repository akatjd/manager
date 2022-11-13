var toolChangeTimeListGrid = new ax5.ui.grid();
var toolChangeTimeDetailListGrid = new ax5.ui.grid();
var tempMachineId = null;
var tempMachineName = null;
var tempData = null;
var dateArr = new Array();
var secArr = new Array();

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["minCycleTime"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["avgCycleTime"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["maxCycleTime"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["minCycleTimeDetail"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["avgCycleTimeDetail"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["maxCycleTimeDetail"] = function() {
	var date = time(this.value);
	return date;
};

ax5.ui.grid.formatter["distribution"] = function() {
//	console.log(this.item.distributionMapList);
	return "<div id='" + this.item.machineId + "'></div>";
};

ax5.ui.grid.formatter["distributionDetail"] = function() {
	return "<div id='" + this.item.machineId + this.item.toolName + "'></div>";
};

ax5.ui.grid.formatter["modalBtn"] = function () {
	var machineId = this.item.machineId;
	var machineName = this.item.machineName;
	
	var scatterDataArr = this.item.changedTimeArrList;
    value = '<a class="btn btn-primary me-1 mb-1 btn-sm" onclick="openModal(\''+ machineId + "," + machineName + '\')" style="height:50px;padding-left:5px;padding-right:5px;padding-top:13px;">상세보기</a>';
	value += '<a class="btn btn-warning me-1 mb-1 btn-sm" onclick="openBellModal(\''+ scatterDataArr + '/' + this.item.machineName + '\')" style="height:50px;padding-left:5px;padding-right:5px;padding-top:13px;">분포보기</a>';
    return value;
};

ax5.ui.grid.formatter["bellModal"] = function() {
    var value = '<a class="btn btn-secondary me-1 mb-1 btn-sm" onclick="openBellModal(\''+ this.value + '/' + this.item.machineName + '\')" style="height:25px;">분포보기</a>';
    return value;
};

ax5.ui.grid.formatter["changeToolCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    return value;
};

function initGrid() {
	toolChangeTimeListGrid.setConfig({
		target: $('[data-ax5grid="toolChangeTimeList-grid"]'),
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
				if (this.colIndex === 1) {
					item = this.item;
					$('#myModal').modal('show');
				}
			},
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		columns: [
			{ key: "areaName", label: "라인명", width: 200, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 150, align: "left", formatter: "tooltipName" },
			{
				key: undefined, label: "실제 공구교환 시간", columns: [
					{ key: "minCycleTime", label: "최소", width: 120, formatter: "minCycleTime" },
					{ key: "avgCycleTime", label: "평균", width: 120, formatter: "avgCycleTime" },
					{ key: "maxCycleTime", label: "최대", width: 120, formatter: "maxCycleTime" },
					{ key: "ctDistribution", label: "분포", width: 250, formatter: "distribution" }
				]
			},
			{ key: "changeToolCnt", label: "교환횟수", width: 100, align: "right", formatter: "changeToolCnt" },
			{ key: "machineId", label: "상세정보", width: 160, formatter: "modalBtn" },
		]
	});
	toolChangeTimeListGrid.setColumnSort({
		"avgCycleTime": {orderBy: "desc", seq: 0},
    });
}

function openModal(str) {
	var tempArr = str.split(',');
	var machineId = tempArr[0];
	var machineName = tempArr[1];
	tempMachineId = machineId;
	tempMachineName = machineName;
	
	$('#myModal').modal('show');
}

function openBellModal(str) {
	
	var tempArr = str.split('/');
	var dataArr = new Array();
	dataArr = tempArr[0].split(',');
	var machineName = tempArr[1];
	var innerDateArr = new Array();
	var innerSecArr = new Array();
	var hcArr = new Array();
	
	for(var i=0; i<dataArr.length; i++){
		if(((i+1)%2) == 0){
			dataArr[i] = parseInt(dataArr[i]); 
			innerSecArr.push(dataArr[i]);
		}else{
			dataArr[i] = parseInt(dataArr[i]); 
			innerDateArr.push(dataArr[i]);
		}
	}
	
	for(var j=0; j<innerDateArr.length; j++){
		var tempArr = Array();
		tempArr.push(innerDateArr[j]);
		tempArr.push(innerSecArr[j]);
		hcArr.push(tempArr);
	}
	
	dateArr = innerDateArr;
	secArr = innerSecArr;
	tempData = hcArr;
	
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value;
	var element = document.getElementById("staticBackdropLabelBell");
	element.innerText = machineName;
	var element2 = document.getElementById("selDateBell");
	element2.innerText = startDate + " ~ " + endDate;
	
	$('#bellCurveModal').modal('show');
}

function chartXaxisChange(value) {
	if(value === 'index'){
		var data = secArr;
		initIndexHighChart(data);
	}else{
		var data = tempData;
		initHighChart(data);
	}
}

// 공구 교환 시간 리스트
function searchToolChangeTimeList() {

	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var standardMaxCt = $('#standardMaxCt').val();
	$('#yMax').val(standardMaxCt);
	
	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	}else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	}else {
		$.ajax({
			url: '/qgToolChangeTimeApi/qgToolChangeTime',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
				standardMaxCt: standardMaxCt
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
				toolChangeTimeListGrid.setData(data.qgToolChangeTimeViewList);
				swal.closeModal();
			}
		});
	}
}

function toolChangeTimeDetailList() {
	var machineName = tempMachineName;
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var endDate2 = document.getElementById("enddate").value;
	var machineId = tempMachineId;
	var element = document.getElementById("staticBackdropLabel");
	element.innerText = "공구별 교환 시간 (" + machineName + ")";
	var element2 = document.getElementById("selDate");
	element2.innerText = startDate + " ~ " + endDate2;
	var standardMaxCt = $('#standardMaxCt').val();
	
	$.ajax({
		url: '/qgToolChangeTimeApi/qgToolChangeTimeDetail',
		type: 'POST',
		data: {
			startDate: startDate,
			endDate: endDate,
			machineId: machineId,
			standardMaxCt: standardMaxCt
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
			swal.closeModal();
			document.getElementById('myModal').focus();
			toolChangeTimeDetailListGrid.setData(data.qgToolChangeTimeDetailViewList);
		}
	});
}

function initDetailGrid(){
	toolChangeTimeDetailListGrid.setConfig({
		target: $('[data-ax5grid="toolChangeTimeDetailList-grid"]'),
        showLineNumber: true,
        showRowSelector: false,
        multipleSelect: false,
        lineNumberColumnWidth: 40,
        rowSelectorColumnWidth: 28,
        sortable: true, 
        multiSort: true,
        scroller: {size: 20},
        header: {
            align: "center",
            columnHeight: 32
        },
        body: {
            align: "center",
            columnHeight: 105,
			trStyleClass: function(){
                return "grid-cell-white";
            }
        },
        columns: [
            {key: "toolName",            label: "공구",         width: 150, align: "left"},
            {
				key: undefined, label: "실제 공구교환 시간", columns: [
					{ key: "minCycleTimeDetail", label: "최소", width: 120, formatter: "minCycleTimeDetail" },
					{ key: "avgCycleTimeDetail", label: "평균", width: 120, formatter: "avgCycleTimeDetail" },
					{ key: "maxCycleTimeDetail", label: "최대", width: 120, formatter: "maxCycleTimeDetail" },
					{ key: "ctDistributionDetail", label: "분포", width: 250, formatter: "distributionDetail" },
					{ key: "changeToolCnt", label: "교환횟수", width: 90, align: "right", formatter: "changeToolCnt" },
				]
			}
        ]
	});
	toolChangeTimeDetailListGrid.setColumnSort({
      "avgCycleTimeDetail": {orderBy: "desc", seq: 0},
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
        dataType:'json',
        success: function (data) {
            var areaOption;
            $.each(data, function (index, areaList) {
                var areaId;
                if(isNull(areaList.areaId)) areaId = '';
                else areaId = areaList.areaId;
                areaOption += '<option value="'+ areaId +'">'+ areaList.areaName  +'</option>';
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
        success: function (data) {
            var machineOption;
            $.each(data, function (index, machineList) {
                var machineId;
                if(isNull(machineList.machineId)) machineId = '';
                else machineId = machineList.machineId;
                machineOption += '<option value="'+ machineId +'">'+ machineList.machineName  +'</option>';
            });
            $('#selMachine').html(machineOption);
        }
    });
}

function isNull(obj){
    return (typeof obj != "undefined" && obj != null && obj != "") ? false :  true;
}

// 초를 00:00:00 형식으로 변환
function time(seconds) {
	var hour = parseInt(seconds/3600) < 10 ? '0' + parseInt(seconds/3600) : parseInt(seconds/3600);
	var min = parseInt((seconds%3600)/60) < 10 ? '0' + parseInt((seconds%3600)/60) : parseInt((seconds%3600)/60);
	var sec = seconds%60 < 10 ? '0' + seconds%60 : seconds%60;
	
	return hour+":"+min+":"+sec;
}

function changeYaxis(){
	var yMin = $('#yMin').val();
	var yMax = $('#yMax').val();
	var data = null;
	if(yMin > yMax){
		swal("구간 설정을 올바르게 해주세요.", "", "warning");
	}else{
		if($('#hcIndexOption').prop("checked")){
			data = secArr;
//			console.log(data);
			initIndexHighChart(data);
		}else{
			data = tempData;
//			console.log(data);
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

function initHighChart(data){
	
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
				text: '시간(s)'
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
					pointFormatter: function () { return '' + Highcharts.dateFormat('%y-%m-%d %H:%M:%S', moment.unix(this.x)) + '</b> , <b>' + (this.y).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '(s)</b>';  }
				}
			}
		},
		series: [{
			name: '교환시간',
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
//			type: 'datetime',
			title: {
				enabled: true,
				text: '교체순서'
			},
//			labels: {
//				formatter: function() {
//					return Highcharts.dateFormat('%y-%m-%d %H:%M', moment.unix(this.value));
//				}
//			},
			startOnTick: false,
			endOnTick: false,
			showLastLabel: true,
			tickInterval: 1
		},
		yAxis: {
			title: {
				text: '시간(s)'
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
			name: '교환시간',
			data: data,
			color: '#000000'
		}],
		credits: {
			enabled: false
		},
	}

	var chart = new Highcharts.Chart(hc_options);
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
	
	initGrid();
	searchAreaList();
	searchMachineList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);
	
	$('#myModal').on('hidden.bs.modal', function (e) {
        toolChangeTimeDetailListGrid.destroy();
		var element = document.getElementById("staticBackdropLabel");
		element.innerText = "공구별 교환 횟수";
		var element2 = document.getElementById("selDate");
		element2.innerText = "";
    });

	$('#myModal').on('shown.bs.modal', function (e) {
		initDetailGrid();
		toolChangeTimeDetailList();
	});
	
	$('#bellCurveModal').on('hidden.bs.modal', function (e) {
		var chartTitleElem = document.getElementById("scatterChart");
		chartTitleElem.innerText = "";
		var chartDateElem = document.getElementById("selDateBell");
		chartDateElem.innerText = "";
		$("input[id='hcDateOption']").prop("checked", true);
    });

	$('#bellCurveModal').on('shown.bs.modal', function (e) {
		var data = tempData;
		initHighChart(data);
	});
	
	/**
	 * Create a constructor for sparklines that takes some sensible defaults and merges in the individual
	 * chart options. This function is also available from the jQuery plugin as $(element).highcharts('SparkLine').
	 */
	Highcharts.SparkLine = function (a, b, c) {
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
	            outside: true,
	            shared: true
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
        toolChangeTimeListGrid.exportExcel("toolChangeTime_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});