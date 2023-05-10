package binus.datastructure.algorithmvisualizer.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import binus.datastructure.algorithmvisualizer.models.SortingModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXCombo;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;

public class AlgorithmVisualizerControllers implements Initializable {
    private final Stage stage;
    private double xOffset;
    private double yOffset;
    private final ToggleGroup toggleGroup;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private HBox windowHeader;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon maximizeIcon;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private StackPane contentPane;

    @FXML
    private MFXCombo<String> algorithmSelector;

    @FXML
    private StackPane randomizeButtonContainer;

    @FXML
    private MFXTextField inputData;

    @FXML
    private MFXButton runVisualizer;

    private Double canvasWidth;
    private Double canvasHeight;

    public AlgorithmVisualizerControllers(Stage stage) {
        this.stage = stage;
        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setup the window header
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        maximizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        });

        windowHeader.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        // Create the randomize button with icon since it can't be done in FXML
        MFXIconWrapper randomizeIconWrapper = new MFXIconWrapper("fas-rotate", 20, 28);
        MFXButton randomizeButton = new MFXButton("", randomizeIconWrapper);
        randomizeButton.setId("randomize-button");
        randomizeButtonContainer.getChildren().add(randomizeButton);

        // Setup canvas container
        // Setup listener for canvas container size
        contentPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            canvasWidth = newValue.doubleValue();
            System.out.println("Width: " + canvasWidth);
        });
        contentPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            canvasHeight = newValue.doubleValue();
            System.out.println("Height: " + canvasHeight);
        });

        // Setup algorithm selector
        // Create sorting model
        SortingModel sortingModel = new SortingModel();
        
        // Add sorting algorithms to the selector
        algorithmSelector.getItems().addAll(sortingModel.getSortingAlgorithms());
    }
}