package binus.datastructure.algorithmvisualizer.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import binus.datastructure.algorithmvisualizer.MFXAppResourcesLoader;
import binus.datastructure.algorithmvisualizer.components.ResizableCanvas;
import binus.datastructure.algorithmvisualizer.controllers.SortingWindowControllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;

public class SortingWindow {
    public SortingWindow(Stage stage) throws IOException {
        // Get the AnchorPane with fx:id `contentPane` from the scene
        AnchorPane contentPane = (AnchorPane) stage.getScene().lookup("#contentPane");
        
        // Create canvas and add it to the canvas container
        ResizableCanvas sortingCanvas = new ResizableCanvas();

        // Load the SortingWindow
        FXMLLoader loader = new FXMLLoader(MFXAppResourcesLoader.loadURL("fxml/SortingWindow.fxml"));
        loader.setControllerFactory(c -> new SortingWindowControllers(stage, contentPane, sortingCanvas));
        Parent root = loader.load();

        // Get the SortingWindowControllers
        SortingWindowControllers controller = loader.getController();

        // Create the control buttons with icon since it can't be done in FXML
        // Backward button
        MFXIconWrapper backwardIconWrapper = new MFXIconWrapper("fas-caret-left", 28, 28);
        MFXButton backwardButton = new MFXButton("", backwardIconWrapper);
        backwardButton.setId("default-button");
        controller.getStepBackwardContainer().getChildren().add(backwardButton);
        // Play button
        MFXIconWrapper playIconWrapper = new MFXIconWrapper("fas-play", 28, 28);
        MFXButton playButton = new MFXButton("", playIconWrapper);
        playButton.setId("default-button");
        controller.getPlayPauseContainer().getChildren().add(playButton);
        // Forward button
        MFXIconWrapper forwardIconWrapper = new MFXIconWrapper("fas-caret-right", 28, 28);
        MFXButton forwardButton = new MFXButton("", forwardIconWrapper);
        forwardButton.setId("default-button");
        controller.getStepForwardContainer().getChildren().add(forwardButton);

        // Set the SortingWindow as the content of the AnchorPane
        contentPane.getChildren().setAll(root);
    }
}
