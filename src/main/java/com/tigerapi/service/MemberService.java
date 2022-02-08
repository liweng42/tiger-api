package com.tigerapi.service;

import com.tigerapi.entity.Member;

/**
 * (Member)表服务接口
 *
 * @author makejava
 * @since 2022-01-30 13:44:44
 */
public interface MemberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Member queryById(Long id);


    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member insert(Member member);

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member update(Member member);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    Member selectByOpenId(String openId);



}
