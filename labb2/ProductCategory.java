package se.iths.labborationer.labb2;

public record ProductCategory(String category) {


    @Override
    public String toString() {
        return category;
    }


}
