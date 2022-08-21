import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class Availability {
    public String log = "";

    public static void main(String[] args) throws InterruptedException {
        Availability web = new Availability();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileWriter writer = new FileWriter(new File("")+"\\log.txt", true);
                    writer.write(web.log);
                    writer.flush();
                    writer.close();
                } catch (FileNotFoundException e) {} catch (IOException e) {}
                System.out.println("Das Programm wurde beendet.");
            }
        }));

        while (true) {
            if (!isAvailable()){
                Thread.sleep(5000);
                if (!isAvailable()) {
                    web.log += "Website Offline at: " + LocalDateTime.now() + "\n";
                    System.out.println("Website Offline at: " + LocalDateTime.now());
                }
            }
            Thread.sleep(60000);
        }
    }

    public static boolean isAvailable() {
        try {
            return InetAddress.getByName("pantherinblack.ddns.net").isReachable(30000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}