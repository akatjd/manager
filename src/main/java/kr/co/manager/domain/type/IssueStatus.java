package kr.co.manager.domain.type;

public enum IssueStatus implements EnumTypeMapper{
    clear("이상없음"),
    not_solved("미처리"),
    complete("완료"),
    hold("보류"),
    not_assigned("담당자 미지정");
    
    String text;
    IssueStatus(String text){
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