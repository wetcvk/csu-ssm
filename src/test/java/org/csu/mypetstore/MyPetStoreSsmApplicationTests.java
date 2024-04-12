package org.csu.mypetstore;

import org.csu.mypetstore.entity.Category;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyPetStoreSsmApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CatalogService catalogService;

    @Test
    void contextLoads() {
        CategoryVO categoryVO = catalogService.getCategory("FISH");
        System.out.println(categoryVO);
    }

}
