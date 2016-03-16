import java.util.*;
public class DecentNumbers {

	static String getDecentNum(int digits)

	{
		int fives = digits;
		int threes = 0;
		while(fives > 0)
		{
			if(fives%3 == 0)
			{
				break;
			}
			fives -= 5; // minimum 5 taken by threes
		}
		threes = digits - fives;

		if(fives < 0 || threes%5 !=0)
			return "-1";

		StringBuffer res = new StringBuffer();
		for(int i =0; i < fives; ++i)
			res.append("5");

		for(int i =0; i < threes; ++i)
			res.append("3");

		return res.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();

		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();
			System.out.println(getDecentNum(n));
		}
	}
}




