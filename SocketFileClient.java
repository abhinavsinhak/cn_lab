// File: SocketFileClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketFileClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);

        // Ask user whether to upload or download
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to upload or download a file? (Enter 'upload' or 'download')");
        String action = scanner.nextLine();

        PrintWriter out = new PrintWriter(socket.getOutputStream());

        if (action.equalsIgnoreCase("upload")) {
            // Get user input for the file to upload
            System.out.println("Enter the path of the file to upload:");
            String filePath = scanner.nextLine();

            // Upload a file
            out.println("UPLOAD");
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            out.println("END");
            out.flush();
            reader.close();
        } else if (action.equalsIgnoreCase("download")) {
            // Download a file
            out.println("DOWNLOAD");
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String downloadedFilePath = "/home/sushant/Documents/downloaded.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadedFilePath));
            String line;
            while ((line = in.readLine()) != null) {
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