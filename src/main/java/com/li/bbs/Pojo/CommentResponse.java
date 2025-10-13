package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Integer postId;
    private Integer userId;
    private String username;
    private String avatarUrl;
    private String content;
    private LocalDateTime createdTime;
}
