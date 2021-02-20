import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.InputMismatchException;

public class ExceptionBanking implements Serializable {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Operation            op    =new Operation();
        Database             db    = new Database();

        System.out.println("########################################################################");
        System.out.println("                        IDEA BANK        ");
        System.out.println("########################################################################");
        System.out.println("1).create new Account \n 2).open an existing account");
        try {
            char c = input.readLine().charAt(0);
            while (true) {
                if (c == '1') {
                    db = op.create(db);
                } else if (c == '2') {
                    db = op.openExisting(db);
                } else if (c == '3') {
                    db = op.deleteAccount(db);
                }
                System.out.println("if you want to exit enter any letter\n else\n1).create new Account \n 2).open an " +
                        "existing account");
                c = input.readLine().charAt(0);
                if (c != '1' && c != '2' && c != '3') {
                    break;
                }
            }
            System.out.println("########################################################################");
            System.out.println("                        THANK YOU!");
            System.out.println("########################################################################");
        }
        catch (FileException | UnsupportedFundException |Exception e){
             System.out.print("the input you entered is incorrect.");
        }



    }
}
