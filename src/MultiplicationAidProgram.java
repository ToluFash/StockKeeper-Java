import javax.swing.*;
import java.security.SecureRandom;

public  class MultiplicationAidProgram {


    private int counter;
    private SecureRandom secureRandom;

    public static void main(String[] args) {

        SecureRandom rand1 = new SecureRandom();
        SecureRandom rand2 = new SecureRandom();
        MultiplicationAidProgram multiplicationAidProgram = new MultiplicationAidProgram();
        multiplicationAidProgram.start();


    }


    public MultiplicationAidProgram() {
        counter = 0;
        secureRandom = new SecureRandom();
    }

    public void start() {

        int number1 = secureRandom.nextInt(10);
        int number2 = secureRandom.nextInt(10);
        displayQuestion(String.format("How much is %d time %d", number1, number2), number1, number2);


    }

    public  void displayQuestion(String question, int number1, int number2) {
        if(counter == 10){
            System.exit(0);
        }
        int value;
        try{
            value = Integer.parseInt(JOptionPane.showInputDialog(question));
            counter++;

        }
        catch (NumberFormatException e){
            value = 101;
            counter++;
        }
        if (value == number1 * number2) {
            number1 = secureRandom.nextInt(10);
            number2 = secureRandom.nextInt(10);
            displayQuestion(String.format("Very Good! \nHow much is %d time %d", number1, number2), number1, number2);
        }
        else {
            displayQuestion(String.format("Wrong! \nHow much is %d time %d", number1, number2), number1, number2);
        }
    }

    public  void openDialog(String message){



    }
}



