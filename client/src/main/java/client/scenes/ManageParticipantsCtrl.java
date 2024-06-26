package client.scenes;

import client.utils.ScreenUtils;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Event;
import commons.Person;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;


/**
 * ManageParticipants screen.
 */
public class ManageParticipantsCtrl implements Initializable {
    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    @FXML
    private ComboBox<Person> participantMenu;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private FlowPane participantsFlowPane;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteParticipantButton;
    @FXML
    private Button editParticipantButton;
    private ResourceBundle resources;
    private Event event;


    @Inject
    public ManageParticipantsCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        rootAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED,
            ScreenUtils.exitHandler(resources, this::handleExit));
    }

    private void handleExit() {
        mainCtrl.showEventOverview(this.event, false);
    }


    @FXML
    private void editParticipant() {
        Person selectedParticipant = participantMenu.getValue();
        if (selectedParticipant == null) {
            return;
        }
        mainCtrl.showEditParticipantPopup(selectedParticipant);
    }

    @FXML
    private void deleteParticipant() {
        Person selectedParticipant = participantMenu.getValue();
        if (selectedParticipant == null) {
            return;
        }
        mainCtrl.showDeleteParticipantConfirmationPopup(() -> {
            if (event.getExpenses().stream().anyMatch(ex ->
                ex.getReceiver().equals(selectedParticipant)
                    || ex.getParticipants().contains(selectedParticipant)
            )) {
                // Show a modal dialog to inform the user
                Dialog<String> dialog = new Dialog<>();
                dialog.initModality(Modality.APPLICATION_MODAL); // Make the dialog modal
                dialog.initOwner(rootAnchorPane.getScene().getWindow()); // Set the owner

                // Customize the dialog appearance
                dialog.setTitle(resources.getString("manage-participants.invalid-operation"));
                dialog.setContentText(
                    resources.getString("manage-participants.invalid-info"));

                // Adding a custom close button inside the dialog,
                // since default buttons are not used
                ButtonType closeButton =
                    new ButtonType(resources.getString("manage-participants.understood"),
                        ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(closeButton);

                // Handling dialog result to perform actions if needed, but it's informational
                dialog.showAndWait();

                return;
            }
            server.deletePersonFromEvent(selectedParticipant, event);
        });

    }

    @FXML
    private void backButton() {
        mainCtrl.showEventOverview(event, false);
    }

    /**
     * Populate the screen.
     */
    public void populate() {
        if (event == null) {
            return;
        }
        List<Person> personList = event.getPeople();

        // Disable the buttons if there are no people
        deleteParticipantButton.setDisable(personList.isEmpty());
        deleteParticipantButton.setDisable(personList.isEmpty());

        participantMenu.getSelectionModel().clearSelection();

        // Populate participants
        participantsFlowPane.getChildren().setAll();
        for (Person participant : personList) {
            AnchorPane participantCard = createParticipantCard(participant);
            participantsFlowPane.getChildren().add(participantCard);
        }

        // Initialize the ComboBox
        participantMenu.getItems().setAll(personList);
        participantMenu.setCellFactory(p -> new ListCell<>() {
            @Override
            protected void updateItem(Person p1, boolean empty) {
                super.updateItem(p1, empty);
                if (p1 != null) {
                    setText(p1.getFirstName() + " " + p1.getLastName());
                } else {
                    setText(null);
                }
            }
        });

        participantMenu.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                if (person != null) {
                    setText(person.getFirstName() + " " + person.getLastName());
                } else {
                    setText(null);
                }
            }
        });

    }

    private AnchorPane createParticipantCard(Person participant) {
        AnchorPane card = new AnchorPane();
        card.setPrefSize(475, 50);
        card.setStyle(
            "-fx-border-color: lightgrey; -fx-border-width: 2px; -fx-border-radius: 5px;");

        String participantRepresentation =
            participant.getFirstName() + " " + participant.getLastName();
        Label participantLabel = new Label(participantRepresentation);
        Font globalFont = new Font("System Bold", 24);
        participantLabel.setFont(globalFont);
        participantLabel.setLayoutX(12.5);
        participantLabel.setLayoutY(7.5);
        participantLabel.setMaxWidth(401);


        card.getChildren().add(participantLabel);
        return card;
    }

    /**
     * Update current event.
     */
    public void refetch() {
        if (this.event == null) {
            return;
        }
        this.event = server.getEventById(event.getId());
        populate();
    }

    public void update(Event event) {
        this.event = event;
        populate();
    }

    public Event getEvent() {
        return event;
    }
}