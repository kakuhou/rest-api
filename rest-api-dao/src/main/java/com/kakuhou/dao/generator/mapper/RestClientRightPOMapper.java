package com.kakuhou.dao.generator.mapper;

import com.kakuhou.dao.generator.bean.RestClientRightPO;
import com.kakuhou.dao.generator.bean.RestClientRightPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestClientRightPOMapper {
    int countByExample(RestClientRightPOExample example);

    int deleteByExample(RestClientRightPOExample example);

    int deleteByPrimaryKey(String id);

    int insertBatch(@Param("records") List<RestClientRightPO> records);

    int insert(RestClientRightPO record);

    int insertSelective(RestClientRightPO record);

    Object functionByExample(RestClientRightPOExample example);

    List<RestClientRightPO> selectByExample(RestClientRightPOExample example);

    RestClientRightPO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RestClientRightPO record, @Param("example") RestClientRightPOExample example);

    int updateByExample(@Param("record") RestClientRightPO record, @Param("example") RestClientRightPOExample example);

    int updateByPrimaryKeySelective(RestClientRightPO record);

    int updateByPrimaryKey(RestClientRightPO record);
}