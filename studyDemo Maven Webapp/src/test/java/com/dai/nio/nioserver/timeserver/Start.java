package com.dai.nio.nioserver.timeserver;

import com.dai.nio.nioserver.Notifier;
import com.dai.nio.nioserver.Server;

/**
 * <p>Title: 启动类</p>
 * @author starboy
 * @version 1.0
 */

public class Start {

    public static void main(String[] args) {
        try {
            LogHandler loger = new LogHandler();
            TimeHandler timer = new TimeHandler();
            Notifier notifier = Notifier.getNotifier();
            notifier.addListener(loger);
            notifier.addListener(timer);

            System.out.println("Server starting ...");
            Server server = new Server(5100);
            Thread tServer = new Thread(server);
            tServer.start();
        }
        catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            System.exit(-1);
        }
    }
}
