package com.kakuhou.dao.generator.mapper;

import com.kakuhou.dao.generator.bean.RestInterfacePO;
import com.kakuhou.dao.generator.bean.RestInterfacePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestInterfacePOMapper {
    int countByExample(RestInterfacePOExample example);

    int deleteByExample(RestInterfacePOExample example);

    int deleteByPrimaryKey(String id);

    int insertBatch(@Param("records") List<RestInterfacePO> records);

    int insert(RestInterfacePO record);

    int insertSelective(RestInterfacePO record);

    Object functionByExample(RestInterfacePOExample example);

    List<RestInterfacePO> selectByExample(RestInterfacePOExample example);

    RestInterfacePO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RestInterfacePO record, @Param("example") RestInterfacePOExample example);

    int updateByExample(@Param("record") RestInterfacePO record, @Param("example") RestInterfacePOExample example);

    int updateByPrimaryKeySelective(RestInterfacePO record);

    int updateByPrimaryKey(RestInterfacePO record);
}