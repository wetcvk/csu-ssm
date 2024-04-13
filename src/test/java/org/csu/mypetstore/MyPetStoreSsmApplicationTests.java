package org.csu.mypetstore;

import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyPetStoreSsmApplicationTests {

    @Autowired
    private CatalogService catalogService;

    @Test
    void contextLoads() {
        CategoryVO categoryVO = catalogService.getCategory("FISH");
        System.out.println(categoryVO);
    }

}
