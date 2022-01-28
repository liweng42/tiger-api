package com.tigerapi.service;

import com.tigerapi.entity.Brand;

import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;

/**
 * (Brand)表服务接口
 *
 * @author makejava
 * @since 2022-01-28 10:01:35
 */
public interface BrandService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Brand queryById(Long id);
//
//    /**
//     * 分页查询
//     *
//     * @param brand 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    PageBean<Brand> queryByPage(Brand brand, PageRequest pageRequest);


    List<Brand> findAll();

    /**
     * 新增数据
     *
     * @param brand 实例对象
     * @return 实例对象
     */
    Brand insert(Brand brand);

    /**
     * 修改数据
     *
     * @param brand 实例对象
     * @return 实例对象
     */
    Brand update(Brand brand);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
