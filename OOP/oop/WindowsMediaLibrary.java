package oop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowsMediaLibrary extends JFrame implements ActionListener {

  private String[]     headings;
  private JLabel[]     labels;
  private JTextField[] fields;
  private JButton      btnForward, btnBack, btnAdd;

  public static void main(String[] args) {

    new WindowsMediaLibrary();
  }

  public WindowsMediaLibrary() {

    headings = new String[] { "TITLE", "ARTIST", "LENGTH (hh:mm:ss)", "FILE SIZE (KB)", "GENRE", "RATING (0-5)" };

    ImageIcon imgLogo = new ImageIcon("images\\windows_media_player_logo.png");
    JLabel lblLogo = new JLabel(imgLogo);
    lblLogo.setPreferredSize(new Dimension(imgLogo.getIconWidth(), imgLogo.getIconHeight() + 20));

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panel.add(lblLogo);

    labels = new JLabel[headings.length];
    fields = new JTextField[headings.length];

    for (int i = 0; i < labels.length; i++) {
      labels[i] = new JLabel();
      labels[i].setText(headings[i] + ":");
      labels[i].setPreferredSize(new Dimension(120, 30));
      labels[i].setFont(new Font("Arial", Font.PLAIN, 12));
      panel.add(labels[i]);

      fields[i] = new JTextField(15);
      fields[i].setEditable(false);
      fields[i].setBackground(Color.WHITE);
      panel.add(fields[i]);
    }

    btnBack = new JButton("BACK");
    btnBack.setPreferredSize(new Dimension(90, 30));
    btnBack.setFont(new Font("Arial", Font.PLAIN, 11));
    btnBack.setFocusable(false);
    btnBack.addActionListener(this);

    btnAdd = new JButton("ADD");
    btnAdd.setPreferredSize(new Dimension(90, 30));
    btnAdd.setFont(new Font("Arial", Font.PLAIN, 11));
    btnAdd.setFocusable(false);
    btnAdd.addActionListener(this);

    btnForward = new JButton("FORWARD");
    btnForward.setPreferredSize(new Dimension(90, 30));
    btnForward.setFont(new Font("Arial", Font.PLAIN, 11));
    btnForward.setFocusable(false);
    btnForward.addActionListener(this);

    panel.add(btnBack);
    panel.add(btnAdd);
    panel.add(btnForward);

    setContentPane(panel);
    setSize(350, 450);
    setTitle("Windows Media Player");
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAdd) {
      if (btnAdd.getText().equals("ADD")) {
        for (int i = 0; i < fields.length; i++) {
          fields[i].setEditable(true);
          fields[i].setText("");
        }

        btnBack.setEnabled(false);
        btnForward.setEnabled(false);
        btnAdd.setText("SAVE");
        fields[0].requestFocus();
      }
      else {
        for (int i = 0; i < fields.length; i++) {
          fields[i].setEditable(false);
        }

        btnBack.setEnabled(true);
        btnForward.setEnabled(true);
        btnAdd.setText("ADD");
        fields[0].requestFocus();
      }
    }
    else if (e.getSource() == btnForward) {

    }
    else {

    }
  }
}
