package org.csu.mypetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.entity.ItemQuantity;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemQuantityMapper extends BaseMapper<ItemQuantity> {
}
