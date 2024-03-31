public class BinaryFormatException extends NumberFormatException {

    public BinaryFormatException(String binaryString) {
        super("Invalid binary string: " + binaryString);
    }
}