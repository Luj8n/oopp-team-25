package client.utils;

import commons.Event;
import commons.Tag;
import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class for all Pane creators.
 */
public class PaneCreator {
    /**
     * Makes a button of an event.
     *
     * @param event       to be created
     * @param handleClick a function which will be called when the item is clicked
     * @return a button containing given event
     */
    public static Button createEventItem(Event event, Consumer<Event> handleClick) {
        Button mainButton = new Button();

        //The entire button
        mainButton.setPrefHeight(150);
        mainButton.setPrefWidth(685);
        mainButton.setStyle("-fx-border-color: #000000;"
            + "-fx-border-width: 0 0 1 0;"
            + "-fx-background-color: #eaeaea;"
            + "-fx-padding: 0");
        mainButton.setOnAction((e) -> handleClick.accept(event));

        // The pane inside the button
        Pane mainPane = new Pane();
        mainPane.setPrefHeight(150);
        mainPane.setPrefWidth(685);

        mainButton.setGraphic(mainPane);

        //Event name
        Label eventName = new Label(event.getTitle());
        eventName.setLayoutX(14);
        eventName.setLayoutY(14);
        eventName.setPrefHeight(21);
        eventName.setMaxWidth(300);
        eventName.setFont(Font.font(18));

        //Event code
        Label eventCode = new Label(event.getCode());
        eventCode.setLayoutX(341);
        eventCode.setLayoutY(14);
        eventCode.setPrefHeight(21);
        eventCode.setPrefWidth(142);
        eventCode.setFont(Font.font(18));

        //Box for tags
        HBox tagsBox = new HBox();
        tagsBox.setAlignment(Pos.CENTER_LEFT);
        tagsBox.setLayoutX(14);
        tagsBox.setLayoutY(101);
        tagsBox.setPrefHeight(35);
        tagsBox.setPrefWidth(500);
        tagsBox.setSpacing(15);

        tagsBox.getChildren().setAll(event.getTags().stream()
            .map(PaneCreator::createTagItem).toList());

        //Last Modified
        Label lastModified =
            new Label(event.getLastModifiedDateTime().toString().substring(0, 10));
        lastModified.setLayoutX(494);
        lastModified.setLayoutY(12);
        lastModified.setPrefHeight(21);
        lastModified.setPrefWidth(150);
        lastModified.setFont(Font.font(18));
        lastModified.setAlignment(Pos.CENTER_RIGHT);

        ImageView dateIcon = new ImageView(new Image("client/icons/calendar.png"));
        dateIcon.setLayoutX(647);
        dateIcon.setLayoutY(13);
        dateIcon.setFitHeight(24);
        dateIcon.setFitWidth(24);

        //User count
        Label usersCount = new Label(String.valueOf(event.getPeople().size()));
        usersCount.setLayoutX(614);
        usersCount.setLayoutY(47);
        usersCount.setPrefHeight(20);
        usersCount.setPrefWidth(30);
        usersCount.setFont(Font.font(18));
        usersCount.setAlignment(Pos.CENTER_RIGHT);

        //User icon
        ImageView usersIcon = new ImageView(new Image("client/icons/users.png"));
        usersIcon.setLayoutX(647);
        usersIcon.setLayoutY(48);
        usersIcon.setFitHeight(24);
        usersIcon.setFitWidth(24);

        //Description
        Label eventDescription = new Label(event.getDescription());
        eventDescription.setLayoutX(14);
        eventDescription.setLayoutY(46);
        eventDescription.setPrefHeight(50);
        eventDescription.setPrefWidth(500);
        eventDescription.setWrapText(true);

        mainPane.getChildren()
            .setAll(eventName, eventCode, tagsBox, lastModified, dateIcon, usersCount, usersIcon,
                eventDescription);

        return mainButton;
    }

