package test_data;

import java.util.HashMap;
import java.util.Map;
public class HerokuAppTestData {

    // Inner map

    public static Map<String, String> bookingDatesMapper(String checkin, String checkout){
        Map<String, String> map = new HashMap<>();
        if (checkin!=null){
            map.put("checkin",checkin);
        }
        if (checkout!=null){
            map.put("checkout",checkout);
        }
        return map;
    }


    // Outer map

    public static Map<String, Object> herokuAppMapper(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String,String> bookingdates,String additionalneeds){
        Map<String, Object> map = new HashMap<>();
        if (firstname!=null){
            map.put("firstname",firstname);
        }
        if (lastname!=null){
            map.put("lastname",lastname);
        }
        if (totalprice!=null){
            map.put("totalprice",totalprice);
        }
        if (depositpaid!= null){
            map.put("depositpaid",depositpaid);
        }
        if (bookingdates!= null){
            map.put("bookingdates",bookingdates);
        }
        if (additionalneeds!= null){
            map.put("additionalneeds",additionalneeds);
        }
        return map;
    }
}


// We use wrapper class of Data types (Integer, Boolean etc.) in this class
// so that when we use Put or Patch methods to update one value of the
// existing data, we can send null values as well
// If we use primitive int, or boolean, we will get NullPointerException for Put or Patch methods