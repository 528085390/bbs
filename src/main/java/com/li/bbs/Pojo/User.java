package com.li.bbs.Pojo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    Integer id;
    String username;
    String password;
    String email;
    String avatarUrl;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;

}
