package com.example.shop.domain.member.domain;

import com.example.shop.global.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member_marketing")
public class MemberMarketing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @Column(name = "push_yn", nullable = false)
    private String pushYn;
    @LastModifiedDate
    @Column(name = "push_yn_updated_at", nullable = false)
    private LocalDateTime pushYnUpdatedAt;

    @Column(name = "email_yn", nullable = false)
    private String emailYn;
    @LastModifiedDate
    @Column(name = "email_yn_updated_at", nullable = false)
    private LocalDateTime emailYnUpdatedAt;

    @Column(name = "sms_yn", nullable = false)
    private String smsYn;
    @LastModifiedDate
    @Column(name = "sms_yn_updated_at", nullable = false)
    private LocalDateTime smsYnUpdatedAt;

    @Builder
    public MemberMarketing(Member member, boolean marketingYn) {

        this.pushYn = "n";
        this.emailYn = "n";
        this.smsYn = "n";
        this.member = member;

        if (marketingYn) {
            this.pushYn = "y";
            this.emailYn = "y";
            this.smsYn = "y";
        }
    }
}
