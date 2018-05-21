package core.util;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr) {
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0) {
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

			i++;
		}
		return returnStr;
	}

	/**获取字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	private static final char[] hex = "0123456789ABCDEF".toCharArray();
	public static final String PathSeperator = "/";
	public static final String LineSeperator = System.getProperty("line.separator");

	/**
	 * @deprecated
	 */
	public static final String Line_Seperator = System.getProperty("line.separator");
	public static final String FileEncoding = System.getProperty("file.encoding");

	/**
	 * @deprecated
	 */
	public static final String File_Encoding = System.getProperty("file.encoding");
	public static final String DateStyle = "yyyy-MM-dd";
	public static final String TimeStyle = "HH:mm:ss";
	public static final String DateTimeStyle = "yyyy-MM-dd HH:mm:ss";
	private static final Pattern IntegerStyle = Pattern.compile("[-+]?\\d{1,10}", 2);
	private static final Pattern NumberStyle = Pattern.compile("[-+]?\\d+", 2);

	public static final boolean isNull(Object o) {
		return (o == null);
	}

	public static final boolean isEmpty(String value) {
		return ((value == null) || (value.trim().length() == 0));
	}

	public static final boolean isEmpty(String[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static final boolean isEmpty(boolean[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static final boolean isEmpty(byte[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static final boolean isEmpty(int[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static final boolean isEmpty(StringBuffer sb) {
		return ((sb == null) || (sb.length() == 0));
	}

	public static final boolean isEmpty(StringBuilder sb) {
		return ((sb == null) || (sb.length() == 0));
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isEmpty(List list) {
		return ((list == null) || (list.size() == 0));
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isEmpty(Set set) {
		return ((set == null) || (set.size() == 0));
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isEmpty(Map map) {
		return ((map == null) || (map.size() == 0));
	}

	public static final boolean isEmpty(Object o) {
		return (o == null);
	}

	public static final boolean isEmpty(Object[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static final boolean isInt(String value) {
		return ((value != null) && (IntegerStyle.matcher(value).matches()));
	}

	public static final boolean isNumber(String value) {
		return ((value != null) && (NumberStyle.matcher(value).matches()));
	}

	public static final int size(String text) {
		if (text == null)
			return 0;

		return text.length();
	}

	public static final int size(String[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	public static final int size(boolean[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	public static final int size(byte[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	public static final int size(int[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	public static final int size(Date[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	@SuppressWarnings("rawtypes")
	public static final int size(List list) {
		if (isEmpty(list))
			return 0;

		return list.size();
	}

	@SuppressWarnings("rawtypes")
	public static final int size(Set set) {
		if (isEmpty(set))
			return 0;

		return set.size();
	}

	@SuppressWarnings("rawtypes")
	public static final int size(Map map) {
		if (isEmpty(map))
			return 0;

		return map.size();
	}

	public static final int size(Object[] array) {
		if (isEmpty(array))
			return 0;

		return array.length;
	}

	public static final boolean isSame(String value1, String value2) {
		return isSame(value1, value2, false);
	}

	public static final boolean isSameIgnoreCase(String value1, String value2) {
		return isSame(value1, value2, true);
	}

	public static final boolean isSame(String value1, String value2, boolean ignoreCase) {
		if ((isEmpty(value1)) && (isEmpty(value2)))
			return true;
		if ((!(isEmpty(value1))) && (!(isEmpty(value2)))) {
			if (ignoreCase)
				return value1.trim().equalsIgnoreCase(value2.trim());

			return value1.trim().equals(value2.trim());
		}

		return false;
	}

	public static final boolean isBeginWith(String content, String theBegin) {
		return isBeginWith(content, new String[] { theBegin });
	}

	public static final boolean isBeginWith(String content, String[] aryBegin) {
		if ((isEmpty(content)) || (isEmpty(aryBegin))) {
			return false;
		}

		boolean flag = false;
		for (int i = 0; i < aryBegin.length; ++i)
			if ((!(isEmpty(aryBegin[i]))) && (content.startsWith(aryBegin[i]))) {
				flag = true;
				break;
			}

		return flag;
	}

	public static final boolean isContainWith(String content, String value) {
		return isContainWith(content, value, true);
	}

	public static final boolean isContainWith(String content, String value, boolean ignoreCase) {
		if ((isEmpty(content)) || (isEmpty(value)))
			return false;

		if (ignoreCase)
			return (content.toLowerCase().indexOf(value.toLowerCase()) != -1);

		return (content.indexOf(value) != -1);
	}

	public static final boolean isContainWith(String[] aryContent, String value) {
		return isContainWith(aryContent, value, false);
	}

	public static final boolean isContainWith(String[] aryContent, String value, boolean ignoreCase) {
		int size = size(aryContent);
		if ((size == 0) || (isEmpty(value)))
			return false;

		for (int i = 0; i < size; ++i)
			if (isContainWith(aryContent[i], value, ignoreCase))
				return true;

		return false;
	}

	public static final boolean isContainWith(String content, String[] aryValue, boolean ignoreCase) {
		int size = size(aryValue);
		if ((isEmpty(content)) || (size == 0))
			return false;

		for (int i = 0; i < size; ++i) {
			if (isContainWith(content, aryValue[i], ignoreCase))
				return true;

		}

		return false;
	}

	public static final boolean isContainWith(int[] array, int value) {
		if (isEmpty(array))
			return false;

		int size = size(array);
		for (int i = 0; i < size; ++i)
			if (array[i] == value)
				return true;

		return false;
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isContainWith(List list, Object object) {
		if (list == null)
			return false;

		return list.contains(object);
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isContainWith(Set set, Object object) {
		if (set == null)
			return false;

		return set.contains(object);
	}

	public static final String middle(String text, int begin, int length) {
		if ((isEmpty(text)) || (begin < 0) || (length < 0))
			return "";

		int end = Math.min(begin + length, text.length());
		return text.substring(begin, end);
	}

	public static final String middle(String text, int begin) {
		if ((isEmpty(text)) || (begin < 0))
			return "";

		return text.substring(begin);
	}

	public static final String left(String text, int length) {
		if ((isEmpty(text)) || (length < 1))
			return "";

		if (length > text.length())
			return text;

		return text.substring(0, length);
	}

	public static final String right(String text, int length) {
		if ((isEmpty(text)) || (length < 1))
			return "";

		if (length > text.length())
			return text;

		return text.substring(text.length() - length);
	}

	public static final String format(String value) {
		return format(value, "");
	}

	public static final String format(String value, String defaultValue) {
		if (isEmpty(value))
			return defaultValue;

		return value.trim();
	}

	public static final String formatLength(String value, int length) {
		return formatLength(value, length, true, " ");
	}

	public static final String formatLength(String value, int length, boolean isBegin) {
		return formatLength(value, length, isBegin, " ");
	}

	public static final String formatLength(String value, int length, boolean isBegin, String joiner) {
		if (joiner == null)
			throw new IllegalArgumentException("连接符不内为 null ！");

		int len = 0;
		if (value != null) {
			value = value.trim();
			len = value.length();
		}
		if ((length < 1) || (len > length))
			return value;

		StringBuilder sb = new StringBuilder(length);
		if (!(isBegin))
			sb.append(value);

		int step = joiner.length();
		int count = (length - len) / step;
		for (int i = 0; i < count; ++i)
			sb.append(joiner);

		if (isBegin)
			count = length - len - sb.length();
		else
			count = length - sb.length();

		if (count > 0)
			sb.append(joiner.substring(0, count));

		if (isBegin)
			sb.append(value);

		return sb.toString();
	}

	public static final String format(Boolean value) {
		return format(value, "");
	}

	public static final String format(Boolean value, String defaultValue) {
		if (value == null)
			return defaultValue;

		return ((value.booleanValue()) ? "是" : "否");
	}

	public static final String format(Integer value) {
		return format(value, "");
	}

	public static final String format(Integer value, String defaultValue) {
		if (value == null)
			return defaultValue;

		return value.toString();
	}

	public static final String formatNumber(String style, int value) {
		return new DecimalFormat(style).format(value);
	}

	public static final String formatNumber(String style, long value) {
		return new DecimalFormat(style).format(value);
	}

	public static final String format(Date date) {
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, "");
	}

	public static final String format(Date date, Date defaultValue) {
		if (date == null)
			date = defaultValue;
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, "");
	}

	public static final String format(Date date, String defaultValue) {
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, defaultValue);
	}

	public static final String formatDate(Date date) {
		return formatDateTimeByStyle("yyyy-MM-dd", date, "");
	}

	public static final String formatDate(Date date, Date defaultValue) {
		if (date == null)
			date = defaultValue;
		return formatDateTimeByStyle("yyyy-MM-dd", date, "");
	}

	public static final String formatDate(Date date, String defaultValue) {
		return formatDateTimeByStyle("yyyy-MM-dd", date, defaultValue);
	}

	public static final String formatTime(Date date) {
		return formatDateTimeByStyle("HH:mm:ss", date, "");
	}

	public static final String formatTime(Date date, Date defaultValue) {
		if (date == null)
			date = defaultValue;
		return formatDateTimeByStyle("HH:mm:ss", date, "");
	}

	public static final String formatTime(Date date, String defaultValue) {
		return formatDateTimeByStyle("HH:mm:ss", date, defaultValue);
	}

	public static final String formatDateTime(Date date) {
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, "");
	}

	public static final String formatDateTime(Date date, Date defaultValue) {
		if (date == null)
			date = defaultValue;
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, "");
	}

	public static final String formatDateTime(Date date, String defaultValue) {
		return formatDateTimeByStyle("yyyy-MM-dd HH:mm:ss", date, defaultValue);
	}

	public static final String formatDateTime(String style, Date date) {
		return formatDateTimeByStyle(style, date, "");
	}

	public static final String formatDateTime(String style, Date date, Date defaultValue) {
		if (date == null)
			date = defaultValue;
		return formatDateTimeByStyle(style, date, "");
	}

	public static final String formatDateTime(String style, Date date, String defaultValue) {
		return formatDateTimeByStyle(style, date, defaultValue);
	}

	private static final String formatDateTimeByStyle(String style, Date date, String defaultValue) {
		if ((isEmpty(style)) || (isEmpty(date)))
			return defaultValue;

		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return format(sdf.format(date), defaultValue);
	}

	public static final Object format(Object object, Object defaultValue) {
		if (object == null)
			object = defaultValue;
		return object;
	}

	public static final String join(int[] array, String joiner) {
		if (isEmpty(array))
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append(array[0]);
		for (int i = 1; i < array.length; ++i) {
			sb.append(joiner);
			sb.append(array[i]);
		}

		return sb.toString();
	}

	public static final String[] split(String content, int length) {
		if ((isEmpty(content)) || (length < 1))
			return new String[0];

		int max = content.length();
		int size = max / length;
		if (size * length < max)
			++size;
		String[] ary = new String[size];
		for (int i = 0; i < size; ++i) {
			int end = (i + 1) * length;
			if (end > max)
				end = max;
			ary[i] = content.substring(i * length, end);
		}

		return ary;
	}

	public static final String[][] split(String[] arySource, int length) {
		if ((isEmpty(arySource)) || (length < 1))
			return new String[0][0];

		int size = arySource.length;
		int count = size / length;
		if (size - count * length > 0)
			++count;

		String[][] ary = new String[count][length];
		for (int i = 0; i < count; ++i)
			for (int j = 0; j < length; ++j) {
				int index = i * length + j;
				if (index < size)
					ary[i][j] = arySource[index];
			}

		return ary;
	}

	public static final String[] ListToArray(List<String> list) {
		return ListToArray(list, false);
	}

	public static final String[] ListToArray(List<String> list, boolean isFilteEmpty) {
		if (isEmpty(list))
			return null;

		String[] array = new String[size(list)];
		for (int i = 0; i < array.length; ++i)
			if (isFilteEmpty) {
				if (!(isEmpty((String) list.get(i))))
					;
				array[i] = ((String) list.get(i)).toString();
			} else {
				array[i] = ((String) list.get(i)).toString();
			}

		return array;
	}

	public static final List<String> ArrayToList(String[] array) {
		return ArrayToList(array, false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final List<String> ArrayToList(String[] array, boolean filterEmpty) {
		if (isEmpty(array))
			return null;

		List list = new ArrayList(array.length);

		for (int i = 0; i < size(array); ++i) {
			if (filterEmpty)
				if (!(isEmpty(array[i])))
					list.add(array[i]);
				else
					list.add(array[i]);

		}

		return list;
	}

	public static final String encodeHtml(String text) {
		if (isEmpty(text))
			return "";

		text = text.replaceAll("&", "&amp;");
		text = text.replaceAll(" ", "&nbsp;");
		text = text.replaceAll("<", "&lt;");
		text = text.replaceAll(">", "&gt;");
		text = text.replaceAll("\"", "&quot;");
		text = text.replaceAll("'", "&apos;");
		text = text.replaceAll(LineSeperator, "<br>");

		return text;
	}

	public static final String decodeHtml(String html) {
		if (isEmpty(html))
			return "";

		html = html.replaceAll("&amp;", "&");
		html = html.replaceAll("&nbsp;", " ");
		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		html = html.replaceAll("&quot;", "\"");
		html = html.replaceAll("&apos;", "'");
		html = html.replaceAll("<br>", LineSeperator);

		return html;
	}

	public static final String encodeUrl(String url) {
		return encodeUrl(url, FileEncoding);
	}

	public static final String encodeUrl(String url, String charset) {
		if (isEmpty(url))
			return "";

		try {
			url = URLEncoder.encode(url, charset).replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return url;
	}

	public static final String decodeUrl(String url) {
		return decodeUrl(url, FileEncoding);
	}

	public static final String decodeUrl(String url, String charset) {
		if (isEmpty(url))
			return "";
		try {
			url = URLDecoder.decode(url, charset);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return url;
	}

	public static final String encodeUnicode(String input) {
		if (isEmpty(input))
			return "";

		char[] aryChar = input.toCharArray();
		StringBuilder sb = new StringBuilder(aryChar.length * 6);
		for (int i = 0; i < aryChar.length; ++i) {
			String hex = Integer.toHexString(aryChar[i]);
			if (hex.length() <= 2)
				hex = "00" + hex;

			sb.append("\\u" + hex);
		}
		return sb.toString();
	}

	public static final String decodeUnicode(String input) {
		if (isEmpty(input))
			return "";

		input = input.toLowerCase();
		int beginIndex = input.indexOf("\\u");
		int closeIndex = 0;
		StringBuilder sb = new StringBuilder(input.length());

		while (beginIndex > -1) {
			closeIndex = input.indexOf("\\u", beginIndex + 2);
			String s = "";
			if (closeIndex == -1) {
				s = input.substring(beginIndex + 2, input.length());
				beginIndex = -1;
			} else {
				s = input.substring(beginIndex + 2, closeIndex);
				beginIndex = closeIndex;
			}
			char c = (char) Integer.parseInt(s, 16);
			sb.append(new Character(c).toString());
		}

		return sb.toString();
	}

	public static final String generateOption(String value, String label) {
		return generateOption(value, label, false);
	}

	public static final String generateOption(String value, String label, boolean isSelected) {
		StringBuilder sb = new StringBuilder();

		sb.append("<option value=\"" + value + "\"");
		if (isSelected)
			sb.append(" selected");
		sb.append(">");
		sb.append(label);
		sb.append("</option>");

		return sb.toString();
	}

	public static final boolean isSubDomain(String subDomain, String domain) {
		if ((isEmpty(subDomain)) || (isEmpty(domain)))
			return false;

		return subDomain.endsWith(domain);
	}

	public static final boolean isSqlValid(String text) {
		if (isEmpty(text)) {
			return true;
		}

		String temp = text.toLowerCase();
		boolean isExist = (temp.indexOf("select ") > -1) || (temp.indexOf("insert ") > -1)
				|| (temp.indexOf("update ") > -1) || (temp.indexOf("delete ") > -1);
		if (!(isExist)) {
			isExist = (temp.indexOf("char") > -1) || (temp.indexOf(" or ") > -1) || (temp.indexOf("'or ") > -1)
					|| (temp.indexOf(" or'") > -1);
			if (!(isExist))
				isExist = (temp.indexOf("script") > -1) || (temp.indexOf("iframe") > -1) || (temp.indexOf("><a") > -1);

			if (!(isExist))
				isExist = (temp.indexOf("onload") > -1) || (temp.indexOf("onerror") > -1)
						|| (temp.indexOf("onmouse") > -1);
		}

		return (!(isExist));
	}

	public static final String abstractSiteDomain(String url) {
		if (isEmpty(url))
			return null;

		url = abstractSiteURL(url).replace("http://", "").replace("https://", "");
		int index = url.indexOf(":");
		return ((index > -1) ? url.substring(0, index) : url);
	}

	public static final String abstractSiteURL(String url) {
		if (isEmpty(url))
			return null;

		boolean isSecurity = url.startsWith("https://");
		url = url.replace("http://", "").replace("https://", "");
		int index = url.indexOf("/");
		if (index != -1) {
			url = url.substring(0, index);
		} else {
			index = url.indexOf("?");
			if (index != -1)
				url = url.substring(0, index);

			index = url.indexOf("#");
			if (index != -1)
				url = url.substring(0, index);
		}

		String prefix = (isSecurity) ? "https://" : "http://";
		if (index == -1)
			return prefix + url;

		return prefix + url.substring(0, index);
	}

	public static final String abstractSiteDirectory(String url) {
		if (isEmpty(url))
			return null;

		int index = url.lastIndexOf("/");
		if (index > -1)
			return url.substring(0, url.lastIndexOf("/") + 1);

		return url;
	}

	public static final String wrap(String data, String prefix, String suffix) {
		return wrap(data, prefix, suffix, true);
	}

	public static final String wrap(String data, String prefix, String suffix, boolean isFilterEmpty) {
		if ((isFilterEmpty) && (isEmpty(data)))
			return "";

		if (prefix != null)
			prefix = prefix.replace("{data}", data);

		if (suffix != null)
			suffix = suffix.replace("{data}", data);

		return format(prefix) + format(data) + format(suffix);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final String[] wrap(String[] aryData, String prefix, String suffix, boolean isFilterEmpty) {
		if (isEmpty(aryData))
			return new String[0];

		List list = new ArrayList();
		for (int i = 0; i < aryData.length; ++i) {
			String data = wrap(aryData[i], prefix, suffix, isFilterEmpty);
			if (isFilterEmpty)
				if (!(isEmpty(data)))
					list.add(data);

				else
					list.add(data);
		}

		return ListToArray(list);
	}

	public static final String wrap(String[] aryData, String prefix, String suffix, boolean isFilterEmpty,
			String splitor) {
		if (isEmpty(aryData))
			return "";

		splitor = format(splitor);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(aryData); ++i) {
			String data = wrap(aryData[i], prefix, suffix, isFilterEmpty);
			if (isFilterEmpty) {
				if (!(isEmpty(data))) {
					if (i != 0)
						sb.append(splitor);
					sb.append(data);
				}
			} else {
				if (i != 0)
					sb.append(splitor);
				sb.append(data);
			}
		}
		return sb.toString();
	}

	public static final String replace(String template, String[] src, String[] aim) {
		if ((isEmpty(template)) || (isEmpty(src)) || (isEmpty(aim)))
			return template;

		int size = src.length;
		for (int i = 0; i < size; ++i)
			template = template.replace(src[i], aim[i]);

		return template;
	}

	public static final String replace(String template, String[] src, List<String[]> aim) {
		if ((isEmpty(template)) || (isEmpty(src)) || (isEmpty(aim)))
			return template;

		int size = size(aim);
		StringBuilder sb = new StringBuilder(template.length() * size);
		for (int i = 0; i < size; ++i) {
			String[] ary = (String[]) aim.get(i);
			sb.append(replace(template, src, ary));
		}
		return sb.toString();
	}

	public static final String replace(String content, String src, String aim, int replaceCount) {
		if ((isEmpty(content)) || (isEmpty(src)) || (isNull(aim)) || (replaceCount == 0))
			return content;

		if (replaceCount == -1)
			return content.replace(src, aim);

		int beginIndex = 0;
		int closeIndex = 0;
		int counter = 0;
		int srcLength = src.length();
		StringBuilder sb = new StringBuilder(content.length());
		do {
			++counter;
			sb.append(content.substring(beginIndex, closeIndex));
			sb.append(aim);

			beginIndex = closeIndex + srcLength;
			closeIndex = content.indexOf(src, beginIndex);

			if (counter >= replaceCount)
				break;
		} while ((closeIndex = content.indexOf(src, beginIndex)) > -1);

		sb.append(content.substring(beginIndex));
		return sb.toString();
	}

	public static final int getSubStringCount(String theString, String subString) {
		if ((isNull(theString)) || (isNull(subString)) || (theString.length() < subString.length()))
			return 0;

		int index = theString.indexOf(subString);
		int len = subString.length();
		int count = 0;
		while (index > -1) {
			++count;
			index = theString.indexOf(subString, index + len);
		}
		return count;
	}

	public static String byteToHex(byte[] array) {
		return byteToHex(array, null);
	}

	public static String byteToHex(byte[] array, String splitor) {
		if (isEmpty(array)) {
			return null;
		}

		int end = array.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < end; ++i) {
			if ((i > 0) && (splitor != null))
				sb.append(splitor);

			sb.append(hex[((array[i] & 0xF0) >>> 4)]).append(hex[(array[i] & 0xF)]);
		}
		return sb.toString();
	}

	public static byte[] hexToByte(String input) {
		if (isEmpty(input))
			return null;

		int len = input.length();
		byte[] output = new byte[len >>> 1];
		for (int i = 0; i <= len - 2; i += 2)
			output[(i >>> 1)] = (byte) (Integer.parseInt(input.substring(i, i + 2).trim(), 16) & 0xFF);

		return output;
	}

	public static byte[] hexToByte(String input, String splitor) {
		if (isEmpty(input))
			return null;

		if (isEmpty(splitor))
			return hexToByte(input);

		byte[] output = (byte[]) null;
		input = input.trim();
		StringTokenizer st = new StringTokenizer(input, splitor);
		output = new byte[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); ++i) {
			String byteString = st.nextToken();

			if (byteString.length() != 2)
				throw new IllegalArgumentException("输入数据格式错误！");

			output[i] = (byte) (Integer.parseInt(byteString, 16) & 0xFF);
		}

		return output;
	}

	public static final String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter(1024);
		BufferedWriter bw = new BufferedWriter(sw);
		PrintWriter pw = new PrintWriter(bw);
		e.printStackTrace(pw);
		pw.flush();
		pw.close();
		return sw.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(abstractSiteURL("www.sian.com:800/#ssss=xx"));
	}
}
