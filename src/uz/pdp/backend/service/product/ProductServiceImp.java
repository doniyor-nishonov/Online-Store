package uz.pdp.backend.service.product;

import static uz.pdp.frontend.tools.COLOR.*;

import uz.pdp.backend.model.product.Product;
import uz.pdp.backend.role.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 16:03
 **/
public class ProductServiceImp implements ProductService {
    private ArrayList<Product> products;

    public ProductServiceImp() {
        this.products = new ArrayList<>();
        products.add(new Product("Olma", 20000, ProductType.FRUIT));
        products.add(new Product("Banan", 24000, ProductType.FRUIT));
        products.add(new Product("Mandarin", 10000, ProductType.FRUIT));
        products.add(new Product("Nok", 30000, ProductType.FRUIT));
        products.add(new Product("Coca Cola", 12000, ProductType.DRINK));
        products.add(new Product("Pepsi", 12000, ProductType.DRINK));
        products.add(new Product("Fanta", 15000, ProductType.DRINK));
        products.add(new Product("Sinkers", 8000, ProductType.SWEET));
        products.add(new Product("Alpen Gold MAX FUN", 28000, ProductType.SWEET));
        products.add(new Product("Kit Kat", 10000, ProductType.SWEET));
        products.add(new Product("iPhone 15 pro", 15000000, ProductType.TECHNIQUE));
        products.add(new Product("iPhone 14 pro", 13000000, ProductType.TECHNIQUE));
        products.add(new Product("iPhone 12 pro", 10000000, ProductType.TECHNIQUE));
        products.add(new Product("Artel Tv 32", 1500000, ProductType.TECHNIQUE));
        products.add(new Product("Samsung TV 43", 15000000, ProductType.TECHNIQUE));
        products.add(new Product("Samsung S 23 Ultra", 15000000, ProductType.TECHNIQUE));
        products.add(new Product("Hp laptop", 12000000, ProductType.TECHNIQUE));
        products.add(new Product("Patir", 10000, ProductType.BREAD));
        products.add(new Product("Non", 5000, ProductType.BREAD));
        products.add(new Product("Buxanka", 3000, ProductType.BREAD));
        products.add(new Product("HeadAndShoulders", 45000, ProductType.PERFUMERY));
        products.add(new Product("Sovun", 15000, ProductType.PERFUMERY));
        products.add(new Product("Idish yuvish uchun gel", 12000, ProductType.PERFUMERY));
        products.add(new Product("Kartoshka", 5000, ProductType.VEGETABLE));
        products.add(new Product("Sabzi", 5000, ProductType.VEGETABLE));
        products.add(new Product("Piyoz", 4000, ProductType.VEGETABLE));
        products.add(new Product("Karam", 7000, ProductType.VEGETABLE));
    }

    public int getSize() {
        return products.size();
    }

    public Product get(String id) {
        return products.stream()
                .filter((product -> Objects.equals(product.getId(),id)))
                .findFirst().orElse(null);
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean check(String name) {
        String lowerCaseName = name.toLowerCase();
        return products.stream()
                .anyMatch(product -> product.getName().toLowerCase().equals(lowerCaseName));
    }

    @Override
    public boolean add(Product product) {
        if (!check(product.getName())) {
            setProducts(product);
        }
        return true;
    }

    @Override
    public String update(String name, Integer price, ProductType type, int chose, String id) {
        switch (chose) {
            case 1 -> get(id).setName(name);
            case 2 -> get(id).setPrice(price);
            case 4 -> get(id).setType(type);
        }
        return GREEN + "Update successfully✔" + STOP;
    }

    @Override
    public List<Product> show(ProductType type) {
        return products.stream()
                .filter((product -> Objects.equals(product.getType(),type)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean remove(String id) {
        return products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public Product findById(String id) {
        return null;
    }

    @Override
    public List<Product> search(String name) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public String discount(String id, double sale) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                double newPrice = product.getPrice() * (1 - sale / 100);
                product.setDiscount(newPrice);
                return GREEN + "Discount successfully✔" + STOP;
            }
        }
        return RED + "Discount failed" + STOP;
    }

    @Override
    public String deleteDiscount(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                products.get(i).setDiscount(0);
                return GREEN + "Delete discount successfully✔" + STOP;
            }
        }
        return RED + "Delete discount failed" + STOP;
    }

}
