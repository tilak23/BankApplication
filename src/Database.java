import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

class Database  {
    String f_path=System.getProperty("user.home")+"\\Bank application";
    // file explorer wil be used when we want to write the data or object into the file, this takes care of the
    // single responsibility
    // creating empty constructor, so that the file automatically can be created in user directory with
    //  creating a new folder in the name of BANK APPLICATION.

    Database() throws IOException {
        if(!Files.isDirectory(Path.of(f_path))) {
            new File(f_path).mkdirs();
        }
    }
    //creating a filepath where the user wants to store the data of the uniqueId,password.
    Database(String path)  {
        if(!Files.isDirectory(Path.of(path))) {
            new File(path).mkdirs();
        }
    }
    public  void updateFile(Account all) {
        try {
            FileExplorer f = new FileExplorer(f_path +"\\"+ all.getUniqueId());
            f.delete();
            add(all);
        }
        catch(FileException e){
            System.out.print("FileException: the file you wanted to update is unsuccessful!");
        }
    }
    public boolean checkExist(String path){
        return new File(f_path+"\\"+path).exists();
    }
    public void  add( Account all) throws FileException {
        FileExplorer f = new FileExplorer(f_path + "\\" + all.getUniqueId() + ".dat");
        f.createFile();
        f.writeObj(all);
    }
    public Account get(Long userId)  throws FileNotFoundException {
        FileExplorer f=new FileExplorer(f_path+"\\"+userId+".dat");
        if(!f.checkExist()){
            throw new FileNotFoundException("No Such file present");
        }
        return  f.readObject();
    }
}
