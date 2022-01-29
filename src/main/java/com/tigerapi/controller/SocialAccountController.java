package com.tigerapi.controller;

import com.tigerapi.common.core.controller.BaseController;
import com.tigerapi.common.core.page.TableDataInfo;
import com.tigerapi.entity.SocialAccount;
import com.tigerapi.service.SocialAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SocialAccount)表控制层
 *
 * @author makejava
 * @since 2022-01-29 14:08:20
 */
@RestController
@RequestMapping("socialAccount")
public class SocialAccountController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private SocialAccountService socialAccountService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SocialAccount> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.socialAccountService.queryById(id));
    }

    public TableDataInfo findAll(){
        List<SocialAccount> list = socialAccountService.findAll();
        return getDataTable(list);
    }

    /**
     * 新增数据
     *
     * @param socialAccount 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SocialAccount> add(SocialAccount socialAccount) {
        return ResponseEntity.ok(this.socialAccountService.insert(socialAccount));
    }

    /**
     * 编辑数据
     *
     * @param socialAccount 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SocialAccount> edit(SocialAccount socialAccount) {
        return ResponseEntity.ok(this.socialAccountService.update(socialAccount));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.socialAccountService.deleteById(id));
    }

}

