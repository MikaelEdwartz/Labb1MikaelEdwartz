package se.iths.labborationer.labb2MikaelEdwartz.FileHandling;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import se.iths.labborationer.labb2MikaelEdwartz.Inventory.InventoryBalance;
import se.iths.labborationer.labb2MikaelEdwartz.Products.Product;
import se.iths.labborationer.labb2MikaelEdwartz.Products.ProductCategory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class GsonReader {
    private final Gson gson;
    private final List<ProductCategory> categories;
    private final String folder;
    private final Path store;

    public GsonReader(List<ProductCategory> categories){
        this.gson = new Gson();
        this.categories = categories;
        this.folder = System.getProperty("user.home");
        this.store = getPath();
    }

    private Path getPath() {
        return Path.of(this.folder, "KortedalaAffär", "products.txt");
    }

    public InventoryBalance read(){
        return readFromPotentialFile();
    }

    private InventoryBalance readFromPotentialFile() {
        if(!(Files.exists(getPath())))
            return createNewFile();
        else
            return getInventoryBalanceFromFile();
    }

    private InventoryBalance createNewFile() {
        try {
            Files.createDirectory(Path.of(this.folder, "KortedalaAffär"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new InventoryBalance();
    }

    private InventoryBalance getInventoryBalanceFromFile() {
        try (Reader reader = new FileReader(this.store.toFile())) {
            return readFileAndReturnList(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InventoryBalance readFileAndReturnList(Reader reader) {
        Type getTypeList = new TypeToken<ArrayList<Product>>() {}.getType();
        List<Product> list = gson.fromJson(reader, getTypeList);
        var inventoryBalance = new InventoryBalance(list);
        addCategories(inventoryBalance);
        return inventoryBalance;
    }

    private void addCategories(InventoryBalance inventoryBalance) {
        for (int i = 0; i < inventoryBalance.size(); i++)
            addIfCategoryDoesntExists(inventoryBalance, i);
    }

    private void addIfCategoryDoesntExists(InventoryBalance b, int i) {
        if (!(this.categories.contains(b.getCategory(i))))
            this.categories.add(b.getCategory(i));
    }

    public void save(InventoryBalance balance){
        var data = gson.toJson(balance.getInventory());
        try {
            Files.writeString(this.store, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
