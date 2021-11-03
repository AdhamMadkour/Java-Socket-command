import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Socket socket = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            socket = new Socket("localhost", 8833);
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String command = sc.next();
                if (Objects.equals(command, "SEND")) {
                    String msg = sc.nextLine();
                    bufferedWriter.write(msg);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } else if (Objects.equals(command, "WHO")) {
                    bufferedWriter.write(command);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    String serverMsg = bufferedReader.readLine();
                    System.out.println(serverMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
