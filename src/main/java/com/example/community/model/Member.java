package com.example.community.model;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "password")
	private String password;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "account_id")
	private String accountId;

	@Column(name = "quit")
	private boolean quit;

	@Builder
	private Member(Long id, String password, String nickname, String accountType,
				   String accountId, boolean quit) {
		Assert.hasText(String.valueOf(id), "User Id must not be empty");

		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.accountType = accountType;
		this.accountId = accountId;
		this.quit = quit;
	}
}