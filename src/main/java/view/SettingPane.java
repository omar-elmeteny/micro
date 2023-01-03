package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

public class SettingPane extends JPanel {

    private final String settingName;
    private final String settingDescription;
    private final int initialValue;
    private final int minValue;
    private final int maxValue;

    private FixedSizeField valueField;
    private JLabel descriptionLabel;

    public SettingPane(String settingName, String settingDescription, int initialValue, int minValue, int maxValue) {
        super();
        this.settingName = settingName;
        this.settingDescription = settingDescription;
        this.initialValue = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;

        descriptionLabel = new FixedSizeLabel(settingName, settingDescription);

        valueField = new FixedSizeField(Integer.toString(initialValue));

        BoxLayout mgr = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(mgr);
        add(descriptionLabel);
        add(Box.createRigidArea(new Dimension(5, 0)));
        add(valueField);


    }


    public String getSettingName() {
        return settingName;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getValue() throws SettingsException {
        String s = valueField.getValue().trim();
        try {

        int v =  Integer.parseInt(s);
        if (v < minValue || v > maxValue) {
            throw new SettingsException("Invalid value for " + settingName + ": " + v + ". Value must be an integer between " + minValue + " and " + maxValue + ".");
        } else {
            return v;
        }
        } catch (NumberFormatException e) {
            throw new SettingsException("Invalid value for " + settingName + ": " + s + ". Value must be an integer between " + minValue + " and " + maxValue + ".", e);
        }
    }

}


