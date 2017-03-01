package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * <p>
 * <b>Registration Form</b> according to 10_Layout_Exercises.pdf
 * </p>
 * 
 * @author Xin (jn_xyp)
 * @version 2017-02-06
 */
public class RegistrationForm extends JFrame {
  // GUI Variables
  private JPanel       mainPanel, genderPanel, gradePanel, buttonPanel;
  private JLabel       firstNameLbl, lastNameLbl, numberLbl;
  private JTextField   firstNameTFld, lastNameTFld, numberTFld;
  private ButtonGroup  genderBtnGrp;
  private JRadioButton maleRBtn, femaleRBtn;
  private ButtonGroup  gradeBtnGrp;
  private JRadioButton grade9RBtn, grade10RBtn, grade11RBtn, grade12RBtn;
  private JButton      okBtn, clearBtn, exitBtn;

  private GridBagConstraints gc          = new GridBagConstraints();
  private ActionListener     btnListener = new ButtonListener();

  public RegistrationForm() {
    // Set frame properties
    setTitle("Registration Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(330, 350);
    // setResizeable (false);
    // Initialize containers
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    genderPanel = new JPanel();
    genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
    genderPanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "GENDER"));

    gradePanel = new JPanel();
    gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
    gradePanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), "GRADE"));

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 1, 10, 20));

    // Initialize button groups
    genderBtnGrp = new ButtonGroup();
    gradeBtnGrp = new ButtonGroup();

    // Initialize components
    firstNameLbl = new JLabel("First Name:");
    lastNameLbl = new JLabel("Last Name:");
    numberLbl = new JLabel("Student Number:");

    firstNameTFld = new JTextField(15);
    lastNameTFld = new JTextField(15);
    numberTFld = new JTextField(15);

    maleRBtn = new JRadioButton("Male");
    femaleRBtn = new JRadioButton("Female");

    grade9RBtn = new JRadioButton("Grade 9");
    grade10RBtn = new JRadioButton("Grade 10");
    grade11RBtn = new JRadioButton("Grade 11");
    grade12RBtn = new JRadioButton("Grade 12");

    okBtn = new JButton("OK");
    okBtn.addActionListener(btnListener);
    clearBtn = new JButton("CLEAR");
    clearBtn.addActionListener(btnListener);
    exitBtn = new JButton("EXIT");
    exitBtn.addActionListener(btnListener);

    // Add buttons to group
    genderBtnGrp.add(femaleRBtn);
    genderBtnGrp.add(maleRBtn);

    gradeBtnGrp.add(grade9RBtn);
    gradeBtnGrp.add(grade10RBtn);
    gradeBtnGrp.add(grade11RBtn);
    gradeBtnGrp.add(grade12RBtn);

    // Add components
    genderPanel.add(maleRBtn);
    genderPanel.add(femaleRBtn);

    gradePanel.add(grade9RBtn);
    gradePanel.add(grade10RBtn);
    gradePanel.add(grade11RBtn);
    gradePanel.add(grade12RBtn);

    buttonPanel.add(okBtn);
    buttonPanel.add(clearBtn);
    buttonPanel.add(exitBtn);

    // Add components to main panel using grid bag layout
    gc.gridx = 0;
    gc.gridy = 0;
    gc.gridwidth = 1;
    gc.gridheight = 1;
    gc.weightx = 0.0;
    gc.weighty = 0.0;
    gc.anchor = GridBagConstraints.WEST;
    gc.insets = new Insets(10, 10, 10, 10);
    mainPanel.add(firstNameLbl, gc);

    gc.gridy = 1;

    mainPanel.add(lastNameLbl, gc);

    gc.gridy = 2;

    mainPanel.add(numberLbl, gc);

    gc.gridx = 1;
    gc.gridy = 0;
    gc.gridwidth = 2;
    gc.gridheight = 1;
    gc.weightx = 1.0;
    gc.weighty = 0.1;
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.EAST;

    mainPanel.add(firstNameTFld, gc);

    gc.gridy = 1;

    mainPanel.add(lastNameTFld, gc);

    gc.gridy = 2;

    mainPanel.add(numberTFld, gc);

    gc.gridx = 0;
    gc.gridy = 3;
    gc.weightx = 0.0;
    gc.weighty = 0.0;
    gc.gridwidth = 1;
    gc.gridheight = 1;
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.CENTER;

    mainPanel.add(genderPanel, gc);

    gc.gridx = 1;

    mainPanel.add(gradePanel, gc);

    gc.gridx = 2;

    mainPanel.add(buttonPanel, gc);
    // Set visible
    setContentPane(mainPanel);
    setVisible(true);

  }

  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == okBtn) {
        boolean isInvalid = false;
        // Check if any text field is empty
        if (firstNameTFld.getText().isEmpty() || lastNameTFld.getText().isEmpty() || numberTFld.getText().isEmpty()) {
          isInvalid = true;
        }
        // Check if any button group is not selected
        if (!isInvalid) {
          if ((!btnGroupSelected(genderBtnGrp)) || (!btnGroupSelected(gradeBtnGrp))) {
            isInvalid = true;
          }
        }
        // If invalid, show option pane
        if (isInvalid) {
          showInvalidNotice();
        }
        else {
          System.exit(0);
        }
      }
      else if (e.getSource() == clearBtn) {
        firstNameTFld.setText(null);
        lastNameTFld.setText(null);
        numberTFld.setText(null);
        genderBtnGrp.clearSelection();
        gradeBtnGrp.clearSelection();
      }
      else if (e.getSource() == exitBtn) {
        System.exit(0);
      }
    }
  }

  /**
   * This method will take a ButtonGroup and check if any elements inside it is
   * selected.
   * 
   * @param g
   *          The ButtonGroup
   * @return if any element is selected
   */
  private boolean btnGroupSelected(ButtonGroup g) {
    boolean isSelected = false;
    Enumeration<AbstractButton> buttons = g.getElements();
    while (buttons.hasMoreElements()) {
      if (buttons.nextElement().isSelected()) {
        isSelected = true;
        break;
      }
    }
    return isSelected;
  }

  private void showInvalidNotice() {
    JOptionPane.showConfirmDialog(this, "Invalid Input", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
  }

  public static void main(String[] args) {
    new RegistrationForm();
  }
}
