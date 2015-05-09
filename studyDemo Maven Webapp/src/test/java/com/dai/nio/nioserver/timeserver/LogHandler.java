package com.dai.nio.nioserver.timeserver;

import java.util.Date;

import com.dai.nio.nioserver.Request;
import com.dai.nio.nioserver.event.EventAdapter;

/**
 * 日志记录
 */
public class LogHandler extends EventAdapter {
    public LogHandler() {
    }

    public void onClosed(Request request) throws Exception {
        String log = new Date().toString() + " from " + request.getAddress().toString();
        System.out.println(log);
    }

    public void onError(String error) {
        System.out.println("Error: " + error);
    }
}
