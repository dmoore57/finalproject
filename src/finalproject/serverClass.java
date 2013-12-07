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
 * @author Josh Bodnar, Joe Dister, David Moore, Roger Rado
 */
public class serverClass {
    //We will need a class for serializable products I think
    //private ArrayList<SerializableProduct> AllProducts = new ArrayList<SerializableProduct>();
    
    //These variables are needed for the server to function properly
    private Socket connection = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private ServerSocket socket = null;
    private ServerSocket server;
    
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
                server = new ServerSocket(2000,100);
                
                //Nested while statement waits for a connection on the newly
                //created server
                while(isConnected == false)
                {
                    try
                    {
                        //Wait for a client to connect with waitForClient()
                        waitForClient();
                        //Wait for streams of data
                        getDataStreams();
                        //logic portions
                        processRequest();
                    }
                     //If the server could not be established on the given port
                    //This catch will be executed
                    catch(Exception e)
                    {
                        
                    }
                    //Executes after the try/catch to close up connections and free things
                    //up for our next connection
                    finally
                    {
                        //nested try attempts to close out resources, but it will error
                        //out if these resources are never established in the first place
                        try
                        {
                            input.close();
                            output.close();
                            connection.close();
                        }
                        catch(Exception e)
                        {
                            //required exception handling
                        }
                    }
                }
        }
        catch (Exception e)
        {
            //required exception handling
        }
    }

    public void waitForClient()
    {
        System.out.println("Waiting For Connection");
        try
        {
            //This command doesn't let anything happen until someone actually
            //connects
            connection = server.accept();
        }
        catch(Exception e)
        {
            //No actual exception expected just required
        }
    }    
    
    public void getDataStreams()
    {
        //Open data streams for accepting command from client
        try
        {
            input = new ObjectInputStream(connection.getInputStream());
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
        }
        catch(Exception e)
        {
            //No exception here to actually catch just a required method
        }
    }
    
    public void processRequest()
    {
        //This is where the text that is passed from the client is stored
        String requestCommand = "";
        
        try
        {
            //Takes an object in from the data stream and converts it to a string
            //that will be used for comparison with our defined protocol. (String)
            //will explicitly cast the object into the String type
            requestCommand = (String) input.readObject();
            //ProcessUPC
            //switch (command)
        }
        catch(Exception e)
        {
            //If there is an error this will tell us what it is
            System.out.println(e.getMessage());
        }
        //Tells us what command was recieved
        System.out.println("Received Command: " + requestCommand);
        
        //This switch case will do different things depending on the request recieved
        //This is our defined protocol
        switch(requestCommand)
        {
            case "ProcessUPC": ProcessUPC(); break; // dmoore57
        } // end switch
        
    }
    public void ProcessUPC() {
        // 
        try {
            // defines integer to hold integer data sent from client
            int UPCLookup = (int) input.readObject(); // dmoore57
            // print the recieved UPC from client on the server console
            System.out.println("Recieved UPC: " + UPCLookup); // dmoore57
            // this if/else is only for testing, should be replaced
            // with actual database handles when ready
            UPCObject tempupcobject = new UPCObject(); // dmoore57
            // this object is for testing, realistically this needs to be
            // pulled out of the database
            if (UPCLookup == 11234) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 1");
                tempupcobject.SetItemPrice(11.11);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            } else if (UPCLookup == 12345) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 2");
                tempupcobject.SetItemPrice(22.22);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            } else if (UPCLookup == 23456) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 3");
                tempupcobject.SetItemPrice(33.33);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            } else if (UPCLookup == 34567) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 4");
                tempupcobject.SetItemPrice(44.44);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            } else if (UPCLookup == 45678) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 5");
                tempupcobject.SetItemPrice(55.55);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            } else if (UPCLookup == 56789) {
                tempupcobject.SetItemUPC(UPCLookup);
                tempupcobject.SetItemName("Item 6");
                tempupcobject.SetItemPrice(66.66);
                output.writeObject(tempupcobject);
                System.out.println("Sent object information for UPC " + UPCLookup + " to client.");
            }
        }
        catch (Exception exception) { // dmoore57
            
        }
    }
    
}

