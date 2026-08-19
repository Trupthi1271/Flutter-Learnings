import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Take plaintext input from user
        System.out.print("Enter Plain Text: ");
        String plainText = sc.nextLine();

        // 16-byte secret key
        String key = "1234567890abcdef";
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Create Cipher
        Cipher cipher = Cipher.getInstance("AES");

        // Encrypt
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encrypted);

        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        System.out.println("Decrypted Text: " + new String(decrypted));

        sc.close();
    }
}
import java.util.Scanner;

public class RC4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter Plain Text: ");
        String plaintext = sc.nextLine();

        // Input key
        System.out.print("Enter Secret Key: ");
        String key = sc.nextLine();

        // Encrypt
        byte[] encrypted = rc4(plaintext.getBytes(), key.getBytes());

        System.out.print("Encrypted Text (Hex): ");
        for (byte b : encrypted) {
            System.out.printf("%02X", b);
        }
        System.out.println();

        // Decrypt
        byte[] decrypted = rc4(encrypted, key.getBytes());

        System.out.println("Decrypted Text: " + new String(decrypted));

        sc.close();
    }

    public static byte[] rc4(byte[] data, byte[] key) {
        byte[] S = new byte[256];
        byte[] T = new byte[256];

        // Initialize S and T
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = key[i % key.length];
        }

        // Key Scheduling Algorithm (KSA)
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + (S[i] & 0xFF) + (T[i] & 0xFF)) % 256;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        // Pseudo-Random Generation Algorithm (PRGA)
        byte[] result = new byte[data.length];
        int i = 0;
        j = 0;

        for (int k = 0; k < data.length; k++) {
            i = (i + 1) % 256;
            j = (j + (S[i] & 0xFF)) % 256;

            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            int t = ((S[i] & 0xFF) + (S[j] & 0xFF)) % 256;
            byte keyStream = S[t];

            result[k] = (byte) (data[k] ^ keyStream);
        }

        return result;
    }
}
import java.security.SecureRandom;
import java.util.Scanner;

public class RandomStringGenerator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Subset of digits and alphabets
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        System.out.print("Enter the length of random string: ");
        int length = sc.nextInt();

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(chars.length());
            randomString.append(chars.charAt(index));
        }

        System.out.println("Random String: " + randomString);

        sc.close();
    }
}

import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class RSASignature {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Take message input
        System.out.print("Enter message: ");
        String message = sc.nextLine();

        // Generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Create digital signature
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());

        byte[] signatureBytes = sign.sign();

        String digitalSignature = Base64.getEncoder()
                .encodeToString(signatureBytes);

        System.out.println("Digital Signature: " + digitalSignature);

        // Verify digital signature
        Signature verifySign = Signature.getInstance("SHA256withRSA");
        verifySign.initVerify(publicKey);
        verifySign.update(message.getBytes());

        boolean verified = verifySign.verify(signatureBytes);

        System.out.println("Signature Verified: " + verified);

        sc.close();
    }
}

import java.util.Scanner;

public class SubsetSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Superincreasing private key
        int[] privateKey = {2, 3, 7, 14, 30, 57, 120, 251};

        // q must be greater than sum(privateKey)
        int q = 491;

        // r must be coprime with q
        int r = 41;

        // Modular inverse of 41 mod 491
        int rInverse = 12;

        // Generate public key
        int[] publicKey = new int[privateKey.length];
        System.out.println("Public Key:");
        for (int i = 0; i < privateKey.length; i++) {
            publicKey[i] = (privateKey[i] * r) % q;
            System.out.print(publicKey[i] + " ");
        }

        System.out.println();

        // Input binary message
        System.out.print("Enter an 8-bit binary message: ");
        String message = sc.nextLine();

        if (message.length() != 8) {
            System.out.println("Please enter exactly 8 bits.");
            return;
        }

        // Encryption
        int cipher = 0;
        for (int i = 0; i < 8; i++) {
            if (message.charAt(i) == '1') {
                cipher += publicKey[i];
            }
        }

        System.out.println("Ciphertext: " + cipher);

        // Decryption
        int temp = (cipher * rInverse) % q;

        char[] decrypted = new char[8];

        for (int i = privateKey.length - 1; i >= 0; i--) {
            if (temp >= privateKey[i]) {
                decrypted[i] = '1';
                temp -= privateKey[i];
            } else {
                decrypted[i] = '0';
            }
        }

        System.out.println("Decrypted Binary: " + new String(decrypted));

        sc.close();
    }
}

// import java.util.Scanner;

// public class SubsetSum {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);

