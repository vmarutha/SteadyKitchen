/**
 A subclass of the Ingredient superclass
 @version 1.0 2012-03-02
 @author Vinoth Maruthalingam
 */

public class Sweets extends Ingredient
{
  public double sugarContent;  // Instance field specific to Sweets object
  
  public Sweets(String name, String foodGroup, double sugarContent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.sugarContent = sugarContent;
  }
  
  public Sweets(String name, String foodGroup, double sugarContent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.sugarContent = sugarContent;
  }
  
  public Sweets(String name, String foodGroup, double sugarContent)
  {
    super(name, foodGroup);
    this.sugarContent = sugarContent;
  }
  
  public double getSugarContent() // Accessor Method
  {
    return this.sugarContent;
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
            "Sugar Content: " + this.getSugarContent() + '\n' + 
            this.displayKeyFeatures());
  }
}