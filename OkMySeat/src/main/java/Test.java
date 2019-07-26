import java.util.Date;

public class Test {
    public static void main(String[] args) {
       String s= new String("1561352400000");
        long l = System.currentTimeMillis();
        System.out.println(l);
        Date date = new Date( Long.valueOf(s));
        System.out.println(date.toString());
    }
}
