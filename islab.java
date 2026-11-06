import java.math.BigInteger;
import java.util.Random;


public class GoldwasserMicali {


    static BigInteger p = BigInteger.valueOf(11);
    static BigInteger q = BigInteger.valueOf(19);
    static BigInteger n = p.multiply(q);
    static BigInteger z = BigInteger.valueOf(2);
    static Random random = new Random();


    static BigInteger encrypt(int bit) {
        BigInteger r;
        do { r = new BigInteger(n.bitLength(), random); }
        while (r.compareTo(n) >= 0 || !r.gcd(n).equals(BigInteger.ONE));
        BigInteger c = r.multiply(r).mod(n);
        return bit == 1 ? c.multiply(z).mod(n) : c;
    }


    static int decrypt(BigInteger c) {
        BigInteger val = c.mod(p);
        for (int x = 1; x < p.intValue(); x++)
            if (BigInteger.valueOf(x * x).mod(p).equals(val)) return 0;
        return 1;
    }


    public static void main(String[] args) {
        String msg = "10101";
        BigInteger[] cipher = new BigInteger[msg.length()];
        for (int i = 0; i < msg.length(); i++)
            cipher[i] = encrypt(msg.charAt(i) - '0');


        System.out.println("Original  : " + msg);
        System.out.print("Encrypted : ");
        for (BigInteger c : cipher) System.out.print(c + " ");
        System.out.print("\nDecrypted : ");
        for (BigInteger c : cipher) System.out.print(decrypt(c));
        System.out.println();
    }
}
// Output :
// Original  : 10101                                                           
// Encrypted : 204 157 40 104 52   
// Decrypted : 10101


import java.math.BigInteger;


public class RabinCryptosystem {


    static BigInteger p = BigInteger.valueOf(7);
    static BigInteger q = BigInteger.valueOf(11);
    static BigInteger n = p.multiply(q);


    static BigInteger encrypt(BigInteger message) {
        return message.multiply(message).mod(n);
    }
   
    static void decrypt(BigInteger cipher) {
        System.out.println("Possible plaintexts:");


        for (int m = 0; m < n.intValue(); m++) {
            BigInteger x = BigInteger.valueOf(m);


            if (x.multiply(x).mod(n).equals(cipher)) {
                System.out.println(m);
            }
        }
    }


    public static void main(String[] args) {
        BigInteger message = BigInteger.valueOf(20);


        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("Public Key n = " + n);


        BigInteger cipher = encrypt(message);


        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + cipher);


        decrypt(cipher);
    }
}

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
public class Kerberos{
        // AES encryption
        static String encrypt(String data, String key) throws Exception {
            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }
    // AES decryption
        static String decrypt(String data, String key) throws Exception {
            SecretKeySpec secretKey =  new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.getDecoder().decode(data);
            return new String(cipher.doFinal(decoded));
        }


        public static void main(String[] args) throws Exception {
            String clientKey = "1234567890123456";
            String serverKey = "9876543210987654";
            // Client requests authentication
            System.out.println("Client: Requesting authentication...");
            // Authentication Server creates a TGT
            String tgt = "Client=Alice;Service=TGS";
            String encryptedTGT = encrypt(tgt, clientKey);
            System.out.println("AS: TGT generated.");
            System.out.println("Encrypted TGT: " + encryptedTGT);
            // Client decrypts TGT
            String receivedTGT = decrypt(encryptedTGT, clientKey);
            System.out.println("Client received TGT: "+ receivedTGT);
            // TGS generates service ticket
            String serviceTicket = "Client=Alice;Service=WebServer";
            String encryptedTicket =  encrypt(serviceTicket, serverKey);
            System.out.println("TGS: Service ticket generated.");
            System.out.println("Encrypted Service Ticket: "+ encryptedTicket);
            // Server verifies the service ticket
            String verifiedTicket = decrypt(encryptedTicket, serverKey);
            System.out.println("Server verified ticket: " + verifiedTicket);
            System.out.println("Authentication Successful!");
    


    System.out.println("Access Granted to Server.");
    }
}

// Client: Requesting authentication...
// AS: TGT generated.
// Encrypted TGT: zUHS4JNYrMvvc86lCvVO1jnRqalv0XxrwNJavkQOmNw=
// Client received TGT: Client=Alice;Service=TGS
// TGS: Service ticket generated.
// Encrypted Service Ticket: W7omu/paBQ/zDKlxmpkXlaHPBxsbmtjvZ823y75XVpw=
// Server verified ticket: Client=Alice;Service=WebServer
// Authentication Successful!
// Access Granted to Server.


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.Base64;


public class SecureWebTransaction {


    public static void main(String[] args) throws Exception {


        // Generate symmetric session key
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey sessionKey = keyGenerator.generateKey();
    // Data sent by client
    String message = "Payment of Rs.1000";
    // Encrypt transaction
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, sessionKey);
    byte[] encrypted = cipher.doFinal(message.getBytes());
    String encryptedData = Base64.getEncoder().encodeToString(encrypted);
    System.out.println("Original Data: " + message);
    System.out.println("Encrypted Data: " + encryptedData);
    // Server decrypts transaction
    cipher.init(Cipher.DECRYPT_MODE, sessionKey);
    byte[] decrypted = cipher.doFinal(encrypted);
    System.out.println("Decrypted Data: " + new String(decrypted));
    System.out.println("Secure Transaction Successful!");
        }
}

