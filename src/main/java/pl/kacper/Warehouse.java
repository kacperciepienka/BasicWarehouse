package pl.kacper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class Warehouse {
    private final int startId = 1001;
    private int currentId = 1000;
    Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        product.setId(++currentId);
        products.put(currentId, product);
        log.info("\nDodano {} do magazynu", product.getName());
    }

    public void removeProduct(int id) throws ProductNotFoundException {

        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("\nProdukt o podanym ID nie istnieje!\nSpróbuj ponownie!\n");
        }

        products.remove(id);
        log.info("\nUsunięto produkt z bazy!\n");
    }

    public void updateQuantity(int id, int newQuantity) throws IncorrectValueException, ProductSoldOutException, ProductNotFoundException {

        if (id < startId || id > currentId) {
            throw new ProductNotFoundException("\nProdukt o podanym ID nie istnieje!\nSpróbuj ponownie!\n");
        }

        if (newQuantity < 0) {
            throw new IncorrectValueException("\nNowa wartość musi być >= 0\nSpróbuj ponownie!\n");
        } else if (newQuantity == 0) {
            throw new ProductSoldOutException("\nProdukt został wyprzedany!\nWykonaj dostawę\n");
        }

        Product newProduct = products.get(id);
        newProduct.setQuanity(newQuantity);

        products.put(id, newProduct);
    }

    public void showAllProducts() {
        products.forEach((key, value) -> System.out.println("ID" + key + " --> " + value));
    }
}
