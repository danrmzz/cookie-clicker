package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class CookieClicker extends JFrame {
    private JTextField counterField;

    public CookieClicker() {
        super("Cookie Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        SpringLayout springlayout = new SpringLayout();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(springlayout);

        // banner
        JLabel bannerImg = loadImage("resources/banner.png");

        jPanel.add(bannerImg);

        // cookie button
        JButton cookieButton = createImageButton("resources/cookie.png");
        cookieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int counter = Integer.parseInt(counterField.getText());
                counterField.setText(Integer.toString(++counter));
            }
        });

        jPanel.add(cookieButton);
        springlayout.putConstraint(SpringLayout.WEST, cookieButton, 127, SpringLayout.WEST, jPanel);
        springlayout.putConstraint(SpringLayout.NORTH, cookieButton, 175, SpringLayout.NORTH, jPanel);

        // counter
        JLabel counterLabel = new JLabel("Clicks: ");
        counterLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        jPanel.add(counterLabel);
        springlayout.putConstraint(SpringLayout.WEST, counterLabel, 124, SpringLayout.WEST, jPanel);
        springlayout.putConstraint(SpringLayout.NORTH, counterLabel, 550, SpringLayout.NORTH, jPanel);

        // counter field
        counterField = new JTextField(10);
        counterField.setFont(new Font("Dialog", Font.BOLD, 26));
        counterField.setHorizontalAlignment(SwingConstants.RIGHT);
        counterField.setText("0");
        counterField.setEditable(false);

        // Set black border
        counterField.setBorder(new LineBorder(Color.BLACK, 1));

        jPanel.add(counterField);
        springlayout.putConstraint(SpringLayout.WEST, counterField, 225, SpringLayout.WEST, jPanel);
        springlayout.putConstraint(SpringLayout.NORTH, counterField, 550, SpringLayout.NORTH, jPanel);





        // reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Dialogue", Font.BOLD, 18));

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counterField.setText("0");
            }
        });

        jPanel.add(resetButton);
        springlayout.putConstraint(SpringLayout.WEST, resetButton, 274, SpringLayout.WEST, jPanel);
        springlayout.putConstraint(SpringLayout.NORTH, resetButton, 615, SpringLayout.NORTH, jPanel);




        this.getContentPane().add(jPanel);
    }

    private JButton createImageButton (String fileName) {
        JButton button;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            Image image = ImageIO.read(inputStream);

            button = new JButton(new ImageIcon(image));
            // Set cursor to hand cursor when hovering over the button
            Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            button.setCursor(handCursor);

            // Remove blue highlight and border effects
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            return button;
        }catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }

    }

    private JLabel loadImage(String fileName) {
        BufferedImage image;
        JLabel imageContainer;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(fileName);
            image = ImageIO.read(inputStream);
            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        }catch(Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
