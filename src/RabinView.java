import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class RabinView extends JFrame {


    private final JTextArea privateKey1Area;
    private final JTextArea privateKey2Area;
    private final JTextArea publicKeyArea;
    private final JButton generateKeysButton;



    private final JTextArea plainTextField;
    private final JTextArea cipherTextField;

    private final JButton encryptButton;
    private final JButton decryptButton;

    private final JTextArea resultArea;


    RabinView() {

//        addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//                int width = getContentPane().getWidth();
//                int height = getContentPane().getHeight();
//                System.out.println("Current size: " + width + "x" + height + " px");
//            }
//        });

        JPanel rabinPanel = new JPanel(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);


        JPanel keyPanel = new JPanel(new GridBagLayout());
        keyPanel.setBorder(new TitledBorder("Keys"));


        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblPrivateKey1 = new JLabel("Private Key 1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(lblPrivateKey1, gbc);

        privateKey1Area = new JTextArea(5,25);
        privateKey1Area.setLineWrap(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(privateKey1Area, gbc);

        JLabel lblPrivateKey2 = new JLabel("Private Key 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(lblPrivateKey2, gbc);

        privateKey2Area = new JTextArea(5,25);
        privateKey2Area.setLineWrap(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(privateKey2Area, gbc);

        JLabel lblPublicKey = new JLabel("Public Key");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(lblPublicKey, gbc);

        publicKeyArea = new JTextArea(4, 25);
        publicKeyArea.setLineWrap(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(publicKeyArea, gbc);

        generateKeysButton = new JButton("Generate Keys");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;

        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        keyPanel.add(generateKeysButton, gbc);



//        Start of Encrypt Decrypt Panel

        JPanel encryptDecryptPanel = new JPanel(new GridBagLayout());
        encryptDecryptPanel.setBorder(new TitledBorder("Encrypt/Decrypt"));

        JLabel lblEncrypt= new JLabel("Plain Text");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(lblEncrypt, gbc);

        plainTextField = new JTextArea(5,25);
        plainTextField.setLineWrap(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(plainTextField, gbc);

        JLabel lblDecrypt= new JLabel("Cipher Text");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(lblDecrypt, gbc);

        cipherTextField = new JTextArea(5,25);
        cipherTextField.setLineWrap(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(cipherTextField, gbc);

        encryptButton = new JButton("Encrypt");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(encryptButton, gbc);

        decryptButton = new JButton("Decrypt");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        encryptDecryptPanel.add(decryptButton, gbc);



//      Result Panel
        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBorder(new TitledBorder("Result"));

        resultArea = new JTextArea(15,25);
        resultArea.setLineWrap(true);


        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 5;

        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5,75,5,75);
        resultPanel.add(resultArea, gbc);


//        Adding to main window

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;
        rabinPanel.add(keyPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;
        rabinPanel.add(encryptDecryptPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,5,0,5);
        gbc.fill = GridBagConstraints.BOTH;
        rabinPanel.add(resultPanel, gbc);

        this.add(rabinPanel);

    }

    public String getPrivateKey1() {
        return privateKey1Area.getText();
    }

    public String getPrivateKey2() {
        return privateKey2Area.getText();
    }

    public String getPublicKey() {
        return publicKeyArea.getText();
    }

    public String getPlainText() {
        return plainTextField.getText();
    }

    public String getCipherText() {
        return cipherTextField.getText();
    }

    public void setPlainText(String plainText) {
        plainTextField.setText(plainText);
    }

    public void setPrivateKey1(String privateKey1) {
        privateKey1Area.setText(privateKey1);
    }

    public void setPrivateKey2(String privateKey2) {
        privateKey2Area.setText(privateKey2);
    }

    public void setPublicKey(String publicKey) {
        publicKeyArea.setText(publicKey);
    }

    public void setResult(String result) {
        resultArea.setText(result);
    }

    public void setCipherText(String cipherText) {
        cipherTextField.setText(cipherText);
    }

    void addGenerateKeysListener(ActionListener listener) {
        generateKeysButton.addActionListener(listener);
    }

    void addEncryptKeyListener(ActionListener listener) {
        encryptButton.addActionListener(listener);
    }

    void addDecryptKeyListener(ActionListener listener) {
        decryptButton.addActionListener(listener);
    }
    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
