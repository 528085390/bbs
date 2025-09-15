package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Post {
    private Integer id;
    private String title;
    private String subtitle;
    private String content;
    private Integer userId;
    private Integer boardId;
    private Integer viewsCount;
    private Integer commentsCount;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
