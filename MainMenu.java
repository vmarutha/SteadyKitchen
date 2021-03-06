/**
 The class that is executed first. It is the main menu of the program.
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
import java.util.Scanner;
import java.io.*;

  /**
  The main menu of the program. It should be exucuted first
   */
public class MainMenu
{
  public static void main(String args[]) throws IOException
  {   
    displayMainMenu();
  }
  
  public static void displayMainMenu()
  {
    Scanner input = new Scanner(System.in);
    System.out.println("Welcome to La Primavera Kitchen Planner");
    System.out.println("-------------------------------------------");
    System.out.println("1) Ingredients submenu");
    System.out.println("2) Exit");
    System.out.println("-------------------------------------------");
    System.out.println("Enter your choice: ");
    
    try
    {
      int choice = input.nextInt();
      if (choice < 1 || choice > 2) // If wrong choice input
      {
        System.out.println("Invalid choice. Please choose again");
        displayMainMenu();
      }
      
      else if (choice == 1) // For ingredients submenu
      {
        String foodArgs[] = {"a", "b"}; // Creates an argument for the main menu of the FoodItemDatabase class
        
        FoodItemDatabase foodItemDatabase = new FoodItemDatabase();
        foodItemDatabase.main(foodArgs);
      }
      else if (choice == 2) 
      {
        System.out.println("Have a nice day!"); // Friendly message when quiting
      }
    }
    catch(Exception e)
    {
      String flush = input.nextLine();
      System.out.println("Invalid input type. Please try again"); //If invalid input
      displayMainMenu();
    }
  }
}
