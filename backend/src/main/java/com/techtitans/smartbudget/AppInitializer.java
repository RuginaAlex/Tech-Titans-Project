package com.techtitans.smartbudget;

import com.techtitans.smartbudget.dataManagement.MainDataManagement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {
    private final MainDataManagement mainDataManagement;

    public AppInitializer(MainDataManagement mainDataManagement) {
        this.mainDataManagement = mainDataManagement;
    }

    @Override
    public void run(String... args) throws Exception {
        //mainDataManagement.runInBackground();
    }
}