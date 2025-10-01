package com.li.bbs.Pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "副标题不能为空")
    private String subtitle;

    @NotBlank(message = "内容不能为空")
    private String content;

    private Integer userId;
    private Integer boardId;
    private Integer viewsCount;
    private Integer commentsCount;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
