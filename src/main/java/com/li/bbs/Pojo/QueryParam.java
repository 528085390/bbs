package com.li.bbs.Pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class QueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;

    private String title;
    private Integer userId;
    private Integer postId;
    private Integer boardId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;


}
