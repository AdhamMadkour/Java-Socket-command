import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = new ServerSocket(8833);
        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Accepted !");
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                while (true) {
                    String msg = bufferedReader.readLine();
                    if (Objects.equals(msg, "WHO")) {
                        InetAddress ip = InetAddress.getLocalHost();
                        bufferedWriter.write(ip.getHostName());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {
                        msg = msg.substring(1);
                        System.out.println(msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
