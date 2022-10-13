package se.iths.labborationer.labb2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class InventoryBalance {
    private final List<Product> inventory;

    public InventoryBalance() {
        this.inventory = new ArrayList<>();
    }

    public InventoryBalance(List<Product> list) {
        this.inventory = new ArrayList<>(list);
    }

    public void printBalance() {
        this.inventory.stream()
                .distinct()
                .sorted(sortByNaturalOrder())
                .forEach(this::printProductSaldo);

    }

    public void add(Product product) {
        this.inventory.add(product);
    }

    public void remove(Product product) {
        this.inventory.remove(product);
    }

    public void remove(String name) {
        this.inventory.removeIf(hasSameName(name));
    }

    public List<Product> listToRemove(String name) {
        return this.inventory.stream()
                .filter(hasSameName(name))
                .toList();
    }

    public Product getProduct(int i) {
        return this.inventory.get(i);
    }

    private static Predicate<Product> hasSameName(String name) {
        return product -> product.product().equals(name);
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public ProductCategory getCategory(int i) {
        return this.inventory.get(i).category();
    }

    public void printProductWithCategory(ProductCategory category) {
        this.inventory.stream()
                .filter(hasSameCategory(category))
                .distinct()
                .sorted(sortByNaturalOrder())
                .forEach(this::printProductSaldo);
    }

    private Predicate<Product> hasSameCategory(ProductCategory category) {
        return p -> p.category().equals(category);
    }

    public static Function<Product, String> compareCategoryOrder() {
        return product -> product.category().category();
    }

    public List<Product> getListWithChosenCategory(ProductCategory category) {
        return this.inventory.stream()
                .filter(hasSameCategory(category))
                .distinct()
                .sorted(sortByNaturalOrder())
                .toList();
    }

    public long nrOfProducts(Product number) {
        return this.inventory.stream()
                .filter(hasSameEanCode(number))
                .count();
    }

    private static Predicate<Product> hasSameEanCode(Product number) {
        return product -> product.matchingEanCode(number.productNumber());
    }

    public void printBetweenPrices(BigDecimal lowestInputPrice, BigDecimal highestInputPrice) {
        this.inventory.stream()
                .filter(isLowerThan(highestInputPrice))
                .filter(isHigherThan(lowestInputPrice))
                .distinct()
                .sorted(sortByNaturalOrder())
                .forEach(this::printProductSaldo);
    }

    private static Predicate<Product> isHigherThan(BigDecimal lowestInputPrice) {
        return p -> p.price().compareTo(lowestInputPrice) >= 0;
    }

    private static Predicate<Product> isLowerThan(BigDecimal highestInputPrice) {
        return p -> p.price().compareTo(highestInputPrice) <= 0;
    }

    private void printProductSaldo(Product p) {
        System.out.println(p + " \t\t" + nrOfProducts(p) + "st");
    }

    public List<Product> getDistinctProducts(List<Product> listIn) {
        return listIn
                .stream()
                .sorted(sortByNaturalOrder())
                .distinct()
                .toList();
    }

    private static Comparator<Product> sortByNaturalOrder() {
        return Comparator.comparing(compareCategoryOrder())
                .thenComparing(Product::product);
    }

    public int size() {
        return inventory.size();
    }
}
