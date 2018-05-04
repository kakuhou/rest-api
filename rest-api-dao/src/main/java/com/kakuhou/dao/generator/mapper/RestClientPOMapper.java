package com.kakuhou.dao.generator.mapper;

import com.kakuhou.dao.generator.bean.RestClientPO;
import com.kakuhou.dao.generator.bean.RestClientPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestClientPOMapper {
    int countByExample(RestClientPOExample example);

    int deleteByExample(RestClientPOExample example);

    int deleteByPrimaryKey(String id);

    int insertBatch(@Param("records") List<RestClientPO> records);

    int insert(RestClientPO record);

    int insertSelective(RestClientPO record);

    Object functionByExample(RestClientPOExample example);

    List<RestClientPO> selectByExample(RestClientPOExample example);

    RestClientPO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RestClientPO record, @Param("example") RestClientPOExample example);

    int updateByExample(@Param("record") RestClientPO record, @Param("example") RestClientPOExample example);

    int updateByPrimaryKeySelective(RestClientPO record);

    int updateByPrimaryKey(RestClientPO record);
}