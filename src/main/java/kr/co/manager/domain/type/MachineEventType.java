package kr.co.manager.domain.type;

public enum MachineEventType implements EnumTypeMapper {
	/*product_add("제품 추가"),
    product_change("제품 변경"),
    gcode_change("G-Code 변경"),
    gcode_add("G-Code 추가"),
    barfeeder("바피더"),
    machine_alarm("기계 알람"),
    machine_operate_message("기계 작동 메세지 변경"),
    monitoring_alarm("모니터링 알람"),
    monitoring_stop("모니터링 정지"),
    monitoring_stop_release("모니터링 정지 해제"),
    tool_change("공구변경"),
    tool_change_remark("공구변경-이상없음"),
    feed_rate_override_value("Feed Rate 변경"),
    spindle_speed_override_value("Spindle Speed 변경"),
    inspection_use("검사설정 ON/OFF"),
    stop_use("정지설정 ON/OFF"),
    failure_start("고장등록"),
    failure_end("고장해제"),
    basic_req("표준파형 요청"),
    change_basic_raw_data("표준파형 변경");*/
	
	product_add("Add product"),
    product_change("Change product"),
    gcode_change("NC Program change"),
    gcode_add("Add NC program"),
    barfeeder("Barfeeder"),
    machine_alarm("Machine alarm"),
    machine_operate_message("Machine operate message"),
    monitoring_alarm("Monitoring alarm"),
    monitoring_stop("Monitoring stop"),
    monitoring_stop_release("Monitoring stop release"),
    tool_change("Tool replacement"),
    tool_change_remark("Tool replacement-clear"),
    feed_rate_override_value("Feed Rate override"),
    rapid_override_value("Rapid override"),
    spindle_speed_override_value("Spindle Speed override"),
    inspection_use("Inspection Setting ON/OFF"),
    stop_use("Stop setting ON/OFF"),
    failure_start("Failure start"),
    failure_end("Failure end"),
    basic_req("Request Standard wave"),
    change_basic_raw_data("Change Standard wave"),
    inspection_add("Add Inspection policy"),
    change_inspection("Change Inspection policy"),
    change_basic_section_alarm("Change basic section alarm");
    
    String text;
    MachineEventType(String text){
        this.text = text;
    }
    @Override
    public String getCode() {
        return name();
    }
    @Override
    public String getText() {
        return text;
    }
}
