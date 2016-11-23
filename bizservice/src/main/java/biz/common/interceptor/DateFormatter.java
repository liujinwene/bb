package biz.common.interceptor;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	public String print(Date object, Locale locale) {
		if(object == null) {
			return null;
		}
		return String.valueOf(object.getTime());
	}

	public Date parse(String text, Locale locale) throws ParseException {
		if(StringUtils.isEmpty(text)) {
			return null;
		}
		return new Date(Long.valueOf(text));
	}

}
