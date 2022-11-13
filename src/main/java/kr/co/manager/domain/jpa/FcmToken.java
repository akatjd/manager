package kr.co.manager.domain.jpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FcmToken {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ft_idx", nullable = false)
    private int ftIdx;

    @Column(name = "token", nullable = false)
    private String token;

    //1:1 관계로 설정
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false) //Account table의 id를 가져온다.
    private Account account;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;


    public int getFtIdx() {
        return ftIdx;
    }

    public void setFtIdx(int ftIdx) {
        this.ftIdx = ftIdx;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
