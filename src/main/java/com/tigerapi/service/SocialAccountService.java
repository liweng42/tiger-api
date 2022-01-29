package com.tigerapi.service;

import com.tigerapi.entity.SocialAccount;

import java.util.List;

/**
 * (SocialAccount)表服务接口
 *
 * @author makejava
 * @since 2022-01-29 14:08:28
 */
public interface SocialAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SocialAccount queryById(Long id);

    List<SocialAccount> findAll();
    /**
     * 新增数据
     *
     * @param socialAccount 实例对象
     * @return 实例对象
     */
    SocialAccount insert(SocialAccount socialAccount);

    /**
     * 修改数据
     *
     * @param socialAccount 实例对象
     * @return 实例对象
     */
    SocialAccount update(SocialAccount socialAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
