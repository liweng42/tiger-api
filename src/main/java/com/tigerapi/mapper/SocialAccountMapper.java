package com.tigerapi.mapper;

import com.tigerapi.entity.SocialAccount;
import com.tigerapi.entity.SocialAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SocialAccountMapper {
    long countByExample(SocialAccountExample example);

    int deleteByExample(SocialAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SocialAccount record);

    int insertSelective(SocialAccount record);

    List<SocialAccount> selectByExample(SocialAccountExample example);

    SocialAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SocialAccount record, @Param("example") SocialAccountExample example);

    int updateByExample(@Param("record") SocialAccount record, @Param("example") SocialAccountExample example);

    int updateByPrimaryKeySelective(SocialAccount record);

    int updateByPrimaryKey(SocialAccount record);
}