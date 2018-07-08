package uestc.ccg;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //为了简单起见，所有的异常信息都往外抛
        int port = 9999;
        //定义一个ServerSocket监听在端口8899上
        ServerSocket server = new ServerSocket(port);
        //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
        do {
            System.out.println("test");
            Socket socket = server.accept();
            //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
            InputStream is = socket.getInputStream();
            Thread thread = new Thread(new ServerThread(is));
            thread.start();
        } while (true);
    }
}

class ServerThread implements Runnable{
    private InputStream is;
    ServerThread(InputStream is){
        this.is = is;
    }
    @Override
    public void run() {
        try {
            byte[] data = new byte[8];
            int length=0;
            while(-1 != (length = is.read(data))){
                String result = new String(data);
                System.out.println(result);
                System.out.println("length:" + length);
            }
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
