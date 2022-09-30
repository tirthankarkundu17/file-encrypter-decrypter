package in.bitmaskers.service;

import in.bitmaskers.exceptions.CryptoException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class EncryptionDecryptionServiceImpl implements EncryptionDecryptionService {
    private String encryptionKey;

    public EncryptionDecryptionServiceImpl(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public void encryptFilesInFolder(String folderPath) throws CryptoException, IOException {
        CryptoService cryptoService = new AESCryptoServiceImpl();


        File encryptedFileDirectory = new File(folderPath + "/encrypted/");
        if (encryptedFileDirectory.exists()) {
            FileUtils.cleanDirectory(encryptedFileDirectory);
        } else {
            encryptedFileDirectory.mkdir();
        }

        File directoryPath = new File(folderPath);
        String[] contents = directoryPath.list();

        if (contents != null) {
            for (String fileName : contents) {
                File inputFile = new File(folderPath + "/" + fileName);
                if (inputFile.isFile() && !fileName.endsWith(".lnk")) { // Ignore shortcut link files
                    File encryptedFile = new File(folderPath + "/encrypted/", fileName + ".enc");
                    cryptoService.encrypt(encryptionKey, inputFile, encryptedFile);
                }
            }
        }
    }

    @Override
    public void decryptFilesInFolder(String folderPath) throws CryptoException, IOException {
        CryptoService cryptoService = new AESCryptoServiceImpl();
        File encryptedFileDirectory = new File(folderPath + "/decrypted/");
        if (encryptedFileDirectory.exists()) {
            FileUtils.cleanDirectory(encryptedFileDirectory);
        } else {
            encryptedFileDirectory.mkdir();
        }

        File directoryPath = new File(folderPath);
        String[] contents = directoryPath.list();
        if (contents != null) {
            for (String fileName : contents) {
                File inputFile = new File(folderPath + "/" + fileName);
                if (fileName.endsWith(".enc") && inputFile.isFile()) {
                    File encryptedFile = new File(folderPath + "/decrypted/" + fileName.split(".enc")[0]);
                    cryptoService.decrypt(encryptionKey, inputFile, encryptedFile);
                }
            }
        }
    }
}
