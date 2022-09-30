package in.bitmaskers.ui;


import in.bitmaskers.exceptions.CryptoException;
import in.bitmaskers.service.EncryptionDecryptionService;
import in.bitmaskers.service.EncryptionDecryptionServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {
    private JButton btnEncrypt;
    private JPanel panelMain;
    private JTextField tfSecret;
    private JTextField tfInput;
    private JButton btnDecrypt;
    private JTextArea tfOutput;
    private JComboBox algoCombo;
    private JTextArea JTextAreaErr;
    private JButton btnClear;
    private EncryptionDecryptionService encryptionDecryptionService;

    public App() {
        // create checkbox
        algoCombo.addItem("AES");

        btnEncrypt.addActionListener(e -> {
            String fileLocation = tfInput.getText();
            String encryptionKey = tfSecret.getText();
            List<String> validationErrors = validate(fileLocation, encryptionKey);
            if (!validationErrors.isEmpty())
                JTextAreaErr.setText(String.join("\n", validationErrors));
            else {
                if (algoCombo.getSelectedItem().toString().equals("AES"))
                    encryptionDecryptionService = new EncryptionDecryptionServiceImpl(encryptionKey);
                try {
                    encryptionDecryptionService.encryptFilesInFolder(fileLocation);
                    tfOutput.setText("Encrypted all files successfully!");
                } catch (CryptoException | IOException ex) {
                    JTextAreaErr.setText("Error occurred " + ex.getMessage());
                }
            }
        });
        btnClear.addActionListener(e -> {
            tfInput.setText("");
            tfSecret.setText("");
            JTextAreaErr.setText("");
            tfOutput.setText("");
        });
        btnDecrypt.addActionListener(e -> {
            String fileLocation = tfInput.getText();
            String encryptionKey = tfSecret.getText();
            List<String> validationErrors = validate(fileLocation, encryptionKey);
            if (!validationErrors.isEmpty())
                JTextAreaErr.setText(String.join("\n", validationErrors));
            else {
                if (algoCombo.getSelectedItem().toString().equals("AES"))
                    encryptionDecryptionService = new EncryptionDecryptionServiceImpl(encryptionKey);
                try {
                    encryptionDecryptionService.decryptFilesInFolder(fileLocation);
                    tfOutput.setText("Decrypted all files successfully!");
                } catch (CryptoException | IOException ex) {
                    JTextAreaErr.setText("Error occurred " + ex.getMessage());
                }
            }
        });
    }

    private List<String> validate(String fileLocation, String encryptionKey) {
        List<String> errors = new ArrayList<>();
        if (Objects.isNull(fileLocation) || fileLocation.isEmpty()) {
            errors.add("File Location cannot be empty");
        }
        if (Objects.isNull(encryptionKey) || encryptionKey.isEmpty()) {
            errors.add("Encryption Key cannot be empty");
        }

        return errors;
    }

    public static void main(String[] args) {
        JFrameCustom jFrame = new JFrameCustom("File Encrypter Decrypter");
        ImageIcon img = new ImageIcon("src/main/resources/icon.png");
        Dimension dimension = new Dimension(500, 500);
        jFrame.setMaximumSize(dimension);
        jFrame.setSize(dimension);
        jFrame.setIconImage(img.getImage());
        jFrame.setContentPane(new App().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
