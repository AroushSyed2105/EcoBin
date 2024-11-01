import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ChatGPTClient {

    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "API_KEY";

    public String getBinType(String product) {
        try {
            String prompt = "Which bin should " + product + " go in?";
            JSONObject json = new JSONObject();
            json.put("prompt", prompt);
            json.put("max_tokens", 50);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject responseJson = new JSONObject(response.body());
            return responseJson.getJSONArray("choices").getJSONObject(0).getString("text").trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving bin type.";
        }
    }
}
