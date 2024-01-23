package com.techtitans.smartbudget.dataManagement;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MainDataManagement {

    @Async
    public void runInBackground() {
        while (true) {
            // Your finance API logic that runs indefinitely
            System.out.println("Running finance API background task...");

            //TODO Add your finance API code here

            try {
                // Sleep for a while to avoid high CPU usage
                Thread.sleep(100); // Sleep for 0.1 seconds
            } catch (InterruptedException e) {
                // Handle interruption if needed
                Thread.currentThread().interrupt();
            }
        }
    }

}
