package ch.tabea.reiffer.calendar.Category;

import org.springframework.stereotype.Service;

import ch.tabea.reiffer.calendar.storage.EntityNotFoundException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getCategorys() {
        return repository.findByOrderByNameAsc();
    }

    public Category getCategory(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Category.class));
    }

    public Category insertCategory(Category Category) {
        return repository.save(Category);
    }

    public Category updateCategory(Category Category, Long id) {
        return repository.findById(id)
                .map(CategoryOrig -> {
                    CategoryOrig.setName(Category.getName());
                    return repository.save(CategoryOrig);
                })
                .orElseGet(() -> repository.save(Category));
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
