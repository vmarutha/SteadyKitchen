/**
 A subclass of the Ingredient superclass
 @version 1.0 2012-03-02
 @author Vinoth Maruthalingam
 */
public class Fruits extends Ingredient 
{
   public double vitaminCContent;  // Instance field specific to Fruits object
  
  public Fruits(String name, String foodGroup, double vitaminCContent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.vitaminCContent = vitaminCContent;
  }
  
  public Fruits(String name, String foodGroup, double vitaminCContent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.vitaminCContent = vitaminCContent;
  }
  
  public Fruits(String name, String foodGroup, double vitaminCContent)
  {
    super(name, foodGroup);
    this.vitaminCContent = vitaminCContent;
  }
  
  public double getVitaminCContent() // Accessor Method
  {
    return this.vitaminCContent;
  }
   
   /**
   Overrides the toString method in the Ingredient super class since there is more information to display
   @param no paramters
   @return the String containing the information about the object.
   */
  public String toString()
  {
    return ("Name: " + this.getName() + '\n' +
            "Food Group: " + this.getFoodGroup() + '\n' +
            "Color: " + this.getColor() + '\n' +
            "Mass: " + this.getMass() + '\n' +
            "Stock: " + this.getStock() + '\n' + 
            "Vitamin C Content: " + this.getVitaminCContent() + '\n' + 
            this.displayKeyFeatures());
  }
}
