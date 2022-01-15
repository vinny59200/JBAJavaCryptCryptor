package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String msg = "we found a treasure!";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < msg.length(); i++) {
            String currentLetter = msg.substring(i, i + 1);
            if (alphabet.contains(currentLetter)) {
                int newLetterIndex = new StringBuilder(alphabet).reverse().toString().indexOf(currentLetter);
                System.out.print(alphabet.substring(newLetterIndex, newLetterIndex + 1));
            } else {
                System.out.print(currentLetter);
            }
        }
    }
}
