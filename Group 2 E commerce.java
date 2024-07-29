import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Product class representing a product in the e-commerce platform
class Product {
    private String id;
    private String name;
    private String category;
    private double price;

    public Product(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

// Cart class representing a shopping cart
class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        return "Cart{" + "products=" + products + '}';
    }
}

// ECommercePlatform class managing the products and user interactions
class ECommercePlatform {
    private Map<String, Product> productCatalog;
    private Cart cart;

    public ECommercePlatform() {
        productCatalog = new HashMap<>();
        cart = new Cart();
        initializeProducts();
    }

    private void initializeProducts() {
        productCatalog.put("1", new Product("1", "Running Shoes", "Shoes", 59.99));
        productCatalog.put("2", new Product("2", "Jeans", "Trousers", 39.99));
        productCatalog.put("3", new Product("3", "Summer Dress", "Dresses", 49.99));
        productCatalog.put("4", new Product("4", "Baseball Cap", "Caps and Hats", 14.99));
        productCatalog.put("5", new Product("5", "Leather Bag", "Bags", 79.99));
    }

    public void searchProduct(String keyword) {
        productCatalog.values().stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(System.out::println);
    }

    public void addToCart(String productId) {
        Product product = productCatalog.get(productId);
        if (product != null) {
            cart.addProduct(product);
            System.out.println(product.getName() + " added to cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void buyProducts() {
        double total = cart.getTotal();
        if (total > 0) {
            System.out.println("Purchased products for a total of $" + total);
            cart.clear();
        } else {
            System.out.println("Your cart is empty.");
        }
    }

    public void viewCart() {
        System.out.println(cart);
    }

    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter a command (search, add, buy, view, exit):");
            command = scanner.nextLine();

            switch (command) {
                case "search":
                    System.out.println("Enter a keyword to search:");
                    String keyword = scanner.nextLine();
                    platform.searchProduct(keyword);
                    break;
                case "add":
                    System.out.println("Enter the product ID to add to cart:");
                    String productId = scanner.nextLine();
                    platform.addToCart(productId);
                    break;
                case "buy":
                    platform.buyProducts();
                    break;
                case "view":
                    platform.viewCart();
                    break;
                case "exit":
                    System.out.println("Exiting the platform.");
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
