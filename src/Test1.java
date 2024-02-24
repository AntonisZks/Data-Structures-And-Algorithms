import java.util.ArrayList;


public class Test1
{
    public static void main(String[] args)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add(i + 1);
        }
        
        System.out.println(arrayList);
    }
}
