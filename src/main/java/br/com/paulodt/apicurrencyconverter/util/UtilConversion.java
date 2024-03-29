package br.com.paulodt.apicurrencyconverter.util;

import java.io.IOException;
import java.util.Scanner;

public class UtilConversion {

    public static String converteJsonEmString(String json) throws IOException {
        
        Scanner scanner = new Scanner(json);
        String jsonEmString = "";
        while (scanner.hasNextLine()) {
            jsonEmString += scanner.nextLine();
        }
        scanner.close();
        return jsonEmString;
    }
    
}
