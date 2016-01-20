/**
 A subclass of the Ingredient superclass
 @version 1.0 2012-03-02
 @author Vinoth Maruthalingam
 */
public class Vegetable extends Ingredient 
{
   public double carbsContent;  // Instance field specific to the Vegetable object
  
  public Vegetable(String name, String foodGroup, double carbsContent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.carbsContent = carbsContent;
  }
  
  public Vegetable(String name, String foodGroup, double carbsContent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.carbsContent = carbsContent;
  }
  
  public Vegetable(String name, String foodGroup, double carbsContent)
  {
    super(name, foodGroup);
    this.carbsContent = carbsContent;
  }
  
  public double getCarbsContent() // Accessor Method
  {
    return this.carbsContent;
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
            "Carbs Content: " + this.getCarbsContent() + '\n' + 
            this.displayKeyFeatures());
  }
}
