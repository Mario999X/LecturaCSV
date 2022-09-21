import model.Product;
import utils.UtilCSV;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class ConsultasProductos {

    List<Product> productList;

    public ConsultasProductos() {
        UtilCSV csv = UtilCSV.getInstance();
        try {
            productList = csv.getProducts();
            initStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initStreams() {
        allProducts();
    }

    private void allProducts(){
        System.out.println("Todos los productos");
        Stream<Product> p = productList.stream();
        p.forEach(System.out::println);
    }
}
