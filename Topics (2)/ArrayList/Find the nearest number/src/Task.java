import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner myScanner = new Scanner(System.in);
        String inputString = myScanner.nextLine();
        String target = myScanner.nextLine();
        Integer targetInteger = Integer.valueOf(target);
        List<Integer> toProceed = Arrays.stream(inputString.split(" "))
                .collect(Collectors.toList())
                .stream().map(str -> Integer.valueOf(str))
                .sorted()
                .collect(Collectors.toList());

        Integer closestDown = toProceed.stream().sorted(Comparator.reverseOrder()).filter(down -> down <= targetInteger).mapToInt(v -> v).max().orElse(-1);
        Integer closestUp = toProceed.stream().filter(up -> up >= targetInteger).mapToInt(v -> v).min().orElse(-1);

        long downCount = toProceed.stream().filter(a -> a.equals(closestDown)).count();
        long upCount = toProceed.stream().filter(a -> a.equals(closestUp)).count();

        String result = "";
//        if (closestDown != -1&& closestUp - targetInteger == targetInteger - closestDown)
//                for (int i = 0; i < downCount; i++) result += closestDown + " ";
//        if (closestUp != -1 && closestDown != closestUp &&closestUp - targetInteger == targetInteger - closestDown)
//                for (int i = 0; i < upCount; i++) result += closestUp + " ";
//         else if (closestUp != -1 && closestDown != closestUp &&closestUp != -1 && closestDown != closestUp &&closestUp - targetInteger < targetInteger - closestDown)
//            for (int i = 0; i < upCount; i++) result += closestUp + " ";
//        else if (closestDown != -1)
//            for (int i = 0; i < downCount; i++) result += closestDown + " ";

        //1 nominal case
        if (closestDown != -1 && closestUp != -1 && closestDown != closestUp && closestUp - targetInteger == targetInteger - closestDown) {
            for (int i = 0; i < downCount; i++) result += closestDown + " ";
            for (int i = 0; i < upCount; i++) result += closestUp + " ";
        } else if (closestDown != -1 && closestUp == -1) {
            //2 up outbound
            for (int i = 0; i < downCount; i++) result += closestDown + " ";
        } else if (closestDown == closestUp) {
            //3 contains value
            for (int i = 0; i < downCount; i++) result += closestDown + " ";
        } else if (closestDown != -1 && closestUp != -1 && closestDown != closestUp && closestUp - targetInteger > targetInteger - closestDown) {
            //4 deep up
            for (int i = 0; i < downCount; i++) result += closestDown + " ";
        } else if (closestDown != -1 && closestUp != -1 && closestDown != closestUp && closestUp - targetInteger < targetInteger - closestDown) {
            //5 deep down
            for (int i = 0; i < upCount; i++) result += closestUp + " ";
        }
        System.out.println(result);

//        LinkedList<Integer> list = new ArrayList();

        Collection<Integer> list1 = new ArrayList<>();

        LinkedList<Integer> list11 = new LinkedList<>(new ArrayList<>());

        ArrayList<Integer> list111 = new ArrayList<>();

        List<Integer> list1111 = new ArrayList<>();
    }
}
