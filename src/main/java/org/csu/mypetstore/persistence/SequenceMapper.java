package org.csu.mypetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.entity.Sequence;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper extends BaseMapper<Sequence> {
}
