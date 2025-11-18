import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "Тест {index}: ингредиент {0} ''{1}''")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "острый соус", 100f},
                {IngredientType.FILLING, "котлета", 100f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        burger.addIngredient(ingredient);

        assertEquals("Размер должен увеличиться на 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        burger.addIngredient(ingredient);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);

        assertEquals("Размер должен уменьшиться на 1", initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        Ingredient ingredient1 = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "сосиска", 300f);
        Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "чили", 200f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Запоминаем исходный порядок
        Ingredient originalFirst = burger.ingredients.get(0);
        Ingredient originalSecond = burger.ingredients.get(1);
        Ingredient originalThird = burger.ingredients.get(2);

        burger.moveIngredient(0, 2);

        // Проверяем новый порядок
        assertEquals("Элемент на позиции 0 должен измениться", originalSecond, burger.ingredients.get(0));
        assertEquals("Элемент на позиции 1 должен измениться", originalThird, burger.ingredients.get(1));
        assertEquals("Элемент на позиции 2 должен измениться", originalFirst, burger.ingredients.get(2));
    }
}