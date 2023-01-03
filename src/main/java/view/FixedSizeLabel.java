package view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FixedSizeLabel extends JLabel {

    public FixedSizeLabel(String text,  String tooltip) {
        super(text);
        setToolTipText(tooltip);
        setHorizontalAlignment(SwingConstants.RIGHT);
        // setBackground(Color.RED);
        // setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 30);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
