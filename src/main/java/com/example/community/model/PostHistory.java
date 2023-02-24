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
@Table(name = "post_history")
public class PostHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "post_id")
	private Long postId;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	private PostHistory(Long id, Long postId, Long memberId, LocalDateTime createdAt,
						LocalDateTime updatedAt, LocalDateTime deletedAt) {
		Assert.hasText(String.valueOf(id), "Post ID must not be empty");

		this.id = id;
		this.postId = postId;
		this.memberId = memberId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
}