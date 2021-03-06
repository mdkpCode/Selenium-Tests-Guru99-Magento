import PageObjects.ProductsDetailPage;
import PageObjects.ProductsListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsListTest extends BaseTest {

    // Test Data

    // Add to compare xPath's for testing products
    String xPathForFirstProduct = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/ul/li[2]/a";
    String xPathForSecondProduct = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a";



    @Test //TC1
    public void itemsCanBeSortedByNameInProductsListPageTest() {
        ProductsListPage productsListPage = new ProductsListPage(driver);
        boolean areSortedProperly = productsListPage.header.goToMobileCategory().checkIfProductsAreSortedProperly("asc", "Name");
        Assert.assertTrue(areSortedProperly, "Products are not sorted properly.");
    }

    @Test //TC2
    public void itemsCanBeSortedByPriceInProductsListPageTest() {
        ProductsListPage productsListPage = new ProductsListPage(driver);
        boolean areSortedProperly = productsListPage.header.goToMobileCategory().checkIfProductsAreSortedProperly("asc", "Price");
        Assert.assertTrue(areSortedProperly, "Products are not sorted properly.");
    }

    @Test //TC3
    public void itemsCanBeAddToComparisonListInProductListPageTest() {
        int expectedNumbersOfProductsInComparisonList = 2;

        ProductsListPage productsListPage = new ProductsListPage(driver);
        int actualNumbersOfProductInComparisonList = productsListPage.header.goToMobileCategory().addProductsForComparison(xPathForFirstProduct, xPathForSecondProduct).checkNumbersOfProductsInComparisonList();
        Assert.assertEquals(expectedNumbersOfProductsInComparisonList, actualNumbersOfProductInComparisonList, "Expected numbers of items in comparison list are invalid.");
    }

    @Test //TC3
    public void checkIfPricesAreEqualInProductListPageAndProductPage() {
        ProductsListPage productsListPage = new ProductsListPage(driver);
        ProductsDetailPage productsDetailPage = new ProductsDetailPage(driver);

        String productsListPagePrice = productsListPage.header.goToMobileCategory().getProductPrice(1);
        productsListPage.goToProductDetailPage("Sony Xperia");

        String productDetailPagePrice = productsDetailPage.getProductPrice();

        Assert.assertEquals(productDetailPagePrice, productsListPagePrice, "Expected prices are invalid.");
    }
}



