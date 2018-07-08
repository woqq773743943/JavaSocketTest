package uestc.ccg;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main(String[] args  ) throws IOException, InterruptedException {
        String msg = "ab";
        byte[] byteMsg = msg.getBytes();
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream out = socket.getOutputStream();
        BufferedOutputStream bw = new BufferedOutputStream(out);

        bw.write(byteMsg);
        Thread.sleep(30000);
        bw.flush();
        Thread.sleep(30000);
        bw.close();
    }
}
