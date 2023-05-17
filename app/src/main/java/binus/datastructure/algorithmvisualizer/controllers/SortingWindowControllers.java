package binus.datastructure.algorithmvisualizer.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import binus.datastructure.algorithmvisualizer.DoublyLinkedList.Node;
import binus.datastructure.algorithmvisualizer.models.SortingModel;

public class SortingWindowControllers implements Initializable {
    private final AnchorPane contentPane;
    private final SortingModel sortingModel;
    private Node currentNode;
    private BarChart<String, Number> chart;

    @FXML
    private BorderPane sortingCanvasContainer;

    @FXML
    private AnchorPane stepBackwardContainer;

    @FXML
    private AnchorPane playPauseContainer;

    @FXML
    private AnchorPane stepForwardContainer;

    @FXML
    private MFXProgressBar sortingProgress;

    public SortingWindowControllers(AnchorPane contentPane, SortingModel sortingModel) {
        this.contentPane = contentPane;
        this.sortingModel = sortingModel;
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

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void makeChart() {
        // Data
        if (currentNode == null)
            return;
        ArrayList<Integer> data = currentNode.getItem().getCurrentState();
        ArrayList<Integer> comparedElements = new ArrayList<>() {
            {
                add(currentNode.getItem().getComparedElements().getKey());
                add(currentNode.getItem().getComparedElements().getValue());
            }
        };

        // Initialize chart
        // Set Chart upperbound to the maximum
        // value of data + 2 (for better visualization)
        chart = new BarChart<>(new CategoryAxis(), new NumberAxis(0, sortingModel.getData().getMaxValue() + 2, 0));
        chart.setLegendVisible(false);
        chart.getYAxis().setTickLabelsVisible(false);
        chart.getYAxis().setOpacity(0);
        chart.getXAxis().setTickLabelsVisible(false);
        chart.getXAxis().setOpacity(0);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);

        // Set Chart width and height
        chart.setPrefWidth(contentPane.getWidth());
        chart.setPrefHeight(contentPane.getHeight() - 100); // 100 is the height of the control buttons
        chart.setCategoryGap(0);

        // Initialize bars
        ObservableList<Data<String, Number>> bars = FXCollections.observableArrayList();
        chart.getData().add(new Series<>(bars));

        // Set chart background color
        chart.lookup(".chart-plot-background").setStyle("-fx-background-color: #1e1e2e;");

        // Add data to bars
        for (int i = 0; i < data.size(); i++) {
            Data<String, Number> dataObject = new Data<>(String.valueOf(i + 1), data.get(i));
            bars.add(dataObject);
            if (comparedElements.contains(i))
                dataObject.getNode().setStyle("-fx-bar-fill: #f38ba8;");
            else
                dataObject.getNode().setStyle("-fx-bar-fill: #91d7e3;");
        }

        // Add chart to canvas container
        sortingCanvasContainer.setCenter(chart);
    }

    public void setStepHandler(MFXButton stepBackwardButton, MFXButton stepForwardButton) {
        stepForwardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (currentNode != null) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                    updateSortingWindow();
                }
            }
        });
        stepBackwardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (currentNode != null) {
                if (currentNode.getPrev() != null) {
                    currentNode = currentNode.getPrev();
                    updateSortingWindow();
                }
            }
        });
    }

    public void updateProgressBar() {
        if (currentNode != null) {
            if (currentNode.getItem() != null) {
                // Get current node index
                Integer currentIndex = 0;
                Node tempNode = sortingModel.getData().getHead();
                while (tempNode != null) {
                    if (tempNode == currentNode)
                        break;
                    currentIndex++;
                    tempNode = tempNode.getNext();
                }

                // Get total node
                Integer totalNode = sortingModel.getData().getTotalNode();

                // Calculate progress
                Double progress = (double) currentIndex / totalNode;

                // Set progress
                sortingProgress.setProgress(progress);
            }
        }
    }

    public void updateSortingWindow() {
        makeChart();
        updateProgressBar();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add listener to the canvas container to resize the canvas
        // when contentPane is resized and change the width of progress bar
        // to contentPane width
        contentPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateSortingWindow();
            sortingProgress.setPrefWidth(newVal.doubleValue());
        });
        contentPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateSortingWindow();
        });
    }
}
