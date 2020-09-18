/*
 * RootCellar 9/8/2020
 * This class will be used to assist with errors and error handling. (This includes exceptions)
 * This includes converting the error to a string, and helping to provide info associated with the error as well
*/

package PixelEngine.Util;


//TODO: Implement

public class ErrorHelper
{
	private static final String PREFIX = "{ERROR}";


	public ErrorHelper() {
		
	}

	public static String convertExceptionToString(Throwable e) {
		StackTraceElement[] elements = e.getStackTrace();
		String toRet = "";

		toRet+=PREFIX + " " + e.getMessage() + " at ";

		for(int i=0; i<elements.length; i++) {
			toRet+=elements[i].toString();
			toRet+="\n";
		}

		Throwable cause = e.getCause();
		if(cause!=null) toRet+="Caused by: " + convertExceptionToString(cause);

		return toRet;
		//return "NOT YET IMPLEMENTED";
	}

	public static void main(String[] args) {
		String exc = convertExceptionToString( new Exception("TEST") );
		System.out.println(exc);
	}



}
