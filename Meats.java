/**
 A subclass of the Ingredient superclass
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
public class Meats extends Ingredient
{
  public String parent;  // Instance field specific to Meats object. It is the name of the animal of the meat.
  
  public Meats(String name, String foodGroup, String parent, double mass, String color, int stock)
  {
    super(name, foodGroup, mass, color, stock);
    this.parent = parent;
  }
  
  public Meats(String name, String foodGroup, String parent, double mass, String color, int stock, String[] features)
  {
    super(name, foodGroup, mass, color, stock, features);
    this.parent = parent;
  }
  
  public Meats(String name, String foodGroup, String parent)
  {
    super(name, foodGroup);
    this.parent = parent;
  }
  
  public String getParent() // Accessor Method
  {
    return this.parent;
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
            "Parent: " + this.getParent() + '\n' + 
            this.displayKeyFeatures());
  }
}
