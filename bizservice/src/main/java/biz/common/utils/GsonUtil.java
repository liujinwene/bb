package biz.common.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonUtil {
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, new DateSerializer())
			.setDateFormat(DateFormat.LONG).create();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromJson(String json, Class<T> classType) {
		return gson.fromJson(json, classType);
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		return gson.fromJson(json, typeOfT);
	}

}

class DateDeserializer implements JsonDeserializer<java.util.Date> {

	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
	}
}

class DateSerializer implements JsonSerializer<Date> {
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }
}