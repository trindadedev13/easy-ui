package com.trindade.dev.util;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    
    List<String> logs;
    
    public Logger() {
        logs = new ArrayList<>();
    }
    
    public String getLogs(){
        return logs.toString();
    }
    
    public void add(String logVal){
        logs.add(logVal);
    }
}
