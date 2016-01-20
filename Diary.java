/**
 A subclass of the Ingredient superclass
 @version 1.0 2012-03-02
 @author Vinoth Maruthalingam
 */
public class Diary extends Ingredient 
{
   public double fatContent; // Instance field specific to Diary object
  
  public Diary(String name, String foodGroup, double fatContent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.fatContent = fatContent;
  }
  
  public Diary(String name, String foodGroup, double fatContent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.fatContent = fatContent;
  }
  
  public Diary(String name, String foodGroup, double fatContent)
  {
    super(name, foodGroup);
    this.fatContent = fatContent;
  }
  
  public double getfatContent() // Accessor methods
  {
    return this.fatContent;
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
            "Fat Content: " + this.getfatContent() + '\n' + 
            this.displayKeyFeatures());
  }
}
