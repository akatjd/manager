var toolChangeCntListGrid = new ax5.ui.grid();
var toolChangeCntDetailListGrid = new ax5.ui.grid();
var tempMachineId = null;
var tempMachineName = null;

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["sparkLineData"] = function() {
	return "<div id='" + this.item.machineId + "'></div>";
};

ax5.ui.grid.formatter["totalToolChangeCnt"] = function() {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["modalBtn"] = function () {
	var machineId = this.item.machineId;
	var machineName = this.item.machineName;
    value = '<a class="btn btn-primary mb-1 btn-sm" onclick="openModal(\''+ machineId + "," + machineName +'\')" style="height:25px;padding-left:6px;padding-right:5px;">상세보기</a>';
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
	var toolChangeCnt = this.item.totalToolChangeCnt;
	var standard = $('#standard').val();
	var result = (toolChangeCnt * (standard * btDay)) / value;
	result = Math.floor(result);
	this.value = result;
	return result;
};

ax5.ui.grid.formatter["detailTotalItemCnt"] = function () {
	var startDate = $('#startdate').val();
	var startDateArr = startDate.split("-");
	var newStartDate = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
	var endDate = $('#enddate').val();
	var endDateArr = endDate.split("-");
	var newEndDate = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
	var btMs = newEndDate.getTime() - newStartDate.getTime();
	var btDay = 1 + btMs / (1000 * 60 * 60 * 24);
	var value = this.value;
	var toolChangeCnt = this.item.toolChangeCnt;
	var standard = $('#standard').val();
	var result = (toolChangeCnt * (standard * btDay)) / value;
	result = Math.floor(result);
    return result;
};

ax5.ui.grid.formatter["calCnt"] = function () {
	var value = this.value;
	value = Math.round(value * 10) / 10;
	value = (value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["totalItemCnt"] = function () {
	var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	return value;
};

ax5.ui.grid.formatter["comma"] = function() {
    var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    return value;
};

function initGrid() {
	toolChangeCntListGrid.setConfig({
		target: $('[data-ax5grid="toolChangeCntList-grid"]'),
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
			onDBLClick: function () {
				this.self.select(this.dindex);
                if(this.colIndex === 1){
					item = this.item;
					$('#myModal').modal('show');
                }
            },
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 150, align: "left", formatter: "tooltipName" },
			{ key: "totalToolChangeCnt", label: "교환 횟수", width: 110, align: "right", formatter: "totalToolChangeCnt" },
		]
	});
	toolChangeCntListGrid.setColumnSort({
      "totalToolChangeCnt": {orderBy: "desc", seq: 0},
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

// 공구 교환 횟수 리스트 검색
function searchToolChangeCntList() {
	
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var standard = $('#standard').val();
	
	if (!factoryId) {
		if (alertTrigger) {
			swal("공장을 선택해주세요.", "", "warning");
		}
	} else if (endDate < startDate) {
		if (alertTrigger) {
			swal("기간 설정을 올바르게 해주세요.", "", "warning");
		}
	} else {
		var standard = $('#standard').val();
		$.ajax({
			url: '/qgToolChangeCntApi/getToolChangeCnt',
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
					html: "<h2 style='color:#344050'>데이터를 가져오는 중입니다..</h2>" + '<div class="progress m-t-xs full progress-striped active" style="width: 90%" aria-valuemax="100" aria-valuemin="0" aria-valuenow="90" role="progressbar" class=" progress-bar progress-bar-success"><div class="progress-bar" style="width: 100%"></div></div>',
					background: '#fff',
					showConfirmButton: false,
				});
			},
			success: function(data) {
				toolChangeCntListGrid = new ax5.ui.grid();
				initGrid();
				
				toolChangeCntListGrid.addColumn({ key: "totalItemCnt", label: "생산수량", width: 110, align: "right", formatter: "totalItemCnt"});
				toolChangeCntListGrid.addColumn({ key: "calCnt", label: "회/"+ standard +"개", width: 110, align: "right", formatter: "calCnt"});
				toolChangeCntListGrid.addColumn({ key: "machineId", label: "상세정보", width: 83, formatter: "modalBtn" });
				
				swal.closeModal();
				
				toolChangeCntListGrid.setData(data.qgToolChangeCntViewList);
			},
			complete: function() {
			}
		});
	}
}

function searchToolChangeCntDetailList() {
	var factoryId = $('#selFactory option:selected').val();
	var machineId = tempMachineId;
	var machineName = tempMachineName;
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value + " 23:59:59";
	var endDate2 = document.getElementById("enddate").value;
	var element = document.getElementById("staticBackdropLabel");
	element.innerText = "공구별 교환 횟수 (" + machineName + ")";
	var element2 = document.getElementById("selDate");
	element2.innerText = startDate + " ~ " + endDate2;
	var standard = $('#standard').val();
	
	$.ajax({
		url: '/qgToolChangeCntApi/getToolChangeCntDetail',
		type: 'POST',
		data: {
			factoryId: factoryId,
			machineId: machineId,
			startDate: startDate,
			endDate: endDate,
			standard: standard
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
			toolChangeCntDetailListGrid = new ax5.ui.grid();
			initDetailGrid();
			
			toolChangeCntDetailListGrid.addColumn({ key: "totalItemCnt", label: "생산수량", width: 100, align: "right", formatter: "totalItemCnt" });
			toolChangeCntDetailListGrid.addColumn({ key: "calCnt", label: "회/"+ standard +"개", width: 100, align: "right", formatter: "calCnt"});
			toolChangeCntDetailListGrid.addColumn({ key: "presetCnt", label: "관리수명", width: 100, align: "right", formatter: "comma"});
			
			toolChangeCntDetailListGrid.setData(data.qgToolChangeCntDetailViewList);
		}
	});
}

// 공구 교환 횟수 디테일 그리드 (Tool 별)
function initDetailGrid(){
	toolChangeCntDetailListGrid.setConfig({
		target: $('[data-ax5grid="toolChangeCntDetailList-grid"]'),
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
			trStyleClass: function(){
                return "grid-cell-white";
            }
        },
        columns: [
            {key: "toolName",            label: "공구",         width: 100, align: "left"},
            {key: "toolChangeCnt",   label: "교환 횟수",        width: 100, align: "right",},
        ]
	});
	toolChangeCntDetailListGrid.setColumnSort({
      "toolChangeCnt": {orderBy: "desc", seq: 0},
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
	var stDate = $('#startdate').val();
	var endDate = $('#enddate').val();
	if(stDate === ''){
		document.getElementById("startdate").setAttribute('value', today);
	}
	if(endDate === ''){
		document.getElementById("enddate").setAttribute('value', today);
	}
	
	$('#myModal').on('hidden.bs.modal', function (e) {
        toolChangeCntDetailListGrid.destroy();
		var element = document.getElementById("staticBackdropLabel");
		element.innerText = "공구별 교환 횟수";
		var element2 = document.getElementById("selDate");
		element2.innerText = "";
    });

	$('#myModal').on('shown.bs.modal', function (e) {
		initDetailGrid();
		searchToolChangeCntDetailList();
	});
	
	flatpickr('#startdate, #enddate', {
		plugins: [
			ShortcutButtonsPlugin({
				button: [
					{
						label: 'Today'
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
        toolChangeCntListGrid.exportExcel("toolChangeCount_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});