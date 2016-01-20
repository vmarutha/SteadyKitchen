/**
 Node class with a node having two fields, an Ingredient object and a pointer to the next IngredientNode
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
public class IngredientNode
{
  Ingredient ingredient;
  IngredientNode next;
  
  public IngredientNode(Ingredient ingredient, IngredientNode n) // Constructor method
  {
    this.ingredient = ingredient;
    this.next = n;
  }
  
  // Accessor methods
  public Ingredient getIngredient()
  {
    return this.ingredient;
  }
  
  public String getIngredientName()
  {
    return this.ingredient.getName();
  }
  
  public IngredientNode getNext()
  {
    return next;
  }
  
  // Mutator methods
  public void setIngredient(Ingredient ingredient)
  {
    this.ingredient = ingredient;
  }
  
  public void setNext(IngredientNode r)
  {
    this.next = r;
  }
}
