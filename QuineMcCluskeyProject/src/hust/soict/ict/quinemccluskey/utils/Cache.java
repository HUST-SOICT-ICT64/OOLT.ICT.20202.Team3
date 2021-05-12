package hust.soict.ict.quinemccluskey.utils;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private static List<Object> objects = new ArrayList<Object> ();

    public static void add(Object o) {
        objects.add(o);
    }

    public static boolean exists(Object o) {
        for(Object obj : objects) {
            if(obj.equals(o)) {
                return true;
            }
        }
        return false;
    }
}
