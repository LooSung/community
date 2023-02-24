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
	private AccountType accountType;

	@Column(name = "account_id")
	private String accountId;

	@Column(name = "quit")
	private boolean quit;

	public enum AccountType {
		Realtor, Lessor, Lessee;
	}

	public void setAccountType(String accountType) {
		if (accountType == null) {
			return;
		}
		switch (accountType) {
			case "Realtor":
				this.accountType = AccountType.Realtor;
				break;
			case "Lessor":
				this.accountType = AccountType.Lessor;
				break;
			case "Lessee":
				this.accountType = AccountType.Lessee;
				break;
			default:
				throw new IllegalArgumentException("Invalid account type: " + accountType);
		}
	}

	public String getAccountTypeKor() {
		switch (this.accountType) {
			case Realtor:
				return "공인 중개사";
			case Lessor:
				return "임대인";
			case Lessee:
				return "임차인";
			default:
				return "";
		}
	}

	@Builder
	private Member(Long id, String password, String nickname, AccountType accountType,
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