var temperatureListGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["sparkLineData"] = function() {
	return "<div id='" + this.item.machineId + "'></div>";
};

function initGrid() {
	temperatureListGrid.setConfig({
		target: $('[data-ax5grid="temperatureList-grid"]'),
		//        frozenColumnIndex: 2,
		//        frozenRowIndex: 0,
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: true,
		multiSort: true,
		virtualScrollY: false,
//		virtualScrollX: false,
		scroller: { size: 20 },
		header: {
			align: "center",
			columnHeight: 32
		},
		body: {
			align: "center",
			columnHeight: 105,
			//            mergeCells: ["machineName","itemId","itemType","itemTag"],
			onDBLClick: function () {
				if(this.colIndex === 5){
                    detailChart(this);
                }
            },
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 130, align: "left", formatter: "tooltipName" },
			{
				key: undefined, label: "설비 온도 (℃)", columns: [
					{ key: "minTemp", label: "최소", width: 70 },
					{ key: "avgTemp", label: "평균", width: 70 },
					{ key: "maxTemp", label: "최대", width: 70 },
					{ key: "sparkLineDataTemp", label: "추이", width: 400, formatter: "sparkLineData"},
				]
			},
		]
	});
	temperatureListGrid.setColumnSort({
      "areaName": {orderBy: "asc", seq: 0},
	  "machineName": {orderBy: "asc", seq: 1},
    });
}

function detailChart(val) {
	// 모달 팝업
	var myModal = new bootstrap.Modal(document.getElementById('myModal'), {
		keyboard: true
	});
	myModal.show();

	var $lineChartEl = document.querySelector('.echart-line-chart');

	if ($lineChartEl) {
		
		// HashMap 데이터 가공
		var category = [];
		var value = [];
	
		for (let i = 0; i < val.item.sparkLineDataMapList.length; i++) {
			var item = val.item.sparkLineDataMapList[i];
			category.push("\"" + item['x'] + "\"");
			value.push("\"" + item['y'] + "\"");
		}
		category = '[' + category + ']';
		var result_category = category.toString();
		value = '[' + value + ']';
		var result_value = value.toString();
		
		// Get options from data attribute
		var userOptions = utils.getData($lineChartEl, 'options');
		var chart = window.echarts.init($lineChartEl);

		var _tooltipFormatter2 = function _tooltipFormatter2(params) {
			return "\n      <div>\n          <h6 class=\"fs--1 text-700 mb-0\">\n            <span class=\"fas fa-circle me-1\" style='color:".concat(params[0].borderColor, "'></span>\n            ").concat(params[0].name, " : ").concat(params[0].value, "\n          </h6>\n      </div>\n      ");
		};

		var getDefaultOptions = function getDefaultOptions() {
			return {
				dataZoom: {
					start: 0,
					type: "inside"
				},
				tooltip: {
					trigger: 'axis',
					padding: [7, 10],
					backgroundColor: utils.getGrays()['100'],
					borderColor: utils.getGrays()['300'],
					textStyle: {
						color: utils.getColors().dark
					},
					borderWidth: 1,
					formatter: _tooltipFormatter2,
					transitionDuration: 0,
					position: function position(pos, params, dom, rect, size) {
						return getPosition(pos, params, dom, rect, size);
					},
					axisPointer: {
						type: 'none'
					}
				},
				xAxis: {
					type: 'category',
					data: JSON.parse(result_category),
					boundaryGap: false,
					axisLine: {
						lineStyle: {
							color: utils.getGrays()['300']
						}
					},
					axisTick: {
						show: false
					},
					axisLabel: {
						color: utils.getGrays()['400'],
						formatter: function formatter(value) {
							return value.substring(0, 19);
						},
						margin: 15
					},
					splitLine: {
						show: false
					}
				},
				yAxis: {
					type: 'value',
					splitLine: {
						lineStyle: {
							type: 'dashed',
							color: utils.getGrays()['200']
						}
					},
					boundaryGap: false,
					axisLabel: {
						show: true,
						color: utils.getGrays()['400'],
						margin: 15
					},
					axisTick: {
						show: false
					},
					axisLine: {
						show: false
					},
					min: 0
				},
				series: [{
					type: 'line',
					data: JSON.parse(result_value),
					itemStyle: {
						color: utils.getGrays().white,
						borderColor: utils.getColor('primary'),
						borderWidth: 2
					},
					lineStyle: {
						color: utils.getColor('primary'),
						width: 1
					},
					showSymbol: false,
					symbol: 'circle',
					symbolSize: 10,
					smooth: false,
					hoverAnimation: true
				}],
				grid: {
					right: '3%',
					left: '10%',
					bottom: '10%',
					top: '5%'
				}
			};
		};
		echartSetOption(chart, userOptions, getDefaultOptions);
	}
}

// 설비 온도 리스트 검색
function searchTemperatureList() {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();

	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
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
			url: '/qgTemperatureApi/getTemperatureList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate
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
				$.each(data.qgTemperatureViewList, function(i, list) {
					data.qgTemperatureViewList[i].tag = "<div id='"+list.machineId+"'></div>";
					data.qgTemperatureViewList[i].sparkLineDataTemp = data.qgTemperatureViewList[i].sparkLineData;
				});
				temperatureListGrid.setData(data.qgTemperatureViewList);
				swal.closeModal();
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

$(document).ready(function() {

	initGrid();
	searchAreaList();
	searchMachineList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);
	
	Highcharts.SparkLine = function (a, b, c) {
	    const hasRenderToArg = typeof a === 'string' || a.nodeName;
	    let options = arguments[hasRenderToArg ? 1 : 0];
	    const defaultOptions = {
	        chart: {
	            renderTo: (options.chart && options.chart.renderTo) || (hasRenderToArg && a),
	            backgroundColor: null,
	            borderWidth: 0,
	            type: 'line',
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
        temperatureListGrid.exportExcel("temperature_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});