package Server;

import Server.Manager.Server;

import java.io.*;

public class Server_main {
    public final static int SERVICE_PORT=50001;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s;
        if (args.length!=0) {
            s = args[0];
        } else {
            System.out.println("Введите название файла");
            try {
                s = input.readLine().trim();
            } catch (NullPointerException ex) {
                System.out.println("Программа завершена");
                return;
            }

        }
        File file = new File(s);
        while (!file.isFile()) {
            System.out.println("Такого файла нет");
            System.out.println("Введите название файла");
            s = input.readLine().trim();
            file = new File(s);
        }
        Server server = new Server(SERVICE_PORT);
        server.init(file);

    }
}
