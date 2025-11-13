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
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final String bunName;
    private final IngredientType ingredientType;
    private final String ingredientName;

    public BurgerReceiptTest(String bunName, IngredientType ingredientType, String ingredientName) {
        this.bunName = bunName;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
    }

    @Parameterized.Parameters(name = "Тест {index}: чек для бургера с булочкой ''{0}'' и ингредиентом {1} ''{2}''")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"черная булочка", IngredientType.SAUCE, "острый соус"},
                {"белая булочка", IngredientType.FILLING, "котлета"},
                {"красная булочка", IngredientType.SAUCE, "чили соус"},
                {"особая булочка", IngredientType.FILLING, "сыр"}
        });
    }

    @Test
    public void testGetReceiptWithIngredients() {
        Burger burger = new Burger();

        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertNotNull("Чек не должен быть null", receipt);
        assertTrue("Чек должен содержать название булочки", receipt.contains(bunName));
        assertTrue("Чек должен содержать название ингредиента", receipt.contains(ingredientName.toLowerCase()));
        assertTrue("Чек должен содержать цену", receipt.contains("Price:"));
    }
}
