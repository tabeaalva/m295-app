package ch.ilv.m295.demoapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.fasterxml.jackson.core.sym.Name;

import ch.ilv.m295.demoapp.department.Category.Category;
import ch.ilv.m295.demoapp.department.Category.CategoryRepository;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertCategory() {
        Category obj1 = this.categoryRepository.save(new Category("category1"));
        Assertions.assertNotNull(obj1.getId());
        Category obj2 = this.categoryRepository.save(new Category("category2" ));
        Assertions.assertNotNull(obj2.getId());
    }
}
