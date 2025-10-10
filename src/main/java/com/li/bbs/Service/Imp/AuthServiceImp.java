package com.li.bbs.Service.Imp;
import com.li.bbs.Exception.NoResourceFoundException;
import com.li.bbs.Exception.ResourceDuplicateException;
import com.li.bbs.Mapper.AuthMapper;
import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthMapper authMapper;

    @Transactional
    @Override
    public void register(User newUserInfo) {
        if (authMapper.findByUsername(newUserInfo.getUsername()) != null){
            throw new ResourceDuplicateException("用户名已存在");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(newUserInfo.getPassword());
        newUserInfo.setPassword(encodedPassword);
        newUserInfo.setCreatedTime(LocalDateTime.now());
        newUserInfo.setUpdatedTime(LocalDateTime.now());
        authMapper.addUser(newUserInfo);
    }

    @Transactional
    @Override
    public String login(LoginRequest LoginUser) {
        User user = authMapper.findByUsername(LoginUser.getUsername());
        if (user == null){
            throw new NoResourceFoundException("用户不存在");
        }
        if (!new BCryptPasswordEncoder().matches(LoginUser.getPassword(), user.getPassword())){
            throw new BadCredentialsException("用户名或密码错误");
        }
        updateTime(user.getId());
        return jwtUtil.generateToken(user.getUsername(), user.getId());
    }


    @Override
    public void updateTime(Integer userId) {
        authMapper.updateTime(LocalDateTime.now(), userId);
    }


}
