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

    @NotBlank(message = "标题不能为空")
    @Size(max=50,message = "标题不能超过50个字符")
    private String title;

    @NotBlank(message = "副标题不能为空")
    @Size(max=100,message = "副标题不能超过100个字符")
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
