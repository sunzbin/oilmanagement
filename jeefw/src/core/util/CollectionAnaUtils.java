package core.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author sunzb
 *
 */
public class CollectionAnaUtils {

	/**
	 * omit string
	 * @param strText original string
	 * @param KeepLen expect length
	 * @return
	 */
	public static String omitString(String strText, int KeepLen) {
		String strOmit = "... ";
		if (strText == null)
			return "";
		if (((strText + strOmit).length()) <= KeepLen)
			return strText;
		else
			try {
				return (strText.substring(0, KeepLen - 2) + strOmit);
			} catch (Exception e) {
				return strText;
			}
	}

	
}
