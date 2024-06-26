package client.scenes;

import client.utils.ScreenUtils;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * EditParticipant popup.
 */
public class EditParticipantCtrl implements Initializable {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private ResourceBundle resources;
    @FXML
    private AnchorPane root;

    @Inject
    public EditParticipantCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        root.addEventFilter(KeyEvent.KEY_PRESSED,
            ScreenUtils.exitHandler(resources, mainCtrl::closePopup));
    }

    @FXML
    private Label bicLabel;

    @FXML
    private TextField bicTextField;

    @FXML
    private Button editBic;

    @FXML
    private Button editEmail;

    @FXML
    private Button editFirstName;

    @FXML
    private Button editIban;

    @FXML
    private Button editLastName;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label ibanLabel;

    @FXML
    private TextField ibanTextField;

    @FXML
    private Label invalidBicMessage;

    @FXML
    private Label invalidEmailMessage;

    @FXML
    private Label invalidIbanMessage;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button save;

    private Person person;

    @FXML
    private void editFirstName() {
        firstNameLabel.setVisible(false); // Hide the firstNameLabel
        firstNameTextField.setVisible(true); // Show the firstNameTextField
        firstNameTextField.requestFocus(); // Set focus to firstNameTextField
    }

    @FXML
    private void editLastName() {
        lastNameLabel.setVisible(false); // Hide the lastNameLabel
        lastNameTextField.setVisible(true); // Show the lastNameTextField
        lastNameTextField.requestFocus(); // Set focus to lastNameTextField

    }

    @FXML
    private void editEmail() {
        emailLabel.setVisible(false); // Hide the emailLabel
        emailTextField.setVisible(true); // Show the emailTextField
        emailTextField.requestFocus(); // Set focus to emailTextField
    }

    @FXML
    private void editIban() {
        ibanLabel.setVisible(false); // Hide the ibanLabel
        ibanTextField.setVisible(true); // Show the ibanTextField
        ibanTextField.requestFocus(); // Set focus to ibanTextField
    }

    @FXML
    private void editBic() {
        bicLabel.setVisible(false); // Hide the bicLabel
        bicTextField.setVisible(true); // Show the bicTextField
        bicTextField.requestFocus(); // Set focus to bicTextField
    }

    @FXML
    private void save() {
        if (firstNameTextField.isVisible()) {
            person.setFirstName(firstNameTextField.getText());
            server.updatePerson(person);
            firstNameTextField.setVisible(false);
            firstNameLabel.setVisible(true);
        }
        if (lastNameTextField.isVisible()) {
            person.setLastName(lastNameTextField.getText());
            server.updatePerson(person);
            lastNameTextField.setVisible(false);
            lastNameLabel.setVisible(true);
        }
        if (emailTextField.isVisible()) {
            if (emailTextField.getText().isBlank() || Person.emailCheck(emailTextField.getText())) {
                person.setEmail(emailTextField.getText());
                server.updatePerson(person);
                emailTextField.setVisible(false);
                emailLabel.setVisible(true);
                invalidEmailMessage.setVisible(false);
            } else {
                invalidEmailMessage.setVisible(true);
            }
        }
        if (ibanTextField.isVisible()) {
            if (ibanTextField.getText().isBlank() || Person.ibanCheckSum(ibanTextField.getText())) {
                person.setIban(ibanTextField.getText());
                server.updatePerson(person);
                ibanTextField.setVisible(false);
                ibanLabel.setVisible(true);
                invalidIbanMessage.setVisible(false);
            } else {
                invalidIbanMessage.setVisible(true);
            }
        }
        if (bicTextField.isVisible()) {
            if (bicTextField.getText().isBlank() || Person.bicCheck(bicTextField.getText())) {
                person.setBic(bicTextField.getText());
                server.updatePerson(person);
                bicTextField.setVisible(false);
                bicLabel.setVisible(true);
                invalidBicMessage.setVisible(false);
            } else {
                invalidBicMessage.setVisible(true);
            }
        }
    }

    /**
     * Populate the screen.
     */
    public void populate() {
        if (this.person == null) {
            return;
        }
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        emailLabel.setText(person.getEmail());
        ibanLabel.setText(person.getIban());
        bicLabel.setText(person.getBic());
    }

    /**
     * Update the current person.
     */
    public void refetch() {
        if (this.person == null) {
            return;
        }
        this.person = server.getPersonById(person.getId());
        populate();
    }

    public void update(Person person) {
        this.person = person;
        populate();
    }
}
