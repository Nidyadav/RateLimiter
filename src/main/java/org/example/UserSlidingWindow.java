package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserSlidingWindow {

    Map<Integer,SlidingWindow> bucket;

    public UserSlidingWindow(int id) {
        bucket=new HashMap<Integer,SlidingWindow>();
        bucket.put(id,new SlidingWindow(2,1000));
    }
    void accessApp(int id){
        if(bucket.get(id).grantAccess()){
            System.out.println("able to access app");
        }else{
            System.out.println("denied to access app");
        }
    }
}
