package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Favorite {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private LocalDateTime createdTime;
}
