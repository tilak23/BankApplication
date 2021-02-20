import java.io.Serializable;
import java.security.SecureRandomParameters;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Details implements Serializable {
    private            Queue<String> transaction =new PriorityQueue<>();
    private final int           size        =5;// show only last five 5 transaction.
    private       List< String> history     =new LinkedList<>();
    public void add(String s){
        LocalDateTime t = LocalDateTime.now();
        if(transaction.size()==size){
            transaction.remove();//or can use poll();
        }
        transaction.add(String.valueOf(t)+" "+s);
        history.add(String.valueOf(t)+" "+s);
    }

    @Override public String toString() {
        return "Details{" +
                "transaction=" + transaction +
                ", history=" + history +
                '}';
    }
    public void printTrans(){
        System.out.println("----------------------------------------------------------------");
        for(String x:this.transaction){
            System.out.println("| "+x+" |");
        }
        System.out.println("----------------------------------------------------------------");
    }

}
