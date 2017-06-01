/*
 * GNU License.
 */
package canachat;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Chat window.
 *
 * @author Breno Viana
 * @version 24/04/2017
 */
public class ChatWindow extends javax.swing.JFrame {

    // Cards names
    private final static String CHAT_SERVER_LOG_ON = "CHAT_SERVER_IP_ADDRESS";
    private final static String CHOOSE_CLENT_NAME = "CHOOSE_CLENT_NAME";
    private final static String CHOOSE_CLENT_LANGUAGE = "CHOOSE_CLENT_LANGUAGE";
    private final static String CHAT = "CHAT";

    // Languages 
    private final String[] languagesSTR = {
        Language.ENGLISH.getValue(),
        Language.PORTUGUESE.getValue(),
        Language.SPANISH.getValue(),
        Language.FRENCH.getValue(),
        Language.GERMAN.getValue()};
    private final Language[] languages = {
        Language.ENGLISH,
        Language.PORTUGUESE,
        Language.SPANISH,
        Language.FRENCH,
        Language.GERMAN};

    // Client handler
    private Handler handler;
    // Card layout
    private CardLayout card;

    /**
     * Creates new form ChatWindow.
     *
     * @param handler Client handler
     */
    public ChatWindow(Handler handler) {
        // Init handler
        this.handler = handler;
        initComponents();
        // Center window
        setLocationRelativeTo(this);
        // Init card layout
        this.card = new CardLayout();
        this.jMainPanel.setLayout(this.card);
        this.jMainPanel.add(this.jLogOnServerPanel, this.CHAT_SERVER_LOG_ON);
        this.jMainPanel.add(this.jEnterTheNamePanel, this.CHOOSE_CLENT_NAME);
        this.jMainPanel.add(this.jEnterTheLanguagePanel, this.CHOOSE_CLENT_LANGUAGE);
        this.jMainPanel.add(this.jChatPanel, CHAT);
    }

    public JTextField getJMessageTextField() {
        return this.jMessageTextField;
    }

