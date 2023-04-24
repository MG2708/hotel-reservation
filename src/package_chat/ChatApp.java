/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package_chat;

/**
 *
 * @author gargm
 */
public class ChatApp {
    private ServerFrame server;
    private ClientFrame client;
    Thread serverThread;
    Thread clientThread;
            
    public ChatApp()
    {
        server = new ServerFrame();
        client = new ClientFrame();
    }
    
    public void start()
    {
        serverThread = new Thread(server);
        clientThread = new Thread(client);
        serverThread.start();
        clientThread.start();
    }
    
}
