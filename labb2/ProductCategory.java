package se.iths.labborationer.labb2;

import java.util.Objects;

public final class ProductCategory {
    private final String category;

    public ProductCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        return Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return category != null ? category.hashCode() : 0;
    }

    public String category() {
        return category;
    }



}
