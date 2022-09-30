package in.bitmaskers.service;

import in.bitmaskers.exceptions.CryptoException;

import java.io.File;

public interface CryptoService {
    void encrypt(String key, File inputFile, File outputFile) throws CryptoException;

    void decrypt(String key, File inputFile, File outputFile) throws CryptoException;
}
