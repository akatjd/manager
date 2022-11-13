var stopCntListGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["stopCnt"] = function () {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["totalItemCnt"] = function () {
	var startDate = $('#startdate').val();
	var startDateArr = startDate.split("-");
	var newStartDate = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
	var endDate = $('#enddate').val();
	var endDateArr = endDate.split("-");
	var newEndDate = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
	var btMs = newEndDate.getTime() - newStartDate.getTime();
	var btDay = 1 + btMs / (1000 * 60 * 60 * 24);
	var value = this.value;
	var stopCnt = this.item.stopCnt;
	var standard = $('#standard').val();
	var result = (stopCnt * (standard * btDay)) / value;
	result = Math.floor(result);
    return result;
};

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["calCnt"] = function () {
	var value = this.value;
	value = Math.round(value * 10) / 10;
	value = (value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

function initGrid(){
    stopCntListGrid.setConfig({
        target: $('[data-ax5grid="stopCntList-grid"]'),
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
            columnHeight: 32,
            onClick: function () {
            },
			trStyleClass: function(){
                return "grid-cell-white";
            }
        },
        columns: [
			{key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
            {key: "machineName", label: "설비명", width: 150, align: "left"},
            {key: "machineHoldCnt", label: "위험 정지 횟수(회)", width: 150, align: "right", formatter: "stopCnt"},
        ]
    });
}

var alertPlaceholder = document.getElementById('liveAlertPlaceholder');
var alertTrigger = document.getElementById('liveAlertBtn');

function alert(message, type) {
	var wrapper = document.createElement('div')
	wrapper.innerHTML = '<div class="alert alert-' + type + ' border-2 d-flex align-items-center" role="alert"><div class="bg-danger me-3 icon-item"><span class="fas fa-times-circle text-white fs-3"></span></div><p class="mb-0 flex-1">' + message + '</p><button class="btn-close" type="button" data-bs-dismiss="alert" aria-label="Close"></button></div>'
	alertPlaceholder.append(wrapper)
}

// 프로그램 수정 횟수 리스트 검색
function searchStopCntList() {
	var factoryId = $('#selFactory option:selected').val();
    var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var standard = $('#standard').val();
	var startDate = document.getElementById("startdate").value + " 00:00:00";
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
			url: '/qgStopCntApi/getStopCntList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
				startDate: startDate,
				endDate: endDate,
				standard: standard
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
				stopCntListGrid = new ax5.ui.grid();
				initGrid();
				
				stopCntListGrid.addColumn({key: "totalItemCnt", label: "생산수량", width: 150, align: "right"});
				stopCntListGrid.addColumn({key: "calCnt", label: "회/" + standard + "개", width: 150, align: "right", formatter: "calCnt"});
				
				swal.closeModal();
				
				stopCntListGrid.setData(data.stopCntViewList);
			}
		});
	}
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

function searchAreaList(){
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
	
	$('[data-grid-control]').click(function () {
        stopCntListGrid.exportExcel("alarmStopCount_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});