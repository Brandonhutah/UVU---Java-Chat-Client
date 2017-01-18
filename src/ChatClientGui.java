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
import java.awt.event.*;
import java.io.PrintWriter;

public class ChatClientGui extends JFrame implements ActionListener {
    private PrintWriter socketWriter;
    private String userName;
    private JScrollPane areaScrollPane;
    private JTextArea textArea;
    private JButton button;
    private JTextField textField;

    ChatClientGui(String name)
    {
        userName = name;

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setSize(485, 400);
        areaScrollPane.setLocation(0, 0);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);

        button = new JButton("Disconnect");
        button.setSize(100, 20);
        button.setLocation(0, 410);
        button.addActionListener(this);

        textField = new JTextField();
        textField.setSize(350, 20);
        textField.setLocation(110, 410);
        textField.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    SendMessageToServer(textField.getText());
                    AddNewMessage(userName + ": " + textField.getText());
                    textField.setText("");
                }
            }
        });

        contentPane.add(textField);
        contentPane.add(areaScrollPane);
        contentPane.add(button);

        add(contentPane);

        pack();
    }

    void AddWriter(PrintWriter writer)
    {
        socketWriter = writer;
    }

    synchronized void AddNewMessage(String message)
    {
        textArea.append(message + "\n");
    }

    private void SendMessageToServer(String message)
    {
        socketWriter.write(message + "\n");
        socketWriter.flush();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button))
        {
            SendMessageToServer("disconnect " + userName);
        }
    }
}