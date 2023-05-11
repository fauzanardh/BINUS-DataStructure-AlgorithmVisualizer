package binus.datastructure.algorithmvisualizer.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import io.github.palexdev.materialfx.controls.MFXProgressBar;

import binus.datastructure.algorithmvisualizer.components.ResizableCanvas;

public class SortingWindowControllers implements Initializable {
    private final Stage stage;
    private final AnchorPane contentPane;
    private final ResizableCanvas sortingCanvas;

    @FXML
    private AnchorPane sortingCanvasContainer;

    @FXML
    private AnchorPane stepBackwardContainer;

    @FXML
    private AnchorPane playPauseContainer;

    @FXML
    private AnchorPane stepForwardContainer;

    @FXML
    private MFXProgressBar sortingProgress;


    public SortingWindowControllers(Stage stage, AnchorPane contentPane, ResizableCanvas sortingCanvas) {
        this.stage = stage;
        this.contentPane = contentPane;
        this.sortingCanvas = sortingCanvas;
    }

    public AnchorPane getStepBackwardContainer() {
        return stepBackwardContainer;
    }

    public AnchorPane getPlayPauseContainer() {
        return playPauseContainer;
    }

    public AnchorPane getStepForwardContainer() {
        return stepForwardContainer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add listener to the canvas container to resize the canvas when contentPane is resized
        // And change the width of progress bar to contentPane width
        contentPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            sortingCanvas.setWidth(newVal.doubleValue());
            sortingProgress.setPrefWidth(newVal.doubleValue());
        });
        contentPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            sortingCanvas.setHeight(newVal.doubleValue() - 100); // 100 is the height of the control buttons
        });

        // Set the sortingCanvas to the canvas container
        sortingCanvasContainer.getChildren().add(sortingCanvas);
    }
}
