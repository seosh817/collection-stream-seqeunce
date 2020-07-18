import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Stream {

    public static void main(String[] args) {


        Arrays.stream(new String[]{"d", "c", "b", "a"})
                .sorted((t1, t2) -> {
                    return t1.compareTo(t2);
                })
                .forEach(System.out::println);

        int[] evens = Arrays.stream(new int[]{1,2,3,4,5,6,7,8})
                .filter((num) -> {
                    if(num % 2 == 0)
                            return true;
                    else return false;
                })
                .toArray();

        Arrays.stream(evens)
                .forEach(System.out::println);






    }

}
