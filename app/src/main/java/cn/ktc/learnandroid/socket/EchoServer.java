package cn.ktc.learnandroid.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private final ServerSocket mServerSocket;

    public EchoServer(int port)throws IOException{
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException{
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket client) throws IOException {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        byte[] buffer =new byte[1024];
        int n;
        while ((n=in.read(buffer))>0){
            out.write(buffer,0,n);
        }

    }

    public static void main(String[] argv){
        try{
            EchoServer echoServer = new EchoServer(9877);
            echoServer.run();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
