package com.booking.app.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;


public class Logger {

    private static Logger instance = null;

    private Logger(){}

    private void initializeLogger() {
    }

    public void log(String message) throws IOException {

        InetAddress iAddress = InetAddress.getLocalHost();
        String currentIp = iAddress.getHostAddress();

        BufferedWriter bw = null;
        FileWriter fw = null;
        fw = new FileWriter("logs.txt",true);
        bw = new BufferedWriter(fw);
        bw.append("Current IP address : " +currentIp + " \n\t"+ message+" "+ new Date() + "\n\n");
        bw.newLine();
        bw.close();
    }

    public static Logger getInstance() {
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

}