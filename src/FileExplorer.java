import javax.annotation.processing.FilerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
class FileExplorer{
    /*
    when the File Explorer is called from the database class, a new object must be created and a handling must do in
    that particular file.
    */
     final File file;
     FileExplorer(String path){
        file=new File(path);
     }
    public List<String> read() throws FileException, IOException {

        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        if(lines.isEmpty()){
            throw  new FileException("files are empty, please add something to be read.");
        }
        return  lines;
    }

    public void createFile()  {
        try {
            file.createNewFile();
        }
        catch (IOException e){
            System.out.print("creating file "+file.getName()+"unsuccessful");
        }
    }
    public  void delete() throws FileException{
         file.delete();
         if(file.exists()){
             throw  new FileException("the file  is not deleted, please try again");
         }
    }
    public boolean checkExist(){
         return file.exists();
    }

    public void writeObj(Account obj) {

        try (FileOutputStream fos = new FileOutputStream(file.getPath());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            System.out.println("Success in writing data");
        }
        catch (IOException ex) {
           System.out.println("The Data you wanted to write is unsuccessful..");
        }
     }
        public void  write(String s) throws IOException {
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(s);
        myWriter.close();
        }
    public void  write(String s,String path) throws Exception {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(String.valueOf(path), true));
            w.write(s + "\n");
            w.close();
        }
        catch (Exception e) {
            throw new FilerException(" the data you wanted to store in file is unsuccessful");
        }
    }

        public Account readObject () {
            try (FileInputStream fis = new FileInputStream(file.getPath());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                // read object from file
                Account user = (Account) ois.readObject();
                System.out.println("the data has been fetched");
                return user;
            }
            catch (IOException | ClassNotFoundException ex) {
                System.out.print ("error in read Object");
            }

            return null;
        }
    }

