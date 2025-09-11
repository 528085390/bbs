package com.li.bbs;



import cn.hutool.core.io.IoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;

@RestController
public class testController {
    @GetMapping("/index")

    public String index(String name, HttpServletRequest request) {

        return "hello " + name + "!";
    }


}
