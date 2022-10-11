package se.iths.labborationer.labb2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;

public class JsonReader {
    private final Gson gson;
    private InventoryBalance balance;
    private final List<ProductCategory> categories;
    private String data;

    public JsonReader(InventoryBalance balance, List<ProductCategory> categories){
        this.gson = new Gson();
        this.balance = balance;
        this.categories = categories;
        //this.data = gson.toJson(this.balance);

    }

    public void add(){
        this.balance.add(new Product(new ProductCategory("ä"), "das", valueOf(23), 123));
    }
    public InventoryBalance read(){
        try{
        return new InventoryBalance(gson.fromJson(data, new TypeToken<InventoryBalance>() { }.getType()));

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }



        return new InventoryBalance();

    }

    public void save(InventoryBalance balance){


        data = gson.toJson(balance.getInventory());
        String folder = System.getProperty("user.home");
        Path store = Path.of(folder, "KortedalaAffär", "products.txt");
        Path filePath = store;
        boolean exist = Files.exists(filePath);


        try {
            if(!(exist))
                Files.createDirectory(Path.of(folder, "KortedalaAffär"));

            Files.writeString(store, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
