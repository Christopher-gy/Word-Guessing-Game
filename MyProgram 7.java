import java.util.Scanner;
import java.util.Random;

public class MyProgram
{
    //constant for maximum tries allowed
    private static final int MAX_TRIES = 6;
    
    //Word bank
    private static final String[] words = {"dog", "cat", "car", "sun", "bed", "box", "hat", "map", "cup"};
    public static void main(String[] args)
    {
        //Import scanner for user input
        Scanner input = new Scanner(System.in);
        //Import random to select a word randomly
        Random random = new Random();
        
        
        
        //Keep track of games won
        int score = 0;
        boolean running = true;
        
        while (running)
        {
            //Menu
            System.out.println("\nWord Guessing Game");
            System.out.println("1. Play Game");
            System.out.println("2. Quit");
            System.out.println("Current Score: " + score);
            System.out.print("Choose an option: ");
            
            //Menu input
            int choice;
            if (input.hasNextInt())
            {
                choice = input.nextInt();
                input.nextLine();
            } else
            {
                System.out.println("Invalid option, Enter a number: ");
                input.nextLine();
                continue;
            }
            //Option 1:
            if (choice == 1)
            {
                //Selecct random word from bank
                String word = words[random.nextInt(words.length)];
                
                //Array to keep letter guessed correctly
                char[] hiddenWord = new char[word.length()];
                for (int i = 0; i < hiddenWord.length; i++)
                {
                    hiddenWord[i] = '_';
                }
                
                //Track letters guessed incorrecly
                String incorrectGuesses = "";
                int triesLeft = MAX_TRIES;
                //Check if word has been guessed
                boolean wordGuessed = false;
                
                System.out.println("\nThe word has " + word.length() + " letters. ");
                
                //Game Loop
                while (triesLeft > 0 && !wordGuessed)
                {
                    //Show Game State
                    System.out.println("\nWord: " + String.valueOf(hiddenWord));
                    System.out.println("Incorrect guesses: " + incorrectGuesses);
                    System.out.println("Tries left: " + triesLeft);
                    
                   char guess = getValidGuess(input, String.valueOf(hiddenWord), incorrectGuesses);
                   
                    //Track if the guess was correct
                    boolean correct = false;
                    
                    //Check if guess is in word
                    for (int i = 0; i < word.length(); i++)
                    {
                        if (Character.toLowerCase(word.charAt(i)) == guess && hiddenWord[i] == '_')
                        {
                            //Reveal correct letter
                            hiddenWord[i] = word.charAt(i);
                            //Mark as correct
                            correct = true;
                        }
                    }
                    if (!correct)
                    {
                        incorrectGuesses += guess + " ";
                        triesLeft--;
                        
                    }
                    //Check if entire word was guessed
                    if (String.valueOf(hiddenWord).equals(word))
                    {
                        wordGuessed = true;
                        //Add to score when player wins
                        score++;
                        System.out.println("You guessed the word: " + word + "!");
                        System.out.println("Your total score: " + score);
                    }
                }
                //If tries runs out reveal the word
                if (!wordGuessed)
                {
                    System.out.println("\nYou ran out of tries! the word was: " + word);
                }
                //Option 2:
            } else if (choice == 2)
            {
                running = false;
                System.out.println(" Thanks for playing! Final Score: " + score);
            }
            else
            {
                //Menu validation
                System.out.println("Invalid Option, try again.");
            }
        }
        //Close scanner
        input.close();
    }
    private static char getValidGuess(Scanner input, String hiddenWord, String incorrectGuesses)
    {
        while (true)
        {
            System.out.print("Enter a letter: ");
            String userInput = input.nextLine().toLowerCase();
            
            if (userInput.length() != 1 || !Character.isLetter(userInput.charAt(0)))
            {
                System.out.println("Invalid input. Enter one letter from (a-z).");
                continue;
            }
            char guess = userInput.charAt(0);
            String allGuesses = incorrectGuesses + hiddenWord.toLowerCase().replace("_", "");
            if (allGuesses.contains(String.valueOf(guess)))
            {
                System.out.println("You already guessed that letter!");
                continue;
            }
            return guess;
        }
    }
}