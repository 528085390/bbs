package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String content;
    @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
