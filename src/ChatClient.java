/* I declare that the following source code is my work.
 I understand and can explain everything I have written, if asked.
 I understand that copying any source code, in whole or in part,
 that is not in my textbook nor provided or expressly permitted by the instructor,
 constitutes cheating. I will receive a zero on this project for
 poor academic performance if I am found in violation of this policy.*/

/*
 * Name: Brandon Horlacher
 * Section: 601
 * Project: 5
 */

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClient {
    private static ChatClientGui frame;

    public static void main (String[] args) {
        try
        {
            String userName = "Anonymous";
            int port = 4688;

            if (args.length > 0) {
                userName = args[0];

                if (args.length > 1)
                    port = Integer.parseInt(args[1]);
            }

            String finalUserName = userName;
            frame = new ChatClientGui(finalUserName);

            EventQueue.invokeLater(() ->
            {
                frame.setTitle("Project 5 | Chat Client");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setSize(500, 500);
                frame.setVisible(true);
            });

            Socket s = new Socket("localhost", port);

            PrintWriter writer = new PrintWriter(s.getOutputStream());
            frame.AddWriter(writer);

            ServerListenerThread listener = new ServerListenerThread(s.getInputStream());
            Thread t = new Thread(listener);
            t.start();

            writer.write("connect " + userName + "\n");
            writer.flush();
        }
        catch (Exception ignored)
        {

        }
    }

    static void WriteMessage(String message)
    {
        frame.AddNewMessage(message);
    }
}