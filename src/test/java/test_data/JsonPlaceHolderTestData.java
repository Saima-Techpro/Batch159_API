package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public static Map<String, Object> jsonPlaceHolderMapper(Integer userId, String title, Boolean completed) {

        Map<String, Object> map = new HashMap<>();
        if (userId!=null){
            map.put("userId", userId);
        }
        if (title!= null) {
            map.put("title", title);
        }
        if (completed != null) {
            map.put("completed", completed);
        }
        return map;
    }
}

// We use wrapper class of Data types (Integer, Boolean etc.) in this class
// so that when we use Put or Patch methods to update one value of the
// existing data, we can send null values as well
// If we use primitive int, or boolean, we will get NullPointerException for Put or Patch methods