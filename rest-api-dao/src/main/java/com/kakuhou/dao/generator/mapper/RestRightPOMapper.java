package com.kakuhou.dao.generator.mapper;

import com.kakuhou.dao.generator.bean.RestRightPO;
import com.kakuhou.dao.generator.bean.RestRightPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestRightPOMapper {
    int countByExample(RestRightPOExample example);

    int deleteByExample(RestRightPOExample example);

    int deleteByPrimaryKey(String id);

    int insertBatch(@Param("records") List<RestRightPO> records);

    int insert(RestRightPO record);

    int insertSelective(RestRightPO record);

    Object functionByExample(RestRightPOExample example);

    List<RestRightPO> selectByExample(RestRightPOExample example);

    RestRightPO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RestRightPO record, @Param("example") RestRightPOExample example);

    int updateByExample(@Param("record") RestRightPO record, @Param("example") RestRightPOExample example);

    int updateByPrimaryKeySelective(RestRightPO record);

    int updateByPrimaryKey(RestRightPO record);
}