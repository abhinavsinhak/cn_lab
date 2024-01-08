import java.math.BigInteger;
import java.util.Scanner;

public class SimpleRSA {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string to encrypt:");
        String text = scanner.nextLine();
        System.out.println("Enter the first prime number (p):");
        int p = scanner.nextInt();
        System.out.println("Enter the second prime number (q):");
        int q = scanner.nextInt();

        int n = p * q;
        int z = (p - 1) * (q - 1);
        System.out.println("The value of z = " + z);

        int e;
        for (e = 2; e < z; e++) {
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("The value of e (public key exponent) = " + e);

        int d = 0;
        for (int i = 0; i <= z; i++) {
            int x = 1 + (i * z);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d (private key exponent) = " + d);

        BigInteger N = BigInteger.valueOf(n);
        BigInteger C, msgback;

        // Encrypting the string
        StringBuilder encrypted = new StringBuilder();
        for (char character : text.toUpperCase().toCharArray()) {
            int msg = character - 'A' + 1;
            C = BigInteger.valueOf(msg).modPow(BigInteger.valueOf(e), N);
            encrypted.append(C).append(" ");
        }
        System.out.println("Encrypted message is: " + encrypted.toString());

        // Decrypting the string
        StringBuilder decrypted = new StringBuilder();
        String[] encryptedNumbers = encrypted.toString().split(" ");
        for (String number : encryptedNumbers) {
            C = new BigInteger(number);
            msgback = C.modPow(BigInteger.valueOf(d), N);
            char character = (char) (msgback.intValue() + 'A' - 1);
            decrypted.append(character);
        }
        System.out.println("Decrypted message is: " + decrypted.toString());

        scanner.close();
    }

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
}