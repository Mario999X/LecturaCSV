package utils;

import model.Product;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilCSV {

    private final static String CSV_FILE = "data/products.csv";
    private final static String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static UtilCSV instance;

    private UtilCSV() {
    }

    public static UtilCSV getInstance(){
        if (instance == null){
            instance = new UtilCSV();
        }
        return instance;
    }

    public List<Product> getProducts() throws IOException {
        Path path = Paths.get(WORKING_DIRECTORY + File.separator + CSV_FILE);
        Stream<String> lineas = Files.lines(path, StandardCharsets.UTF_8);
        return lineas.skip(1).parallel().map(this::parse).collect(Collectors.toList());
    }

    private Product parse(String linea){
        String[] datos = linea.split(",");
        Product product = new Product();
        product.setProductId(Integer.parseInt(datos[0]));
        product.setProductName(datos[1]);
        product.setSupplierId(Integer.parseInt(datos[2]));
        product.setCategoryId(Integer.parseInt(datos[3]));
        product.setQuantityPerUnit(datos[4]);
        product.setUnitPrice(Double.parseDouble(datos[5]));
        product.setUnitsInStock(Integer.parseInt(datos[6]));
        product.setUnitsOnOrder(Integer.parseInt(datos[7]));
        product.setReorderLevel(Integer.parseInt(datos[8]));
        product.setDiscontinued(datos[9]);
        if (product.getDiscontinued().equals("0")){
            product.setDiscontinued("No");
        } else if (product.getDiscontinued().equals("1")) {
            product.setDiscontinued("Sí");
        } else {
            product.setDiscontinued("?");
        }
        return product;
    }

}
