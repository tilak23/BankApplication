public class FileException  extends Throwable{
    FileException(String message) {
        super("FileException"+message);
    }
}
