package secure;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mathenge
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashing {

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        System.out.println("Hasheduserpwd"+encryptedAttemptedPassword);
        System.out.println("hasheddbpwd"+encryptedPassword);
        System.out.println("salt"+salt);

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public byte[] getEncryptedPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(spec).getEncoded();
    }

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public static void main(String[] args) {
        Hashing pwd = new Hashing();
        try {
            byte[] salt = pwd.generateSalt();
            byte[] hashedpwd = pwd.getEncryptedPassword("1234", salt);
            
            if (pwd.authenticate("1234", hashedpwd, salt)==true){
                System.out.println("Success");
                System.out.println("Hashed"+hashedpwd.toString());
                System.out.println("salt"+salt.toString());
            } else {
                System.out.println("Fail");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
