import java.util.ArrayList;
import java.lang.Character;

public class Letterbank
{
   protected static ArrayList<Character> guessed;
   
   public Letterbank()
   {
      guessed = new ArrayList<Character>();
   }
   
   public String toString()
   {
      String s = "";
      for(int i = 0; i<guessed.size(); i++)
      {
      s+=guessed.get(i);
      }
      return "So far, you have guessed these letters:" + s;
   }
   
   public void addChar(char c)
   {
      guessed.add(Character.valueOf(c));
   }
}