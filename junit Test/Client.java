import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);


    public static String main(String command) {
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
                //String command = sc.next();
                if (command.contains("SEND")) {
                    //String msg = sc.nextLine();
                    String msg = command.substring(5);
                    bufferedWriter.write(msg);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    outputStreamWriter.close();
                    socket.close();
                    return msg;
                } else if (Objects.equals(command, "WHO")) {
                    bufferedWriter.write(String.valueOf(command));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    String serverMsg = bufferedReader.readLine();
                    return serverMsg;
                    //System.out.println(serverMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
