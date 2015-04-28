import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojc on 2015/4/28.
 */
public class Test {
    public static void main(String args[]) {
        List<String> right = new ArrayList<String>();
        right.add("100100");
        right.add("010010");
        right.add("001001");
        int size = right.size();
        Integer temp = 000000;
        for (int i = 0; i < size; i++) {
            temp = temp | Integer.parseInt(right.get(i), 2);
        }
        System.out.println(right.get(0));
        System.out.print(Integer.toBinaryString(temp));
    }
}
