package binus.datastructure.algorithmvisualizer.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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

import binus.datastructure.algorithmvisualizer.models.SortingModel;

public class AlgorithmVisualizerControllers implements Initializable {
    private final SortingModel sortingModel;
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
    private MFXTextField dataInput;

    @FXML
    private MFXButton runVisualizer;

    public AlgorithmVisualizerControllers(Stage stage, SortingModel sortingModel) {
        this.stage = stage;
        this.sortingModel = sortingModel;
    }

    public AnchorPane getContentPane() {
        return contentPane;
    }

    public StackPane getRandomizeButtonContainer() {
        return randomizeButtonContainer;
    }

    public MFXCombo<String> getAlgorithmSelector() {
        return algorithmSelector;
    }

    public void setRandomizeButtonHandler(MFXButton randomizeButton) {
        randomizeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Generate 10 random numbers
            ArrayList<Integer> currentState = new ArrayList<Integer>();
            for (int i = 0; i < 25; i++) {
                currentState.add(ThreadLocalRandom.current().nextInt(1, 50));
            }

            // Update the input data text field
            // convert the data to string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < currentState.size(); i++) {
                sb.append(currentState.get(i));
                if (i != currentState.size() - 1)
                    sb.append(",");
            }
            // set the text field
            dataInput.setText(sb.toString());
        });
    }

    public void setRunVisualizerButtonHandler(SortingWindowControllers sortingWindowControllers) {
        runVisualizer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Run the selected algorithm
            String selectedAlgorithm = algorithmSelector.getSelectionModel().getSelectedItem();
            String algorithmKey = sortingModel.algorithmNameToKey.get(selectedAlgorithm);

            // Get the data from the input text field
            String[] dataString = dataInput.getText().split(",");
            if (dataString.length == 1) {
                System.out.println("No data inputted or invalid data inputted");
                return;
            }

            // Convert the data to ArrayList<Integer>
            ArrayList<Integer> data = new ArrayList<>();
            try {
                for (String s : dataString) {
                    data.add(Integer.parseInt(s));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid data inputted");
                // Reset the input text field
                dataInput.setText("");
                return;
            }

            // Print the data and the algorithm to be run
            System.out.println("Running " + selectedAlgorithm + " (" + algorithmKey + ") algorithm");
            System.out.println("Data: " + data);

            // Initialize the model with the data
            sortingModel.initializeModel(data);

            // Run the algorithm
            sortingModel.runAlgorithmFully(algorithmKey);

            // Update the sorting window
            sortingWindowControllers.setCurrentNode(sortingModel.getData().getHead());
            sortingWindowControllers.updateSortingWindow();
        });
    }

    public void setDefaultValues() {
        // Set the default algorithm to the first item in the list
        algorithmSelector.getSelectionModel().selectIndex(0);
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