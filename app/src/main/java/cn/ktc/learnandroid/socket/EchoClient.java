package cn.ktc.learnandroid.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class EchoClient {
    private final Socket mSocket;

    public EchoClient(String host,int port)throws IOException{
        mSocket = new Socket(host,port);

    }

    public void run() throws IOException{
        //TODO
        Thread thread = new Thread(this::readResponse);
        thread.start();
        OutputStream outputStream = mSocket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n=System.in.read(buffer))>0){
            outputStream.write(buffer,0,n);

        }
    }

    private void readResponse() {
        try{
            InputStream inputStream =mSocket.getInputStream();
            byte[] buffer =new byte[1024];
            int n;
            while ((n=inputStream.read(buffer))>0){
                System.out.write(buffer,0,n);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] argv){
        try{
            EchoClient echoClient = new EchoClient("localhost",9877);
            echoClient.run();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
