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


    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2到20之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名只能包含字母、数字和下划线")
    String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "密码只能包含字母、数字和下划线")
    String password;

    @Email(message = "邮箱格式不正确")
    String email;

    String avatarUrl;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;

}
