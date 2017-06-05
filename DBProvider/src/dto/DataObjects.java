package dto;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DataObjects {
	
	private String location;
	private String locationcode;
	private JsonArray jsondata;
	private String distance;
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the locationcode
	 */
	public String getLocationcode() {
		return locationcode;
	}
	/**
	 * @param locationcode the locationcode to set
	 */
	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}
	/**
	 * @return the jsondata
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws ParseException 
	 */
	@JsonRawValue
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "jsondata")
	public JsonArray getJsondata() throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		
		return jsondata;
	}
	/**
	 * @param jsondata the jsondata to set
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public void setJsondata(String jsondata) throws ParseException, JSONException {

		JsonParser parser = new JsonParser();
		JsonElement tradeElement = parser.parse(jsondata.trim());
		JsonArray trade = tradeElement.getAsJsonArray();
				
		this.jsondata = trade;		
	}
	/**
	 * @return the jsondata
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param jsondata the jsondata to set
	 */
	public void setDistance(String dist) {
		this.distance = dist;
	}	
}
