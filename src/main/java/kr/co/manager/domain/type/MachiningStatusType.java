package kr.co.manager.domain.type;

public enum MachiningStatusType implements EnumTypeMapper{
    warmup("Warming Up"),
    machining("Machining"),
    none("None"),
    machineAlarm("Machine Alarm");
    String text;
	MachiningStatusType(String text){
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

