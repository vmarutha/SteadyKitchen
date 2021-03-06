/**
 The class responsible for most of the functions in the program 
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class FoodItemDatabase 
{
  static final String FOOD_ITEM_DATABASE = "FoodItemDatabase.txt"; 
  static int foodMenuChoice = 0;
  static int numberOfLines; // Number of lines in database text file
  static int numberOfFood; // Number of food initiall in database
  static final int NUMBEROFLINESPERFOOD = 6; //A constant due to the format of the text file
  static Ingredient[] arrayOfFoodItems;
  
  
  public static void main(String args[]) throws IOException
  {
    lineCounter(); // Obtains the number of food items in text file
    
    arrayOfFoodItems = new Ingredient[numberOfFood]; // Assigns references to Ingredient objects equivalent to the number of food in text file
    saveArrayOfFoodItems(loadArrayOfFoodItems(arrayOfFoodItems));
    
    displayIngredientsMenu(arrayOfFoodItems); // Display ingredient menu
    saveArrayOfFoodItems(loadArrayOfFoodItems(arrayOfFoodItems)); // Save when leaving exiting the main method
  }
  
  /**
   Displays the ingredients menu and takes in input for options
   @param the arrayOfFoodItems that is to be populated from the user and the data text file
   @return does not return value
   */
  public static void displayIngredientsMenu(Ingredient arrayOfFoodItems[]) throws IOException
  {
    System.out.println("1) Add an ingredient");
    System.out.println("2) Delete an ingredient");
    System.out.println("3) Search/Edit an ingredient");
    System.out.println("4) Sort ingredients");
    System.out.println("5) Total stock of particular food group");
    System.out.println("6) Return to main menu");
    
    try
    {
      Scanner input = new Scanner(System.in);   
      int foodMenuChoice = input.nextInt();
      input.nextLine();
      
      if (foodMenuChoice < 1 || foodMenuChoice > 6)
      {
        System.out.println("Invalid choice. Please choose again");
        displayIngredientsMenu(arrayOfFoodItems);
      }
      
      else if (foodMenuChoice == 1) // For adding an ingredient
      {
        System.out.println("Enter the name of the food: ");
        String sampleName = input.nextLine();
        System.out.println("Please choose from the following food groups: ");
        System.out.println("1) Diary ");
        System.out.println("2) Fats");
        System.out.println("3) Fruit");
        System.out.println("4) Grains");
        System.out.println("5) Meats");
        System.out.println("6) Sweets");
        System.out.println("7) Vegetables");
        
        int foodGroupChoice = input.nextInt();
        String sampleFoodGroup = null; 
        input.nextLine();
        if (foodGroupChoice == 1)
        {
          sampleFoodGroup = "Diary";
        }
        else if (foodGroupChoice == 2)
        {
          sampleFoodGroup = "Fats";
        }
        else if (foodGroupChoice == 3)
        {
          sampleFoodGroup = "Fruit";
        }
        else if (foodGroupChoice == 4)
        {
          sampleFoodGroup = "Grains";
        }
        else if (foodGroupChoice == 5)
        {
          sampleFoodGroup = "Meats";
        }
        else if (foodGroupChoice == 6)
        {
          sampleFoodGroup = "Sweets";
        }
        else if (foodGroupChoice == 7)
        {
          sampleFoodGroup = "Vegetables";
        }
        else
        {
          System.out.println("Invalid Input Option");
          displayIngredientsMenu(arrayOfFoodItems);
        }
        
        System.out.println("Enter the color of the food: ");
        String sampleColor = input.nextLine();
        System.out.println("Enter the stock of the food: ");
        int sampleStock = input.nextInt();
        input.nextLine();
        if (sampleStock < 0)
        {
          System.out.println("Cannot have negative stock");
          displayIngredientsMenu(arrayOfFoodItems);
        }
        System.out.println("Enter the mass of the food: ");
        double sampleMass = input.nextDouble();
        input.nextLine();
        if (sampleMass < 0)
        {
          System.out.println("Cannot have negative mass");
          displayIngredientsMenu(arrayOfFoodItems);
        }
        System.out.println("Enter the features of the food one by one (Enter x to stop): "); 
        String lineOfFeatures = "";
        boolean exit = false;
        while (!exit) // terminates if exit is true
        {
          String currentFeature = input.nextLine();
          if (currentFeature.equalsIgnoreCase("x"))
          {
            exit = true;
            if (lineOfFeatures.equals("") || lineOfFeatures.equals(null)) // If the user did not input key features
            {
              lineOfFeatures = "None";
            }
          }
          else
          {
            lineOfFeatures = lineOfFeatures + "," + currentFeature;
          }
        }
        addFood(sampleName, sampleFoodGroup, sampleColor, sampleStock, sampleMass, featuresArrayCreator(lineOfFeatures));
        System.out.println("************");
        System.out.println(sampleName + " has been added"); // Reload array
        lineCounter(); 
        System.out.println("************");
        String sampleArgs[] = {"a", "b"};
        main(sampleArgs);
      }
      else if (foodMenuChoice == 2) // For deleting an ingredient
      {
        try
        {
          System.out.println("Enter the name of the food: ");
          String deleteName = input.nextLine();
          int r = searchByName(deleteName, arrayOfFoodItems); 
          if (r < 0)
          {
            System.out.println("************");
            System.out.println(deleteName + " is not found in the database");
            System.out.println("************");
          }
          else
          {
            String deletionName = arrayOfFoodItems[r].getName();
            deleteFood(arrayOfFoodItems, arrayOfFoodItems[r]);
            System.out.println(deletionName + " is successfully deleted");
          }
          String sampleArgs[] = {"a", "b"};
          main(sampleArgs);
        }
        catch(Exception e)
        {
          System.out.println("ERROR MESSAGE: " + e.getMessage());
        }
      }
      else if (foodMenuChoice == 3) // For search/edit an ingredient
      {
        System.out.println("************");
        System.out.println("1) By name");
        System.out.println("2) By food group");
        System.out.println("3) Color");
        System.out.println("4) Key Features");
        int editChoice = input.nextInt();
        input.nextLine();
        if (editChoice == 1)
        {
          System.out.println("Enter the name of the ingredient: ");
          String searchName = input.nextLine();   
          int k = searchByName(searchName, arrayOfFoodItems); 
          if (k < 0)
          {
            System.out.println("************");
            System.out.println(searchName + " is not found in the database");
            System.out.println("************");
          }
          else
          {
            System.out.println("************");
            editMenu(arrayOfFoodItems[k], arrayOfFoodItems);
          }
        }
        else if (editChoice == 2)
        {
          System.out.println("************");
          searchByFoodGroup(arrayOfFoodItems);
        }
        else if (editChoice == 3)
        {
          System.out.println("Enter the color of the food: ");
          String searchColor = input.nextLine();
          searchByColor(searchColor, arrayOfFoodItems);
          
        }
        else if (editChoice == 4)
        {
          System.out.println("Enter a key feature of the food: ");
          String searchFeature = input.nextLine();
          System.out.println("************");
          searchByFeature(searchFeature, arrayOfFoodItems);
          System.out.println("************");
        }
        else
        {
          System.out.println("Please enter a valid choice");
        }
        String sampleArgs[] = {"a", "b"};
        main(sampleArgs);
      }
      else if (foodMenuChoice == 4) // For sorting ingredients
      {
        System.out.println("Sort in which order: ");
        System.out.println("1) By Stock");
        System.out.println("2) By Mass");
        System.out.println("3) Go Back");
        int sortChoice = input.nextInt();
        input.nextLine();
        if (sortChoice == 1) // Sort by stock
        {
          System.out.println("In the order of the lowest stock count to the greatest: ");
          System.out.println("************");
          sortByStock(arrayOfFoodItems);
          System.out.println("************");
        }
        else if (sortChoice == 2) // Sort by mass 
        {
          System.out.println("In the order of the lowest mass to the greatest: ");
          System.out.println("************");
          sortByMass(arrayOfFoodItems);
          System.out.println("************");
        }
        else if (sortChoice == 3)
        {
          System.out.println("************");
          System.out.println("Redirecting...");
          System.out.println("************");
        }
        else
        {
          System.out.println("Please enter a valid choice");
        }
        String sampleArgs[] = {"a", "b"};
        main(sampleArgs);
      }
      else if (foodMenuChoice == 5) // For displaying the total stock of an ingredient
      {
        System.out.println("The sum of stock in this food group is: " + totalSumOfFoodGroup(arrayOfFoodItems));
        String sampleArgs[] = {"a", "b"};
        main(sampleArgs);
      }
      else if (foodMenuChoice == 6)
      {
        MainMenu menu = new MainMenu();
        String menuArgs[] = {"a", "b"};
        menu.main(menuArgs);
      }
      else
      {
        System.out.println("Please enter a valid choice" + foodMenuChoice);
        String sampleArgs[] = {"a", "b"};
        MainMenu.main(sampleArgs);
      }
    }
    catch(Exception e)
    {
      System.out.println("Invalid input type. Please try again.");
      String sampleArgs[] = {"a", "b"};
      MainMenu.main(sampleArgs);
    }
  }
  
  /**
   Counts the number of lines in the data text file, which is the used to calculate how many ingredients there are in the database
   since it is known that every 6 lines in the data text file represents one ingredient
   @param no parameters
   @return no return
   */
  public static void lineCounter() throws IOException  // To count total number of lines in the text file
  {
    BufferedReader databaseReader = new BufferedReader(new FileReader(FOOD_ITEM_DATABASE));
    try
    {
      numberOfLines = 0;
      while (databaseReader.readLine() != null)
      {
        numberOfLines++;
      }
      numberOfFood = numberOfLines/NUMBEROFLINESPERFOOD;
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File could not be found");
    }
    catch (IOException e)
    {
      System.out.println("There is an error in the input file");
    }
  }
  
  /**
   First reads all information about each ingredient by using a 2D array. Then create objects for each of those information.
   The type of object is determined by the foodGroup, since Ingredient is the parent class, and the food groups are subclasses or 
   child classes to the Ingredient.
   @param the arrayOfFoodItems that is to be populated from the user and the data text file. When first boot, this array is null.
   @return the same arrayOfFoodItems that is populated with the information from the text file.
   */
  public static Ingredient[] loadArrayOfFoodItems(Ingredient arrayOfFoodItems[]) throws IOException
  {
    BufferedReader bufferedReaderRetrieve = new BufferedReader(new FileReader(FOOD_ITEM_DATABASE)); // the bufferedReader used for retrieving info
    
    String arrayOfIngredientInfo[][] = new String[numberOfFood][NUMBEROFLINESPERFOOD];
    
    String lineOfText = bufferedReaderRetrieve.readLine();
    
    for (int x = 0; x < numberOfFood; x++)
    {
      for (int y = 0; y < NUMBEROFLINESPERFOOD; y++)
      {
        if (lineOfText != null)
        {
          arrayOfIngredientInfo[x][y] = lineOfText;
          lineOfText = bufferedReaderRetrieve.readLine();
        }
      }
    }
    bufferedReaderRetrieve.close();
    
    for (int z = 0; z < numberOfFood; z++)
    {
      String arrayOfTokens[] = obtainArrayOfTokens(arrayOfIngredientInfo[z][1]);
      if (arrayOfTokens[0].equalsIgnoreCase("meats"))
      {
        arrayOfFoodItems[z] = new Meats(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], arrayOfTokens[1]); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
        arrayOfFoodItems[z].toString();
      }
      
      else if (arrayOfTokens[0].equalsIgnoreCase("sweets"))
      {
        arrayOfFoodItems[z] = new Sweets(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
      
      else if (arrayOfTokens[0].equalsIgnoreCase("diary"))
      {
        arrayOfFoodItems[z] = new Diary(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
      else if (arrayOfTokens[0].equalsIgnoreCase("fats"))
      {
        arrayOfFoodItems[z] = new Fats(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
      else if (arrayOfTokens[0].equalsIgnoreCase("grains"))
      {
        arrayOfFoodItems[z] = new Grains(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
      else if (arrayOfTokens[0].equalsIgnoreCase("vegetables"))
      {
        arrayOfFoodItems[z] = new Vegetable(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
      else if (arrayOfTokens[0].equalsIgnoreCase("fruits"))
      {
        arrayOfFoodItems[z] = new Fruits(arrayOfIngredientInfo[z][0], arrayOfIngredientInfo[z][1], Double.parseDouble(arrayOfTokens[1])); // Name, FoodGroup instantiation
        arrayOfFoodItems[z].setColor(arrayOfIngredientInfo[z][2]);
        arrayOfFoodItems[z].setStock(Integer.parseInt(arrayOfIngredientInfo[z][3]));
        arrayOfFoodItems[z].setMass(Double.parseDouble(arrayOfIngredientInfo[z][4]));
        String[] features = new String[tokenCounter(arrayOfIngredientInfo[z][5])];
        obtainFeaturesFromTokenizer(arrayOfIngredientInfo[z][5], features);
        arrayOfFoodItems[z].setKeyFeatures(features);
      }
    }
    return arrayOfFoodItems;
  }
  
  /**
   Saves the arrayOfFoodItems by printing the ingredient information to the text file
   @param the arrayOfFoodItems that is to be populated from the user and the data text file
   @return does not return value
   */
  public static void saveArrayOfFoodItems(Ingredient[] arrayOfFoodItems) throws IOException
  {
    PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, false)); // Take out the true for saving
    
    for (int a = 0; a < arrayOfFoodItems.length; a++) // changes number of food to arrayOfFoodItems.length
    {
      if (a == 0)
      {
        databasePrinter.print(arrayOfFoodItems[a].getName());
      }
      else
      {
        databasePrinter.print("\n" + arrayOfFoodItems[a].getName());
        
      }
      databasePrinter.print("\n" + arrayOfFoodItems[a].getFoodGroup());
      databasePrinter.print("\n" + arrayOfFoodItems[a].getColor());
      databasePrinter.print("\n" + arrayOfFoodItems[a].getStock());
      databasePrinter.print("\n" + arrayOfFoodItems[a].getMass());
      databasePrinter.print("\n" + arrayOfFoodItems[a].getFeaturesInStringTabbed());
    }
    databasePrinter.close();
  }
  
  /**
   Adds food to the arrayOfFoodItems
   @param all the fields of the Ingredient object, which are its name, food group, color, mass, stock, and features
   @return does not return value
   */
  public static void addFood(String name, String foodGroup, String color, int stock, double mass, String[] features) throws IOException
  {
    Scanner input = new Scanner(System.in);
    if (foodGroup.equalsIgnoreCase("Diary"))
    {
      System.out.println("Enter the Fat content: ");
      double fatContent = input.nextDouble();
      Ingredient sample = new Diary(name, foodGroup, fatContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + fatContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    }
    else if (foodGroup.equalsIgnoreCase("Fats"))
    {
      System.out.println("Enter the Fat content: ");
      double fatContent = input.nextDouble();
      Ingredient sample = new Fats(name, foodGroup, fatContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + fatContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    } 
    else if (foodGroup.equalsIgnoreCase("Fruit"))
    {
      System.out.println("Enter the Vitamin C Content: ");
      double vContent = input.nextDouble();
      Ingredient sample = new Fruits(name, foodGroup, vContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + vContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    } 
    else if (foodGroup.equalsIgnoreCase("Grains"))
    {
      System.out.println("Enter the Fibre Content: ");
      double fContent = input.nextDouble();
      Ingredient sample = new Grains(name, foodGroup, fContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + fContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    }
    else if (foodGroup.equalsIgnoreCase("Meats"))
    {
      System.out.println("Enter the Parent meat: ");
      String parent = input.nextLine();
      Ingredient sample = new Meats(name, foodGroup, parent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + parent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    }
    else if (foodGroup.equalsIgnoreCase("Sweets"))
    {
      System.out.println("Enter the Sugar Content: ");
      double sContent = input.nextDouble();
      Ingredient sample = new Grains(name, foodGroup, sContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + sContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    }
    else if (foodGroup.equalsIgnoreCase("Vegetables"))
    {
      System.out.println("Enter the Carbs Content: ");
      double cContent = input.nextDouble();
      Ingredient sample = new Vegetable(name, foodGroup, cContent, mass, color, stock);
      sample.setKeyFeatures(features);
      PrintWriter databasePrinter = new PrintWriter(new FileWriter(FOOD_ITEM_DATABASE, true)); 
      
      if (numberOfFood == 0) // Empty file
      {
        databasePrinter.print(sample.getName());
      }
      else
      {
        databasePrinter.print("\n" + sample.getName());
      }
      databasePrinter.print("\n" + sample.getFoodGroup() + "\t" + cContent);
      databasePrinter.print("\n" + sample.getColor());
      databasePrinter.print("\n" + sample.getStock());
      databasePrinter.print("\n" + sample.getMass());
      databasePrinter.print("\n" + sample.getFeaturesInStringTabbed());
      databasePrinter.close();
    }
  }
  
  /**
   This method creates an array of features by using StringTokenizer that is comma delimited
   @param the lineOfFeatures that contains all the features of the object that are comma delimited
   @return an array of String with each index representing a feature.
   */
  public static String[] featuresArrayCreator(String lineOfFeatures)
  {
    StringTokenizer tokenizerForCounting = new StringTokenizer(lineOfFeatures, ",");
    int counter = 0;
    while (tokenizerForCounting.hasMoreTokens())
    {
      tokenizerForCounting.nextToken();
      counter++;
    }
    
    String sampleFeatures[] = new String[counter];
    StringTokenizer tokenizer = new StringTokenizer(lineOfFeatures, ",");
    int element = 0;
    while (tokenizer.hasMoreTokens())
    {
      sampleFeatures[element] = tokenizer.nextToken();
      element++;
    }
    return sampleFeatures;
  }
  
  /**
   Searches for an ingredient by its name field
   @param the name of the target ingredient, and array of ingredients that will be searched
   @return an integer value which is the index of the target ingredient in the given ingredient array. If it returns -1, then the target ingredient is not in the given ingredient array.
   */
  public static int searchByName(String sampleName, Ingredient[] ingredients) // This is linear search
  {
    int location = -1;
    boolean found = false;
    for (int i = 0; i < ingredients.length && !found; i++)
    {
      if (ingredients[i].getName().equalsIgnoreCase(sampleName))
      {
        location = i;
        found = true;
      }
    }
    return location;
  }
  
  /**
   Displays a list of ingredients that have the same foodGroup field
   @param the food group that is being searched for and the array of ingredients which will be searched
   @return does not return value
   */
  public static void searchByFoodGroup(Ingredient[] ingredients) // Must narrow down options, nodes
  {
    LinkedListIngredient searchedList = new LinkedListIngredient();
    Scanner input = new Scanner(System.in);
    System.out.println("Please choose from the following food groups: ");
    System.out.println("1) Diary ");
    System.out.println("2) Fats");
    System.out.println("3) Fruits");
    System.out.println("4) Grains");
    System.out.println("5) Meats");
    System.out.println("6) Sweets");
    System.out.println("7) Vegetables");
    String sampleFoodGroup = null;
    int foodGroupChoice = input.nextInt();
    input.nextLine();
    if (foodGroupChoice == 1)
    {
      sampleFoodGroup = "Diary";
    }
    else if (foodGroupChoice == 2)
    {
      sampleFoodGroup = "Fats";
    }
    else if (foodGroupChoice == 3)
    {
      sampleFoodGroup = "Fruits";
    }
    else if (foodGroupChoice == 4)
    {
      sampleFoodGroup = "Grains";
    }
    else if (foodGroupChoice == 5)
    {
      sampleFoodGroup = "Meats";
    }
    else if (foodGroupChoice == 6)
    {
      sampleFoodGroup = "Sweets";
    }
    else if (foodGroupChoice == 7)
    {
      sampleFoodGroup = "Vegetables";
    }
    else
    {
      System.out.println("Invalid Input Option");
    }
    
    for (int k = 0; k<ingredients.length;k++)
    {
      String[] arrayOfTokens = obtainArrayOfTokens(ingredients[k].getFoodGroup());
      
      if (arrayOfTokens[0].equalsIgnoreCase(sampleFoodGroup))
      {
        searchedList.addAtFront(ingredients[k]);
      }
    }
    if (searchedList.header == null)
    {
      System.out.println("No foods found with food group of " + sampleFoodGroup);
    }
    else
    {
      System.out.println("************");
      System.out.println(searchedList.printData());
      System.out.println("************");
    }
  }
  
  
  public static int totalSumOfFoodGroup(Ingredient[] ingredients) // Must narrow down options, nodes
  {
    LinkedListIngredient sumList = new LinkedListIngredient();
    Scanner input = new Scanner(System.in);
    System.out.println("Please choose from the following food groups: ");
    System.out.println("1) Diary ");
    System.out.println("2) Fats");
    System.out.println("3) Fruits");
    System.out.println("4) Grains");
    System.out.println("5) Meats");
    System.out.println("6) Sweets");
    System.out.println("7) Vegetables");
    String sampleFoodGroup = null;
    int foodGroupChoice = input.nextInt();
    input.nextLine();
    if (foodGroupChoice == 1)
    {
      sampleFoodGroup = "Diary";
    }
    else if (foodGroupChoice == 2)
    {
      sampleFoodGroup = "Fats";
    }
    else if (foodGroupChoice == 3)
    {
      sampleFoodGroup = "Fruits";
    }
    else if (foodGroupChoice == 4)
    {
      sampleFoodGroup = "Grains";
    }
    else if (foodGroupChoice == 5)
    {
      sampleFoodGroup = "Meats";
    }
    else if (foodGroupChoice == 6)
    {
      sampleFoodGroup = "Sweets";
    }
    else if (foodGroupChoice == 7)
    {
      sampleFoodGroup = "Vegetables";
    }
    else
    {
      System.out.println("Invalid Input Option");
    }
    
    for (int k = 0; k < ingredients.length;k++)
    {
      String[] arrayOfTokens = obtainArrayOfTokens(ingredients[k].getFoodGroup());
      
      if (arrayOfTokens[0].equalsIgnoreCase(sampleFoodGroup))
      {
        sumList.addAtFront(ingredients[k]);
      }
    }
    return (sumList.sumOfStock(sumList.header));
  }
  /**
   Displays a list of ingredients that have the same color field
   @param the color that is being searched for and the array of ingredients which will be searched
   @return does not return value
   */
  public static void searchByColor(String color, Ingredient[] ingredients)
  {
    LinkedListIngredient searchedList = new LinkedListIngredient();
    
    for (int k = 0; k<ingredients.length;k++)
    {
      if (ingredients[k].getColor().equalsIgnoreCase(color))
      {
        searchedList.addAtFront(ingredients[k]);
      }
    }
    if (searchedList.header == null)
    {
      System.out.println("No results");
    }
    else
    {
      System.out.println("************");
      System.out.println(searchedList.printData());
      System.out.println("************");
    }
    
  }
  
  public static void searchByFeature(String feature, Ingredient[] ingredients)
  {
    LinkedListIngredient searchedList = new LinkedListIngredient();
    
    for (int k = 0; k<ingredients.length;k++)
    {
      String featuresArray[] = ingredients[k].getFeatures();
      for(int z = 0; z < featuresArray.length;z++)
      {
        if (featuresArray[z].equalsIgnoreCase(feature))
        {
          searchedList.addAtFront(ingredients[k]);
        }
      }
    }
    System.out.println("************");
    System.out.println(searchedList.printData());
    System.out.println("************");
  }
  
  /**
   Sorts the list of ingredients according to their stock field 
   @param the array of ingredients which being sorted
   @return does not return value
   */
  public static void sortByStock(Ingredient[] ingredients)
  {
    int min;
    Ingredient temp = new Ingredient(null, null);
    
    for (int index = 0; index < ingredients.length; index++)
    {
      min = index;
      for (int scan = index + 1; scan < ingredients.length; scan++)
      {
        if (ingredients[scan].getStock() < ingredients[min].getStock())
        {
          min = scan;
        }
      }
      //Swap the values
      temp = ingredients[min];
      ingredients[min] = ingredients[index];
      ingredients[index] = temp;
    }
    printArrayOfIngredients(ingredients);
  }
  
  /**
   Sorts the list of ingredients according to their mass field 
   @param the array of ingredients which being sorted
   @return does not return value
   */
  public static void sortByMass(Ingredient[] ingredients)
  {
    int min;
    Ingredient temp = new Ingredient(null, null);
    
    for (int index = 0; index < ingredients.length; index++)
    {
      min = index;
      for (int scan = index + 1; scan < ingredients.length; scan++)
      {
        if (ingredients[scan].getMass() < ingredients[min].getMass())
        {
          min = scan;
        }
      }
      //Swap the values
      temp = ingredients[min];
      ingredients[min] = ingredients[index];
      ingredients[index] = temp;
    }
    printArrayOfIngredients(ingredients);
  }
  
  /**
   Creates a linkedlist of ingredients, then deletes a node pertaining to the target ingredient to be deleted.
   @param the array of ingredients, the target ingredient that is to be deleted
   @return does not return value
   */
  public static void deleteFood(Ingredient[] ingredients, Ingredient sample) throws IOException // Deletes food by using linked list
  {
    LinkedListIngredient ingredientList = new LinkedListIngredient();
    for (int k = 0; k < ingredients.length; k++)
    {
      ingredientList.addAtFront(ingredients[k]);
    }
    ingredientList.delete(sample);
    saveArrayOfFoodItems(ingredientList.arrayOfIngredientsFromList());
  }
  
  /**
   Outputs to the console the name of ingredients current existing.
   @param the array of ingredients
   @return does not return value
   */
  public static void printArrayOfIngredients(Ingredient[] ingredients)
  {
    for (int i = 0; i < ingredients.length; i++)
    {
      System.out.println(ingredients[i].getName());
    }
  }
  
  /**
   The menu in which specific actions can be taken on a specific ingredient, such as modifying, displaying, or deleting.
   @param the specific ingredient, the array of ingredients
   @return does not return value
   */
  public static void editMenu(Ingredient sample, Ingredient[] arrayOfFood)
  {
    Scanner input = new Scanner(System.in);
    
    try
    {
      System.out.println("What would you like to do?");
      System.out.println("1) Display all info");
      System.out.println("2) Change food group");
      System.out.println("3) Change mass");
      System.out.println("4) Change color");
      System.out.println("5) Change stock");
      System.out.println("6) Change features");
      System.out.println("7) Delete");
      System.out.println("8) Go Back");
      int choice = input.nextInt();
      input.nextLine();
      if (choice == 1)
      {
        System.out.println(sample.toString());
        editMenu(sample, arrayOfFood);
      }
      else if (choice == 2)
      {
        System.out.println("Please choose from the following food groups: ");
        System.out.println("1) Diary ");
        System.out.println("2) Fats");
        System.out.println("3) Fruit");
        System.out.println("4) Grains");
        System.out.println("5) Meats");
        System.out.println("6) Sweets");
        System.out.println("7) Vegetables");
        
        int foodGroupChoice = input.nextInt();
        String sampleFoodGroup = null; 
        input.nextLine();
        if (foodGroupChoice == 1)
        {
          System.out.println("Enter the fat content");
          double fContent = input.nextDouble();
          sampleFoodGroup = "Diary";
          sample = new Diary(sample.getName(), sampleFoodGroup, fContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else if (foodGroupChoice == 2)
        {
          sampleFoodGroup = "Fats";
          System.out.println("Enter the fat content");
          double fContent = input.nextDouble();
          sample = new Fats(sample.getName(), sampleFoodGroup, fContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
          
        }
        else if (foodGroupChoice == 3)
        {
          sampleFoodGroup = "Fruit";
          System.out.println("Enter the vitamin C content");
          double vContent = input.nextDouble();
          sample = new Fruits(sample.getName(), sampleFoodGroup, vContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else if (foodGroupChoice == 4)
        {
          sampleFoodGroup = "Grains";
          System.out.println("Enter the fibre content");
          double fContent = input.nextDouble();
          sample = new Grains(sample.getName(), sampleFoodGroup, fContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else if (foodGroupChoice == 5)
        {
          sampleFoodGroup = "Meats";
          System.out.println("Enter the parent meat");
          String parent = input.nextLine();
          sample = new Meats(sample.getName(), sampleFoodGroup, parent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else if (foodGroupChoice == 6)
        {
          sampleFoodGroup = "Sweets";
          System.out.println("Enter the sugar content");
          double sContent = input.nextDouble();
          sample = new Sweets(sample.getName(), sampleFoodGroup, sContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else if (foodGroupChoice == 7)
        {
          sampleFoodGroup = "Vegetables";
          System.out.println("Enter the carbs content");
          double cContent = input.nextDouble();
          sample = new Vegetable(sample.getName(), sampleFoodGroup, cContent, sample.getMass(), sample.getColor(), sample.getStock(), sample.getFeatures());
          sample.setFoodGroup(sampleFoodGroup);
        }
        else
        {
          System.out.println("Invalid Input Option");
          editMenu(sample, arrayOfFood);
        }
        System.out.println("Successfully updated");
        editMenu(sample, arrayOfFood);
      }
      else if (choice == 3)
      {
        System.out.println("Enter the mass");
        double sampleMass = input.nextDouble();
        if (sampleMass < 0)
        {
          System.out.println("Cannot have negative mass");
        }
        else
        {
          sample.setMass(sampleMass);
          System.out.println("Successfully updated");
        }
        
        editMenu(sample, arrayOfFood);
      }
      else if (choice == 4)
      {
        System.out.println("Enter the color");
        String sampleColor = input.nextLine();
        sample.setColor(sampleColor);
        System.out.println("Successfully updated");
      }
      else if (choice == 5)
      {
        System.out.println("Enter the stock");
        int sampleStock = input.nextInt();
        if (sampleStock < 0)
        {
          sample.setStock(sampleStock);
          System.out.println("Successfully updated");
        }
        else
        {
          System.out.println("Cannot have negative stock");
        }
        editMenu(sample, arrayOfFood);
      }
      else if (choice == 6)
      {
        System.out.println("How many features: ");
        int limit = input.nextInt();
        input.nextLine();
        String sampleFeatures[] = new String[limit];
        for (int k = 0; k< limit; k++)
        {
          System.out.println("Enter feature " + (k+1) + ":");
          sampleFeatures[k] = input.nextLine();
        }
        sample.setKeyFeatures(sampleFeatures);
        System.out.println("Successfully updated");
        editMenu(sample, arrayOfFood);
      }
      else if (choice == 7)
      {
        System.out.println("Are you sure you want to delete (y/n): " + sample.getName());
        if (input.nextLine().equalsIgnoreCase("y"))
        {
          deleteFood(arrayOfFood, sample);
          editMenu(sample, arrayOfFood);
          System.out.println("Successfully deleted");
        }
        else if (input.nextLine().equalsIgnoreCase("n"))
        {
          editMenu(sample, arrayOfFood);
        }
        else
        {
          System.out.println("Invalid input");
          editMenu(sample, arrayOfFood);
        }
      }
      else if (choice == 8) // Go back, do nothing
      {
        
      }
      else
      {
        System.out.println("Invalid option. Please try again");
        editMenu(sample, arrayOfFood);
      }
      saveArrayOfFoodItems(loadArrayOfFoodItems(arrayOfFood));
    }
    catch(Exception e)
    {
      System.out.println("Invalid input type. " + e.getMessage());
    }
  }
  
  /**
   Counts the number of tokens that are seperated by tabs in a given String
   @param the String of text
   @return the number of tokens in that text
   */
  public static int tokenCounter(String lineOfText)
  {
    StringTokenizer tokenizerForCountingTokens = new StringTokenizer(lineOfText, "\t");
    
    int numberOfTokens = 0;
    
    while(tokenizerForCountingTokens.hasMoreTokens())
    {
      tokenizerForCountingTokens.nextToken();
      numberOfTokens++;
    }
    return numberOfTokens;
  }
  
  /**
   Assigns each index of an array of type String to the each token in the lineOfText
   @param the String containing tokens, the array to be populated with the tokens
   @return the populated features array
   */
  public static String[] obtainFeaturesFromTokenizer(String lineOfText, String[] features)
  {     
    StringTokenizer tokenizer = new StringTokenizer(lineOfText);
    
    int element = 0;
    
    while(tokenizer.hasMoreTokens())
    {
      features[element] = tokenizer.nextToken();
      element++;
    }
    return features;
  }
  
  /**
   Counts the number of tokens in a given lineOfText, then creates an array with dimension lineOftext, and populates this array with the tokens of lineOfText
   @param the lineOfText containing the tokens, which are tab delimited
   @return the array of tokens
   */
  public static String[] obtainArrayOfTokens(String lineOfText)
  {
    StringTokenizer tokenizer = new StringTokenizer(lineOfText);
    String arrayOfTokens[] = new String[tokenCounter(lineOfText)];
    for (int i = 0; i < tokenCounter(lineOfText); i++)
    {
      int element = 0;
      while(tokenizer.hasMoreTokens())
      {
        arrayOfTokens[element] = tokenizer.nextToken();
        element++;
      }
    }
    return arrayOfTokens;
  }
}
