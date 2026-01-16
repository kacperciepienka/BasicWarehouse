package pl.kacper;

import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args){
        Warehouse warehouse = new Warehouse();

        System.out.println("===== System Magazynowy =====");

        try(Scanner scanner = new Scanner(System.in)) {
            while (true){
                System.out.println("          MENU          ");
                System.out.println("1 - Wyświetl magazyn");
                System.out.println("2 - Dodaj produkt");
                System.out.println("3 - Usuń produkt");
                System.out.println("4 - Zmień ilość produktu");
                System.out.println("0 - Wyjście");

                try {
                    System.out.print("\nWybierz operację: ");
                    int wybor = scanner.nextInt();
                    scanner.nextLine();

                    if (wybor < 0 || wybor > 4) {
                        throw new IncorrectValueException("\nWprowadzono błędna wartość.\nSpróbuj ponownie!\n");
                    }

                    if (wybor == 0){
                        System.out.println("Wyjście...");
                        return;
                    }

                    switch (wybor){
                        case 1:
                            warehouse.showAllProducts();
                            break;
                        case 2:
                            System.out.print("Podaj nazwę produktu: ");
                            String name = scanner.nextLine();

                            System.out.print("Podaj ilość produktu: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Podaj cenę produktu: ");
                            double price = scanner.nextDouble();
                            scanner.nextLine();

                            Product product = Product.builder()
                                    .name(name)
                                    .quanity(quantity)
                                    .price(price)
                                    .build();

                            warehouse.addProduct(product);
                            break;
                        case 3:
                            System.out.print("Podaj ID produktu który chcesz usunąć: ");
                            int productRemoveId = scanner.nextInt();
                            scanner.nextLine();

                            warehouse.removeProduct(productRemoveId);
                            break;
                        case 4:
                            System.out.print("W jakim produkcie chcesz dokonać zmian (id): ");
                            int productId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Jaka jets nowa ilość produktu: ");
                            int newProductQuantity = scanner.nextInt();
                            scanner.nextLine();

                            warehouse.updateQuantity(productId, newProductQuantity);
                            break;
                    }
                }catch (IncorrectValueException | ProductNotFoundException  | ProductSoldOutException e){
                    log.error(e.getMessage());
                }
            }
        }catch (InputMismatchException e){
            log.error("\nBłędna wartość!");
        }
    }
}