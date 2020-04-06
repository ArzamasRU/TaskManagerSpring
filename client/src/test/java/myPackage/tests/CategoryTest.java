package myPackage.tests;

import myPackage.category.UserTestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertTrue;

@Category(UserTestCategory.class)
public class CategoryTest {

    @Test
    @Category(UserTestCategory.class)
    public void categoryTest() {
        assertTrue(false);
    }
}
