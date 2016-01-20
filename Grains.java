/**
 A subclass of the Ingredient superclass
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */

public class Grains extends Ingredient
{
  public double fibreContent;  // Instance field specific to Grains object.
  
  public Grains(String name, String foodGroup, double fibreContent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.fibreContent = fibreContent;
  }
  
  public Grains(String name, String foodGroup, double fibreContent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.fibreContent = fibreContent;
  }
  
  public Grains(String name, String foodGroup, double fibreContent)
  {
    super(name, foodGroup);
    this.fibreContent = fibreContent;
  }
  
  public double getFibreContent() // Accessor Method
  {
    return this.fibreContent;
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
            "Fibre Content: " + this.getFibreContent() + '\n' + 
            this.displayKeyFeatures());
  }
}
