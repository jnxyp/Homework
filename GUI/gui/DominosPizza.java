package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

/**
 * <p>
 * <b>Dominos Pizza<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class DominosPizza extends JFrame implements ItemListener {
  // [CUSTOM SETTING]
  // Constants
  private final String BUSINESS_NAME   = "Domino's Pizza";
  private final int    BEVERAGES_LIMIT = 6;
  // When people select more than this, only three toppings that has lowest
  // price will be free.
  private final int    FREE_TOPPING        = 3;
  private final double TAX_RATE            = 0.13;
  private final double DELIVERY_FEE        = 3.00;
  private final double DELIVERY_FREE_LIMIT = 15.0;
  private final int    DELIVERY_TIME_LIMIT = 30;
  // Menu setting
  // Format: {"Display name", "Price"}
  private final String[][] sizes     = { { "Small", "7.99" }, { "Medium", "8.99" }, { "Large", "9.99" },
      { "Party", "10.99" } };
  private final String[][] toppings  = { { "Mushrooms", "1.00" }, { "Pepperoni", "1.00" }, { "Green Peppers", "1.00" },
      { "Bacon", "1.00" }, { "Onions", "1.00" }, { "Tomatoes", "1.00" }, { "Hot Peppers", "1.00" },
      { "Extra Cheese", "1.00" } };
  private final String[][] beverages = { { "Coke", "0.99" }, { "Sprite", "0.99" }, { "Orange", "0.99" },
      { "Root Beer", "0.99" } };
  // Trademark setting
  private final ImageIcon noticePic = new ImageIcon(getClass().getResource("OptionPane.png"));
  private final ImageIcon topPic    = new ImageIcon(getClass().getResource("DominosLogo.png"));

  // GUI Components
  private JPanel  mainPanel, sizePanel, toppingPanel, beveragesPanel, pricePanel, buttonPanel;
  private JLabel  topPicLbl, midNoticeLbl, leftPaymentLbl, subTotalLbl, deliveryLbl, hstLbl, grandTotalLbl;
  private JLabel  sizePriceLbl, toppingsPriceLbl, beveragesPriceLbl;
  private JLabel  subTotalPriceLbl, deliveryPriceLbl, hstPriceLbl, grandTotalPriceLbl;
  private JButton calcBtn, clearBtn, checkoutBtn, exitBtn;
  // Arrays to contain components
  private JRadioButton[]       sizeRBtns;
  private JCheckBox[]          toppingCKBoxes;
  private JComboBox<Integer>[] beveragesCBBoxes;
  private JLabel[]             beveragesLabels;
  // GUI Variables
  private final Dimension    frameSize         = new Dimension(540, 640);
  private final Dimension    priceLableMinSize = new Dimension(80, 15);
  private final ImageIcon    paymentPic        = new ImageIcon(getClass().getResource("PaymentOptions.png"));
  private GridBagConstraints gBC               = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
      GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 5, 5);
  private ButtonGroup        sizeBtnGroup      = new ButtonGroup();
  private final Border       defaultBorder     = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
  // Other Variables
  private final DecimalFormat decFmt = new DecimalFormat("$#,##0.00");

  public DominosPizza() {
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(frameSize);
    setTitle("Domino's Pizza");

    // Initialize and add components
    // [[Main Panel]]
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    // Other Panels
    // [topPicLbl]
    topPicLbl = new JLabel();
    topPicLbl.setIcon(topPic);
    topPicLbl.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 0, 3, 1, 1, 0.2, GridBagConstraints.HORIZONTAL);
    mainPanel.add(topPicLbl, gBC);

    // [sizePanel]
    sizePanel = new JPanel();
    sizeRBtns = new JRadioButton[sizes.length];
    // Generate radio buttons for size
    for (int i = 0; i < sizes.length; i++) {
      sizeRBtns[i] = new JRadioButton(sizes[i][0]);
      sizeRBtns[i].setToolTipText(sizes[i][1]);
      sizeRBtns[i].addItemListener(this);
      sizePanel.add(sizeRBtns[i]);
      sizeBtnGroup.add(sizeRBtns[i]);
    }
    sizePanel.setBorder(BorderFactory.createTitledBorder(defaultBorder, "SIZE"));
    sizePanel.setLayout(new GridLayout(sizeRBtns.length, 1));
    gBCSetting(0, 1, 1, 1, 0.33, 0.2, GridBagConstraints.BOTH);
    mainPanel.add(sizePanel, gBC);

    // [toppingPanel]
    toppingPanel = new JPanel();
    toppingCKBoxes = new JCheckBox[toppings.length];
    // Generate check boxes for toppings
    for (int i = 0; i < toppings.length; i++) {
      toppingCKBoxes[i] = new JCheckBox(toppings[i][0]);
      toppingCKBoxes[i].setToolTipText(toppings[i][1]);
      toppingCKBoxes[i].addItemListener(this);
      toppingPanel.add(toppingCKBoxes[i]);
    }
    toppingPanel.setBorder(BorderFactory.createTitledBorder(defaultBorder, "TOPPINGS"));
    toppingPanel.setLayout(new GridLayout((int) Math.round(toppingCKBoxes.length / 2.0), 2));
    gBCSetting(1, 1, 1, 1, 0.34, 0.2, GridBagConstraints.BOTH);
    mainPanel.add(toppingPanel, gBC);

    // [beveragesPanel]
    beveragesPanel = new JPanel();
    beveragesLabels = new JLabel[beverages.length];
    beveragesCBBoxes = new JComboBox[beverages.length];
    // Generate combo boxes for beverages
    for (int i = 0; i < beverages.length; i++) {
      beveragesLabels[i] = new JLabel(beverages[i][0]);
      beveragesLabels[i].setToolTipText(beverages[i][1]);
      beveragesCBBoxes[i] = new JComboBox<Integer>();
      // Generate items in each combo box
      for (Integer j = 0; j <= BEVERAGES_LIMIT; j++) {
        beveragesCBBoxes[i].addItem(j);
      }
      beveragesCBBoxes[i].addItemListener(this);
      beveragesPanel.add(beveragesLabels[i]);
      beveragesPanel.add(beveragesCBBoxes[i]);
    }
    beveragesPanel.setBorder(BorderFactory.createTitledBorder(defaultBorder, "BEVERAGES"));
    beveragesPanel.setLayout(new GridLayout(beveragesCBBoxes.length, 2, 10, 10));
    gBCSetting(2, 1, 1, 1, 0.33, 0.2, GridBagConstraints.BOTH);
    mainPanel.add(beveragesPanel, gBC);

    // [midNoticeLbl]
    String number;
    // Display number
    switch (FREE_TOPPING) {
      case 1:
        number = "one";
        break;
      case 2:
        number = "two";
        break;
      case 3:
        number = "three";
        break;
      case 4:
        number = "four";
        break;
      case 5:
        number = "five";
        break;
      case 6:
        number = "six";
        break;
      case 7:
        number = "seven";
        break;
      case 8:
        number = "eight";
        break;
      case 9:
        number = "nine";
        break;
      default:
        number = "";
        break;
    }
    midNoticeLbl = new JLabel("First " + number + "(" + FREE_TOPPING + ") toppings are FREE!");
    midNoticeLbl.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(1, 2, 1, 1, 1, 0.01, GridBagConstraints.BOTH);
    mainPanel.add(midNoticeLbl, gBC);

    // [sizePriceLbl]
    sizePriceLbl = new JLabel(decFmt.format(0));
    sizePriceLbl.setHorizontalAlignment(JLabel.CENTER);
    sizePriceLbl.setBorder(defaultBorder);
    sizePriceLbl.setPreferredSize(priceLableMinSize);
    gBCSetting(0, 3, 1, 1, 0.33, 0.01, GridBagConstraints.NONE);
    gBC.insets = new Insets(0, 20, 0, 20);
    mainPanel.add(sizePriceLbl, gBC);

    // [toppingsPriceLbl]
    toppingsPriceLbl = new JLabel(decFmt.format(0));
    toppingsPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    toppingsPriceLbl.setBorder(defaultBorder);
    toppingsPriceLbl.setPreferredSize(priceLableMinSize);
    gBCSetting(1, 3, 1, 1, 0.34, 0.01, GridBagConstraints.NONE);
    mainPanel.add(toppingsPriceLbl, gBC);

    // [beveragesPriceLbl]
    beveragesPriceLbl = new JLabel(decFmt.format(0));
    beveragesPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    beveragesPriceLbl.setBorder(defaultBorder);
    beveragesPriceLbl.setPreferredSize(priceLableMinSize);
    gBCSetting(2, 3, 1, 1, 0.33, 0.01, GridBagConstraints.NONE);
    mainPanel.add(beveragesPriceLbl, gBC);

    // [leftPaymentLbl]
    leftPaymentLbl = new JLabel();
    leftPaymentLbl.setIcon(paymentPic);
    leftPaymentLbl.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 4, 1, 1, 0.33, 0.2, GridBagConstraints.BOTH);
    gBC.insets = new Insets(1, 1, 1, 1);
    mainPanel.add(leftPaymentLbl, gBC);

    // [pricePanel]
    pricePanel = new JPanel();
    pricePanel.setLayout(new GridLayout(4, 2, 20, 20));
    subTotalLbl = new JLabel("SUBTOTAL:");
    subTotalPriceLbl = new JLabel(decFmt.format(0));
    subTotalPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    subTotalPriceLbl.setBorder(defaultBorder);
    deliveryLbl = new JLabel("DELIVERY FEE:");
    deliveryPriceLbl = new JLabel(decFmt.format(0));
    deliveryPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    deliveryPriceLbl.setBorder(defaultBorder);
    deliveryPriceLbl.setOpaque(true);
    hstLbl = new JLabel("HST:");
    hstPriceLbl = new JLabel(decFmt.format(0));
    hstPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    hstPriceLbl.setBorder(defaultBorder);
    grandTotalLbl = new JLabel("GRAND TOTAL:");
    grandTotalPriceLbl = new JLabel(decFmt.format(0));
    grandTotalPriceLbl.setHorizontalAlignment(JLabel.CENTER);
    grandTotalPriceLbl.setBorder(defaultBorder);
    pricePanel.add(subTotalLbl);
    pricePanel.add(subTotalPriceLbl);
    pricePanel.add(deliveryLbl);
    pricePanel.add(deliveryPriceLbl);
    pricePanel.add(hstLbl);
    pricePanel.add(hstPriceLbl);
    pricePanel.add(grandTotalLbl);
    pricePanel.add(grandTotalPriceLbl);
    gBCSetting(1, 4, 1, 1, 0.34, 0.2, GridBagConstraints.BOTH);
    gBC.insets = new Insets(20, 20, 20, 20);
    mainPanel.add(pricePanel, gBC);
    gBC.insets = new Insets(1, 1, 1, 1);

    // [buttonPanel]
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 1, 20, 20));
    // CALCULATE Button
    calcBtn = new JButton("CALCULATE");
    buttonPanel.add(calcBtn);
    calcBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        calculatePrice();
        // Enable CHECKOUT button
        checkoutBtn.setEnabled(true);
      }
    });
    // CLEAR Button
    clearBtn = new JButton("CLEAR");
    buttonPanel.add(clearBtn);
    clearBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Clear selections
        sizeBtnGroup.clearSelection();
        for (JCheckBox box : toppingCKBoxes) {
          box.setSelected(false);
        }
        for (JComboBox<Integer> cbBox : beveragesCBBoxes) {
          cbBox.setSelectedIndex(0);
        }
        // Clear values
        updatePrice();
        calculatePrice();
        // Set delivery fee label to 0
        deliveryPriceLbl.setText(decFmt.format(0));
        grandTotalPriceLbl.setText(decFmt.format(0));
      }
    });
    // CHECKOUT Button
    checkoutBtn = new JButton("CHECKOUT");
    checkoutBtn.setEnabled(false);
    buttonPanel.add(checkoutBtn);
    checkoutBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean sizeSelected = false;
        boolean toppingSelected = false;
        for (JRadioButton rdBtn : sizeRBtns) {
          if (rdBtn.isSelected()) {
            sizeSelected = true;
            break;
          }
        }
        for (JCheckBox ckBox : toppingCKBoxes) {
          if (ckBox.isSelected()) {
            toppingSelected = true;
            break;
          }
        }
        if (sizeSelected && toppingSelected) {
          if (showQuestionDialog(BUSINESS_NAME, "Is this order correct?") == JOptionPane.YES_OPTION) {
            showCustomDialog(BUSINESS_NAME,
                "Thank you for ordering from " + BUSINESS_NAME + " !\nYour pizza will be delivered in "
                    + DELIVERY_TIME_LIMIT + " minutes or it's free!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, noticePic);
            System.exit(0);
          }
        }
        else if (!sizeSelected) {
          showErrDialog("Critical error!", "Your order could not be completed!\nPlease select a pizza size.");
        }
        else if (!toppingSelected) {
          showErrDialog("Critical error!", "Your order could not be completed!\nPlease select at least one topping!");
        }
      }
    });
    // EXIT Button
    exitBtn = new JButton("EXIT");
    buttonPanel.add(exitBtn);
    exitBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showExitConfirm();
      }
    });
    gBCSetting(2, 4, 1, 1, 0.33, 0.2, GridBagConstraints.BOTH);
    gBC.insets = new Insets(20, 10, 20, 10);
    mainPanel.add(buttonPanel, gBC);

    // Set visible
    setContentPane(mainPanel);
    setVisible(true);
  }

  public static void main(String[] args) {
    new DominosPizza();
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // Disable CHECKOUT button
    checkoutBtn.setEnabled(false);
    updatePrice();
  }

  // This method will calculate the price in each part
  private void updatePrice() {
    // Size price
    for (JRadioButton rBtn : sizeRBtns) {
      if (rBtn.isSelected()) {
        sizePriceLbl.setText(decFmt.format(Double.parseDouble(rBtn.getToolTipText())));
        break;
      }
      sizePriceLbl.setText(decFmt.format(0));
    }
    // Topping price
    ArrayList<Double> temp = new ArrayList<>();
    for (JCheckBox ckBox : toppingCKBoxes) {
      if (ckBox.isSelected()) {
        temp.add(Double.parseDouble(ckBox.getToolTipText()));
      }
    }
    double s = 0;
    if (temp.size() > FREE_TOPPING) {
      Collections.sort(temp);
      for (int i = 0; i < FREE_TOPPING; i++) {
        temp.remove(0);
      }
      for (Double num : temp) {
        s += num;
      }
    }
    toppingsPriceLbl.setText(decFmt.format(s));
    // Beverages price
    double b = 0;
    for (int i = 0; i < beverages.length; i++) {
      b += (int) beveragesCBBoxes[i].getSelectedItem() * Double.parseDouble(beveragesLabels[i].getToolTipText());
    }
    beveragesPriceLbl.setText(decFmt.format(b));
  }

  // This method will calculate the final price of delivery
  private void calculatePrice() {
    double temp;
    temp = toNumber(sizePriceLbl.getText()) + toNumber(toppingsPriceLbl.getText())
        + toNumber(beveragesPriceLbl.getText());
    subTotalPriceLbl.setText(decFmt.format(temp));
    hstPriceLbl.setText(decFmt.format(temp * TAX_RATE));
    if (temp >= DELIVERY_FREE_LIMIT) {
      deliveryPriceLbl.setText("FREE");
      deliveryPriceLbl.setBackground(Color.GREEN);
      grandTotalPriceLbl.setText(decFmt.format(temp * TAX_RATE + temp));
    }
    else if (temp == 0) {
      deliveryPriceLbl.setBackground(null);
      deliveryPriceLbl.setText(decFmt.format(0));
    }
    else {
      deliveryPriceLbl.setBackground(null);
      deliveryPriceLbl.setText(decFmt.format(DELIVERY_FEE));
      grandTotalPriceLbl.setText(decFmt.format(temp * TAX_RATE + temp + DELIVERY_FEE));
    }
  }

  // These methods will show different confirm dialogs to user
  private void showExitConfirm() {
    int tmp = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", BUSINESS_NAME,
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (tmp == JOptionPane.YES_OPTION) {
      showMsgDialog(BUSINESS_NAME, "Thank you for choosing " + BUSINESS_NAME);
      System.exit(0);
    }
  }

  private void showErrDialog(String title, String message) {
    JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
  }

  private void showMsgDialog(String title, String message) {
    JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
  }

  private int showQuestionDialog(String title, String message) {
    return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  }

  private int showCustomDialog(String title, String message, int optionType, int messageType, Icon icon) {
    return JOptionPane.showConfirmDialog(this, message, title, optionType, messageType, icon);
  }

  // This method can convert String "$xxxx.xx" to a double
  private double toNumber(String num) {
    num = num.substring(1);
    return Double.parseDouble(num);
  }

  // This method can set the properties of Grid Bag Constraint
  private void gBCSetting(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
      int fill) {
    gBC.gridx = gridx;
    gBC.gridy = gridy;
    gBC.gridwidth = gridwidth;
    gBC.gridheight = gridheight;
    gBC.weightx = weightx;
    gBC.weighty = weighty;
    gBC.fill = fill;
  }
}
