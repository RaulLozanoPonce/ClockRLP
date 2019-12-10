package view;

import model.Clock;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ClockView extends javax.swing.JFrame {

    public ClockView() {
        initComponents();
        clock.getClock().getNumbers().forEach((k,v) -> numberTypeComboBox.addItem(k));
        numberTypeComboBox.setSelectedIndex(0);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                clock.newSecond();
                repaint();
            }
        };
        timer.schedule(timerTask, 0, 50);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        numberTypeComboBox = new javax.swing.JComboBox<>();
        numberTypeComboBox.addActionListener(this::numberTypeComboBoxActionPerformed);
        add(numberTypeComboBox, BorderLayout.PAGE_START);

        clock = new controller.ClockController();
        clock.setPreferredSize(new Dimension(400, 400));
        add(clock, BorderLayout.CENTER);

        pack();
    }

    private void numberTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        clock.setNumberType(numberTypeComboBox.getItemAt(numberTypeComboBox.getSelectedIndex()));
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClockView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ClockView().setVisible(true));
    }

    private controller.ClockController clock;
    private javax.swing.JComboBox<String> numberTypeComboBox;
}
