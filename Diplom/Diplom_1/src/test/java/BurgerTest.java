import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "Тест {index}: операции с ингредиентом {0} ''{1}''")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "острый соус", 100f},
                {IngredientType.FILLING, "котлета", 100f},
                {IngredientType.SAUCE, "сметана", 200f},
                {IngredientType.FILLING, "сыр", 150f}
        });
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        burger.addIngredient(ingredient);

        assertEquals("Должен быть один ингредиент", 1, burger.ingredients.size());
        assertEquals("Ингредиент должен совпадать", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "чили соус", 300f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        assertEquals("Должен остаться один ингредиент после удаления", 1, burger.ingredients.size());
        assertEquals("Оставшийся ингредиент должен совпадать", ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "сосиска", 300f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);

        assertEquals("Первый ингредиент должен быть перемещен", ingredient2, burger.ingredients.get(0));
        assertEquals("Второй ингредиент должен быть перемещен", ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun bun = new Bun("красная булочка", 300f);

        burger.setBuns(bun);

        assertNotNull("Булочка должна быть выбрана", burger.bun);
        assertEquals("Булочка должна совпадать", bun, burger.bun);
    }
}