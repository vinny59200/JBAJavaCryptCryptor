package encryptdecrypt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        final Map<String, String> params = getParamsOptions(args);
        if (params == null) return;

        String menu = getMenu(params);
        Integer key = getKey(params);
        String msg = getMsg(params);
        String alg = getAlg(params);

        try {
            if (menu.equals("enc")) {
                cryptCryptor(params, key, msg, alg);
            } else if (menu.equals("dec")) {
                cryptCryptor(params, -key, msg, alg);
            } else {
                System.out.println("Error 1");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Error2");
        }
    }

    private static void cryptCryptor(Map<String, String> params, Integer key, String msg, String alg) throws IOException {
        if (params.containsKey("data")) {
            if (params.containsKey("out")) {
                Path path = Paths.get(params.get("out"));
                Files.write(path, cryptCrypt(msg, key, alg).getBytes(StandardCharsets.UTF_8));
            } else {
                System.out.println(cryptCrypt(msg, key, alg));
            }
        } else if (params.containsKey("in")) {
            String fileName = params.get("in");
//           System.out.println(fileName);
            msg = Files.readString(Paths.get( fileName));
            if (params.containsKey("out")) {
                Path path = Paths.get( params.get("out"));
                Files.write(path, cryptCrypt(msg, key, alg).getBytes(StandardCharsets.UTF_8));
            } else {
                System.out.println(cryptCrypt(msg, key, alg));
            }
        }
    }

    private static Map<String, String> getParamsOptions(String[] args) {
        final Map<String, String> params = new HashMap<>();

        String option;
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return null;
                }

                option = args[i + 1];
                params.put(a.substring(1), option);
            }
        }
        return params;
    }

    private static String getMenu(Map<String, String> params) {
        String menu;
        if (params.containsKey("mode")) {
            menu = params.get("mode");
        } else {
            menu = "enc";
        }
        return menu;
    }

    private static Integer getKey(Map<String, String> params) {
        Integer key;
        if (params.containsKey("key")) {
            key = Integer.valueOf(params.get("key"));
        } else {
            key = 0;
        }
        return key;
    }

    private static String getMsg(Map<String, String> params) {
        String msg;
        if (params.containsKey("data")) {
            msg = params.get("data");
        } else {
            msg = "";
        }
        if (!params.containsKey("data") && !params.containsKey("in")) {
            msg = "";
        }
        return msg;
    }

    private static String getAlg(Map<String, String> params) {
        String _alg;
        if (params.containsKey("alg")) {
            _alg = params.get("alg");
        } else {
            _alg = "shift";
        }
        return _alg;
    }

    private static String cryptCrypt(String msg, int key, String alg) {
        String result = "";
        if (alg.equals("shift")) {
            result = shiftCrypt(msg, key);
        }
        if (alg.equals("unicode")) {
            for (int i = 0; i < msg.length(); i++) {
                result += (char) (msg.substring(i, i + 1).toCharArray()[0] + key);
            }
        }
        return result;
    }

    private static String shiftCrypt(String msg, int key) {
        String result = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < msg.length(); i++) {
            if (alphabet.contains(msg.substring(i, i + 1))) {
                int index = alphabet.indexOf(msg.substring(i, i + 1));
                if (index + key > alphabet.length()) {
                    result += alphabet.substring(index + key - alphabet.length(), index + key - alphabet.length() + 1);
                } else if (index + key < 0) {
                    result += alphabet.substring(index + key + alphabet.length(), index + key + alphabet.length() + 1);
                } else {
                    result += alphabet.substring(index + key, index + key + 1);
                }
            } else if (alphabetUpper.contains(msg.substring(i, i + 1))) {
                int index = alphabetUpper.indexOf(msg.substring(i, i + 1));
                if (index + key > alphabetUpper.length()) {
                    result += alphabetUpper.substring(index + key - alphabetUpper.length(), index + key - alphabetUpper.length() + 1);
                } else if (index + key < 0) {
                    result += alphabet.substring(index + key + alphabet.length(), index + key + alphabet.length() + 1);
                } else {
                    result += alphabetUpper.substring(index + key, index + key + 1);
                }
            } else {
                result += msg.substring(i, i + 1);
            }
        }
        return result;
    }

}
