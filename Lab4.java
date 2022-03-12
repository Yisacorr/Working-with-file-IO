/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.text.NumberFormat;
//import java.io.File;

public class Lab4 {

    public static void main(String[] args) {
        ArrayList<String> states = new ArrayList<String>();
        ArrayList<Integer> populations = new ArrayList<Integer>();
        ArrayList<String> population = null;
        readFile(states, population);
        menu(states, populations);
      }
    public static void readFile (ArrayList<String> states, ArrayList<String> populations){
        states = new ArrayList<String>( );
        populations = new ArrayList<String>( );
        
        try{
            File statePops = new File("C:\\Users\\Yisacor\\OneDrive\\Desktop\\Files\\cecs 277\\lab-4\\src\\lab\\pkg4\\StatePops.txt");
            ArrayList<String> fileData = new ArrayList<>();
            Scanner in = new Scanner(statePops);
             while(in.hasNext()){
                String[] str = in.nextLine().split(",");
                populations.add(str[str.length - 1]);
                states.add(str[str.length - 2]);
            }
        
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

}
    public static void sortStates(ArrayList<String> states, ArrayList<Integer> populations)
  {
    sortArrays(states, populations, true);
  }

  /**
  *Sort both the States and the Populations arraylists togetherpopulation values.
  *@param states The states which will be sorted
  *@param populations The popluations which will be sorted
  **/
  public static void sortPopulation(ArrayList<String> states, ArrayList<Integer> populations)
  {
    sortArrays(states, populations, false);
  }

  /**
  *Used to sort both the States and the Populations arraylists together by either state names or population values.
  *@param states The states which will be sorted
  *@param populations The popluations which will be sorted
  *@param isStatesThePrimary If sorting by states names, give true; if sorting by population values, give false.
  **/
  private static void sortArrays(ArrayList<String> states, ArrayList<Integer> populations, boolean isStatesThePrimary)
  {
    boolean isSwapping;
    String sPlaceHolder;
    int iPlaceHolder;

    if(states.size() >= 2 && states.size() == populations.size())
    {
      for(int i = 1; i < states.size(); i++)
      {
        for(int j = 1; j < states.size(); j++)
        {  
          isSwapping = false;

          if(isStatesThePrimary)
          {
              isSwapping = states.get(j).compareToIgnoreCase(states.get(j-1)) < 0;
          }
          else
          {
              isSwapping = populations.get(j) > populations.get(j-1);
          }

          if(isSwapping)
          {
            sPlaceHolder = states.get(j);
            iPlaceHolder = populations.get(j);

            states.set(j, states.get(j-1));
            populations.set(j, populations.get(j-1));

            states.set(j-1, sPlaceHolder);
            populations.set(j-1, iPlaceHolder);
          }
          
        }
      }
    }
  }
  
  /**
  *Returns the sum of the population in every state.
  *@param populations The populations that will be summed.
  *@return The sum of all populations.
  **/
  public static int totalSum(ArrayList<Integer> populations) 
  {
    int total = 0;

    for(Integer i : populations)
    {
        total += i.intValue();
    }

    return total;
  }

  /**
  *Prompts the user for a number and then prints all the states with population greater than that number.
  *@param states A list of states that will be output, should already be sorted in parallel to the populations. 
  *@param popuations A list of populations that will be output, should already be sorted in parallel to the states.
  **/
  public static void populationGreater(ArrayList<String> states, ArrayList<Integer> populations)
  {
    System.out.println("Please enter the number against which state popluation will be matched");
    int greaterThan = CheckInput.getPositiveInt();
    

    for(int i = 0; i < populations.size(); i++)
    {

      if(populations.get(i) > greaterThan)
      {
        System.out.printf("%s .. %,d\n", states.get(i), populations.get(i));
      }

    }

    System.out.println();
    
  }
//----------------------------------------------------

  /**
  *displayStates() prints out the states and their corresponding population
  *@param states Lists all of the state names in StatePops
  *@param population Lists all of the population for each state
  **/
  public static void displayStates(ArrayList<String> states, ArrayList<Integer> populations)
  {
    NumberFormat nf = NumberFormat.getInstance();
    for(int i = 0; i < states.size(); i++) {
      System.out.println(states.get(i) + " .. " + nf.format(populations.get(i)));
    }
  }
  
  /**
  *Gives the menu prompt and runs the menu options.
  *@param states The list of states that will be used.
  *@param populations The list of state populations that will be used.
  **/
  public static void menu(ArrayList<String> states, ArrayList<Integer> populations)
  {

    int choice;
    boolean isQuitting = false;

    do{
      System.out.println("State Stats");
      System.out.println("1. Display states sorted by state name");
      System.out.println("2. Display states sorted by population");
      System.out.println("3. Display Total US Population");
      System.out.println("4. Display States with Population Greater Than");
      System.out.println("5. Quit");
      choice = CheckInput.getIntRange(1,5);

      switch(choice){
        case 1:
          sortStates(states, populations);
          displayStates(states, populations);
          break;
          
        case 2:
          sortPopulation(states, populations);
          displayStates(states, populations);
          break;
        case 3:
          System.out.printf("The total popluation of the US is: %,d\n", totalSum(populations));
          break;
        case 4:
          populationGreater(states, populations);
          break;
        case 5:
          isQuitting = true;
          break;
        default:
      }

    } while(!isQuitting);

    }

}