package com.dai.nio.nioserver.timeserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Client {
    public Client() {
    }

    public static void main(String[] args) {
        Socket client = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        try {
            client = new Socket("localhost", 8889);
            client.setSoTimeout(10000);
            out = new DataOutputStream( (client.getOutputStream()));

            String query = "GB";
            byte[] request = query.getBytes();
            out.write(request);
            out.flush();
            client.shutdownOutput();

            in = new DataInputStream(client.getInputStream());
            byte[] reply = new byte[40];
            int len =in.read(reply);
            System.out.println("Time: " + new String(reply, 0,len,"UTF-8"));
            
            in.close();
            out.close();
            client.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
