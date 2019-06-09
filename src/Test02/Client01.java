package Test02;

import java.io.*;
import java.net.Socket;

public class Client01 {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();

        //client.close();
    }

}
class Send implements Runnable{

    private Socket client;
    private DataOutputStream dos;
    private BufferedReader console;
    private boolean isRunning;

    Send(Socket client) throws IOException {
        this.client=client;
        console = new BufferedReader(new InputStreamReader(System.in));
        dos = new DataOutputStream(client.getOutputStream());
        isRunning = true;
    }
    private String getStr(){
        try{
            return console.readLine();
        }catch (IOException e){
            release();
        }
        return null;
    }
    private void send(String msg) {
        try{
            dos.writeUTF(msg);
            dos.flush();
        }catch (IOException e){
            release();
        }

    }
    private void release(){
        this.isRunning = false;
        try {
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
            String msg = null;
            msg = getStr();

            if(!msg.equals("")){
                send(msg);
            }
        }
    }
}

class Receive implements Runnable{

    private Socket client;
    private DataInputStream dis;
    private boolean isRunning;

    Receive(Socket client) throws IOException {
        this.client=client;
        dis = new DataInputStream(client.getInputStream());
        isRunning = true;
    }
    private String receive() {
        String msg = "";
        try{
            msg = dis.readUTF();
        }catch (IOException e){
            release();
        }
        return msg;
    }
    private void release(){
        this.isRunning = false;
        try {
            if(null!=dis)
                dis.close();
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
                System.out.println(msg);
            }
        }
    }
}

