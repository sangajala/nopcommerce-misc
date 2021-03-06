package nopcommerce.misc;

import nopcommerce.misc.utils.PopulartagsPage;
import nopcommerce.misc.utils.WaitUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class MiscRegressionTestSuite extends baseTestSuite {
/////-------------DATA-----------------//

    private String username = "jamesmiller";
    private String password = "jm1234";

    private String[] newcustomerInfo = {"Male", "James", "Miller", "30", "June", "1991", "jamesmiller@test.com", "jamesmiller", "testcom", " ", "Yes", "(UTC) Dublin, Edinburgh, Lisbon, London", "jm1234", "jm1234"};
    private String[] updateCustomerInfo = {"Female", "Sam", "Mill", "3", "July", "1990", "samesmil@test.com", "samesmil", "testercom", " ", "No   ", "(UTC-06:00) Central Time (US & Canada)"};
    private String[] customerInforevert = {"Male", "James", "Miller", "30", "June", "1991", "jamesmiller@test.com", "jamesmiller", "testcom", " ", "Yes", "(UTC) Dublin, Edinburgh, Lisbon, London"};

    private String firstname = "Benq";
    private String firstname1 = "James";
    private String Lang_Deutsch = "Deutsch";
    private String Lang_English = "English";
    private String Currency_Dollar = "Dollar";
    private String Currency_Euro = "Euro";

    ////-----------end of data----------//

    //Page Objects
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    CustomerInfoPage customerInfoPage = new CustomerInfoPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    ManufacturersPage manufacturespage = new ManufacturersPage();
    PopulartagsPage populartagsPage = new PopulartagsPage();
    RecentlyViewedProductPage rvp = new RecentlyViewedProductPage();
    AddAddressPage addAddressPage = new AddAddressPage();
    ChangePasswordPage changepassword = new ChangePasswordPage();
    CommunityPoll communityPoll = new CommunityPoll();

//--------------shahab work Data...............//

    private String[] newAddressInfo = {"Naeem", "ali", "3 - goodroad", "e3 5dy", "london", "united kingdon", "ali@test.com", "02323233299"};


    // end of shahab work Data...........//
    ////-------------DATA-----------------//
    private String oldpassword = "jm1234";
    private String newpassword = "Jm1234";
    private String confirmpassword = "IJm1234";
////-----------end of data----------//


    ///////-------------shahab Work---------------///////////
    @Test
    public void AddNewAddress() {
        homePage.gotoLogin();
        loginPage.loginAsConsumer(username, password);
        myAccountPage.gotoAccountPage();
        addAddressPage.gotoaddresspage();
        addAddressPage.gotoNewAddressPage();
        addAddressPage.accountAddNewAddress(newAddressInfo[0], newAddressInfo[1], newAddressInfo[2], newAddressInfo[3], newAddressInfo[4], newAddressInfo[5], newAddressInfo[6], newAddressInfo[7]);
        addAddressPage.gotoNewAddressPage();
        WaitUtils.pause(100);

    }

    @Test
    public void select_Popular_tags_From_HomePage() {

        String popular_tags = homePage.select_first_Popular_tags();
        Assert.assertTrue(populartagsPage.isPopulartagsPage(popular_tags));

    }

    @Test
    public void select_Popular_tags_By_ViewAll() {
        homePage.select_viewall_Popular_tags();
        Assert.assertTrue(populartagsPage.isPopulartagsListPage());

    }
//-----end of shahab work ..........////////////


    @Test
    public void registerNewUser()

    {

        homePage.gotoLogin();

        loginPage.gotoRegisternewUser();

        RegisterUserPage registerUserPage = new RegisterUserPage();

        if (!registerUserPage.isUserAlreadyRegistered(username)) {
            Assert.assertTrue(registerUserPage.registerNewUser(newcustomerInfo));
            homePage.logout();
        }
    }

    @Test

    public void updatePersonalDetails() {
        homePage.gotoLogin();
        loginPage.loginAsConsumer(username, password);

        MyAccountPage myAccountPage = new MyAccountPage();

        myAccountPage.gotoAccountPage();

        Assert.assertTrue(customerInfoPage.updateUserInfo(updateCustomerInfo));

        Assert.assertTrue(customerInfoPage.updateUserInfo(customerInforevert));

        homePage.logout();

    }

    @Test
    public void updateFirstName() {


        homePage.gotoLogin();

        loginPage.loginAsConsumer(username, password);


        myAccountPage.gotoAccountPage();

        customerInfoPage.updateFirstName(firstname);

        customerInfoPage.savedetails();

        String pageSource = driver.getPageSource();

        Assert.assertTrue(customerInfoPage.isFirstNameValid(pageSource));

        customerInfoPage.updateFirstName(firstname1);

        customerInfoPage.savedetails();

    }

    @Test
    public void invalidFirstName() {


        homePage.gotoLogin();


        loginPage.loginAsConsumer(username, password);

        myAccountPage.gotoAccountPage();

        customerInfoPage.updateFirstName(" ");

        String pageSource = driver.getPageSource();

        Assert.assertTrue(customerInfoPage.isFirstNameValid(pageSource));

    }

    @Test
    public void select_Manufacture_From_HomePage() {

        String manufacturer = homePage.select_first_manufacturer();
        Assert.assertTrue(manufacturespage.isMaufacturePage(manufacturer));

    }

    @Test
    public void select_Manufacture_By_ViewAll() {
        homePage.select_viewall_manufacture();
        Assert.assertTrue(manufacturespage.isMaufactureListPage());

    }

    @Test
    public void change_language_to_Duetsch() {

        homePage.setLanguage(Lang_Deutsch);

        Assert.assertTrue(homePage.isSiteLanguageSet(Lang_Deutsch));

    }

    @Test
    public void change_language_to_English() {

        homePage.setLanguage(Lang_English);

        Assert.assertTrue(homePage.isSiteLanguageSet(Lang_English));

    }

    @Test
    public void change_currency_to_Dollars() {

        homePage.setCurrency(Currency_Dollar);

        Assert.assertTrue(homePage.isCurrencySet(Currency_Dollar));

    }

    @Test
    public void change_currency_to_Euro() {


        homePage.setCurrency(Currency_Euro);

        Assert.assertTrue(homePage.isCurrencySet(Currency_Euro));

    }

    @Test
    public void verifyRecentyViewdPage() {

        homePage.navigatetologinpage();
        loginPage.loginAsConsumer(username, password);
        homePage.navigateToBooks();
        Assert.assertEquals("Books", driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText());
        homePage.viewProducts();
        rvp.verifyViewedProductShownInRecentlyViewedProductsPage();
    }

//  Work by Narmatha


    @Test
    public void changepassword() {

        homePage.gotoLogin();

        loginPage.loginAsConsumer(username, password);

        myAccountPage.gotoAccountPage();

        changepassword.updatepassword(oldpassword, newpassword, confirmpassword);

        changepassword.savedetails();

        Assert.assertTrue(ChangePasswordPage.ispasswordchanged());

        homePage.logout();
    }


    @Test

    public void addcommunitypoll() {

        homePage.gotoLogin();

        loginPage.loginAsConsumer(username, password);

        myAccountPage.CommunityPoll();

        Assert.assertTrue(CommunityPoll.Polldone());

        homePage.logout();

    }

//------- THIS IS @IMCHANDU WORK --------------------------------------------------- //

    SiteMapPage siteMapPage = new SiteMapPage(driver);
    SearchPage searchPage = new SearchPage(driver);
    BaseClass baseClass = new BaseClass(driver);

    @Test
    public void loginWithValidCredentials() {

        baseClass.loginAttempt("test13", "Insoft12");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='shopbar-account']/a/span[1]/span[1]")).getText(), "test13");

    }

    @Test
    public void loginWithInvalidCredentials() {

        baseClass.loginAttempt("test13", "chandu12");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content-center\"]/div/div[2]/div[1]/div[2]/div/div[1]")).getText(), "Returning Customer");
    }


    @Test
    public void searchWithProductAvailable() {

        baseClass.url();
        baseClass.goToSearchPage();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Search");
        searchPage.searchWithProdName("Iphone 5");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[2]/form/div[4]/div[1]/div/div/div/article/div[2]/h3/a/span")).getText(), "Apple iPhone 5");
        baseClass.waitforsometime();

    }

    @Test
    public void searchWithProductNotAvailable() {
        baseClass.url();
        baseClass.goToSearchPage();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Search");
        searchPage.searchWithProdName("Iphone 5s");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[2]/form/div[3]/span")).getText(), "No products were found that matched your criteria.");
        baseClass.waitforsometime();
    }

    @Test
    public void searchWithoutProduct() {
        baseClass.url();
        baseClass.goToSearchPage();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Search");
        searchPage.searchWithProdName("");
        //Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[2]/form/div[1]/div[1]")).getText(),"Search term minimum length is 3 characters");
        baseClass.waitforsometime();
    }

    @Test
    public void SiteMapSearchWithinCategory() {
        baseClass.url();
        baseClass.goToSiteMapPage();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Sitemap");
        siteMapPage.siteMapSearchByCategory("Computer");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Computer");
        siteMapPage.waitforsometime();
    }

    @Test
    public void SiteMapSearchWithinManufacturer() {

        siteMapPage.url();
        siteMapPage.goToSiteMapPage();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Sitemap");
        siteMapPage.siteMapSearchByManufacture("Zara");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content-center']/div/div[1]/h1")).getText(), "Zara");
    }

}
