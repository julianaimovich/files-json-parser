import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = readString("src/main/resources/data.json");
        List<Employee> list = jsonToList(json);
        list.stream().map(Employee::toString).forEach(System.out::println);
    }

    public static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String jsonString;
            while ((jsonString = reader.readLine()) != null) {
                sb.append(jsonString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<Employee> jsonToList(String jsonString) {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Gson gson = new GsonBuilder().create();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Employee employee = gson.fromJson(jsonObject.toJSONString(), Employee.class);
                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}