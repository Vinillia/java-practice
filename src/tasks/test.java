package tasks;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import tasks.contacts.super_market;

class ProductListTest extends contacts
{
    private super_market market;

    @BeforeEach
    void setUp()
    {
        market = new super_market("Пятерочка");
    }

    @Test
    void testAddProduct()
    {
        super_market.item item = new super_market.Toy("Lego Set", "Lego", "Denmark", 3000, 4000, "3+", "Building Blocks");
        market.addProduct(item);

        List<super_market.item> products = market.get_products();
        assertEquals(1, products.size());
        assertEquals("Lego Set", products.getFirst().get_name()); // Проверка имени
    }
}
