package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostControllerImp implements PostController {

    @Autowired
    PostService postService;

    @Autowired
    ValidationUtil validationUtil;


    /**
     * 添加帖子
     *
     * @param newPost 帖子内容
     */
    @PostMapping
    @Override
    public Result add(@RequestBody Post newPost, @RequestHeader String token) {
        log.info("正在添加新帖子中...");
        //校验帖子参数
        if (!validationUtil.isValidPost(newPost)) {
            return Result.error(Result.PARAM_ERROR, "帖子参数不符合要求：标题、副标题或内容格式不正确");
        }

        postService.add(newPost, token);
        log.info("添加新帖子：{},传递令牌：{}", newPost, token);
        return Result.success(Result.CREATED);
    }


    /**
     * 分页查询帖子
     *
     * @param queryParam 查询参数 默认不分页 保留功能
     */
    @GetMapping
    @Override
    public Result<PageResult<PostResponse>> page(QueryParam queryParam) {
        log.info("正在分页查询帖子中...");
        PageResult<PostResponse> pageResult = postService.page(queryParam);
        log.info("分页查询帖子：{}", queryParam);
        return Result.success(pageResult);
    }


    /**
     * 分页查询点击量前十帖子
     */
    @GetMapping("/hot")
    @Override
    public Result<PageResult<PostResponse>> hotPageViews() {
        QueryParam queryParam = new QueryParam();
        log.info("正在分页查询点击量前十帖子中...");
        queryParam.setPageSize(10);
        queryParam.setPage(1);
        PageResult<PostResponse> pageResult = postService.hotpageViews(queryParam);
        log.info("查询成功：{}", pageResult);
        return Result.success(pageResult);
    }


    /**
     * 根据id查询帖子
     *
     * @param id 帖子id
     */
    @GetMapping("/{id}")
    @Override
    public Result<PostResponse> findById(@PathVariable Integer id) {
        log.info("正在查询帖子中...");
        //校验帖子id
        if (id == null) {
            return Result.error(Result.PARAM_ERROR, "帖子id不能为空");
        }
        PostResponse postById = postService.findById(id);
        log.info("查询帖子：{}", id);
        return Result.success(postById);
    }


    /**
     * 更新帖子 未使用
     *
     * @param post 帖子内容
     * @param id   帖子id
     */
    @PutMapping("/{id}")
    @Override
    public Result update(@RequestBody Post post, @PathVariable Integer id, @RequestHeader String token) {
        log.info("正在更新帖子中...");
        //校验帖子参数
        if (!validationUtil.isValidPost(post)) {
            return Result.error(Result.PARAM_ERROR, "帖子参数不符合要求：标题、副标题或内容格式不正确");
        }
        postService.update(post, id, token);
        log.info("更新帖子：{},传递令牌：{}", post, token);
        return Result.success(Result.NO_CONTENT);
    }


    /**
     * 删除帖子
     *
     * @param id 帖子id
     */
    @DeleteMapping("/{id}")
    @Override
    public Result delete(@PathVariable Integer id, @RequestHeader String token) {
        log.info("正在删除帖子中...");
        postService.delete(id, token);
        log.info("删除帖子：{},传递令牌：{}", id, token);
        return Result.success(Result.NO_CONTENT);
    }
}
