package com.li.bbs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    Integer id;
    String username;
    String email;
    String avatarUrl;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
}
