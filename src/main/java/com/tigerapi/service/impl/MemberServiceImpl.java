package com.tigerapi.service.impl;

import com.tigerapi.entity.Member;
import com.tigerapi.entity.MemberExample;
import com.tigerapi.mapper.MemberMapper;
import com.tigerapi.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Member)表服务实现类
 *
 * @author makejava
 * @since 2022-01-30 13:44:44
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Member queryById(Long id) {
        return this.memberMapper.selectByPrimaryKey(id);
    }


    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member insert(Member member) {
        this.memberMapper.insert(member);
        return member;
    }

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member update(Member member) {
        MemberExample example = new MemberExample();
        example.createCriteria().andIdEqualTo(member.getId());
        this.memberMapper.updateByExampleSelective(member, example);
        return this.queryById(member.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.memberMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Member selectByOpenId(String openId) {
        MemberExample record = new MemberExample();
        record.createCriteria().andOpenIdEqualTo(openId);
        List<Member> list = this.memberMapper.selectByExample(record);
        if (!list.isEmpty()){
            return list.get(0);
        }
        else {
            return null;
        }
    }
}
