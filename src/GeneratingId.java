import java.io.File;
import java.io.IOException;
import java.io.Serializable;

final class GeneratingId implements Serializable {
    private  long   customer_Id ;
    final   String def_path          = System.getProperty("user.home")+"\\BANK APPLICATION\\uniqueID.txt";
    FileExplorer id_exp ;
    GeneratingId() throws FileException, IOException {
        id_exp=new FileExplorer(def_path);
        if(!id_exp.checkExist()){
            System.out.println("creating a new UniqueId file");
            id_exp.createFile();
            id_exp.write("102456");
        }
        try{
        customer_Id =  Long.parseLong(String.valueOf(id_exp.read().get(0))) ;
        }
        catch (FileException e){
        System.out.print("FileException:creating a new UniqueId to customer is unsuccessful");
        }
    }
    GeneratingId(String path) throws IOException {
        id_exp=new FileExplorer(path);
        if(!id_exp.checkExist()){
            System.out.println("creating a new UniqueId file");
            id_exp.createFile();
            id_exp.write("102456");
        }
        try {
            customer_Id = Long.parseLong(String.valueOf(id_exp.read().get(0)));
        }
        catch (FileException e){
            System.out.print("FileException:creating a new UniqueId to customer is unsuccessful");
        }
    }
    public long  generateId() throws IOException {
        id_exp.write(String.valueOf(customer_Id+1));
        return (customer_Id)+1;
    }

}
