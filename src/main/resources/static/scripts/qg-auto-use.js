var autoUseListGrid = new ax5.ui.grid();

ax5.ui.grid.formatter["tooltipName"] = function() {
	var value = "<p title='"+ this.value +"'>";
	value += this.value;
	value += "</p>";
	return value;
};

ax5.ui.grid.formatter["sparkLineData"] = function() {
	return "<div id='" + this.item.machineId + "'></div>";
};

ax5.ui.grid.formatter["rate"] = function() {
	value = this.value + "%";
	return value;
};

ax5.ui.grid.formatter["comma"] = function() {
    var value = (this.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    return value;
};

function initGrid() {
	autoUseListGrid.setConfig({
		target: $('[data-ax5grid="autoUseList-grid"]'),
		showLineNumber: true,
		showRowSelector: false,
		multipleSelect: false,
		lineNumberColumnWidth: 40,
		rowSelectorColumnWidth: 28,
		sortable: true,
		multiSort: false,
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
			trStyleClass: function(){
                return "grid-cell-white";
            }
		},
		page: {
            display: false
        },
		columns: [
			{ key: "areaName", label: "라인명", width: 180, align: "left", formatter: "tooltipName" },
			{ key: "machineName", label: "설비명", width: 150, align: "left",styleClass: function () {
                                                                                                            return "grid-cell-white";
                                                                                                        }, formatter: "tooltipName" },
		]
	});
	autoUseListGrid.setColumnSort({
      "areaName": {orderBy: "asc", seq: 0},
	  "machineName": {orderBy: "asc", seq: 1},
    });
}

function searchAutoUseList() {
	
	var factoryId = $('#selFactory option:selected').val();
	var areaId = $('#selArea option:selected').val();
	var machineId = $('#selMachine option:selected').val();
//	var productId = $('#selProduct option:selected').val();
//	var date = document.getElementById("date").value;
	var startDate = document.getElementById("startdate").value;
	var endDate = document.getElementById("enddate").value;
	
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
			url: '/qgAutoUseApi/getAutoUseList',
			type: 'POST',
			data: {
				factoryId: factoryId,
				areaId: areaId,
				machineId: machineId,
//				productId: productId,
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
				autoUseListGrid = new ax5.ui.grid();
	            initGrid();
				
				var dayTypeArr = new Array();
				var dayLabelList = new Array();
				var nightLableList = new Array();
				var keyList = new Array();
				var dtSeqList = new Array();
				var machineIdList = new Array();
				var dataList = new Array();
				
				var autoRate = $('#autoRate').val();
				
				$.each(data.qgAutoUseViewList, function (index, row) {
					dayTypeArr.push(row.dayOrNight);
					dtSeqList.push(row.dtSeq);
					
					var dtSeq = row.dtSeq;
					if(row.dayOrNight === 'day'){
						dayLabelList.push({key: undefined,  label: row.dtScheduleName, columns: [
							{key: undefined,  label: row.dtStartTime + " ~ " + row.dtEndTime,  columns:[
								{key: "dtCnt" + row.dtSeq,  label: "생산수량", align: "right", sortable: false, formatter: "comma"},
								{key: "dtRate" + row.dtSeq,  label: "가동률(%)",  sortable: false, formatter: "rate", styleClass: function () {
                                                                                                            var value;
																											var keyName = "dtRate" + row.dtSeq;
																											if(this.item[keyName] < autoRate) {
																												value = 'grid-cell-red';
																											}
                                                                                                            return value;
                                                                                                        }}
							]},
						]});
						keyList.push("dtCnt" + row.dtSeq);
						keyList.push("dtRate" + row.dtSeq);
					}else {
						nightLableList.push({key: undefined,  label: row.dtScheduleName, columns: [
							{key: undefined,  label: row.dtStartTime + " ~ " + row.dtEndTime,  columns:[
								{key: "dtCnt" + row.dtSeq,  label: "생산수량", align: "right", sortable: false, formatter: "comma"},
								{key: "dtRate" + row.dtSeq,  label: "가동률(%)",  sortable: false, formatter: "rate", styleClass: function () {
                                                                                                            var value;
																											var keyName = "dtRate" + row.dtSeq;
																											if(this.item[keyName] < autoRate) {
																												value = 'grid-cell-red';
																											}
                                                                                                            return value;
                                                                                                        }}
							]},
						]});
						keyList.push("dtCnt" + row.dtSeq);
						keyList.push("dtRate" + row.dtSeq);
					}
					
					$.each(row.dtDatas, function (index, row) {
						machineIdList.push(row.machineId);
						var dataObj = new Object();
						dataObj['dtSeq'] = dtSeq;
						dataObj['totalAutoCnt'] = row.totalAutoCnt;
						dataObj['resultRate'] = row.resultRate;
						dataObj['machineId'] = row.machineId;
						dataObj['machineName'] = row.machineName;
						dataObj['areaName'] = row.areaName;
						dataList.push(dataObj);
					})
				})
				machineIdList = Array.from(new Set(machineIdList));
				
				var gridDataList = new Array();
				$.each(machineIdList, function (index, row) {
					var machineId = row;
					var tempObj = new Object();
					$.each(dataList, function (index, row) {
						if(machineId === row.machineId){
							tempObj['dtCnt'+row.dtSeq] = row.totalAutoCnt;
							tempObj['dtRate'+row.dtSeq] = row.resultRate;
							tempObj['machineId'] = row.machineId;
							tempObj['machineName'] = row.machineName;
							tempObj['areaName'] = row.areaName;
						}
					})
					gridDataList.push(tempObj);
				})
				
				dayTypeArr = Array.from(new Set(dayTypeArr));
				
				$.each(dayTypeArr, function (index, row) {
					if(row === 'day'){
						autoUseListGrid.addColumn({key: undefined,  label: '주간', columns: dayLabelList});
					}else if(row === 'night'){
						autoUseListGrid.addColumn({key: undefined,  label: '야간', columns: nightLableList});
					}
				})
				swal.closeModal();
				autoUseListGrid.setData(gridDataList);
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
			searchProductList();
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
        success: function (data) {
            var productOption;
            $.each(data, function (index, productList) {
                var productId;
                if(isNull(productList.productId)) productId = '';
                else productId = productList.productId;
                productOption += '<option value="'+ productId +'">'+ productList.productName  +'</option>';
            });
            $('#selProduct').html(productOption);
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
	searchProductList();
	
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
		]
	});
	
	$('[data-grid-control]').click(function () {
        autoUseListGrid.exportExcel("autoUse_"+ $('#startdate').val() + "~"+ $('#enddate').val() + ".xls");
    });
});