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

	@Column(name = "post_id")
	private Long postId;

	@Column(name = "member_id")
	private Long memberId;


	@Column(name = "liked_at")
	private LocalDateTime likedAt;


	@Builder
	private PostLike(Long id, Long postId, Long memberId, LocalDateTime likedAt) {
		Assert.hasText(String.valueOf(id), "Post Like ID must not be empty");

		this.id = id;
		this.postId = postId;
		this.memberId = memberId;
		this.likedAt = likedAt;
	}
}