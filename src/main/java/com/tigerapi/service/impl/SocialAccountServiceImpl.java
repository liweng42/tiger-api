package com.tigerapi.service.impl;

import com.tigerapi.entity.SocialAccount;
import com.tigerapi.entity.SocialAccountExample;
import com.tigerapi.mapper.SocialAccountMapper;
import com.tigerapi.service.SocialAccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SocialAccount)表服务实现类
 *
 * @author makejava
 * @since 2022-01-29 14:08:31
 */
@Service("socialAccountService")
public class SocialAccountServiceImpl implements SocialAccountService {
    @Resource
    private SocialAccountMapper socialAccountMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SocialAccount queryById(Long id) {
        return this.socialAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SocialAccount> findAll() {
        return socialAccountMapper.selectByExample(new SocialAccountExample());
    }


    /**
     * 新增数据
     *
     * @param socialAccount 实例对象
     * @return 实例对象
     */
    @Override
    public SocialAccount insert(SocialAccount socialAccount) {
        this.socialAccountMapper.insert(socialAccount);
        return socialAccount;
    }

    /**
     * 修改数据
     *
     * @param socialAccount 实例对象
     * @return 实例对象
     */
    @Override
    public SocialAccount update(SocialAccount socialAccount) {
//        this.socialAccountMapper.update(socialAccount);
        return this.queryById(socialAccount.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.socialAccountMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public SocialAccount findByUniqueId(String uniqueId) {
        SocialAccountExample example = new SocialAccountExample();
        example.createCriteria().andUniqueIdEqualTo(uniqueId);
        List<SocialAccount> socialAccountList = socialAccountMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(socialAccountList)) {
            return socialAccountList.get(0);
        }
        return null;
    }
}
