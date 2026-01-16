import org.junit.jupiter.api.Test;
import pl.kacper.*;

import static org.junit.jupiter.api.Assertions.*;

public class WareHouseTest {

    Warehouse warehouse = new Warehouse();
    Product mleko = Product.builder()
            .id(1001)
            .name("mleko")
            .quanity(20)
            .price(5)
            .build();
    @Test
    void shouldAddProductToWarehouse(){
        int sizeBefore = warehouse.getProducts().size(); // 0
        warehouse.addProduct(mleko);
        int sizeAfter = warehouse.getProducts().size();  // 1

        assertEquals(sizeBefore + 1, sizeAfter);
        assertTrue(warehouse.getProducts().containsKey(1001));
    }

    @Test
    void shouldRemoveProduct() throws ProductNotFoundException {
        warehouse.addProduct(mleko);
        int sizeBefore = warehouse.getProducts().size(); // 1
        warehouse.removeProduct(1001);
        int sizeAfter = warehouse.getProducts().size(); //0

        assertEquals(sizeBefore - 1, sizeAfter);
        assertFalse(warehouse.getProducts().containsKey(1001));
    }

    @Test
    void shouldUpdateQuantity() throws ProductNotFoundException {
        warehouse.addProduct(mleko); // quantity 20
        int quantityBefore = warehouse.getProducts().get(1001).getQuanity();
        warehouse.updateQuantity(1001, 100); //quantity 100
        int quantityAfter = warehouse.getProducts().get(1001).getQuanity();

        assertEquals(quantityBefore + 80, quantityAfter);
    }

    @Test
    void shouldThrowProductSoldOutException(){
        warehouse.addProduct(mleko); // quantity 20

        assertThrows(ProductSoldOutException.class, () -> warehouse.updateQuantity(1001, 0));
    }

    @Test
    void shouldThrowIncorrectValueException() {
        warehouse.addProduct(mleko); // quantity 20

        assertThrows(IncorrectValueException.class, () -> warehouse.updateQuantity(1001, -3));
    }
}

