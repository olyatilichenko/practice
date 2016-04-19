
import java.util.Comparator;


public class TimeCompare implements Comparator<Message>  {
    @Override
    public int compare(Message temp1, Message temp2) {
        return Long.compare(temp2.getTimestamp() , temp1.getTimestamp());
    }
}
