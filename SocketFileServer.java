// File: SocketFileServer.java
import java.io.*;
import java.net.*;

public class SocketFileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ClientHandler(socket)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String request = in.readLine();
            if (request.startsWith("UPLOAD")) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("uploaded_file.txt"));
                String line;
                while (!(line = in.readLine()).equals("END")) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
                out.println("File uploaded successfully!");
            } else if (request.startsWith("DOWNLOAD")) {
                BufferedReader reader = new BufferedReader(new FileReader("uploaded_file.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    out.println(line);
                }
                reader.close();
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}