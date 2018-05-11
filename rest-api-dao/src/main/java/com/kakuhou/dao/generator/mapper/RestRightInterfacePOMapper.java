package com.kakuhou.dao.generator.mapper;

import com.kakuhou.dao.generator.bean.RestRightInterfacePO;
import com.kakuhou.dao.generator.bean.RestRightInterfacePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestRightInterfacePOMapper {
    int countByExample(RestRightInterfacePOExample example);

    int deleteByExample(RestRightInterfacePOExample example);

    int deleteByPrimaryKey(String id);

    int insertBatch(@Param("records") List<RestRightInterfacePO> records);

    int insert(RestRightInterfacePO record);

    int insertSelective(RestRightInterfacePO record);

    Object functionByExample(RestRightInterfacePOExample example);

    List<RestRightInterfacePO> selectByExample(RestRightInterfacePOExample example);

    RestRightInterfacePO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RestRightInterfacePO record, @Param("example") RestRightInterfacePOExample example);

    int updateByExample(@Param("record") RestRightInterfacePO record, @Param("example") RestRightInterfacePOExample example);

    int updateByPrimaryKeySelective(RestRightInterfacePO record);

    int updateByPrimaryKey(RestRightInterfacePO record);
}