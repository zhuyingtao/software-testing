import java.util.Scanner;

/**
 * @author Z.Y.T
 * 
 */

public class RMBtransfer {

	String[] chineseNumber = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
	String[] chineseBit = { "��", "��", "", "Ԫ", "ʰ", "��", "Ǫ", "��", "", "", "",
			"��" };
	double maxValue = 999999999999.99;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RMBtransfer rmb = new RMBtransfer();
		// *** Step 1 : input the number;
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String s = scan.next();
			try {
				double d = Double.parseDouble(s);
				if (d > rmb.maxValue || d < 0) {
					System.out.println("the input is out of limit !!");
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("the input is not a number !!");
				break;
			}

			// *** Step 2 : find the bit of each char;
			int[] numBit = new int[s.length()];
			int periodLoc = s.indexOf(".");
			if (periodLoc == -1)
				periodLoc = s.length();

			for (int i = 0; i < s.length(); i++) {
				int loc = 0;
				if (i > periodLoc)
					loc = -(i - periodLoc);
				else
					loc = periodLoc - i;
				numBit[i] = loc;
			}
			// System.out.println(Arrays.toString(numBit));

			// *** Step 3 : transfer each char to Chinese;
			StringBuffer result = new StringBuffer("�����");
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if (ch == '.')
					continue;
				int bit = numBit[i];
				if (bit < -2)
					continue;
				
				result.append(rmb.transferNumber(ch));
				if (bit > 9) {
					result.append(rmb.transferBit(bit - 8));
				} else if (bit > 5 && bit < 9) {
					result.append(rmb.transferBit(bit - 4));
				} else {
					result.append(rmb.transferBit(bit));
				}
				if (i == s.length() - 1 && numBit[i] > 0)
					result.append("��");
			}

			// *** Step 4 : format the output;
			String result2 = result.toString();
			result2 = result2.replaceAll("��[^����Ԫ]", "��").replaceAll("��+", "��")
					.replaceAll("��$", "").replaceAll("����", "��")
					.replaceAll("����", "��").replaceAll("��Ԫ", "Ԫ");
			System.out.println(result2);
		}
		scan.close();
	}

	public String transferNumber(char ch) {
		int n = ch - '0';
		return chineseNumber[n];
	}

	public String transferBit(int num) {
		int n = 2 + num;
		return chineseBit[n];
	}
}
