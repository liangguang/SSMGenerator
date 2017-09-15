package com.swust.zj.mapper;

import com.swust.zj.pojo.TbOther;
import com.swust.zj.pojo.TbOtherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOtherMapper {
    int countByExample(TbOtherExample example);

    int deleteByExample(TbOtherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbOther record);

    int insertSelective(TbOther record);

    List<TbOther> selectByExample(TbOtherExample example);

    TbOther selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbOther record, @Param("example") TbOtherExample example);

    int updateByExample(@Param("record") TbOther record, @Param("example") TbOtherExample example);

    int updateByPrimaryKeySelective(TbOther record);

    int updateByPrimaryKey(TbOther record);
}