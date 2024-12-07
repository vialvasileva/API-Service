import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String apiUsers = "https://fake-json-api.mock.beeceptor.com/users";
        String apiTodos = "https://dummy-json.mock.beeceptor.com/todos";

        HttpService httpService = new HttpService();

        try {
            // выполняем запросы
            String usersResponse = httpService.sendGetRequest(apiUsers);
            String todosResponse = httpService.sendGetRequest(apiTodos);

            // парсим и выводим результаты для пользователей
            System.out.println("=== Users ===");
            List<Map<String, String>> users = JsonParser.parseJsonArray(usersResponse);
            printUsers(users);

            // парсим и выводим результаты для задач
            System.out.println("\n=== Todos ===");
            List<Map<String, String>> todos = JsonParser.parseJsonArray(todosResponse);
            printTodos(todos);

        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // выводим пользователей
    private static void printUsers(List<Map<String, String>> users) {
        for (Map<String, String> user : users) {
            System.out.println("ID: " + user.get("id"));
            System.out.println("Name: " + user.get("name").replaceAll("^(\\S+ \\S+).*", "$1"));
            System.out.println("Email: " + user.get("email"));
            System.out.println("------------");
        }
    }

    // выводим список задач
    private static void printTodos(List<Map<String, String>> todos) {
        for (Map<String, String> todo : todos) {
            System.out.println("ID: " + todo.get("id"));
            System.out.println("Task: " + todo.get("title"));
            System.out.println("Completed: " + todo.get("completed"));
            System.out.println("------------");
        }
    }
}
