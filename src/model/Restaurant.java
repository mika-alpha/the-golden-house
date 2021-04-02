package model;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Restaurant {

    private List<Ingredient> ingredients;
    private List<Product> products;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<User> users;
    private List<Order> orders;
    private List<ProductType> productTypes;
    public final static String DATA_FILE_PATH = "data/";

    public Restaurant() {
        ingredients = new ArrayList<>();
        products = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        users = new ArrayList<>();
        orders = new ArrayList<>();
        productTypes = new ArrayList<>();
    }

    public void saveData(String data) throws IOException, NoSuchFieldException, IllegalAccessException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH + data + ".bdd"));
        Field fd = this.getClass().getDeclaredField(data); //use of reflection to get the attribute as Field by String, "this" refers to this class (model.Restaurant)
        fd.setAccessible(true);
        oos.writeObject(fd.get(this));
        oos.close();
    }


    public void loadData() throws ClassNotFoundException, IllegalAccessException, IOException {
        Field[] fds = this.getClass().getDeclaredFields(); //use of reflection to get all attributes as Fields, "this" refers to this class (model.Restaurant)
        for(Field fd : fds){
            File f = new File(DATA_FILE_PATH + fd.getName() + ".bdd");
            if(f.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                fd.setAccessible(true);
                fd.set(this, ois.readObject());
                ois.close();
            }
        }
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}