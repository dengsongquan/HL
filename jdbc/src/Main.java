import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map m = new HashMap(){{
        put("a",2.1);
    }};
    public static void main(String[] args) {
       Integer isInt = (Integer) m.get("a");
       System.out.println(isInt);
    }


}

