
import com.company.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Client client;


    @FXML
    private Button StartButton;


    @FXML
    private Button crash;

    @FXML
    private Button exit;

    @FXML
    private TextField nametf;

    @FXML
    private TextField numbertf;

    @FXML
    private TextField ipaddresstf;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void PressStart()  {
        String name;
        String number;
        String ipaddress;
        StartButton.setDisable(true);
        label.setText("Uruchomiono");

        name = nametf.getText();
        number=numbertf.getText();
        ipaddress=ipaddresstf.getText();

        client = new Client(name, number,ipaddress);
        Thread t1 = new Thread(client);
        t1.start();
    }
    public void PressExit()
    {
        client.End();
        System.exit(1);
    }
    public void PressCrash()
    {
        System.exit(1);
    }

    }

