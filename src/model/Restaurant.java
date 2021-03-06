package model;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private List<Ingredient> ingredients;
    private List<BaseProduct> products;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<User> users;
    private List<Order> orders;
    private List<ProductType> productTypes;
    private User loggedUser;
    public final static String DATA_FILE_PATH = "data/";

    public Restaurant() {
        ingredients = new ArrayList<>();
        products = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        users = new ArrayList<>();
        orders = new ArrayList<>();
        productTypes = new ArrayList<>();
        loggedUser = null;
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

    public void exportOrders(String fn, String sp) throws IOException { /////// unfinished
        PrintWriter pw = new PrintWriter(new FileWriter(fn,true));
        pw.write("Customer" + sp + "Address" + sp + "Phone" + sp + "Deliverer" + sp + "Status" + sp + "Date" + sp + "Code" + sp);
        for (Order o : orders) {
            return;
        }
        pw.close();
    }


    public void addCustomer(String n, String ln, long id, User cb, User lb, String ad, String pn, String cm){
        Customer c = new Customer(n,ln,id,cb,lb,ad,pn,cm);
        if (customers.isEmpty()){
            customers.add(c);
        } else {
            int i = 0;
            for (; i < customers.size(); i++){
                if (c.compareTo(customers.get(i)) > 0){ //compareTo() compares by names.
                    break;
                } else if (c.compareTo(customers.get(i)) == 0){
                    if (c.compare(c,customers.get(i)) > 0){ // compare() compares by lastNames.
                        break;
                    } else if (c.compare(c,customers.get(i)) == 0){
                        if(c.compare(c,customers.get(i+1)) > 0){
                            break;
                        }
                    }
                }
            }
            customers.add(i, c);
        }
    }

    public Customer searchCustomer(long id){
        int pos = -1;
        int s = 0;
        int e = customers.size();
        while (s < e && pos < 0){
            int mp = (s + e) / 2;
            if (customers.get(mp).getIdNumber() == id){
                pos = mp;
            } else if (customers.get(mp).getIdNumber() < id){
                s = mp+1;
            } else if (customers.get(mp).getIdNumber() > id){
                e = mp-1;
            }
        }
        return customers.get(pos);
    }

    public void logIn(String us, String pass) throws InvalidLoginException {
        for (User u : users){
            if ((us.compareTo(u.username)==0) && (pass.compareTo(u.password)==0)){
                loggedUser = u;
                return;
            }
        } throw new InvalidLoginException("The username or password is incorrect");
    }

    public void logOut(){
        loggedUser = null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}