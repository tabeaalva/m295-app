package ch.ilv.m295.demoapp.department.Category;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ch.ilv.m295.demoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
@SecurityRequirement(name = "bearerAuth")

@RestController
@Validated
public class CategoryController {

    private final CategoryService CategoryService;

    CategoryController(CategoryService CategoryService) {
        this.CategoryService = CategoryService;
    }

    @GetMapping("api/category")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Category>> all() {
        List<Category> result = CategoryService.getCategorys();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/category/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Category> one(@PathVariable Long id) {
        Category Category = CategoryService.getCategory(id);
        return new ResponseEntity<>(Category, HttpStatus.OK);
    }

    @PostMapping("api/category")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Category> newCategory(@Valid @RequestBody Category Category) {
        Category savedCategory = CategoryService.insertCategory(Category);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @PutMapping("api/category/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category Category, @PathVariable Long id) {
        Category savedCategory = CategoryService.updateCategory(Category, id);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("api/category/{id}")
    @RolesAllowed(Roles.Admin)
    public void deleteCategory(@PathVariable Long id) {
        CategoryService.deleteCategory(id);
    }
}
