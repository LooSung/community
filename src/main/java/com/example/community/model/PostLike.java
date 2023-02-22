package com.example.community.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "post_like")
public class PostLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Member member;

	@Column(name = "liked_at")
	private LocalDateTime likedAt;


	@Builder
	private PostLike(Long id, Post post, Member member, LocalDateTime likedAt) {
		Assert.hasText(String.valueOf(id), "Post Like ID must not be empty");

		this.id = id;
		this.post = post;
		this.member = member;
		this.likedAt = likedAt;
	}
}