package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Person;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * AddParticipant screen.
 */
public class AddParticipantCtrl {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private ResourceBundle resources;

    @Inject
    public AddParticipantCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    //@Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }

    @FXML
    private TextField bicTextField;

    @FXML
    private Button cross;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField ibanTextField;

    @FXML
    private Label invalidBicMessage;

    @FXML
    private Label invalidEmailMessage;

    @FXML
    private Label invalidIbanMessage;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button save;

    private String firstName;
    private String lastName;
    private String email;
    private String iban;
    private String bic;

    @FXML
    private void save() {
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();

        if (isValidEmail(emailTextField.getText())) {
            email = emailTextField.getText();
            invalidEmailMessage.setVisible(false);
        } else {
            invalidEmailMessage.setVisible(true);
        }

        if (Person.ibanCheckSum(ibanTextField.getText())) {
            iban = ibanTextField.getText();
            invalidIbanMessage.setVisible(false);
        } else {
            invalidIbanMessage.setVisible(true);
        }

        if (Person.bicCheck(bicTextField.getText())) {
            bic = bicTextField.getText();
            invalidBicMessage.setVisible(false);
        } else {
            invalidBicMessage.setVisible(true);
        }

        if (!invalidEmailMessage.isVisible()
                && !invalidIbanMessage.isVisible()
                && !invalidBicMessage.isVisible()) {
            // TODO: Store the new Participant in this Event.
            // TODO: Go back to the Event Overview scene.
        }

    }

    /**
     * Check if the email address is valid.
     *
     * @param email The email address that need to checked.
     * @return True if it is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+"
                + "(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @FXML
    private void cross(){
        // TODO: Go back to the Event Overview scene.
    }

}

