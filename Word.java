import java.lang.String;
public class Word
{
   /*instance variables*/
   protected static String word;
   protected static char[] guessWord;
   
   /*constructor*/
   public Word(String w)
   {
      word=w;
      guessWord = new char[word.length()];
      for (int i=0; i<word.length();i++)
      {
         guessWord[i] = '-';//this does not account for spaces in words
      }
   }
   
   /*methods*/
   public String toString()
   {
      return (String.valueOf(guessWord));
   }
   
   public boolean processChar(char c) //if guesssed letter correctly return true..if not return false
   {
      for (int i=0; i<word.length();i++)
      {
         if(c == word.charAt(i))
          return true;
      }
      return false;
   }
   
   public boolean solved()
   {
      String temp = String.valueOf(guessWord);
      String check = temp.trim();
      if(check.equals(word))
            return true;
      else if(!check.equals(word))
            return false;
      else
         return false;
   }
   
   public void showChar(char c)
   {
      for(int i=0;i<word.length();i++)
          {
             if(word.charAt(i)==c)
             {
                guessWord[i] = Character.valueOf(c);
             }
          }
   }
}