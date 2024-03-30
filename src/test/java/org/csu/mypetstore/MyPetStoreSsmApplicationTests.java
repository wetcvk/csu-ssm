package org.csu.mypetstore;

import org.csu.mypetstore.entity.Category;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyPetStoreSsmApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() {
        Category category = categoryMapper.selectById("CATS");
        System.out.println(category);
    }

}
