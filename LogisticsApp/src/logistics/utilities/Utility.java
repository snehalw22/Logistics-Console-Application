package logistics.utilities;

import java.text.DecimalFormat;

public class Utility {

	public static String printSpaces(int length) {
	
			switch(length){
		    case 0 :
		       return "          ";
		    case 1 :
		    	 return "         ";
		    case 2 :
		    	 return "        ";
		    case 3 :
		    	 return "       ";
		    case 4 :
		    	 return "      ";
		    case 5 :
		    	 return "     ";
		    case 6 :
		    	 return "    ";
		    case 7 :
		    	 return "   ";
		    case 8 :
		    	 return "  ";
		    case 9 :
		    	 return "  ";
		    	 
		    default :
		    	return "";
		       
		}	
	}
	public static String getAlphabets(int number) {
		
		switch(number){
	    case 0 :
	       return "a)";
	    case 1 :
	    	 return "b)";
	    case 2 :
	    	 return "c)";
	    case 3 :
	    	 return "d)";
	    case 4 :
	    	 return "e)";
	    case 5 :
	    	 return "f)";
	    case 6 :
	    	 return "g)";
	    case 7 :
	    	 return "h)";
	    case 8 :
	    	 return "i)";
	    case 9 :
	    	 return "j)";
	    case 10 :
	    	 return "k)";
	    case 11 :
	    	 return "l)";
	    case 12 :
	    	 return "m)";
	    case 13 :
	    	 return "n)";
	    case 14 :
	    	 return "o)";
	    case 15 :
	    	 return "p)";
	    case 16 :
	    	 return "q)";
	    case 17 :
	    	 return "r)";
	    case 18 :
	    	 return "s)";
	    case 19 :
	    	 return "t)";
	    case 20 :
	    	 return "u)";
	    case 21 :
	    	 return "v)";
	    case 22 :
	    	 return "w)";
	    case 23 :
	    	 return "x)";
	    case 24 :
	    	 return "y)";
	    case 25 :
	    	 return "z)";
	    default :
	    	return "";
	       
	}	
}
	public static double calcTravelTime(double hrsPerDay,double milesPerDay,double miles)
	{
		DecimalFormat f = new DecimalFormat("##.00");
		return Double.parseDouble(f.format(miles/(hrsPerDay*milesPerDay)));
	}
	public static String printScheduleSpaces(int length) {
		
		switch(length){
	    case 0 :
	       return "    ";
	    case 1 :
	    	 return "   ";
	    case 2 :
	    	 return "  ";
	    case 3 :
	    	 return " ";
	    default :
	    	return "";
	       
	}	
}
	public static boolean isNumeric(String value)
	{
		boolean result=true;
		for(int i=0;i<value.length();i++)
		{
			if(Character.isDigit(value.charAt(i))==false)
			result= false;
			break;
		}
		return result;
	}
}
