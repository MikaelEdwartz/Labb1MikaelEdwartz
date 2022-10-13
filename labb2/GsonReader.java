package se.iths.labborationer.labb2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;

public class GsonReader {
    private final Gson gson;
    private InventoryBalance balance;
    private final List<ProductCategory> categories;
    private String data;


    public GsonReader(List<ProductCategory> categories){
        this.gson = new Gson();
        this.balance = balance;
        this.categories = categories;
        //this.data = gson.toJson(this.balance);

    }


    public InventoryBalance read(){
        String folder = System.getProperty("user.home");
        Path store = Path.of(folder, "KortedalaAffär", "products.txt");
        Path filePath = store;
        boolean exist = Files.exists(filePath);

        if(!(exist)) {
            try {
                Files.createDirectory(Path.of(folder, "KortedalaAffär"));

                return new InventoryBalance();
            } catch (IOException e) {
                System.out.println(e.getMessage());;
            }
        }



        try (Reader reader = new FileReader(store.toFile())) {

            Type getTypeList = new TypeToken<ArrayList<Product>>() {}.getType();
            List<Product> list = gson.fromJson(reader, getTypeList);
            var b = new InventoryBalance(list);
            addCategories(b);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }

    return new InventoryBalance();
    }

    private void addCategories(InventoryBalance b) {
        for (int i = 0; i < b.size(); i++) {
            if (!(this.categories.contains(b.getCategory(i))))
                this.categories.add(b.getCategory(i));
        }
    }

    public void save(InventoryBalance balance){

        data = gson.toJson(balance.getInventory());
        String folder = System.getProperty("user.home");
        Path store = Path.of(folder, "KortedalaAffär", "products.txt");
        Path filePath = store;
        boolean exist = Files.exists(filePath);
        try {
//            if(!(exist))
//                Files.createDirectory(Path.of(folder, "KortedalaAffär"));

            Files.writeString(store, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
