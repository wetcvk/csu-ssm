package org.csu.mypetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.entity.LineItem;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemMapper extends BaseMapper<LineItem> {
}
