package kr.co.manager.domain.type;

public enum MachineStatusType implements EnumTypeMapper {
	
	run("RUN"),
    hold("HOLD"),
    stop("STOP"),
    dis("DIS"),
    setting("SETTING"),
    off("OFF"),
    mt("기기정비"), //기기정비
    tc("툴교체"), //툴교체
    cl("기기청소"), //기기청소
    gc("GCode변경"), //지코드 변경작업
    failure("고장"); //고장
	
	String text;
	MachineStatusType(String text){
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
