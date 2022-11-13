var alarmHistoryListGrid = new ax5.ui.grid();

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
	var hour = this.value / 3600;
	hour = hour.toFixed(1);
	return hour + ' hr';
};

ax5.ui.grid.formatter["machineHoldCnt"] = function() {
	return this.value + ' 건';
};

ax5.ui.grid.formatter["regDate"] = function() {
	var result = (this.value).toString().substring(0, 19);
	return result;
};

ax5.ui.grid.formatter["updateDate"] = function() {
	var result = (this.value).toString().substring(0, 19);
	return result;
};

ax5.ui.grid.formatter["gapDate"] = function() {
	if(this.item.updateDate === ''){
		return '';
	}else{
		var startDate = new Date(this.item.regDate);
		var endDate = new Date(this.item.updateDate);
		var gapDate = endDate - startDate;
		gapDate = gapDate / 1000;
		var hours = gapDate / 3600;
		hours = Math.floor(hours);
		if(hours < 10){
			hours = '0'+hours;
		}
		var mins = (gapDate % 3600) / 60;
		mins = Math.floor(mins);
		if(mins < 10){
			mins = '0'+mins;
		}
		var secs = (gapDate % 3600) % 60;
		secs = Math.floor(secs);
		if(secs < 10){
			secs = '0'+secs;
		}
		
		return hours + ':' + mins + ':' + secs;
	}
};

function initAlarmHistoryGrid() {
	alarmHistoryListGrid.setConfig({
		target: $('[data-ax5grid="alarmHistoryList-grid"]'),
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: true,
		multiSort: true,
//		virtualScrollY: false,
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
        page: {
            navigationItemCount: 9,
            height: 30,
            display: true,
            firstIcon: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
            prevIcon: '<i class="fa fa-caret-left" aria-hidden="true"></i>',
            nextIcon: '<i class="fa fa-caret-right" aria-hidden="true"></i>',
            lastIcon: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            onChange: function () {
                pageSetData(this.page.selectPage);
            }
        },
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "regDate", label: "발생시간", width: 160, align: "center", formatter: "regDate" },
			{ key: "updateDate", label: "종료시간", width: 160, align: "center", formatter: "updateDate" },
			{ key: "updateDate", label: "시간", width: 80, align: "center", formatter: "gapDate" },
			{ key: "alarmId", label: "Id", width: 90, align: "center", formatter: "tooltipName", hidden: true },
			{ key: "strAlarmType", label: "유형", width: 110, align: "left", formatter: "tooltipName" },
			{ key: "alarmNo", label: "No", width: 90, align: "center", formatter: "tooltipName", hidden: true },
			{ key: "alarmAxis", label: "Axis", width: 90, align: "center", formatter: "tooltipName", hidden: true },
			{ key: "alarmMsg", label: "알람메세지", width: 250, align: "left", formatter: "tooltipName" },
		]
	});
	alarmHistoryListGrid.setColumnSort({
      "regDate": {orderBy: "desc", seq: 0},
    });
}

var alarmHistoryList = [];
function searchAlarmHistoryList() {
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var alarmTypeId = $('#selAlarmType option:selected').val();
	var startDate = document.getElementById("startdate").value + " 00:00:00";
	var endDate = document.getElementById("enddate").value + " 23:59:59";
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
			url: '/qgAlarmHistoryApi/getAlarmHistoryList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
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
                alarmHistoryList = data.qgAlarmHistoryViewList;
				initAlarmHistoryGrid();
				swal.closeModal();
                pageSetData();
//				alarmHistoryListGrid.setData(data.qgAlarmHistoryViewList);
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
	if(!isNull(factoryId)){
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

function searchAreaListAndAlarmTypeList(){
	searchAreaList();
	searchAlarmTypeList();
}

function pageSetData(_pageNo){
    if(isNull(_pageNo)){
        var startNo = 0;
        var endNo = 100;
        
        var list = alarmHistoryList.slice(startNo, endNo);
        
        var totalElementsCnt = alarmHistoryList.length;
        var totalPageCnt = Math.floor(alarmHistoryList.length / 100);
        if((alarmHistoryList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        alarmHistoryListGrid.setData({
            list: list,
            page: {
                currentPage: 0 || 0,
                pageSize: 50,
                totalElements: totalElementsCnt,
                totalPages: totalPageCnt
            }
        });
    }else{
        var startNo = (_pageNo * 100);
        var endNo = (_pageNo + 1) * 100;
        
        var list = alarmHistoryList.slice(startNo, endNo);
        
        var totalElementsCnt = alarmHistoryList.length;
        var totalPageCnt = Math.floor(alarmHistoryList.length / 100);
        if((alarmHistoryList.length % 100) > 0){
            totalPageCnt = totalPageCnt + 1;
        }
        alarmHistoryListGrid.setData({
            list: list,
            page: {
                currentPage: _pageNo || 0,
                pageSize: 50,
                totalElements: totalElementsCnt,
                totalPages: totalPageCnt
            }
        });
    }
}

$(document).ready(function() {

	initAlarmHistoryGrid();
	searchAreaList();
	searchMachineList();
	searchAlarmTypeList();

	var today = getToday();

	document.getElementById("startdate").setAttribute('value', today);
	document.getElementById("enddate").setAttribute('value', today);

	flatpickr("#startdate, #enddate", {
		//		enableTime: true,
		//		time_24hr: true,
		//		minuteIncrement: 1,
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
	
	$('[data-grid-control]').click(function () {
        alarmHistoryListGrid.exportExcel("alarmHistory"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});