package Client;

import Client.Manager.*;
import lib.Console;
import lib.Commands;
import lib.Response;

import java.io.*;
import java.net.*;

public class Client_main {
    public final static int SERVICE_PORT=50001;
    public static void main(String[] args) throws  IOException {
        Console console = new Console();
        Inputs inputs = new Inputs(console);
        Commands s;
        Client client = new Client(SERVICE_PORT, console);
        boolean flag = true;
        while (flag) {
            try {
                s = inputs.commandInput();
            } catch (NullPointerException ex) {
                console.println("Программа завершена");
                return;
            }
            try {
                client.send(s);
                Response response = (Response) client.read().readObject();
                String answer = response.getResponse();
                if (answer.equals("exit")) {
                    flag = false;
                } else {
                    console.println(answer);
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SocketTimeoutException e) {
                System.out.println("Сервер не доступен! Попробуйте ещё раз!");
            }
        }
    }
}