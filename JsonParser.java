import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    // метод для парсинга JSON массива
    public static List<Map<String, String>> parseJsonArray(String json) {
        List<Map<String, String>> result = new ArrayList<>();
        json = json.trim();

        // удаляем квадратные скобки массива
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1).trim();
        }

        // разбиваем массив объектов
        String[] objects = json.split("(?<=}),");

        for (String obj : objects) {
            obj = obj.trim();
            if (obj.endsWith(",")) {
                obj = obj.substring(0, obj.length() - 1);
            }
            Map<String, String> map = parseJsonObject(obj);
            result.add(map);
        }

        return result;
    }

    // метод для парсинга JSON объекта
    private static Map<String, String> parseJsonObject(String json) {
        Map<String, String> result = new HashMap<>();
        json = json.trim();

        // удаляем фигурные скобки объекта
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1).trim();
        }

        // разбиваем на пары ключ-значение
        String[] pairs = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("^\"|\"$", ""); // Удаляем кавычки
                String value = keyValue[1].trim().replaceAll("^\"|\"$", ""); // Удаляем кавычки
                result.put(key, value);
            }
        }

        return result;
    }
}
