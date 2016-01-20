/**
 A LinkedListIngredient class in which a LinkedListIngredient object can be created
 @version 1.0 2015-03-02
 @author Vinoth Maruthalingam
 */
public class LinkedListIngredient
{
  IngredientNode header; // Instance fields
  
  public LinkedListIngredient() // Constructor
  {
    header = null;
  }
  
  public void addAtFront(Ingredient info)
  {
    if (header == null)
    {
      header = new IngredientNode(info, null);
    }
    else
    {
      IngredientNode temp = header; // temp gets the value of the address that header is pointing to.Also known as the first node
      header = new IngredientNode(info, temp); // header now points to a new node with data info and pointer temp which was the first node
    }
  }
  
  public void deleteAtFront()
  {
    if (header != null && header.getNext() != null)
    {
      header = header.getNext(); 
      //header has null data but some reference point. The get symbol '=' changes the reference point. header.getNext() returns the second node in the list
    }
  }
  
  public void delete(Ingredient n)
  {
    IngredientNode current = header;
    IngredientNode previous = null;
    
    while (current != null)
    {
      if (current.ingredient.equals(n))
      {
        if (previous == null) // This means that the first node is the target node
        {
          header = header.getNext();
          previous = current;
        }
        else
        {
          previous.setNext(current.getNext());
          current = null;
        }
      }
      else
      {
        previous = current;
        current = current.next;
      }
    } 
  }
  
  
  public void push(Ingredient a) // For stacks, where a node of data a is inserted at the end of the list
  {
    if (header == null)
    {
      header = new IngredientNode(a, null); // header points to the new node
    }
    else
    {
      IngredientNode current = header;
      while (current.getNext() != null) // second node doesn't equal to null
      {
        current = current.getNext(); // second node gets third node...third node gets fourth node...until the LAST NODE (current.getNext() == null)
      }
      current.setNext(new IngredientNode(a, null)); //Set reference for last node to the new node
    }
  }
  
  public void pop() // For stacks, where a node at the end of the list is deleted
  {
    if (header.getNext() == null) // if there is not second node AKA one node in the list
    {
      header = null; // header points to nothing
    }
    else if (header.getNext() != null) //If there is a second node AKA more than two nodes in the list 
    {
      header = header.getNext(); // header points to the second node in the list.
    }
  }
  
  public void queue(Ingredient b) // For queues, where a node of data b is added at the end of the list. SAME AS PUSH
  {
    push(b);
  }
  
  public void dequeue() // For queues, where a node at the front is deleted. SAME AS deleteAtFront()
  {
    deleteAtFront();
  }
  
  public String printData()
  {
    IngredientNode current = header;
    String s = "";
    while (current!= null)
    {
      s = s + "\n" + current.getIngredient();
      current = current.getNext();
    }
    return s;
  }
  
  public Ingredient[] arrayOfIngredientsFromList()
  {
    IngredientNode current = header;
    Ingredient array[] = new Ingredient[this.size()];
    for (int i = 0; i < this.size(); i++)
    {
      array[i] = current.ingredient;
      current = current.next;
    }
    return array;
  }
  
  public int size()
  {
    int size = 0;
    IngredientNode current = header;
    while(current.next != null)
    {
      current = current.next;
      size++;     
    }
    return size+1;
  }
  
   public int sumOfStock(IngredientNode n)
  {
    if (n == null)
    {
      return 0;
    }
    else
    {
      return n.getIngredient().getStock() + sumOfStock(n.getNext());
    }
  }
}
