/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author macbookpro
 */
public class TCPService {
    
    
    private static String hostname;
    private static int PORT ;
    public TCPService(String hostname, int PORT) {
        this.hostname = hostname;
        this.PORT = PORT;
    }
    
    private static Socket socket = getConnection(hostname,PORT);

    
    public static Socket getConnection(String host , int port ){ 
        try{
            socket = new Socket(host,port);        
                    return socket;
            
        }catch(IOException ex){
                ex.printStackTrace();
                return null;
        }
    }
    
    public static void writeObject(Object o ) throws IOException{
          ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
          os.writeObject(o);
    }
    public static void writeObject(Object o, Socket socketInput ) throws IOException{
          ObjectOutputStream os = new ObjectOutputStream(socketInput.getOutputStream());
          os.writeObject(o);
    }
    public static Socket writeObject(Object o, String localhost, int PORT ) throws IOException{
        Socket socketInput = new Socket(localhost,PORT);
        ObjectOutputStream os = new ObjectOutputStream(socketInput.getOutputStream());
          os.writeObject(o);
          return socketInput;
    }
    public static Object readObject() throws IOException, ClassNotFoundException{
          ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
          return is.readObject();
    }
    public static Object readObject(Socket socketInput) throws IOException, ClassNotFoundException{
          ObjectInputStream is = new ObjectInputStream(socketInput.getInputStream());
          return is.readObject();
    }
    
    
}
