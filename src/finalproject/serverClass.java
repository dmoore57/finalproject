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
import java.util.Date;
import java.text.SimpleDateFormat;

// imports for SQLite
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import org.sqlite.*;

// test
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;



/**
 *
 * @author Josh Bodnar, Joe Dister, David Moore, Roger Rado
 */
public class serverClass {//jdister1
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
    
    
    public void loadServer()//jdister1
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

    public void waitForClient()//jdister1
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
    
    public void getDataStreams()//jdister1
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
            case "NewTransaction": NewTransaction(); break; // dmoore57
            case "SendStores": SendStores(); break;
            case "TransactionLookup": TransactionLookup(); break; // dmoore57
            case "GetUPCList": GetUPCList(); break; // dmoore57
        } // end switch
        
    }
    
    public void SendStores() 
    {
        //Queries the database for a list of stores and returns them as an array
        // query database for list of upc items and compile them into an arraylist
        try {
            // loads SQLite wrapper
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("The SQLite wrapper is not available." + ex.getMessage());
        }
        // establish connection variable
        Connection conn = null;
        //Creates an array of strings to pass back to the client
        ArrayList <String> storelist = new ArrayList(); 
        try {
            // set up sqlite and connection
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection("jdbc:sqlite:POS.db", config.toProperties());
            // set up sql statement to pull all items from inventory
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name FROM Stores");
            // loop to iterate through objects pulled from database and puts
            // them into the arraylist to send back to the client
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                storelist.add(rs.getString("Name"));
            }
        }
        catch(Exception e)
        {
            
        }
        try {
            // sending list of stores back to the client
            output.writeObject(storelist);
            System.out.println("Sent store list to client.");
        }
        catch (Exception exception) {
            
        }
        //NEED DATABASE CODE TO GET STORE NAMES AND PUT THEM IN AN ARRAY
        //For now using the code David had on the client
        //String[] storelist = { "Store1", "Store2","Store3" };
        for(String store: storelist)
        {
            //need proper try catch since we're sending over the network
            try
            {
                output.writeObject(store);
            }
            catch (Exception e)
            {
                //required error catching
            }
        }
        //Tries to flush the connection we just used
        try
        {
            output.flush();
        }
        catch (Exception e)
        {
            //Required error catching
        }
    }
    
    public void ProcessUPC() {
        try {
            // loads SQLite wrapper
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("The SQLite wrapper is not available." + ex.getMessage());
        }
        Connection conn = null;
        // temporary object to store data in from database to send
        // back to the client to display
        try {
            UPCObject tempupc = new UPCObject(); // dmoore57
            // defines integer to hold integer data sent from client
            int UPCLookup = (int) input.readObject(); // dmoore57
            // set up sqlite and connection
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection("jdbc:sqlite:POS.db", config.toProperties());
            // set up sql statement to pull all items from inventory
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Inventory WHERE UPC = " + UPCLookup);

            try {

            // print the recieved UPC from client on the server console
            System.out.println("Recieved UPC: " + UPCLookup); // dmoore57

            // loop to iterate through objects pulled from database and puts
            // them into the arraylist to send back to the client
            // pull the data for each field out of the database
            tempupc.SetItemUPC(rs.getInt("UPC"));
            tempupc.SetItemName(rs.getString("ItemName"));
            tempupc.SetItemPrice(rs.getDouble("CurrentPrice"));
            // get ready to send object back to server
            output.writeObject(tempupc);
            output.flush();
            System.out.println("Sent item information to client.");
            rs.close();
            }
            catch (Exception exception) {
                
            }
        }
        catch (Exception exception) {
            System.out.println("An error has occurred.");
        }
        finally {
            try {
                // close all connections
                output.close(); // dmoore57
                input.close(); // dmoore57
                connection.close(); // dmoore57
            } catch (Exception exception) { // dmoore57
                // exception handling
            }
        }
    }
    public void NewTransaction() { // dmoore57
        // declaring variables to hold total and subtotal for transaction
        double subtotal = 0;
        double total = 0;
        // change date format so that it matches what the database creates
        String DateFormat = "MMM dd yyyy HH:mm:ss";
        Date currentdate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        try {
            // creates a new arraylist to receive data from client
            ArrayList <UPCObject> ReceivedItemArrayList = (ArrayList) input.readObject();
            // print that object was received
            System.out.println("Recieved transaction object");
            // read through the received array list and parse data
            for (UPCObject tempobject : ReceivedItemArrayList) {
                // print out each received item to the system log
                System.out.println("" + tempobject.GetItemUPC() + " " + tempobject.GetItemName() + " $" + tempobject.GetItemPrice());
                // add each item's price to the subtotal
                subtotal += tempobject.GetItemPrice();
            }
            // add in 6% sales tax to calculate grand total
            total = (subtotal * 0.06) + subtotal;
            // print out total and subtotal for the transaction to the system log
            System.out.println("Transaction date: " + sdf.format(currentdate));
            System.out.println("Number of items sold: " + ReceivedItemArrayList.size());
            System.out.println("Subtotal: $" + subtotal);
            System.out.println("Total: $" + total);
            // we need to generate a unique ID for each transaction when we
            // add them to the database. this should ultimately be relayed
            // back to the user on the client side (at least report total,
            // subtotal and transaction ID)
        }
        catch (Exception exception) { // dmoore57
            // exception handling
        }        
        finally {
            try {
                // close all connections
                output.close(); // dmoore57
                input.close(); // dmoore57
                connection.close(); // dmoore57
            } catch (Exception exception) { // dmoore57
                // exception handling
            }
        }
    }
    public void TransactionLookup() {
        int transactionID = 0;
        try {
            transactionID = (int) input.readObject();
            System.out.println("Received transaction ID " + transactionID + " for lookup.");
            // select * from salesdetails where transactionid = transactionID
            // 
            // pull transaction out of database and put objects for each item
            // into an arraylist to send back to the client to display on the 
            // form
        }
        catch (Exception exception) {
            
        }
        finally {
            try {
                // close all connections
                output.close(); // dmoore57
                input.close(); // dmoore57
                connection.close(); // dmoore57
            } catch (Exception exception) { // dmoore57
                // exception handling
            }
        }
    }
    public void GetUPCList() {
        // this loads all items from the server onto the form
        // query database for list of upc items and compile them into an arraylist
        try {
            // loads SQLite wrapper
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("The SQLite wrapper is not available." + ex.getMessage());
        }
        // establish connection variable
        Connection conn = null;
        // declare empty arraylist to hold objects to send to client
        ArrayList <UPCObject> upcarraylist = new ArrayList();
        try {
            // set up sqlite and connection
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection("jdbc:sqlite:POS.db", config.toProperties());
            // set up sql statement to pull all items from inventory
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Inventory");
            // loop to iterate through objects pulled from database and puts
            // them into the arraylist to send back to the client
            while (rs.next()) {
                // create a new object each time the loop iterates
                UPCObject tempupc = new UPCObject();
                // pull the data for each field out of the database
                tempupc.SetItemUPC(rs.getInt("UPC"));
                tempupc.SetItemName(rs.getString("ItemName"));
                tempupc.SetItemPrice(rs.getDouble("CurrentPrice"));
                // add the temporary object to the arraylist
                upcarraylist.add(tempupc);
            }
            rs.close();
        }
        catch (Exception exception) {
            System.out.println("An error has occurred.");
        }
        try {
            // get ready to send object back to server
            output.writeObject(upcarraylist);
            System.out.println("Sent item information to client.");
            output.flush();
        }
        catch (Exception exception) {
            
        }
        finally {
            try {
                // close all connections
                output.close(); // dmoore57
                input.close(); // dmoore57
                connection.close(); // dmoore57
            } catch (Exception exception) { // dmoore57
                // exception handling
            }
        }
    }
    
}

