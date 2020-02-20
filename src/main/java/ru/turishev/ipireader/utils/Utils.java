package ru.turishev.ipireader.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String convertTimestampToString(Timestamp time) {
		Date date = new Date(time.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy HH:mm");
		return sdf.format(date);
	}
}
