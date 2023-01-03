package view;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;

public class FixedSizeField extends JPanel {

    private final JTextField valueField;
    public FixedSizeField(String value) {
        super();
        valueField = new JTextField(value);
        Dimension d = new Dimension(50, 20);
        valueField.setMaximumSize(d);
        valueField.setMinimumSize(d);
        valueField.setPreferredSize(d);

        this.setLayout(new BorderLayout());

        this.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.WEST);
        this.add(valueField, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(300, 5)), BorderLayout.EAST);
        this.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.SOUTH);

        valueField.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Dimension getMinimumSize() {
        return valueField.getMinimumSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 40);
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public String getValue() {
        return valueField.getText();
    }

    public void setValue(String value) {
        valueField.setText(value);
    }
}
