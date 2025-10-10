package com.li.bbs.Pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
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
