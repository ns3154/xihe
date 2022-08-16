package com.tcmr.xihe.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcmr.xihe.dao.domain.TestDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestMapper extends BaseMapper<TestDO> {
    int updateBatch(List<TestDO> list);

    int updateBatchSelective(List<TestDO> list);

    int batchInsert(@Param("list") List<TestDO> list);

    int insertOrUpdate(TestDO record);

    int insertOrUpdateSelective(TestDO record);
}
