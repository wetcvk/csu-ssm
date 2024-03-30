package org.csu.mypetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
