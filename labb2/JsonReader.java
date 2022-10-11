package se.iths.labborationer.labb2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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


    public InventoryBalance read(InventoryBalance balance){
//        String folder = System.getProperty("user.home");
//        Path store = Path.of(folder, "KortedalaAffär", "products.txt");
//        Path filePath = store;
//        boolean exist = Files.exists(filePath);
//       // if (jsonObj != null && jsonObj.length > 0)
//        if(exist) {
//            try {
//                var list = this.balance.copy(new InventoryBalance(gson.fromJson(data, new TypeToken<ArrayList<Product>>() {
//                }.getType())));
//                for (int i = 0; i < list.size(); i++) {
//                    this.balance.add(list.get(i));
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
        try {
            FileReader fileReader1 = new FileReader("products.json");
            var getTypeList = new TypeToken<InventoryBalance>() {
            }.getType();
            System.out.println("Import of fruit products successful");
            return gson.fromJson(fileReader1, getTypeList);

        } catch (FileNotFoundException e) {
            System.out.println("File of products not found continues without import.");
            return balance;
        }


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
