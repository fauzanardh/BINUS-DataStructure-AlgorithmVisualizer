package binus.datastructure.algorithmvisualizer.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXCombo;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;

public class AlgorithmVisualizerControllers implements Initializable {
    private final Stage stage;
    private double xOffset;
    private double yOffset;

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
    private AnchorPane contentPane;

    @FXML
    private MFXCombo<String> algorithmSelector;

    @FXML
    private StackPane randomizeButtonContainer;

    @FXML
    private MFXTextField inputData;

    @FXML
    private MFXButton runVisualizer;

    public AlgorithmVisualizerControllers(Stage stage) {
        this.stage = stage;
    }

    public StackPane getRandomizeButtonContainer() {
        return randomizeButtonContainer;
    }

    public MFXCombo<String> getAlgorithmSelector() {
        return algorithmSelector;
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
    }
}