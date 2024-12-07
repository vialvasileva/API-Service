import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {
    private final HttpClient client;

    public HttpService() {
        this.client = HttpClient.newHttpClient();
    }

    // метод для выполнения GET-запроса
    public String sendGetRequest(String apiUrl) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder() // создаем заготовку запроса
                .uri(URI.create(apiUrl)) // устанавливаем URI для запроса
                .GET() // получаем данные с сервера
                .build(); // завершаем сборку объекта запроса


        // отправляем HTTP-запрос и получаем ответ от сервера в виде строки
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
