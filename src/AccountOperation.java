import java.util.Scanner;

public class AccountOperation  {
    static Scanner in =new Scanner(System.in);

    public synchronized void  withdraw(Account userId) throws UnsupportedFundException {
        System.out.println("Enter the 4-digit pin number ");
        int user_pin=in.nextInt();
        try{
            if(user_pin!= Integer.parseInt(Operation.decryption(String.valueOf(userId.getPin()))))
                throw new InvalidPasswordException(7);

            System.out.print("enter the amount:");
            double amount =in.nextDouble();
            if(userId.getBalance()-amount <= userId.getMinimum_balance()){
                throw new UnsupportedFundException("Insufficient fund: you can't withdraw more than the minimum " +
                        "balance");
            }
            userId.setBalance(userId.getBalance()-amount);
            userId.getTransaction().add("withdraw "+amount+" "+userId.getBalance());

        }
        catch (InvalidPasswordException e){
            e.printMessage();
        }
        finally{
            System.out.println("please take your cash...");
        }

    }

    public synchronized void deposit(Account userId) throws UnsupportedFundException,InvalidPasswordException {

        System.out.println("Enter the 4-digit pin number ");
        int user_pin=in.nextInt();
        if(user_pin!=Integer.parseInt(Operation.decryption(String.valueOf(userId.getPin())))){
            throw new InvalidPasswordException(7);
        }
        System.out.println("enter the amount you wanted to deposit:");
        double amount =in.nextDouble();
        if(amount>=50000){
            throw new UnsupportedFundException("the amount you can deposit in a day is 50000");
        }
        userId.setBalance(userId.getBalance()+amount);
        userId.getTransaction().add("deposit "+amount+" "+userId.getBalance());
    }
    public void balance(Account userId){
        System.out.println(userId.getBalance());
    }
    public void transaction(Account userId){
        System.out.println("last 5 transaction will be displayed ,for more details please visit the nearer bank ");
        userId.getTransaction().printTrans();
    }
}
