package kr.co.manager.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.manager.exception.QgException;

public class JsonConverter {
	
	private static ObjectMapper om = new ObjectMapper();
    
    /**
     * <pre>
     * 1. 개요 : String형 json을 Map<String, Object> 로 변환
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : convert
     * @date : 2017. 8. 17.
     * @author : Jinsung
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2017. 8. 18.     Jinsung             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param  json                   Map으로 된 Json형식의 String
     * @return Map<String, Object>
     * @throws QgException
     */
    public static Map<String, Object> convert(String json) throws QgException {
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = om.readValue(json,new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new QgException("json.parse.fail.jsonToMap",e);
        }
        return jsonMap;
    }
    
    /**
     * <pre>
     * 1. 개요 : String형 json을 List<Object> 로 변환
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : convertToList
     * @date : 2017. 8. 17.
     * @author : Jinsung
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2017. 8. 18.     Jinsung             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param  json            Json형식의 String
     * @return List<Object>
     * @throws QgException
     */
    public static List<Object> convertToList(String json) throws QgException {
        List<Object> list = null;
        try {
            list = om.readValue(json,new TypeReference<List<Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new QgException("json.parse.fail.convertToList",e);
        }
        return list;
    }

    /**
     * <pre>
     * 1. 개요 : String형 json을 List<Integer> 로 변환
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : convertToIntegerList
     * @date : 2017. 8. 17.
     * @author : Jinsung
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2017. 8. 18.     Jinsung             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param  json             Integer로 된 Json형식의 String
     * @return List<Integer>
     * @throws QgException
     */
    public static List<Integer> convertToIntegerList(String json) throws QgException {
        List<Integer> list = null;
        try {
            list = om.readValue(json,new TypeReference<List<Integer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new QgException("json.parse.fail.convertToList",e);
        }
        return list;
    }
    
//    public static Machine getMachineObject(String json) throws JsonParseException, JsonMappingException, IOException{
//        JsonNode root = om.readTree(json);
//        String machineName = root.get("name").asText();
//        Machine m = new Machine();
//        m.setName(machineName);
//        return m;
//    }
//
    /**
     * <pre>
     * 1. 개요 : Json을 String으로 변환
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : convertToJson
     * @date : 2017. 8. 17.
     * @author : Jinsung
     * @history : 
     *  -----------------------------------------------------------------------
     *  Date                Author                        Note
     *  ----------- ------------------- ---------------------------------------
     *  2017. 8. 18.     Jinsung             최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param  obj             Json형식의 Object
     * @return String
     * @throws QgException
     */
    public static String convertToJson(Object obj) throws QgException {
        String returnString = null;
        try {
            returnString = om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new QgException("json.parse.fail.convertToJson",e);
        }
        return returnString;
    }
    
 // list<map> 을 json 형태로 변형.
    @SuppressWarnings({ "unchecked" })
    public static JSONArray convertListToJson(List<Map<String, Object>> bankCdList) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> map : bankCdList) {
            jsonArray.put(convertMapToJson(map));
        }

        return jsonArray;

    }

    // map 을 json 형태로 변형
    @SuppressWarnings({ "unchecked" })
    public static JSONObject convertMapToJson(Map<String, Object> map) throws JSONException {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // json.addProperty(key, value);
            json.put(key, value);
        }
        return json;
    }


//
//    public static byte[] convertToJsonByte(Object obj) throws JsonParseException, JsonMappingException, IOException{
//        return om.writeValueAsBytes(obj);
//    }
//
////    public static Gcode toGcodeHistory(String json) throws JsonParseException, JsonMappingException, IOException{
////        return om.readValue(json, Gcode.class);
////    }
//
//    public static OffsetValue toOffsetHistory(String json) throws JsonParseException, JsonMappingException, IOException{
//        return om.readValue(json, OffsetValue.class);
//    }
//    public static Status toStatusHistory(String json) throws JsonParseException, JsonMappingException, IOException{
//        return om.readValue(json, Status.class);
//    }
}
