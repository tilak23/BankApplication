import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Operation  extends AccountOperation implements Serializable {
    BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
    public Database create(Database db) {
        try {
            System.out.println("Enter the FirstName:");
            String firstName = input.readLine();
            System.out.println("Enter the LastName:");
            String LastName = input.readLine();
            System.out.println("Enter the age:");
            int    age      = Integer.parseInt(input.readLine());
            String password = getPassword();
            System.out.println("enter the address:");
            String   address = input.readLine();
            if(firstName.equals("") ||LastName.equals("")){
                throw  new IOException("the input you given is blank please try again");
            }
            Customer auto    = new Customer(firstName, LastName, encryption(password), address, age);
            System.out.println("deposit a minimum amount of 1000.00\n enter the amount");
            Double deposit = getmoney(Double.parseDouble(input.readLine()));
            System.out.println("enter the 4 digit pin :");
            String pin = input.readLine();
            Account all = new Account(auto, deposit,
                    Integer.parseInt(encryption(pin)));
            all.getTransaction().add(" deposit "+deposit+" "+all.getBalance());
            //while adding into db we can write the object in a particular file and retrieve it for later.
            db.add(all);
            System.out.println(
                    "bank account is created successfully....\n please remember  userid " + all.getUniqueId() +
                            " " +
                            "and password");
        }

        catch(IOException | UnsupportedFundException | FileException e){
            System.out.print("the data you entered is incorrect..");
        }
        return db;
    }
    private Double getmoney(double money) throws  UnsupportedFundException {
        if(money<1000){
            throw new UnsupportedFundException("The amount you deposited is less than 1000" +
                    ".00 " +
                    "rupees");
        }
        return  money;
    }
    public String getPassword() throws  IOException {
        String instruction= """
                 The Length of password must be minimum of 8 character.
                 the password must contain uppercase alphabets,
                 lowercase alphabets,
                 one special character, 
                 atleast one digit.
                """;
        System.out.println(instruction+"\nEnter the password:");
        int i=0;
        String password;
        while(true) {
            password = input.readLine();

            try {
                isValid(password);
                System.out.println("Valid Password");
                i++;
                if (i == 1) {
                    return password;
                }
            }
            catch (InvalidPasswordException e) {
                System.out.print(e.getMessage());
                System.out.println(e.printMessage());
            }
        }

    }
    public static String isValid(String password)
            throws InvalidPasswordException
    {

        // for checking if password length
        // is between 8 and 15
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
            throw new InvalidPasswordException(1);
        }
        System.out.println("the");
        // to check space
        if (password.contains(" ")) {
            throw new InvalidPasswordException(2);
        }
        int count = 0;

        // check digits from 0 to 9
        for (int i = 0; i <= 9; i++) {
            // to convert int to string
            String str1 = Integer.toString(i);
            if (password.contains(str1)) {
                count = 1;
            }
        }
        if (count == 0) {
            throw new InvalidPasswordException(3);
        }


        // for special characters
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            throw new InvalidPasswordException(4);
        }

        count = 0;

        // checking capital letters
        for (int i = 65; i <= 90; i++) {

            // type casting
            char c = (char)i;

            String str1 = Character.toString(c);
            if (password.contains(str1)) {
                count = 1;
            }
        }
        if (count == 0) {
            throw new InvalidPasswordException(5);
        }
        count = 0;

        // checking small letters
        for (int i = 90; i <= 122; i++) {

            // type casting
            char c = (char)i;
            String str1 = Character.toString(c);

            if (password.contains(str1)) {
                count = 1;
            }
        }
        if (count == 0) {
            throw new InvalidPasswordException(6);
        }

        // The password is valid
        System.out.println("Password you entered is valid.");
        return password;
    }
    //encrypting the password is better cuz the data can't be fetched .
    public static String encryption(String password){
        StringBuffer encrypted=new StringBuffer();
        for(int i=0;i<password.length();i++){
            encrypted.append((char)(password.charAt(i)+1));
        }
        return  String.valueOf(encrypted);
    }
    public static String decryption(String password){
        StringBuffer decrypt=new StringBuffer();
        for (int i=0;i<password.length();i++){
            decrypt.append((char)(password.charAt(i)-1));
        }
        return String.valueOf(decrypt);
    }
    public Database openExisting(Database db)
            throws UnsupportedFundException, IOException, FileException, InvalidPasswordException {
        System.out.print("Enter the userId:");
        Long userId = Long.parseLong(input.readLine());
        System.out.print("Enter the password:");
        String password =input.readLine();
        db.checkExist("/"+userId);
        Account check= db.get(userId);

        if(password.equals(decryption(check.getCustomer_details().getPassword()))){
            System.out.println("                 welcome");
            System.out.println("press ,\n 1).withdrawn\n 2).deposit\n 3).balance\n 4).transaction");
            int select = Integer.parseInt(input.readLine());
            if (select == 1) {
               withdraw(check);
               db.updateFile(check);
            }else if (select == 2) {
                deposit(check);
                db.updateFile(check);
            }else if (select == 3) {
                balance(check);
            }else if(select==4){
                transaction(check);
            }
            else {
                throw new IOException("the num you entered is invalid");
            }
        }
        else{
            throw  new InvalidPasswordException("the password doesn't match please try again!.");
        }
        return db;
    }
     public Database deleteAccount(Database db){
        return  db;
    }

}
