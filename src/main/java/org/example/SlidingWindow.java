package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimiter{
    Queue<Long> slidingWindow;
    int windowSize;
   long time;

    public SlidingWindow(int windowSize, long time) {
        this.windowSize = windowSize;
        this.time = time;
        slidingWindow = new ConcurrentLinkedQueue<>();

    }

    @Override
    public boolean grantAccess() {
        long currentTime = System.currentTimeMillis();
        updateQueue(currentTime);
        if(slidingWindow.size()<windowSize){
            slidingWindow.offer((long) currentTime);
            return true;
        }
        return false;
    }

    private void updateQueue(long currentTime) {
        if(slidingWindow.isEmpty()) return;
        long time=(currentTime- slidingWindow.peek())/1000;
        while(time>=this.time){
            slidingWindow.poll();
            if(slidingWindow.isEmpty()) break;
            time=(currentTime- slidingWindow.peek())/1000;
        }
    }
}
