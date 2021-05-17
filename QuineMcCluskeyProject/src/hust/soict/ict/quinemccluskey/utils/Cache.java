package hust.soict.ict.quinemccluskey.utils;

import java.util.ArrayList;
import java.util.List;

public class Cache<T extends Object> {
    private List<T> objects = new ArrayList<T> ();

    public void add(T o) {
        objects.add(o);
    }

    public boolean exists(T o) {
        for(T obj : objects) {
            if(obj.equals(o)) {
                return true;
            }
        }
        return false;
    }
}
