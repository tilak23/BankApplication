import java.io.IOException;
import java.io.Serializable;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private String Password;
    private int     age;
    private String address;

    Customer(String firstName, String lastName, String password, String address,int age) throws IOException,
            FileException{
        this.firstName = firstName;
        this.lastName = lastName;
        Password = password;
        this.age = age;
        this.address=address;

    }
    // empty Constructor
    // so that this can be used when the client   extends this class as a part of their program.
    // they can set customer  details using Set methods
    Customer(){

    }


    @Override public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Password='" + Password + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
