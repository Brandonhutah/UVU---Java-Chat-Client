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

import java.io.InputStream;
import java.util.Scanner;

public class ServerListenerThread implements Runnable {
    private Scanner socketReader;

    ServerListenerThread(InputStream inputStream)
    {
        socketReader = new Scanner(inputStream);
    }

    public void run()
    {
        String streamInput;

        while(!Thread.currentThread().isInterrupted())
        {
            try {
                if ((streamInput = socketReader.nextLine()) != null)
                {
                    ChatClient.WriteMessage("Server: " + streamInput);
                }
            }
            catch (Exception ignored) {
            }
        }
    }
}