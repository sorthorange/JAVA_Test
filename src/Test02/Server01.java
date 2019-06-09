package Test02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server01 {

    public static ServerSocket server;
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(8888);
        while(true){
            Socket client = server.accept();
            System.out.println("link");

            new Thread(new Channal(client)).start();
        }

        //server.close();
    }

}
class Channal implements Runnable {
    private Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning;

    Channal(Socket client){
        this.client=client;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            release();
        }
        isRunning = true;
    }
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            release();
        }

    }
    private String receive(){
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            release();
        }
        return msg;
    }
    private void release() {
        this.isRunning = false;
        try {
            if(null!=dis)
                dis.close();
            if(null!=dos)
                dos.close();
            if(null!=client)
                client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(isRunning){
            String msg = "";
            msg = receive();
            if(!msg.equals("")){
                send(msg);
            }
        }
    }
}
