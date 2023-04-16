package Theme;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;

public class MyTheme {
    public static Color themeColor = new Color(10, 133, 204);
    public static Color hoverColor = new Color(3, 116, 168);
    public static Color greenColor = new Color(24, 168, 40);
    public static Color yellowColor = new Color(255, 193, 7);
    public static Color redColor = new Color(220, 53, 69);

    public static class MyButtonBlue extends JButton implements MouseListener {
        public MyButtonBlue(String text) {
            super(text);
            setBackground(themeColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(themeColor));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
        }

        // Implement MouseListener methods
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor); // Set background color when mouse enters
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(themeColor); // Set background color back to default when mouse exits
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    public static class MyButtonWhite extends MyButtonBlue {
        public MyButtonWhite(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(Color.GRAY);
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(Color.GRAY);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(Color.GRAY);
        }
    }

    public static class MyButtonGreen extends MyButtonBlue {
        public MyButtonGreen(String text) {
            super(text);
            setBackground(greenColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(greenColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(8, 120, 21));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(greenColor);
        }

    }

    public static class MyButtonYellow extends MyButtonBlue {
        public MyButtonYellow(String text) {
            super(text);
            setBackground(yellowColor);
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(yellowColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(224, 168, 0));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(yellowColor);
        }

    }

    public static class MyButtonRed extends MyButtonBlue {
        public MyButtonRed(String text) {
            super(text);
            setBackground(redColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(redColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(200, 35, 51));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(redColor);
        }

    }

    public static class MyButtonHoverBlue extends MyButtonBlue {
        public MyButtonHoverBlue(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(themeColor);
            setBorder(BorderFactory.createLineBorder(themeColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(themeColor);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(themeColor);
        }

    }

    public static class MyButtonHoverGreen extends MyButtonBlue {
        public MyButtonHoverGreen(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(greenColor);
            setBorder(BorderFactory.createLineBorder(greenColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(greenColor);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(greenColor);
        }
    }

    public static class MyButtonHoverRed extends MyButtonBlue {
        public MyButtonHoverRed(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(redColor);
            setBorder(BorderFactory.createLineBorder(redColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(redColor);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(redColor);
        }
    }

    public static class MyButtonHoverYellow extends MyButtonBlue {
        public MyButtonHoverYellow(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(yellowColor);
            setBorder(BorderFactory.createLineBorder(yellowColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(yellowColor);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(yellowColor);
        }
    }

    public static class DashboardCountButton extends MyButtonBlue {
        public DashboardCountButton(int count, String label, Color buttonColor) {
            super("<html><div style='text-align: right;'><font size='6'><b>" + count + "</b></font><br><font size='3'>"
                    + label + "</font></div></html>");
            setOpaque(true);
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(buttonColor));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(getBackground().darker());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(getBackground().brighter());
        }
    }

    public static class JMenuItemTheme extends JMenuItem implements MouseListener {
        public JMenuItemTheme(String text) {
            super(text);
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(5, 9, 5, 10));
            setBackground(themeColor);
            setForeground(Color.WHITE);
            setHorizontalAlignment(CENTER);
            setFont(new Font("Calibri", Font.BOLD, 16));
            addMouseListener(this);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
            setFont(new Font("Calibri", Font.BOLD, 18));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(themeColor);
            setFont(new Font("Calibri", Font.BOLD, 16));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}
