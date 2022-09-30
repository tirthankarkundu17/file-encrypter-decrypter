package in.bitmaskers.service;

import in.bitmaskers.exceptions.CryptoException;

import java.io.IOException;

public interface EncryptionDecryptionService {
    void encryptFilesInFolder(String folderPath) throws CryptoException, IOException;

    void decryptFilesInFolder(String folderPath) throws CryptoException, IOException;
}
