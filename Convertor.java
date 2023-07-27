
package CSC4101Assignment2;

/**
 *
 * @author Lama Malay Sut Aung
 */

 public class Convertor {
    
    public static void IEEE754(double decimalNum) {
        // Check if number is negative
        boolean isNegative = false;
        if (decimalNum < 0) {
            isNegative = true;
            decimalNum = -decimalNum;
        }
        
        // Convert to binary
        int intPart = (int) decimalNum;
        double fracPart = decimalNum - intPart;
        
        String intPartBin = "";
        while (intPart > 0) {
            int rem = intPart % 2;
            intPartBin = rem + intPartBin;
            intPart /= 2;
        }
        
        String fracPartBin = "";
        while (fracPart > 0) {
            if (fracPartBin.length() >= 23) {
                break;
            }
            fracPart *= 2;
            if (fracPart >= 1) {
                fracPartBin += "1";
                fracPart -= 1;
            } else {
                fracPartBin += "0";
            }
        }
        
        // Calculate exponent
        int exponent = intPartBin.length() - 1;
        exponent += 127;
        
        // Convert exponent to binary
        String exponentBin = "";
        while (exponent > 0) {
            int rem = exponent % 2;
            exponentBin = rem + exponentBin;
            exponent /= 2;
        }
        while (exponentBin.length() < 8) {
            exponentBin = "0" + exponentBin;
        }
        
        // Combine sign, exponent, and mantissa to get IEEE 754 format
        String sign = isNegative ? "1" : "0";
        String mantissa = intPartBin.substring(1) + fracPartBin;
        while (mantissa.length() < 23) {
            mantissa += "0";
        }
        String singlePrecision = sign + " " + exponentBin + " " + mantissa.substring(0, 23);
        String doublePrecision = sign + " " + exponentBin + " " + mantissa;
        
        // Print results
        System.out.println("Input: "+ decimalNum);
        System.out.println("Single precision: " + singlePrecision);
        System.out.println("Double precision: " + doublePrecision);
    }
}
