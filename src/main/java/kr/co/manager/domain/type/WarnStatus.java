package kr.co.manager.domain.type;

public enum WarnStatus implements EnumTypeMapper {
	good("GOOD"),
    info("IGNORE"),
    warn("LV2"),
    error("LV3"),
    stop("STOP"),
    standard("INIT"),
    warmup("WARM UP"),
    badsync("Sync ERR"),
    first("FIRST"),
    test("TEST");
    
    String text;
    WarnStatus(String text){
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
