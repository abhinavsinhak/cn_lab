import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        Scanner scr = new Scanner(System.in);

        System.out.println("choose between upload and Download");
        String action = scr.nextLine();
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        if (action.equalsIgnoreCase("Upload")) {
            System.out.println("enter the file path");  
            String filePath = scr.nextLine();
            out.println("UPLOAD");
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            out.println("END");
            out.flush();
            reader.close();
        } else if (action.equalsIgnoreCase("Download")) {
            out.print("Download");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/sushant/Documents/downloaded.txt"));
            String line;
            while (!(line = in.readLine()).equals("END")) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            in.close();

        }
        out.close();
        socket.close();
    }
}