package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Integer id;
    private String title;
    private String subtitle;
    private String content;
    private Integer userId;
    private String username;
    private String avatarUrl;
    private Integer boardId;
    private Integer viewsCount;
    private Integer commentsCount;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
