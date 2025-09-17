package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String content;
    private LocalDateTime createdTime;
}
