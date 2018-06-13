/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月30日
 */
package core.util;

/**
 * @Author:梁英男
 * @Date:2018年5月30日
 */
public class HexAccumulation{
	public static char [] HEX_FIRST_HALF = {'7','6','5','4','3','2','1','0'};
	
	public static char [] HEX_LAST_HALF = {'8','9','a','b','c','d','e','f'};
	
	public static char [] HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
	
	
	public static void main(String[] args) {
		String a  = HexAccumulation.getCheckCode("1002303036380101000000");
		System.out.println(a);
		
	}
	
	public static String makeChecksum(String hexdata) {
	    if (hexdata == null || hexdata.equals("")) {
	        return "00";
	    }
	    hexdata = hexdata.replaceAll(" ", "");
	    int total = 0;
	    int len = hexdata.length();
	    if (len % 2 != 0) {
	        return "00";
	    }
	    int num = 0;
	    while (num < len) {
	        String s = hexdata.substring(num, num + 2);
	        total += Integer.parseInt(s, 16);
	        num = num + 2;
	    }
	    return hexInt(total);
	}
	
	private static String hexInt(int total) {
	    int a = total / 256;
	    int b = total % 256;
	    if (a > 255) {
	        return hexInt(a) + format(b);
	    }
	    return format(a) + format(b);
	}

	private static String format(int hex) {
	    String hexa = Integer.toHexString(hex);
	    int len = hexa.length();
	    if (len < 2) {
	        hexa = "0" + hexa;
	    }
	    return hexa;
	}
	
	public static String getCheckCode(String hex) {
		String hexSum = makeChecksum(hex);
		char [] charHexSum = hexSum.substring(hexSum.length()-2).toCharArray();
		char[] qufan = new char[2];
		if(charHexSum.length==2) {
			for (int i = 0; i < charHexSum.length; i++) {
				for (int j = 0; j < HEX.length; j++) {
					if(charHexSum[i] == HEX[j]) {
						qufan[i] = HEX[HEX.length-1-j];
					}
				}
			}
		}
		return makeChecksum(String.valueOf(qufan)+"01");
	}

}
