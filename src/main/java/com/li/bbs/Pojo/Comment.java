package com.li.bbs.Pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer postId;
    private Integer userId;

    @NotBlank(message = "评论内容不能为空")
    @Size(max=200,message = "评论内容不能超过200个字符")
    private String content;

    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

}