    public JTextArea getJMessageArea() {
        return this.jMessageArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jChatPanel = new javax.swing.JPanel();
        jMessageTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMessageArea = new javax.swing.JTextArea();
        jEnterTheNamePanel = new javax.swing.JPanel();
        jClientNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jEnterButton = new javax.swing.JButton();
        jEnterTheLanguagePanel = new javax.swing.JPanel();
        jClientLanguageComboBox = new javax.swing.JComboBox(languagesSTR);
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jEnterButton1 = new javax.swing.JButton();
        jMainPanel = new javax.swing.JPanel();
        jLogOnServerPanel = new javax.swing.JPanel();
        jIPAddressTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLogInButton = new javax.swing.JButton();

        jMessageTextField.setEditable(false);
        jMessageTextField.setToolTipText("To send a message press Enter");
        jMessageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMessageTextFieldActionPerformed(evt);
            }
        });

        jMessageArea.setEditable(false);
        jMessageArea.setColumns(20);
        jMessageArea.setForeground(new java.awt.Color(51, 51, 51));
        jMessageArea.setRows(5);
        jScrollPane1.setViewportView(jMessageArea);

        javax.swing.GroupLayout jChatPanelLayout = new javax.swing.GroupLayout(jChatPanel);
        jChatPanel.setLayout(jChatPanelLayout);
        jChatPanelLayout.setHorizontalGroup(
                jChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jMessageTextField)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jChatPanelLayout.setVerticalGroup(
                jChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jChatPanelLayout.createSequentialGroup()
                        .addComponent(jMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
        );

        jClientNameTextField.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jClientNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jClientNameTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jLabel5.setText("Enter the your name or what do you want to be called:");
        jLabel5.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cana Chat");
        jLabel6.setRequestFocusEnabled(false);

        jEnterButton.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jEnterButton.setText("Enter");
        jEnterButton.setRequestFocusEnabled(false);
        jEnterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEnterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jEnterTheNamePanelLayout = new javax.swing.GroupLayout(jEnterTheNamePanel);
        jEnterTheNamePanel.setLayout(jEnterTheNamePanelLayout);
        jEnterTheNamePanelLayout.setHorizontalGroup(
                jEnterTheNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEnterTheNamePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jEnterTheNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jClientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jEnterButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jEnterTheNamePanelLayout.setVerticalGroup(
                jEnterTheNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEnterTheNamePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jClientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jEnterButton)
                        .addContainerGap(89, Short.MAX_VALUE))
        );

        jClientLanguageComboBox.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jClientLanguageComboBox.setBackground(Color.white);
        jClientLanguageComboBox.setEnabled(true);

        jLabel7.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jLabel7.setText("Choose a language:");
        jLabel7.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cana Chat");
        jLabel8.setRequestFocusEnabled(false);

        jEnterButton1.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jEnterButton1.setText("Enter");
        jEnterButton1.setRequestFocusEnabled(false);
        jEnterButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEnterButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jEnterTheLanguagePanelLayout = new javax.swing.GroupLayout(jEnterTheLanguagePanel);
        jEnterTheLanguagePanel.setLayout(jEnterTheLanguagePanelLayout);
        jEnterTheLanguagePanelLayout.setHorizontalGroup(
                jEnterTheLanguagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEnterTheLanguagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jEnterTheLanguagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jClientLanguageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jEnterButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jEnterTheLanguagePanelLayout.setVerticalGroup(
                jEnterTheLanguagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEnterTheLanguagePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jClientLanguageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jEnterButton1)
                        .addContainerGap(89, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CanaChat");

        jIPAddressTextField.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jIPAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIPAddressTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jLabel3.setText("Enter IP Address of the Server:");
        jLabel3.setRequestFocusEnabled(false);

        jLabel4.setFont(new java.awt.Font("Raleway Light", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cana Chat");
        jLabel4.setRequestFocusEnabled(false);

        jLogInButton.setFont(new java.awt.Font("Raleway Light", 1, 12)); // NOI18N
        jLogInButton.setText("Log In");
        jLogInButton.setRequestFocusEnabled(false);
        jLogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLogInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLogOnServerPanelLayout = new javax.swing.GroupLayout(jLogOnServerPanel);
        jLogOnServerPanel.setLayout(jLogOnServerPanelLayout);
        jLogOnServerPanelLayout.setHorizontalGroup(
                jLogOnServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLogOnServerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLogOnServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jIPAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLogInButton))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLogOnServerPanelLayout.setVerticalGroup(
                jLogOnServerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLogOnServerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jIPAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLogInButton)
                        .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jMainPanelLayout = new javax.swing.GroupLayout(jMainPanel);
        jMainPanel.setLayout(jMainPanelLayout);
        jMainPanelLayout.setHorizontalGroup(
                jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLogOnServerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jMainPanelLayout.setVerticalGroup(
                jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLogOnServerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMessageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMessageTextFieldActionPerformed
        this.handler.setOut(this.jMessageTextField.getText());
        this.jMessageTextField.setText("");
    }//GEN-LAST:event_jMessageTextFieldActionPerformed

    private void jLogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLogInButtonActionPerformed
        this.handler.setIPAddress(this.jIPAddressTextField.getText());
        this.card.show(this.jMainPanel, CHOOSE_CLENT_NAME);
        this.jClientNameTextField.requestFocusInWindow();
    }//GEN-LAST:event_jLogInButtonActionPerformed

    private void jEnterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEnterButtonActionPerformed
        this.handler.setName(this.jClientNameTextField.getText());
        this.card.show(this.jMainPanel, CHOOSE_CLENT_LANGUAGE);
        this.jClientLanguageComboBox.requestFocusInWindow();
    }//GEN-LAST:event_jEnterButtonActionPerformed

    private void jEnterButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEnterButton1ActionPerformed
        this.handler.setLanguage(this.languages[this.jClientLanguageComboBox.getSelectedIndex()]);
        this.card.show(this.jMainPanel, CHAT);
        this.jMessageTextField.requestFocusInWindow();
    }//GEN-LAST:event_jEnterButton1ActionPerformed

    private void jIPAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIPAddressTextFieldActionPerformed
        this.handler.setIPAddress(this.jIPAddressTextField.getText());
        this.card.show(this.jMainPanel, CHOOSE_CLENT_NAME);
        this.jClientNameTextField.requestFocusInWindow();
    }//GEN-LAST:event_jIPAddressTextFieldActionPerformed

    private void jClientNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jClientNameTextFieldActionPerformed
        this.handler.setName(this.jClientNameTextField.getText());
        this.card.show(this.jMainPanel, CHOOSE_CLENT_LANGUAGE);
        this.jClientLanguageComboBox.requestFocusInWindow();
    }//GEN-LAST:event_jClientNameTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jChatPanel;
    private javax.swing.JComboBox jClientLanguageComboBox;
    private javax.swing.JTextField jClientNameTextField;
    private javax.swing.JButton jEnterButton;
    private javax.swing.JButton jEnterButton1;
    private javax.swing.JPanel jEnterTheLanguagePanel;
    private javax.swing.JPanel jEnterTheNamePanel;
    private javax.swing.JTextField jIPAddressTextField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jLogInButton;
    private javax.swing.JPanel jLogOnServerPanel;
    private javax.swing.JPanel jMainPanel;
    private javax.swing.JTextArea jMessageArea;
    private javax.swing.JTextField jMessageTextField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
