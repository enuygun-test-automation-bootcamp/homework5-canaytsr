package pages;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Data;
import org.openqa.selenium.support.PageFactory;
import testng.ContactManagerTestNG;


@Data
public class AddContactPage {
    public AddContactPage(){

        PageFactory.initElements(new AppiumFieldDecorator(ContactManagerTestNG.Driver), this);

    }

    @AndroidFindBy(id = "android:id/title")
    private MobileElement title;

    @AndroidFindBys({@AndroidFindBy(id="title"),@AndroidFindBy(tagName = "title")})
    private MobileElement titleBys;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/accountSpinner")
    private MobileElement targetAccountField;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactNameEditText")
    private MobileElement contactNameField;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactPhoneEditText")
    private MobileElement contactPhoneField;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactPhoneTypeSpinner")
    private MobileElement contactPhoneDrop;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactEmailEditText")
    private MobileElement contactEmailField;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactEmailTypeSpinner")
    private MobileElement contactEmailDrop;

    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactSaveButton")
    private MobileElement contactSaveBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[3]")
    private MobileElement spinnerPhoneItemOfMobile;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[2]")
    private MobileElement spinnerEmailItemOfWork;

    public void setEmail(String email){
        this.contactEmailField.sendKeys(email);

    }
    public void setContactName(String name){
        this.contactNameField.sendKeys(name);

    }
    public void setPhoneNumber(String phoneNumber){
        this.contactPhoneField.sendKeys(phoneNumber);

    }

    public void clickPhoneSpinner(){
        contactPhoneDrop.click();

    }

    public void clickEmailSpinner(){
        contactEmailDrop.click();

    }
}
