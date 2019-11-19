package myMath1;

public class _Exception extends Exception
{

		public _Exception(String error) 
		{
			super("New error was catched, the error is ->" + error);
		}
}
