package view;

import java.util.Timer;
import java.util.TimerTask;

public class ClockView extends javax.swing.JFrame {

    private Timer timer;
    private TimerTask timerTask;
    
    public ClockView() {
        initComponents();
        clock.getClock().getNumbers().forEach((k,v) -> numberTypeComboBox.addItem(k));
        numberTypeComboBox.setSelectedIndex(0);
        
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                clock.newSecond();
                repaint();
            }
        };
        timer.schedule(timerTask, 0, 50);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numberTypeComboBox = new javax.swing.JComboBox<>();
        clock = new controller.ClockController();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        numberTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberTypeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clockLayout = new javax.swing.GroupLayout(clock);
        clock.setLayout(clockLayout);
        clockLayout.setHorizontalGroup(
            clockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        clockLayout.setVerticalGroup(
            clockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
            .addComponent(clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(numberTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numberTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberTypeComboBoxActionPerformed
        clock.setNumberType(numberTypeComboBox.getItemAt(numberTypeComboBox.getSelectedIndex()));
    }//GEN-LAST:event_numberTypeComboBoxActionPerformed

    public static void main(String args[]) {
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
        java.awt.EventQueue.invokeLater(() -> { new ClockView().setVisible(true); });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private controller.ClockController clock;
    private javax.swing.JComboBox<String> numberTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
