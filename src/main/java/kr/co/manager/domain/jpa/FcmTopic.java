package kr.co.manager.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FcmTopic {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ft_idx", nullable = false)
    private int ftIdx;

    @Column(name = "topic", nullable = false)
    private String topic;

    //1:1 관계로 설정
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false) //Account table의 id를 가져온다.
    private Account account;


    public int getFtIdx() {
        return ftIdx;
    }

    public void setFtIdx(int ftIdx) {
        this.ftIdx = ftIdx;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
