package com.aps.monitor.dao;

import com.aps.monitor.model.ComResorce;
import com.aps.monitor.model.ComResorceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComResorceMapper {
    int countByExample(ComResorceExample example);

    int deleteByExample(ComResorceExample example);

    int deleteByPrimaryKey(Integer resourceId);

    int insert(ComResorce record);

    int insertSelective(ComResorce record);

    List<ComResorce> selectByExample(ComResorceExample example);

    ComResorce selectByPrimaryKey(Integer resourceId);

    int updateByExampleSelective(@Param("record") ComResorce record, @Param("example") ComResorceExample example);

    int updateByExample(@Param("record") ComResorce record, @Param("example") ComResorceExample example);

    int updateByPrimaryKeySelective(ComResorce record);

    int updateByPrimaryKey(ComResorce record);
}