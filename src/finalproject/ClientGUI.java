package finalproject;
// import required client/server components
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;
// import arraylist and defaultlistmodel, not sure which would be more beneficial to use
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import org.sqlite.*;

/**
 *
 * @author Josh Bodnar, Joe Dister, David Moore, Roger Rado
 */
public class ClientGUI extends javax.swing.JFrame {
    // declare connection variables to connect to server
    Socket connection = null;
    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    // used in upc check
    int UPCCheck = 0;
    DefaultComboBoxModel upcModel = new DefaultComboBoxModel();
    DefaultListModel itemModel = new DefaultListModel();
    DefaultListModel cartModel = new DefaultListModel();
    DefaultListModel checkoutModel = new DefaultListModel();
    ArrayList <UPCObject> IncomingList = new ArrayList();

    /**
     * Creates new form ClientGUI
     */
    public ClientGUI() {
        initComponents();
        PopulateList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        AddCartButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ItemsList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        EmptyCartButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CartList = new javax.swing.JList();
        CheckoutButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        TransactionIDTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TransactionLookupButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        RefundButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        UPCComboBox = new javax.swing.JComboBox();
        UPCLookupButton = new javax.swing.JButton();
        ItemUPCTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ItemNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ItemPriceTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Items"));

        AddCartButton.setText("Add to Cart");
        AddCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCartButtonActionPerformed(evt);
            }
        });

        ItemsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(ItemsList);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(85, 85, 85)
                .add(AddCartButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(AddCartButton))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cart"));

        EmptyCartButton.setText("Empty Cart");
        EmptyCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmptyCartButtonActionPerformed(evt);
            }
        });

        CartList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(CartList);

        CheckoutButton.setText("Checkout");
        CheckoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckoutButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(EmptyCartButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(CheckoutButton)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(EmptyCartButton)
                    .add(CheckoutButton)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaction Lookup"));

        jLabel1.setText("Transaction ID");

        TransactionLookupButton.setText("Find");
        TransactionLookupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionLookupButtonActionPerformed(evt);
            }
        });

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList1);

        RefundButton.setText("Refund Item");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3)
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(TransactionIDTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(TransactionLookupButton)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(RefundButton)
                .add(87, 87, 87))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(TransactionIDTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(TransactionLookupButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(RefundButton))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("UPC Lookup"));

        jLabel2.setText("UPC");

        UPCComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11234", "12345", "23456", "34567", "45678", "56789" }));

        UPCLookupButton.setText("Get Info");
        UPCLookupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPCLookupButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Item UPC");

        jLabel4.setText("Item Name");

        jLabel5.setText("Item Price");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(UPCComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(UPCLookupButton))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel5)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel4)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(ItemUPCTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .add(ItemNameTextField)
                            .add(ItemPriceTextField))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(UPCComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(UPCLookupButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ItemUPCTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(ItemNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(ItemPriceTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PopulateList() {
       /* 
       ArrayList <String> storelist = new ArrayList(); 
        //This code can be used to query the server to get an updated list of stores
        //from the database
        try{
            // create a connection to the server
            connection = new Socket("127.0.0.1", 2000); 
            //define output stream
            output = new ObjectOutputStream(connection.getOutputStream()); 
            // tell the server which command we're sending
            output.writeObject("SendStores"); 
            // flush output
            output.flush(); 
            // establish input stream
            input = new ObjectInputStream(connection.getInputStream()); 
            //We are going to need to recieve an array of stores from the server, Since theere
            //is no .hasNext() equivalent, we will just read in stores from the server
            //until we recieved an EOFException
            try
            {
                int storeIncrementer = 0;
                while(true)
                {
                    storelist.add((String)input.readObject());
                }
            }
            catch (EOFException eof)
            {
                //Just means there are no more stores to send over
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            //Proper closing of file input stream
            try
            {
                input.close();
            }
            catch(Exception e)
            {
                //Does nothin
            }
            
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Could not establish connection with server or no object was returned."); // dmoore57
        }
     */   

        // THIS NEEDS TO HAVE SOME EXCEPTION HANDLING
        String[] storelist = { "Store1", "Store2" };
        String selectedstore = "";
        JFrame frame = new JFrame("Store Select");
        selectedstore = (String) JOptionPane.showInputDialog(frame,
                "Select a store:",
                "Store Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                //storelist.toArray(), storelist.get(0));
                storelist,storelist[0]);
        
        // populate arraylist with test data until we can pull it from the server
        // this is temporary code //////////////////////////////////////////////
        if (selectedstore == "Store1") {
            for (int i = 0; i < 10; i++) {
                UPCObject tempupcobject = new UPCObject();
                tempupcobject.SetItemUPC(10000 + i); // dmoore57
                tempupcobject.SetItemName("Store 1 Item " + i); // dmoore57
                tempupcobject.SetItemPrice(11.11 + (double) i); // dmoore57
                IncomingList.add(tempupcobject);
            }
        } else if (selectedstore == "Store2") {
            for (int i = 0; i < 10; i++) {
                UPCObject tempupcobject = new UPCObject();
                tempupcobject.SetItemUPC(20000 + i); // dmoore57
                tempupcobject.SetItemName("Store 2 Item " + i); // dmoore57
                tempupcobject.SetItemPrice(22.22 + (double) i); // dmoore57
                IncomingList.add(tempupcobject);
            }
        }
        // end of temporary code ///////////////////////////////////////////////
        
        // loop through the list of items given by the server and populate
        // the items list on the form
        for (UPCObject tempupcobject2 : IncomingList) { // dmoore57
            // declare temp int variable
            int tempupc = 0; // dmoore57
            // get item upc from object
            tempupc = tempupcobject2.GetItemUPC(); // dmoore57
            // declare temp string variable
            String tempname = ""; // dmoore57
            // get item name from object
            tempname = tempupcobject2.GetItemName(); // dmoore57
            // declare temp double variable
            Double tempprice = 0.00; // dmoore57
            // get item price from object
            tempprice = tempupcobject2.GetItemPrice(); // dmoore57
            // take all the data gathered from object and put it into an index on the list and in the upc combo box
            itemModel.addElement(Integer.toString(tempupc) + " - " + tempname + " - $" + tempprice.toString()); // dmoore57
            upcModel.addElement(Integer.toString(tempupc)); // dmoore57
        }
        // set list and combo box to display created models
        ItemsList.setModel(itemModel); // dmoore57
        UPCComboBox.setModel(upcModel); // dmoore57
    }
    
    private void EmptyCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmptyCartButtonActionPerformed
        // removes all elements from the list to empty cart
        cartModel.removeAllElements(); // dmoore57
        // updates list on form to show that all items have been removed from list
        CartList.setModel(cartModel); // dmoore57
    }//GEN-LAST:event_EmptyCartButtonActionPerformed

    private void UPCLookupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPCLookupButtonActionPerformed
        try { // dmoore57
            // get the value of the UPC from the combo box on the form
            UPCCheck = Integer.valueOf((String)UPCComboBox.getSelectedItem()); // dmoore57
            // create a connection to the server
            connection = new Socket("127.0.0.1", 2000); // dmoore57
            // define output stream
            output = new ObjectOutputStream(connection.getOutputStream()); // dmoore57
            // tell the server which command we're sending
            output.writeObject("ProcessUPC"); // dmoore57
            // send actual UPC data to the server
            output.writeObject(UPCCheck); // dmoore57
            // flush output
            output.flush(); // dmoore57
            // establish input stream
            input = new ObjectInputStream(connection.getInputStream()); // dmoore57
            // make a temporary object to hold object received from the server
            UPCObject tempupcobject = new UPCObject(); // dmoore57
            // receive the object from the server and store it in temp object
            tempupcobject = (UPCObject) input.readObject(); // dmoore57
            // get information from the object and display it on the form
            ItemUPCTextField.setText(Integer.toString(tempupcobject.GetItemUPC())); // dmoore57
            ItemNameTextField.setText(tempupcobject.GetItemName()); // dmoore57
            ItemPriceTextField.setText(Double.toString(tempupcobject.GetItemPrice())); // dmoore57
        }
        catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Could not establish connection with server or no object was returned."); // dmoore57
        }
        finally {
            try {
                output.close(); // dmoore57
                input.close(); // dmoore57
                connection.close(); // dmoore57
            }
            catch (Exception exception) {
                // exception handling
            }
        }
    }//GEN-LAST:event_UPCLookupButtonActionPerformed

    private void AddCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCartButtonActionPerformed
        // adds elements from the item list to the cart list
        cartModel.addElement((String) ItemsList.getSelectedValue()); // dmoore57
        // updates list on form to show new items added to list
        CartList.setModel(cartModel); // dmoore57
    }//GEN-LAST:event_AddCartButtonActionPerformed

    private void CheckoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckoutButtonActionPerformed
        try
        {
        // loads SQLite wrapper
        Class.forName("org.sqlite.JDBC");
        } // end try
        catch (ClassNotFoundException ex)
        {
        System.out.println("The SQLite wrapper is not available." + ex.getMessage());
        } // end catch
        
        Connection conn = null;
        try
        {
        // create a SQLite wrapper config
        SQLiteConfig config = new SQLiteConfig();
        // turn on foreign key constraints
        config.enforceForeignKeys(true);
        // create a connection to the mydemo.db file using the jdbc
        // API and the sqlite driver/wrapper and apply the SQLiteConfig
        conn = DriverManager.getConnection("jdbc:sqlite:POS.db", config.toProperties());
        // declare the SQL statement
        Statement stmt = conn.createStatement();
        // set a max timeout for the query
        stmt.setQueryTimeout(30);
        // drop the table 'Person' if it exists in the database file
        stmt.executeUpdate("DROP TABLE IF EXISTS People");
        //creates Stores table
        stmt.executeUpdate("CREATE TABLE Stores (ID integer PRIMARY KEY, Name longtext)");
        //creates Transactions table
        stmt.executeUpdate("CREATE TABLE Transactions (ID integer PRIMARY KEY, SubTotal currency, GrandTotal currency, StoreID integer, FOREIGN KEY(StoreID) REFERENCES Stores(ID))");
        //creates Inventory
        stmt.executeUpdate("CREATE TABLE Inventory (ID integer PRIMARY KEY, UPC integer, ItemName text, CurrentPrice currency)");
        //creates SalesDetails table
        stmt.executeUpdate("CREATE TABLE SalesDetails (ID integer PRIMARY KEY, TransactionID integer, Price currency, Date datetime, UPC integer, FOREIGN KEY(TransactionID) REFERENCES Transactions(ID), FOREIGN KEY(UPC) REFERENCES Inventory(UPC))");
        }
        catch (SQLException ex)
        {
        System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_CheckoutButtonActionPerformed

    private void TransactionLookupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionLookupButtonActionPerformed
        int transactionid = 0;
        try {
            // get the transaction ID entered by the user
            transactionid = Integer.parseInt(TransactionIDTextField.getText()); // dmoore57
            // establish connection
            connection = new Socket("127.0.0.1", 2000); // dmoore57
            output = new ObjectOutputStream(connection.getOutputStream()); // dmoore57
            // send command to the server
            output.writeObject("TransactionLookup"); // dmoore57
            // send transaction id to be looked up
            output.writeObject(transactionid); // dmoore57
            input = new ObjectInputStream(connection.getInputStream());
            input.readObject();
        }
        catch (Exception notanumberexception) {
            // exception if anything except numbers are entered in the id box
            JOptionPane.showMessageDialog(null, "Please enter an integer transaction ID."); // dmoore57

    }//GEN-LAST:event_TransactionLookupButtonActionPerformed
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCartButton;
    private javax.swing.JList CartList;
    private javax.swing.JButton CheckoutButton;
    private javax.swing.JButton EmptyCartButton;
    private javax.swing.JTextField ItemNameTextField;
    private javax.swing.JTextField ItemPriceTextField;
    private javax.swing.JTextField ItemUPCTextField;
    private javax.swing.JList ItemsList;
    private javax.swing.JButton RefundButton;
    private javax.swing.JTextField TransactionIDTextField;
    private javax.swing.JButton TransactionLookupButton;
    private javax.swing.JComboBox UPCComboBox;
    private javax.swing.JButton UPCLookupButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
