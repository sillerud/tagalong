package no.westerdals.westbook;

import no.westerdals.westbook.model.RequestResponse;

public class Util
{
    /**
     * A response to send when something is successful
     */
    public static final RequestResponse OK_RESPONSE = new RequestResponse("OK");

    /**
     * This method checks if the String can be parsed as a int, using try-catch uses a lot of resources and would be
     * slow. This method does not see negative numbers as a int as its not needed.
     * @param str The string to parse
     * @return true if its a positive int, false if not
     */
    public static boolean isPositiveInteger(String str)
    {
        for (char c : str.toCharArray())
        {
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    /**
     * This method checks if the Strings can be parsed as a int, using try-catch uses a lot of resources and would be
     * slow. This method does not see negative numbers as a int as its not needed.
     * @param strings The strings to check
     * @return true if its a positive int, false if not
     */
    public static boolean isPositiveInteger(String... strings)
    {
        for (String string : strings)
        {
            if (!isPositiveInteger(string))
                return false;
        }
        return true;
    }
}
