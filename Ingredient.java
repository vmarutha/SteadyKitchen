/**
 Super class to the Diary, Fats, Fruits, Grains, Meats, Sweets, and Vegetable subclasses
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
public class Ingredient
{
  String name; // Instance fields of the Ingredient object
  String foodGroup;
  String color;
  double mass;
  int stock;
  String[] features;
  
  public Ingredient(String name, String foodGroup, double mass, String color, int stock)
  {
    this.name = name;
    this.foodGroup = foodGroup;
    this.mass = mass;
    this.color = color;
    this.stock = stock;
    this.features = null;
  }
  
  public Ingredient(String name, String foodGroup, double mass, String color, int stock, String[] features)
  {
    this.name = name;
    this.foodGroup = foodGroup;
    this.mass = mass;
    this.color = color;
    this.stock = stock;
    this.features = features;
  }
  
  public Ingredient(String name, String foodGroup)
  {
    this.name = name;
    this.foodGroup = foodGroup;
    mass = 0;
    color = null;
    stock = 0;
    this.features = null;
  }
  
  //Accessor methods
  public String getName()
  {
    return this.name;
  }
  
  public String getFoodGroup()
  {
    return this.foodGroup;
  }
  
  public String getColor()
  {
    return this.color;
  }
  
  public double getMass()
  {
    return this.mass;
  }
  
  public int getStock()
  {
    return this.stock;
  }
  
  public String[] getFeatures()
  {
    return this.features;
  }
  
   /**
   Returns the features of the object as a single String with features seperated by a tab. It is used for saving the ingredient to the text file. 
   @param no paramters
   @return the String containing the features of the object
   */
  public String getFeaturesInStringTabbed()
  {
    String featuresInStringTabbed = "";
    for (int i = 0; i < this.features.length; i++)
    {
      if (i == 0)
      {
        featuresInStringTabbed = features[i];
      }
      else
      {
        featuresInStringTabbed = featuresInStringTabbed + "\t" + features[i];
      }
    }
    return featuresInStringTabbed;
  }
  
  // Mutator methods
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setFoodGroup(String foodGroup)
  {
    this.foodGroup = foodGroup;
  }
  
  public void setColor(String color)
  {
    this.color = color;
  }
  
  public void setMass(double mass)
  {
    this.mass = mass;  
  }
  
  public void setStock(int stock)
  {
    this.stock = stock;
  }
  
  public void setKeyFeatures(String[] features)
  {
    this.features = features;
  } 
  
  public String toString()
  {
    return ("Name: " + this.getName() + '\n' +
            "Food Group: " + this.getFoodGroup() + '\n' +
            "Color: " + this.getColor() + '\n' +
            "Mass: " + this.getMass() + '\n' +
            "Stock: " + this.getStock() + '\n' + 
            this.displayKeyFeatures());
  }
  
   /**
   Returns a string that is ready to be used by the FoodItemDataBase class for displaying the features of a specific ingredient
   @param no paramters
   @return the String containing the features of the object
   */
  public String displayKeyFeatures()
  {
    String listOfKeyFeatures = "";
    if (this.features == null)
    {
      listOfKeyFeatures = "No key features";
    }
    else
    {
      for (int i = 0; i < this.features.length; i++)
      {
        listOfKeyFeatures = (listOfKeyFeatures + "Key Features: " + this.features[i] + '\n');
      }
    }    
    return listOfKeyFeatures;
  }
}
