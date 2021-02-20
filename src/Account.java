import java.io.IOException;
import java.io.Serializable;

public class Account extends Details implements Serializable {
    private Customer  customer_details;//name,age,etc....
    private Long UniqueId; //account number;
    private Details Transaction;
    private double  minimum_balance =1000.00;
    private double balance;
    private int     pin;// 4 length character

    public Account(Customer customer_details, double minimum_balance,
                   int pin) throws IOException, FileException {

        this.customer_details = customer_details;
        GeneratingId gd=new GeneratingId();
        this.UniqueId = gd.generateId();
        Transaction = new Details();
        this.minimum_balance = minimum_balance;
        balance=minimum_balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    public Customer getCustomer_details() {
        return customer_details;
    }

    public void setCustomer_details(Customer customer_details) {
        this.customer_details = customer_details;
    }

    public Long getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        UniqueId = uniqueId;
    }

    public Details getTransaction() {
        return Transaction;
    }

    public void setTransaction(Details transaction) {
        Transaction = transaction;
    }

    public double getMinimum_balance() {
        return minimum_balance;
    }

    public void setMinimum_balance(double minimum_balance) {
        this.minimum_balance = minimum_balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Account =" +
                ", customer_details =" + customer_details.toString() +
                ", UniqueId=" + UniqueId +
                ", Transaction=" + Transaction +
                ", minimum_balance=" + minimum_balance +
                ", balance=" + balance +
                ", pin=" + pin +
                '}';
    }
}
