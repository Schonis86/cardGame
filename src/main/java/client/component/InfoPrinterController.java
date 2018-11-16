package client.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoPrinterController {

    @FXML
    private Label INFO_TEXT_INPUT;

    public void setINFO_TEXT_INPUT(String INFO_TEXT_INPUT) {
        this.INFO_TEXT_INPUT.setText(INFO_TEXT_INPUT);
    }
}
