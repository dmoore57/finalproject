/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

//These imports are needed for the server componenet
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.ArrayList;


/**
 *
 * @author joe
 */
public class serverClass {
    //We will need a class for serializable products I think
    //private ArrayList<SerializableProduct> AllProducts = new ArrayList<SerializableProduct>();
    
    //These variables are needed for the server to function properly
    private Socket connection = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private ServerSocket socket = null;
    
    public static void main(String[] args)
    {
        new serverClass().loadServer();
    }
    
    public void loadServer()
    {
        //We will need to query the Database to get an up to date list of our products
        //loadProducts();
        
       //boolean to be changed when client diconnects
        boolean isConnected = false;
        
        //This try catch will attempt to create a server
        try
        {
            ServerSocket server = new ServerSocket(2000,100);
                
                //Nested while statement waits for a connection on the newly
                //created server
                while(isConnected == false)
                {
                    //Wait for a client to connect with waitForClient()
                    //waitForClient();
                    //Wait for streams of data
                    //getDataStreams();
                    //logic portions
                }
        }
        
        //If the server could not be established on the given port
        //This catch will be executed
        catch(Exception e)
        {
            
        }
    }
}

