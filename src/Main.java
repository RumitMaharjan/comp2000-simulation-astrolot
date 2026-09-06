import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String name;

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        System.out.println("Hello " + name+ "!");
        System.out.println("This is the intial commit for the comp2000 simulation project by the team 'astrolot'!");
        System.out.println("Welcome to my simulation project git setup!");

        scanner.close();
    }
}
