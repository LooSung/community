package com.example.community.model;

import lombok.*;
import org.springframework.util.Assert;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "member_id")
	private Long memberId;

	@Builder
	private Post(Long id, String title, String content, Long memberId) {
		Assert.hasText(String.valueOf(id), "Post ID must not be empty");

		this.id = id;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
	}
}