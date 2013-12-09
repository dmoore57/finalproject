package finalproject;
import java.io.Serializable;
/**
 *
 * @author Josh Bodnar, Joe Dister, David Moore, Roger Rado
 */
public class PriceObject implements Serializable { // dmoore57
    private double Subtotal; // dmoore57
    private double Total; // dmoore57
    
    public PriceObject() { // dmoore57
        // empty
    }
    
    public PriceObject(double subtotal, double total) { // dmoore57
        this.SetSubtotal(Subtotal); // dmoore57
        this.SetTotal(Total); // dmoore57
    }
    
    public double GetSubtotal() { // dmoore57
        return Subtotal; // dmoore57
    }
    
    public void SetSubtotal(double st) { // dmoore57
        Subtotal = st; // dmoore57
    }
    
    public double GetTotal() { // dmoore57
        return Total; // dmoore57
    }
    
    public void SetTotal(double gt) { // dmoore57
        Total = gt; // dmoore57
    }
}
