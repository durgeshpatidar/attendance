package com.incture.attendance.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


public class ServicesUtil {

	public static final String NOT_APPLICABLE = "N/A";
	public static final String SPECIAL_CHAR = "∅";
	private static final SimpleDateFormat custMarginDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat dateFormatForSalesPlanner = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	protected static Date formatedDate;
	public static String stringDate;

	public static boolean isEmpty(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Collection<?> o) {
		if (o == null || o.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Date date) {
		if (date == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(BigDecimal value) {
		if (value == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuffer sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuilder sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}

	public static String getCSV(Object... objs) {
		if (!isEmpty(objs)) {
			if (objs[0] instanceof Collection<?>) {
				return getCSVArr(((Collection<?>) objs[0]).toArray());
			} else {
				return getCSVArr(objs);
			}

		} else {
			return "";
		}
	}

	private static String getCSVArr(Object[] objs) {
		if (!isEmpty(objs)) {
			StringBuffer sb = new StringBuffer();
			for (Object obj : objs) {
				sb.append(',');
				if (obj instanceof Field) {
					sb.append(extractFieldName((Field) obj));
				} else {
					sb.append(extractStr(obj));
				}
			}
			sb.deleteCharAt(0);
			return sb.toString();
		} else {
			return "";
		}
	}

	public static String extractStr(Object o) {
		return o == null ? "" : o.toString();
	}

	public static String extractFieldName(Field o) {
		return o == null ? "" : o.getName();
	}

	public static String buildNoRecordMessage(String queryName, Object... parameters) {
		StringBuffer sb = new StringBuffer("No Record found for query: ");
		sb.append(queryName);
		if (!isEmpty(parameters)) {
			sb.append(" for params:");
			sb.append(getCSV(parameters));
		}
		return sb.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static Date convertDate(Date date) {

		try {

			stringDate = dateFormat1.format(date);
			formatedDate = dateFormat1.parse(stringDate);

		} catch (Exception e) {

			// todo
		}

		return formatedDate;
	}

	@SuppressWarnings("restriction")
	public static String getBasicAuth(String userName, String password) {
		String userpass = userName + ":" + password;
		return "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
	}

	public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date addMonth(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}

	public static Date convertStringToDate(String inDate) {
		Date outDate = null;
		try {
			outDate = custMarginDateFormatter.parse(inDate);
		} catch (ParseException e) {
			System.err.println(" ServicesUtil | convertStringToDate | Error : " + e.getMessage());
		}
		return outDate;
	}

	public static Date convertStringToDate(String inDate, SimpleDateFormat simpleDateFormat) {
		Date outDate = null;
		try {
			outDate = simpleDateFormat.parse(inDate);
		} catch (ParseException e) {
			System.err.println(" ServicesUtil | convertStringToDate | Error : " + e.getMessage());
		}
		return outDate;
	}

}