// Output : 
// Original Data: Payment of Rs.1000                                           
// Encrypted Data: JSeey8nWh2arN6U1mVRf+Q7nr6JJSZEWpmYv7naqpnQ=
// Decrypted Data: Payment of Rs.1000             
// Secure Transaction Successful!



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;


public class DigitalCertificatePKI {
    public static void main(String[] args) throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey =  keyPair.getPublic();
        PrivateKey privateKey =  keyPair.getPrivate();
        // Generate AES session key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey sessionKey = keyGenerator.generateKey();
        // Message
        String message = "Confidential PKI Transaction";
        // Encrypt message using AES
        Cipher aes = Cipher.getInstance("AES");
        aes.init(Cipher.ENCRYPT_MODE, sessionKey);
        byte[] encryptedMessage = aes.doFinal(message.getBytes());
        // Encrypt AES key using RSA
        Cipher rsa = Cipher.getInstance("RSA");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedSessionKey =  rsa.doFinal(sessionKey.getEncoded());
        System.out.println("Original Message: "+ message);
        System.out.println("Encrypted Message: "+ Base64.getEncoder()
        .encodeToString(encryptedMessage));
        // Receiver decrypts AES key using private key
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKey =   rsa.doFinal(encryptedSessionKey);
        SecretKey recoveredKey =    new javax.crypto.spec.SecretKeySpec(decryptedKey, "AES");
        // Decrypt message
        aes.init(Cipher.DECRYPT_MODE, recoveredKey);
        byte[] decryptedMessage = aes.doFinal(encryptedMessage);
        System.out.println("Decrypted Message: "  + new String(decryptedMessage));
        System.out.println("Hybrid Encryption Successful!");
    }
}
// Output : 
// Original Message: Confidential PKI Transaction
// Encrypted Message: 3SyH4FSafpP5cHRCqYVTpxOpUj0tk9DP8nW0jksEqNI=
// Decrypted Message: Confidential PKI Transaction
// Hybrid Encryption Successful!


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class MAC {


    public static void main(String[] args) throws Exception {


        String message = "This is a confidential message";
        String secretKey = "MySecretKey12345";


        // Create HMAC
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        mac.init(key);


        // Generate MAC
        byte[] macBytes = mac.doFinal(message.getBytes());
        String generatedMAC = Base64.getEncoder().encodeToString(macBytes);


        System.out.println("Message       : " + message);
        System.out.println("Generated MAC : " + generatedMAC);


        // Receiver generates MAC again
        mac.init(key);
        byte[] receivedMAC = mac.doFinal(message.getBytes());
        String calculatedMAC = Base64.getEncoder().encodeToString(receivedMAC);


        System.out.println("Calculated MAC: " + calculatedMAC);


        // Compare MAC values
        if (generatedMAC.equals(calculatedMAC)) {
            System.out.println("Message is Authentic.");
            System.out.println("Message Integrity Verified.");
        } else {
            System.out.println("Message has been Modified.");
        }
    }
}
// Output : 
// Message       : This is a confidential message
// Generated MAC : nYqyMevnJKphMnpCfrtIbcm0wt1qRAwvvIokzbqwGpQ=
// Calculated MAC: nYqyMevnJKphMnpCfrtIbcm0wt1qRAwvvIokzbqwGpQ=
// Message is Authentic.
// Message Integrity Verified.

import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;


public class EllipticCurve {
    public static void main(String[] args) throws Exception {


        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair alice = kpg.generateKeyPair();


        KeyPair bob = kpg.generateKeyPair();


        KeyAgreement a = KeyAgreement.getInstance("ECDH");
        a.init(alice.getPrivate());
        a.doPhase(bob.getPublic(), true);
        byte[] secretA = a.generateSecret();


        KeyAgreement b = KeyAgreement.getInstance("ECDH");
        b.init(bob.getPrivate());
        b.doPhase(alice.getPublic(), true);
        byte[] secretB = b.generateSecret();


        System.out.println("Alice Shared Secret: "
                + Arrays.toString(secretA));
        System.out.println("Bob Shared Secret: "
                + Arrays.toString(secretB));


        if (Arrays.equals(secretA, secretB)) {
            System.out.println("Shared Secret Successfully Established!");
        } else {
            System.out.println("Key Agreement Failed.");
        }
    }
}
// Output : 
// Alice Shared Secret: [-33, -95, 27, -44, -34, -35, -121, 20, 13, -23, 75, 65, -13, 68, -114, -16, 47, 58, -128, 102, 23, -7, 56, 95, -56, -124, -76, 53, -91, 0, -52, -41]                             
// Bob Shared Secret: [-33, -95, 27, -44, -34, -35, -121, 20, 13, -23, 75, 65, -13, 68, -114, -16, 47, 58, -128, 102, 23, -7, 56, 95, -56, -124, -76, 53, -91, 0, -52, -41]
// Shared Secret Successfully Established!

