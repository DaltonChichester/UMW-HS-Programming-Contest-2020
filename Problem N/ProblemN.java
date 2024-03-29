import java.io.*;
import java.util.*;

/**
 * The fourtenth problem in the HS programming constest official solution code.
 *
 * @author Dalton Chichester
 * @version 1.0.0
 */
public class ProblemN
{
    public static void main(String[] args)
    {	
        String stringBuffer;
        int guestCount = 0;
        int weddingDayTemp;
        double rateOfCooling;
        int temp;
        double newtonTime;
        double currentTemp;
        int hours;
        int minutes;
        int guestsListCounter = 0;
        String[] guestBuffer = new String[2];
        String[][] guestFinal;
        ArrayList<String> guestNames = new ArrayList<String>();
        ArrayList<Integer> guestPreferedTemps = new ArrayList<Integer>();
        ArrayList<String> unhappyGuestList = new ArrayList<String>();
    	Scanner scnr = new Scanner(System.in);

		stringBuffer = scnr.nextLine();

        while(!stringBuffer.equals("."))
        {
            guestCount++;
            guestBuffer = stringBuffer.split("\\s");

            guestNames.add(guestBuffer[0]);
            guestPreferedTemps.add(Integer.parseInt(guestBuffer[1]));
            stringBuffer = scnr.nextLine();
        }

        weddingDayTemp = scnr.nextInt();
        scnr.nextLine();

        guestFinal = new String[guestCount][3];

        for(int i = 0; i < guestCount; i++)
        {
            currentTemp = 40;

            System.out.println(guestPreferedTemps.get(i));

            if(guestPreferedTemps.get(i) <= weddingDayTemp && guestPreferedTemps.get(i) >= currentTemp)
            {
                boolean gotTemp = false;

                if(40 == guestPreferedTemps.get(i))
                {
                    gotTemp = true;
                }

                hours = 0;
                minutes = 0;

                while(!gotTemp)
                {
                    currentTemp += .5;
                    minutes++;
                    if(minutes == 60)
                    {
                        minutes = 0;
                        hours += 1;
                    }

                    if(currentTemp == guestPreferedTemps.get(i))
                    {
                        gotTemp = true;
                        break;
                    }
                }
              
                if(gotTemp)
                {
                    guestFinal[guestsListCounter][0] = guestNames.get(i);
                    guestFinal[guestsListCounter][1] = String.valueOf(minutes + (hours * 60));
                    guestFinal[guestsListCounter][2] = "unsorted"; 
                    guestsListCounter++;
                }
            }
            else
            {
                unhappyGuestList.add(guestNames.get(i));
            }
        }

        for(int i = 0; i < guestsListCounter; i++)
        {
            int longest = -1;
            int indext = 0;

            for(int j = 0; j < guestsListCounter; j++)
            {
                if(Integer.parseInt(guestFinal[j][1]) > longest && guestFinal[j][2] == "unsorted")
                {
                    longest = Integer.parseInt(guestFinal[j][1]);
                    indext = j;
                }
            }

            if(Integer.parseInt(guestFinal[indext][1]) == 0)
            {
                System.out.println(guestFinal[indext][0] + ": Remove bottle from fridge at the time of the wedding.");
                guestFinal[indext][2] = "sorted";
            }
            else
            {
                int h = (Integer.parseInt(guestFinal[indext][1]) / 60);
                int m = (Integer.parseInt(guestFinal[indext][1]) % 60);

                System.out.print(guestFinal[indext][0] + ": Remove bottle from fridge ");
                if(h == 1)
                {
                    System.out.print(h + " hour and ");
                }
                else
                {
                    System.out.print(h + " hours and ");
                }

                if(m == 1)
                {
                    System.out.println(m + " minute before the wedding.");
                }
                else
                {
                    System.out.println(m + " minutes before the wedding.");
                }
                guestFinal[indext][2] = "sorted"; 
            }
        }

        for(int i = 0; i < unhappyGuestList.size(); i++)
        {
            System.out.println(unhappyGuestList.get(i) + ": Unable to get the right temperature.");
        }
    }
}


