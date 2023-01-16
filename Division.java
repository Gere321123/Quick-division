import java.util.Scanner;

public class Division {
    int[] big_number;
    int[] characters_to_multiply;
    int big_number_size;
    int small_number;
    int final_number;

    Division() {
        System.out.println("This program tells you if two numbers are divisible by each other.\n" +
                "Please enter the larger number (can be very large): ");
        Scanner input = new Scanner(System.in);
        String big_number_string = input.nextLine();
        big_number = new int[big_number_string.length()];
        char[] big_number_char = new char[big_number_string.length()];
        for (int i = 0; i < big_number_string.length(); i++) {
            big_number_char[i] = big_number_string.charAt(i);
        }
        for (int i = 0; i < big_number_string.length(); i++) {
            try {
                big_number[i] = Integer.parseInt(String.valueOf(big_number_char[i]));
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        big_number_size = big_number_string.length();
        System.out.println("Please enter the smaller number : ");
        small_number = input.nextInt();
        Characters_to_Multiply();
        math();
    }

    public void math() {
        final_number = 0;
        for (int i = 0; i < big_number_size; i++) {
            final_number += big_number[big_number_size - i - 1] * characters_to_multiply[i];
        }
        System.out.println("Final number is : " + final_number);
        if (final_number % small_number == 0) {
            System.out.println("The smaller number is divisible by the larger number (because the smaller number is divisible by the final number)");
        } else
            System.out.println("The smaller number is NOT divisible by the larger number (because the smaller number NOT is divisible by the final number)");
    }

    public void Characters_to_Multiply() {
        boolean end = true;
        int i = 1;
        int j = 1;
        characters_to_multiply = new int[big_number_size];
        characters_to_multiply[0] = 1;
        while (end) {
            if ((double) (small_number / (10 * i)) > 1) {
                characters_to_multiply[j] = 10 * i;
                j++;
                i *= 10;
            } else {
                end = false;
            }
        }
        int sn_pieces = j;
        while (j < big_number_size && !end) {
            int multiply = (10 * i) - (((10 * i) / small_number) * small_number);
            i *= 10;
            characters_to_multiply[j] = multiply;
            j++;
            //This section is trying to find recurrence in the array and its most likely not the best method
            for (int k = sn_pieces; k < j; k++) {
                for (int g = sn_pieces; g < j; g++) {
                    if (characters_to_multiply[g] == characters_to_multiply[k] && g != k) {
                        end = true;
                        int repeate = g - k;
                        g = j;
                        k = j;
                        for (int u = j; u < big_number_size; u++) {
                            characters_to_multiply[u] = characters_to_multiply[u - repeate];
                        }

                    }
                }
            }
        }
    }
}
