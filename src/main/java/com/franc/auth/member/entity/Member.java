package com.franc.auth.member.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(
        name = "seq_member_id",
        sequenceName = "MEMBER_ID_SEQ",
        initialValue = 1000000001,
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_id")
    private Long member_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;



    @Builder
    public Member(Long member_id, String username, String password, String role) {
        this.member_id = member_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    /**
     * 패스워드 암호화 메서드
     */
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
