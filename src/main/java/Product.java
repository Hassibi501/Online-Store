public class Product {
    private final String SKU;
    private final String productName;
    private final double price;
    private final String department;

    public Product(String SKU, String productName, double price, String department) {
        this.SKU = SKU;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    public String getSKU() {
        return SKU;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }

}