    /**
     * Makes a pane for the admin view of an event.
     *
     * @param event       to be created
     * @param handleClick a function which will be called when the item is clicked
     * @return a pane containing given event
     */
    public static Pane createAdminEventItem(Event event, Consumer<Event> handleClick,
                                            Consumer<Event> handleDelete,
                                            Consumer<Event> handleDownload) {
        Pane pane = new Pane();

        //The entire pane
        pane.setPrefHeight(150);
        pane.setPrefWidth(685);
        pane.setStyle("-fx-border-color: #000000; -fx-border-width: 0 0 1 0;");

        //Event name
        Button eventName = new Button(event.getTitle());
        eventName.setLayoutX(14);
        eventName.setLayoutY(14);
        eventName.setPrefHeight(21);
        eventName.setMaxWidth(300);
        eventName.setFont(Font.font(18));
        eventName.setOnAction((e) -> handleClick.accept(event));

        //Event code
        Label eventCode = new Label(event.getCode());
        eventCode.setLayoutX(341);
        eventCode.setLayoutY(14);
        eventCode.setPrefHeight(21);
        eventCode.setPrefWidth(142);
        eventCode.setFont(Font.font(18));

        //Box for tags
        HBox tagsBox = new HBox();
        tagsBox.setAlignment(Pos.CENTER_LEFT);
        tagsBox.setLayoutX(14);
        tagsBox.setLayoutY(101);
        tagsBox.setPrefHeight(35);
        tagsBox.setPrefWidth(500);
        tagsBox.setSpacing(15);

        tagsBox.getChildren().setAll(event.getTags().stream()
            .map(PaneCreator::createTagItem).toList());

        //Last Modified
        Label lastModified =
            new Label(event.getLastModifiedDateTime().toString().substring(0, 10));
        lastModified.setLayoutX(494);
        lastModified.setLayoutY(12);
        lastModified.setPrefHeight(21);
        lastModified.setPrefWidth(150);
        lastModified.setFont(Font.font(18));
        lastModified.setAlignment(Pos.CENTER_RIGHT);

        ImageView dateIcon = new ImageView(new Image("client/icons/calendar.png"));
        dateIcon.setLayoutX(647);
        dateIcon.setLayoutY(13);
        dateIcon.setFitHeight(24);
        dateIcon.setFitWidth(24);

        //User count
        Label usersCount = new Label(String.valueOf(event.getPeople().size()));
        usersCount.setLayoutX(614);
        usersCount.setLayoutY(47);
        usersCount.setPrefHeight(20);
        usersCount.setPrefWidth(30);
        usersCount.setFont(Font.font(18));
        usersCount.setAlignment(Pos.CENTER_RIGHT);

        //User icon
        ImageView usersIcon = new ImageView(new Image("client/icons/users.png"));
        usersIcon.setLayoutX(647);
        usersIcon.setLayoutY(48);
        usersIcon.setFitHeight(24);
        usersIcon.setFitWidth(24);

        //Description
        Label eventDescription = new Label(event.getDescription());
        eventDescription.setLayoutX(14);
        eventDescription.setLayoutY(46);
        eventDescription.setPrefHeight(50);
        eventDescription.setPrefWidth(500);
        eventDescription.setWrapText(true);

        ImageView trashIcon = new ImageView("client/icons/trashcan.png");
        trashIcon.setFitHeight(24);
        trashIcon.setFitWidth(24);
        trashIcon.setPickOnBounds(true);
        trashIcon.setPreserveRatio(true);

        Button trashButton = new Button();
        trashButton.setLayoutX(647);
        trashButton.setLayoutY(84);
        trashButton.setStyle("-fx-background-color: transparent; -fx-padding: 0");
        trashButton.setOnAction((e) -> handleDelete.accept(event));
        trashButton.setGraphic(trashIcon);

        ImageView downloadIcon = new ImageView("client/icons/download.png");
        downloadIcon.setFitHeight(30);
        downloadIcon.setFitWidth(30);
        downloadIcon.setPickOnBounds(true);
        downloadIcon.setPreserveRatio(true);

        Button downloadButton = new Button();
        downloadButton.setLayoutX(644);
        downloadButton.setLayoutY(112);
        downloadButton.setStyle("-fx-background-color: transparent; -fx-padding: 0");
        downloadButton.setOnAction((e) -> handleDownload.accept(event));
        downloadButton.setGraphic(downloadIcon);

        pane.getChildren()
            .setAll(eventName, eventCode, tagsBox, lastModified, dateIcon, usersCount, usersIcon,
                eventDescription, trashButton, downloadButton);

        return pane;
    }

    /**
     * Makes a label based on tag.
     *
     * @param tag tag to be created
     * @return a label made from tag
     */
    public static Label createTagItem(Tag tag) {
        Label tagLabel = new Label(tag.getName());

        tagLabel.setStyle(String.format(
            "-fx-background-color: %s; "
                + "-fx-padding: 4px 8px;"
                + "-fx-border-radius: 12px;"
                + "-fx-background-radius: 12px;",
            tag.getColour().toHexString()));

        // calculate what colour the text should be depending on the background
        int red = tag.getColour().getRed();
        int green = tag.getColour().getGreen();
        int blue = tag.getColour().getBlue();
        if (red * 0.299 + green * 0.587 + blue * 0.114 > 186) {
            tagLabel.setTextFill(Color.web("#000000"));
        } else {
            tagLabel.setTextFill(Color.web("#ffffff"));
        }

        return tagLabel;
    }
}
