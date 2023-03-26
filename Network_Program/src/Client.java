import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final Scanner input;
    private final DataInputStream in;
    private final DataOutputStream out;

    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);

        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());

        input = new Scanner(System.in);

        System.out.println("Connected!\n");
    }

    public void serverShutDownCommandCheck(String message) {
        if(Objects.equals(message, "quit")) {
            throw new ServerShutOffException("\nServer will shut down!\n");
        }
    }

    public void clientServerCommunication() throws IOException {
        System.out.println("Enter your notification:");
        String message = input.nextLine();
        out.writeUTF(message);

        try {
            serverShutDownCommandCheck(message);
        }
        catch (ServerShutOffException err) {
            System.out.println(err.getMessage());
            return;
        }

        System.out.println("Enter delay of your message in ms:");
        out.writeUTF(input.nextLine());

        String message_back = in.readUTF();
        System.out.println(message_back);
    }

    public void closeEverything() throws IOException {
        System.out.println("Closing connection!");

        input.close();
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 1234);
        client.clientServerCommunication();
        client.closeEverything();
    }
}