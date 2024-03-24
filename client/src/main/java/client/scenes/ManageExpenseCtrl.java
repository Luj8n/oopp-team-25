package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Event;
import commons.Expense;
import commons.Person;
import commons.Tag;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ManageExpenseCtrl implements Initializable {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    @FXML
    private Label expenseAmountLabel;

    @FXML
    private Label expenseDate;

    @FXML
    private Label expenseNameLabel;

    @FXML
    private Label expenseRecipient;

    @FXML
    private FlowPane participantsFlowPane;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ComboBox<Tag> tagMenu;
    private ResourceBundle resources;
    private Expense expense;

    @Inject
    public ManageExpenseCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }

    /**
     * populates the UI with appropiate data from the expense object.
     */
    public void populate() {
        Event event = server.getEvents().getFirst();
        this.expense = event.getExpenses().getFirst();
        List<Tag> allTags = server.getTags();
        // Initialize UI with expense data
        expenseNameLabel.setText(expense.getDescription());
        expenseAmountLabel.setText("€ " + expense.getPaid().toString());
        tagMenu.getItems().setAll(allTags);
        tagMenu.setCellFactory(p -> {
            return new ListCell<Tag>() {
                protected void updateItem(Tag t1, boolean empty) {
                    super.updateItem(t1, empty);
                    if (t1 != null){
                        setText(t1.getName());
                    } else {
                        setText(null);
                    }

                }
            };
        });


        // Populate participants
        for (Person participant : expense.getParticipants()) {
            AnchorPane participantCard = createParticipantCard(participant);
            participantsFlowPane.getChildren().add(participantCard);
        }
    }

    /**
     * Creates a new Participant card for the dynamically scaled FlowPane.
     *
     * @param participant The participant
     * @return An anchor pane
     */
    private AnchorPane createParticipantCard(Person participant) {
        AnchorPane card = new AnchorPane();
        card.setPrefSize(475, 50);
        card.setStyle(
            "-fx-border-color: lightgrey; -fx-border-width: 2px; -fx-border-radius: 5px;");

        String participantRepresentation = participant.getFirstName().concat("-"
            + participant.getId());
        Label participantLabel = new Label(participantRepresentation);
        Font globalFont = new Font("System Bold", 24);
        participantLabel.setFont(globalFont);
        participantLabel.setLayoutX(12.5);
        participantLabel.setLayoutY(7.5);
        participantLabel.setOnMouseEntered(
            event -> participantLabel.setText("ID: " + participant.getId()));
        participantLabel.setOnMouseExited(
            event -> participantLabel.setText(participantRepresentation));

        card.getChildren().add(participantLabel);
        return card;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

}