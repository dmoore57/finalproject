package finalproject;

import java.io.Serializable;
/**
 *
 * @author Josh Bodnar, Joe Dister, David Moore, Roger Rado
 */
public class UPCObject implements Serializable { // dmoore57
    // declare object to pass between client and server
    private int ItemUPC; // dmoore57
    private String ItemName; // dmoore57
    private Double ItemPrice; // dmoore57
    
    public UPCObject() { // dmoore57
        // empty
    }
    
    public UPCObject(int ItemUPC, String ItemName, Double ItemPrice) { // dmoore57
        this.SetItemUPC(ItemUPC); // dmoore57
        this.SetItemName(ItemName); // dmoore57
        this.SetItemPrice(ItemPrice); // dmoore57
    }
    
    public int GetItemUPC() { // dmoore57
        return ItemUPC; // dmoore57
    }
    
    public void SetItemUPC(int iupc) { // dmoore57
        ItemUPC = iupc; // dmoore57
    }
    
    public String GetItemName() { // dmoore57
        return ItemName; // dmoore57
    }
    
    public void SetItemName(String iname) { // dmoore57
        ItemName = iname; // dmoore57
    }
    
    public Double GetItemPrice() { // dmoore57
        return ItemPrice; // dmoore57
    }
    
    public void SetItemPrice(Double iprice) { // dmoore57
        ItemPrice = iprice; // dmoore57
    }
}
