package org.example.tipcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipController {

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentLabel;


    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    public void initialize() {
        tipPercentLabel.setText(percent.format(tipPercentageSlider.getValue() / 100));

        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipPercentLabel.setText(percent.format(newValue.doubleValue() / 100));
            calculateTip();
        });
    }

    private void calculateTip() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tipPercentage = BigDecimal.valueOf(tipPercentageSlider.getValue()).divide(BigDecimal.valueOf(100));
            BigDecimal tip = amount.multiply(tipPercentage).setScale(2, RoundingMode.HALF_UP);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter valid amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

}
