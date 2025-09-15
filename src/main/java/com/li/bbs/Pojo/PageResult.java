package com.li.bbs.Pojo;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    // 手动添加无参构造方法
    public PageResult() {
    }

    // 手动添加全参构造方法
    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}