//         // Superincreasing private key
//         int[] privateKey = {2, 3, 7, 14, 30, 57, 120, 251};

//         // q must be greater than sum(privateKey)
//         // int q = 491;

//         // r must be coprime with q
//         // int r = 41;

//         // Modular inverse of 41 mod 491
//         // int rInverse = 12;

//         // Generate public key
//         // int[] publicKey = new int[privateKey.length];
//         // System.out.println("Public Key:");
//         // for (int i = 0; i < privateKey.length; i++) {
//         //     publicKey[i] = (privateKey[i] * r) % q;
//         //     System.out.print(publicKey[i] + " ");
//         // }

//         System.out.println();

//         // Input binary message
//         System.out.print("Enter an 8-bit binary message: ");
//         String message = sc.nextLine();

//         if (message.length() != 8) {
//             System.out.println("Please enter exactly 8 bits.");
//             return;
//         }

//         // Encryption
//         int cipher = 0;
//         for (int i = 0; i < 8; i++) {
//             if (message.charAt(i) == '1') {
//                 cipher += privateKey[i];
//             }
//         }

//         System.out.println("Ciphertext: " + cipher);

//         // Decryption
//         // int temp = (cipher * rInverse) % q;
//         int temp= cipher;

//         char[] decrypted = new char[8];

//         for (int i = privateKey.length - 1; i >= 0; i--) {
//             if (temp >= privateKey[i]) {
//                 decrypted[i] = '1';
//                 temp -= privateKey[i];
//             } else {
//                 decrypted[i] = '0';
//             }
//         }

//         System.out.println("Decrypted Binary: " + new String(decrypted));

//         sc.close();
//     }
// }

import java.security.MessageDigest;
import java.util.Scanner;

public class MD5 {

    // Method to generate MD5 hash
    public static String getMD5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the original message: ");
        String message = sc.nextLine();

        // Generate MD5 hash (Signature)
        String originalHash = getMD5(message);

        System.out.println("Generated MD5 Signature: " + originalHash);

        System.out.print("Enter the received message: ");
        String receivedMessage = sc.nextLine();

        // Generate hash for received message
        String receivedHash = getMD5(receivedMessage);

        System.out.println("Received MD5 Signature: " + receivedHash);

        // Authenticate
        if (originalHash.equals(receivedHash)) {
            System.out.println("Authentication Successful.");
            System.out.println("Message is authentic and not modified.");
        } else {
            System.out.println("Authentication Failed.");
            System.out.println("Message has been modified.");
        }

        sc.close();
    }
}

import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input prime number and primitive root
        System.out.print("Enter Prime Number (P): ");
        BigInteger P = sc.nextBigInteger();

        System.out.print("Enter Primitive Root (G): ");
        BigInteger G = sc.nextBigInteger();

        // Private keys
        System.out.print("Enter Private Key of User A: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter Private Key of User B: ");
        BigInteger b = sc.nextBigInteger();

        // Public keys
        BigInteger x = G.modPow(a, P); // A's public key
        BigInteger y = G.modPow(b, P); // B's public key

        System.out.println("Public Key of User A: " + x);
        System.out.println("Public Key of User B: " + y);

        // Shared secret keys
        BigInteger keyA = y.modPow(a, P);
        BigInteger keyB = x.modPow(b, P);

        System.out.println("Secret Key Computed by User A: " + keyA);
        System.out.println("Secret Key Computed by User B: " + keyB);

        if (keyA.equals(keyB)) {
            System.out.println("Key Exchange Successful!");
        } else {
            System.out.println("Key Exchange Failed!");
        }

        sc.close();
    }
}

import java.math.BigInteger;
import java.util.Scanner;

public class ElGamal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input values
        System.out.print("Enter Prime Number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter Generator (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter Private Key (x): ");
        BigInteger x = sc.nextBigInteger();

        System.out.print("Enter Plaintext (m): ");
        BigInteger m = sc.nextBigInteger();

        System.out.print("Enter Random Number (k): ");
        BigInteger k = sc.nextBigInteger();

        // Public key
        BigInteger y = g.modPow(x, p);

        System.out.println("Public Key (y): " + y);

        // Encryption
        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = m.multiply(y.modPow(k, p)).mod(p);

        System.out.println("Ciphertext:");
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        // Decryption
        BigInteger s = c1.modPow(x, p);
        BigInteger sInverse = s.modInverse(p);
        BigInteger decrypted = c2.multiply(sInverse).mod(p);

        System.out.println("Decrypted Message: " + decrypted);

        sc.close();
    }
}