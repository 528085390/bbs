package com.li.bbs.Pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class User {

    Integer id;
    String username;
    String password;
    String email;
    String avatarUrl;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;

}
