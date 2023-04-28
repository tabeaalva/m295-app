package ch.tabea.reiffer.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.tabea.reiffer.calendar.department.Category.Category;
import ch.tabea.reiffer.calendar.department.Category.CategoryRepository;
import ch.tabea.reiffer.calendar.department.Category.CategoryService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class categoryServiceTests {

    private CategoryService categoryService;
    private final CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);

    private final Category categoryMock = mock(Category.class);

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepositoryMock);
    }

    @Test
    void createcategory() {
        when(categoryRepositoryMock.save(categoryMock)).thenReturn(categoryMock);
        categoryService.insertCategory(categoryMock);
        verify(categoryRepositoryMock, times(1)).save(any());
    }

    @Test
    void findcategory() {
        when(categoryRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(categoryMock));
        Category v = categoryService.getCategory(any());
        verify(categoryRepositoryMock, times(1)).findById(any());
    }

    @Test
    void deletecategory() {
        categoryService.deleteCategory(any());
        verify(categoryRepositoryMock, times(1)).deleteById(any());
    }
}

