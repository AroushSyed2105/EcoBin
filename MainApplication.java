import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private ChatGPTClient chatGPTClient = new ChatGPTClient(); // Initialize client

    @Override
    public void start(Stage stage) {
        Label promptLabel = new Label("Enter the product:");
        TextField productInput = new TextField();
        Button submitButton = new Button("Submit");
        Label resultLabel = new Label();

        submitButton.setOnAction(event -> {
            String product = productInput.getText();
            String binType = chatGPTClient.getBinType(product); // Query ChatGPT
            resultLabel.setText("Bin: " + binType);
        });

        VBox layout = new VBox(10, promptLabel, productInput, submitButton, resultLabel);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("EcoBin Advisor");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
