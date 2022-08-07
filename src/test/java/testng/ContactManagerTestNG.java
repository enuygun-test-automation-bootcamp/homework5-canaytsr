package testng;

import devices.DeviceFarm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddContactPage;
import pages.HomePage;
import utility.DeviceFarmUtility;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ContactManagerTestNG {

     public static AppiumDriver<MobileElement> Driver;
    HomePage homePage;
    AddContactPage addContactPage;
    String oreo, kitkat;
    DesiredCapabilities capabilities;

    public ContactManagerTestNG() {
        oreo = DeviceFarm.ANDROID_OREO.path;
        kitkat = DeviceFarm.ANDROID_KITKAT.path;
    }

    @BeforeClass
    public void setup()  {

        //Control the file and drivers error
        try {
            capabilities = new DesiredCapabilities();
            capabilities = DeviceFarmUtility.pathToDesiredCapabilitites(this.oreo);
            capabilities.setCapability("app", new File("src/test/resources/apps/ContactManager.apk").getAbsolutePath());
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Resource Failed");
        }

        try {
            Driver = new AndroidDriver(new URL("http://127.0.0.1:8080/wd/hub"), capabilities);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            ex.getMessage();
            System.out.println("Android driver not created");
        }


        //Create Objects
        homePage = new HomePage();
        addContactPage = new AddContactPage();

    }

    @Test(priority = 0)
    public void openAddContactOnOreo() throws NullPointerException, InterruptedException {
        //Click the contact button
        homePage.getAddContactBtn().click();
        Thread.sleep(4000);
    }


    //User checks title whether it is "Add Contact"
    @Test(priority = 1)
    public void checkAddContactTitle() {
        //verify the Page Title
        Assert.assertEquals(addContactPage.getTitle().getText(), "Add Contact");
    }



    @Test(priority = 2)
    public void checkEmail() {
        //set a mail address
        addContactPage.setEmail("deneme@gmail.com");
        //verify the Email
        Assert.assertTrue(EmailValidator.getInstance().isValid(addContactPage.getContactEmailField().getText()));

    }

    @Test(priority = 3)
    public void checkPhoneNumber() {
        //set the phone number
        addContactPage.setPhoneNumber("90 555 444 33 22");
        WebElement phoneNumber = addContactPage.getContactPhoneField();
        String phoneNumberString =phoneNumber.getText();
        //Clean space of the string
        phoneNumberString=phoneNumberString.replaceAll("\\s","");
        //verify the phone number size is 12
        Assert.assertEquals(phoneNumberString.length(), 12);
        //verify the phone number is not include character
        Assert.assertFalse(phoneNumber.getText().contains("[a-zA-Z]+"));

    }

    @Test(priority = 4)
    public void checkContactName() {
        int digit=0,letter=0,spl=0;
        char ch;
        //set contact name
        addContactPage.setContactName("User1");
        WebElement contactName = addContactPage.getContactNameField();

        //Create a loop for count string elements
        for (int i=0;i<contactName.getText().length();i++)
        {
           ch = contactName.getText().toLowerCase().charAt(i);
           if ((ch>='a' && ch<='z')){

               letter++;
           }
           else if (ch>='0' && ch<='9') {
               digit++;
           }
           else {
               spl++;
           }
        }
        //checks special characters limit for contact name(limit=3)
        Assert.assertTrue(spl<=3);
        //checking if there is at least letter in the contact name
        Assert.assertTrue(letter>0);
        //checking if there is a digit in the contact name
        Assert.assertTrue(digit>=0);

    }
    @Test(priority = 5)
    public void checkSpinnerPhone() throws InterruptedException {
        //Control contactPhoneSpinner
       addContactPage.clickPhoneSpinner();
        Thread.sleep(4000);
        //Selection control for Mobile item
        Assert.assertEquals(addContactPage.getSpinnerPhoneItemOfMobile().getText(),"Mobile");
        //Click on element
        addContactPage.getSpinnerPhoneItemOfMobile().click();
        Thread.sleep(4000);

    }
    @Test(priority = 6)
    public void checkSpinnerEmail() throws InterruptedException {
        //Control contactEmailSpinner
        addContactPage.clickEmailSpinner();
        Thread.sleep(4000);
        //Selection control item of Work(Not Home)
        Assert.assertNotEquals(addContactPage.getSpinnerEmailItemOfWork().getText(),"Home");
        Assert.assertEquals(addContactPage.getSpinnerEmailItemOfWork().getText(),"Work");
        //Click on element
        addContactPage.getSpinnerEmailItemOfWork().click();

    }
    @Test(priority = 7)
    public void checkSaveButtonAndLogin() {
        //Control Save Button Text
        Assert.assertEquals( addContactPage.getContactSaveBtn().getText(),"Save");
        //Login Check
        addContactPage.getContactSaveBtn().click();

    }

    @AfterClass
    public void waiter() throws InterruptedException {
        Thread.sleep(5000);
    }


}