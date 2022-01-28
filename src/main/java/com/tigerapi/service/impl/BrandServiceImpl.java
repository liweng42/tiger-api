package com.tigerapi.service.impl;

import com.tigerapi.entity.Brand;
import com.tigerapi.entity.BrandExample;
import com.tigerapi.mapper.BrandMapper;
import com.tigerapi.service.BrandService;
import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Brand)表服务实现类
 *
 * @author makejava
 * @since 2022-01-28 10:01:35
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Brand queryById(Long id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> findAll() {
        BrandExample example = new BrandExample();
        return this.brandMapper.selectByExample(example);
    }

//    /**
//     * 分页查询
//     *
//     * @param brand 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<Brand> queryByPage(Brand brand, PageRequest pageRequest) {
//        long total = this.brandDao.count(brand);
//        return new PageImpl<>(this.brandDao.queryAllByLimit(brand, pageRequest), pageRequest, total);
//    }


    /**
     * 新增数据
     *
     * @param brand 实例对象
     * @return 实例对象
     */
    @Override
    public Brand insert(Brand brand) {
        brand.setCreateBy("jerry");
        brand.setCreateTime(new Date());
        this.brandMapper.insert(brand);
        return brand;
    }

    /**
     * 修改数据
     *
     * @param brand 实例对象
     * @return 实例对象
     */
    @Override
    public Brand update(Brand brand) {

//        this.brandMapper.updateByExampleSelective(brand);
        return this.queryById(brand.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.brandMapper.deleteByPrimaryKey(id) > 0;
    }
}
