package game;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleUI {

    // Console Display Dimensions
    private static final int WIDTH = 15;
    private static final int DISPLAY_WIDTH = WIDTH * 2;

    // Default Food Border
    private static final String[] DEFAULT_BORDER = { "🍓", "🍎", "🍌", "🍇" };

    // Console Input
    private final Scanner scanner;

    // Console UI Constructor
    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    // Display a Header with the Default Border Pattern
    public void printHeader(String title) {
        printHeader(title, DEFAULT_BORDER);
    }

    // Display A Header With Food Borders
    public void printHeader(String title, String[] borderPattern) {
        print("\n\n");
        printBorder(borderPattern);
        printEmptyLine(borderPattern[0]);
        printLine(centerText(title), borderPattern[0]);
        printEmptyLine(borderPattern[0]);
        printBorder(borderPattern);
    }

    // Display The Available Menu Options
    public void printMenuOptions() {
        printEmptyLine();
        printContent("[1] Start Game");
        printContent("[2] How to Play");
        printContent("[3] Quit");
        printEmptyLine();
    }

    // Display the Default Food Emoji Border
    public void printBorder() {
        printBorder(DEFAULT_BORDER);
    }

    // Display a Border Using the Given Pattern
    public void printBorder(String[] borderPattern) {
        print(createBorder(borderPattern));
        print("\n");
    }

    // Create a Repeating Food Emoji Border
    private String createBorder(String[] borderPattern) {

        // Store the Completed Border
        StringBuilder border = new StringBuilder();

        // Repeat the Border Pattern across the Display Width
        for (int i = 0; i < WIDTH; i++) {
            border.append(borderPattern[i % borderPattern.length]);
        }

        // Return the Completed Border
        return border.toString();
    }

    // Print a Bordered Line With the Default Symbol
    private void printLine(String content) {
        printLine(content, DEFAULT_BORDER[0]);
    }

    // Print A Bordered Line With The Given Symbol
    private void printLine(String content, String symbol) {
        print(symbol + content + symbol + "\n");
    }

    // Print a Left-Aligned Content Line
    public void printContent(String content) {
        printLine(padContent(content));
    }

    // Print a Left-Aligned Content Line Using the Given Symbol
    public void printContent(String content, String symbol) {
        printLine(padContent(content), symbol);
    }

    // Print an Empty Content Line
    public void printEmptyLine() {
        printContent("");
    }

    // Print an Empty Content Line Using the Given Symbol
    public void printEmptyLine(String symbol) {
        printContent("", symbol);
    }

    // Center the Given Text Within the Display Width
    private String centerText(String title) {

        // Calculate the Width of the Title and the Padding Needed to Center It
        int titleWidth = title.length() + 2;

        // Calculate the Left and Right Padding to Center the Title
        int padding = DISPLAY_WIDTH - titleWidth - 2;
        int leftPadding = (padding / 2) - 1;
        int rightPadding = padding - leftPadding - 2;

        // Return the Centered Title with Padding
        return repeat(" ", leftPadding)
                + " " + title + " "
                + repeat(" ", rightPadding);
    }

    // Pad the Content to Fit Within the Board Width
    private String padContent(String content) {
        int padding = DISPLAY_WIDTH - content.length() - 4;
        return " " + content + repeat(" ", padding - 2) + " ";
    }

    // Get User Input from the Console
    public String getUserInput(String prompt) {

        // Display the Prompt to the User
        print("\n");
        print(prompt);

        // Read the User's Input from the Console
        return scanner.nextLine();
    }

    // Repeat a String Multiple Times
    private String repeat(String text, int count) {

        // Store the Repeated Text with a StringBuilder for Efficiency
        StringBuilder result = new StringBuilder();

        // Append the Text the Specified Number of Times
        for (int i = 0; i < count; i++) {
            result.append(text);
        }

        // Return the Repeated Text
        return result.toString();
    }

    // Print Text Using UTF-8 Encoding
    public void print(String text) {

        // Convert the Text to Bytes Using UTF-8 Encoding
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

        // Write the Bytes to the Console Output
        try {
            System.out.write(bytes);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}