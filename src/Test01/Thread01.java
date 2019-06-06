package Test01;

/**
 *
 *
 */
public class Thread01 {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        new Thread(god).start();
        new Thread(you).start();
    }
}

class You extends Thread{
    @Override
    public void run() {
        for(int i=0;i<365*100;i++)
            System.out.println("happy");
        System.out.println("death");
    }
}
class God extends Thread{
    @Override
    public void run() {
        for(;;)
            System.out.println("bless");
    }
}