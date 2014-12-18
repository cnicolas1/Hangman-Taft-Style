import java.util.Scanner;

public class Hangman
{
   public static Scanner in = new Scanner(System.in);
   public static Letterbank human_guess = new Letterbank();
   public static Gallows guess_remaining = new Gallows();
   public static int rant_num = 0;
   public static String name="";
   public static Word wordToGuess;
   
   public static void main(String args[])
   {
      System.out.println("Welcome to Hangman!");
      System.out.println("What is your name?");
      name = in.nextLine();
      
      System.out.println("Hello, "+name+"! How are you?");
      System.out.println("I'm Stan the Computer, and I hate repeating myself.");
      System.out.println("So when I ask you a question, you better have been paying attention.");
      
      askExperience();                                //to personalise it, we'll ask the user if s/he played before.
      playGame();
      askToReplay();                                  /*after finishing the user may choose to replay*/
   }
   
   
   public static void playGame()
   {
      Scanner entered = new Scanner(System.in);
      pickCategory(); //we'll also ask if the user gets to choose from three categories
      
      do                               /*if the word is not solved, this is what ensues*/
      {                                                    /*Here we print the information needed by the user.*/
         System.out.println(guess_remaining);                /*the # of guesses left*/
         System.out.println(human_guess);                        /*the letters guessed thus far*/
         System.out.println(wordToGuess);                      /*and a representation of the word with the letters s/he guessed*/
         
         System.out.println("Please guess a letter.");       /*Then we prompt the user to guess a letter*/
         char char_guessed = entered.nextLine().charAt(0);                  /*and process that letter to see if it's in the word*/
         human_guess.addChar(char_guessed);
         
         if(wordToGuess.processChar(char_guessed))
         {
            wordToGuess.showChar(char_guessed);
         }
         else
         {                                                  
            System.out.println("Sorry! That is not a letter in this word.");
            Gallows.guessesLeft--; //everytime the player guesses incorrectly they lose how many guesses they have left
         }
         if((Gallows.guessesLeft==0)&&(!wordToGuess.solved()))  //if there are no guesses left & the word isnt solved the user loses
         {
            System.out.println(name+", you lost.");
            continue;
         }
      }while((wordToGuess.solved()==false)&&(Gallows.guessesLeft>0));
      
      
      if (wordToGuess.solved())                              /*if the word is solved the user wins*/     
      {
         System.out.println("Yay! You figured it out!");
      }
   }
   
   
   public static void pickCategory() //this method allows the user to choose a category of words to guess from
   {
      System.out.println("Pick a category. Enter '1' for TAFT DORMS/LINGO, '2' for COMPUTER SCIENCE, or '3' for FACULTY SURNAMES.");
      int category = in.nextInt(); 
      
      //depending on the number, a different word will be guessed from a different wordbank
      if(category==1) 
      {
         wordToGuess = new Word(Wordbank.pickWord(Wordbank.taftWordBank));
      }
      else if(category==2)
      {
         wordToGuess = new Word(Wordbank.pickWord(Wordbank.apcsWordBank));
      }
      else if(category==3)
      {
         wordToGuess = new Word(Wordbank.pickWord(Wordbank.facWordBank));
      }
      else              //otherwise the computer rants
      {
         System.out.println("That's an invalid input.");
         rant_num++;
         rant();
         pickCategory();
      }  
   }
   
   
   public static void askExperience() //this method is used to ask the experience level of the user
   {
      System.out.println("Have you played Hangman before? Enter 'Yes' or 'No' without quotes, commas, or any other special characters.");
      String experience = in.nextLine();
      if(experience.compareToIgnoreCase("yes")==0)              //if the player has played hangman we skip the rules of the game
      {
         System.out.println("Ok! Let's play!");
      }
      else if(experience.compareToIgnoreCase("no")==0)          //if the player hasn't played it before we tell them the rules of the game
      {
         System.out.println("That's okay! The game of Hangman is as easy as pie! Here are the rules.");
         System.out.println("There are two players. The first player thinks of a word /n while the second player guesses what the word is.");
         System.out.println("Usually the second player guesses letter by letter; however, the second player only has a limited number of guesses.");
         System.out.println("If the second player doesn't guess the word before the amount of guesses are up, s/he loses!");
         System.out.println("In this case I'll be the first player and you'll be the second player.");
      }
      else                                         //otherwise we rant
      {
         System.out.println("That's an invalid input.");
         rant_num++;
         rant();
         askExperience();
      }  
   }
   
   
   public static void askToReplay() //this method asks the user if s/he would like to replay the game
   {
      Scanner temp = new Scanner(System.in);
      System.out.println("Would you like to play again? Enter 'Yes' or 'No'.");
      String replay = temp.nextLine();
      if(replay.compareToIgnoreCase("yes")==0)                  //if yes s/he does
      {
         System.out.println("Yay!");
         Letterbank.guessed.clear();
         Gallows.guessesLeft=10;
         playGame();
      }
      else if(replay.compareToIgnoreCase("no")==0)              //if no the game ends
         System.out.println("Thanks for playing!");
      else              //otherwise the computer rants and asks to replay again
      {
         System.out.println("That's an invalid input.");
         rant_num++;
         rant();
         askToReplay();
      }  
   }
   
   
   public static void rant() //this is the rant the computer makes depending on how many times it had to repeat itself
   {
      if (rant_num==1)
      {
         System.out.println(name+", you know I hate repeating myself!");
      }
      else if (rant_num==2)
      {
         System.out.println("You know, rules exist for a reason.");
         System.out.println("Without rules, E-V-E-R-Y-T-H-I-N-G --> leads to --> chaos.");
         System.out.println("Here we go again.");
      }
      else if (rant_num==3)
      {
         System.out.println("I told you I don't like r3pe@ting myself.");
         System.out.println("But it seems 1ike you don't understand. . .");
         System.out.println("YOU don't understand the CONCEPT of HAVOC and DISARRAY!");
         System.out.println("Do you want know what chaos looks like?");
         for(int chaos = 0; chaos<777; chaos++)
         {
            System.out.println("T^h+ISiS-W/Hat?Ch#a@oslO0K5sL=1i3kE!");
         }
         System.out.println("Petty human this game is over.");
         System.out.println("You were a waste of my time.");
         System.exit(0); 
      }
   }
}

class Wordbank
{
   protected static String[] taftWordBank = {"rhino", "taftie", "ministretur", "usgd",
      "hdt", "cpt", "congdon", "vogelstein", "mac", "centennial","monitor","walkback","formal", "jig"};
   protected static String[] apcsWordBank = {"motulsky", "lord", "marshall",
      "dayton","nicolas","cyrus","bharadwaj","kanyo","jocelyn", "patterson","methods","constructors",
      "recursion", "variables", "gridworld", "gui"};
   protected static String[] facWordBank = {"sanborn", "shotwell", "byrnes", "hostage", "padgett", "thompson",
      "valdez", "conroy", "borken", "parente", "antonucci", "farrar", "fifer", "kievit", "macmullen", "frew", "ganung",
      "blomberg", "vris", "macdonald", "ritacco", "piacenza", "lehner", "mooney", "kenerson", "palmer", "revron"};
      
   public static String pickWord(String[] pickBank)
   {
      return pickBank[(int)(Math.random()*(pickBank.length))];
   }
}

class Gallows
{
   protected static int guessesLeft;
   
   public Gallows()
   {
      guessesLeft = 10;
   }
   
   public String toString()
   {
      return "You have " +guessesLeft+ " guesses left!";
   }
}
