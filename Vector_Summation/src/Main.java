import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Vector summation\n");

        String[] vectorReader_1, vectorReader_2;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter first Vector: ");
        vectorReader_1 = scan.nextLine().split(" ");
        System.out.print("Enter second Vector: ");
        vectorReader_2 = scan.nextLine().split(" ");

        scan.close();

        Vector<Integer> vector;

        try {
            vector = addVectors(vectorReader_1, vectorReader_2);

            System.out.print("\nVector created by summing those two Vectors is equal: ");
            for(int i=0; i<vector.size(); i++) {
                System.out.print(vector.elementAt(i) + " ");
            }
        }
        catch(VectorsOfDifferentLengthException err) {
            System.out.println(err.getMessage());
        }
    }

    private static Vector<Integer> addVectors(String[] vectorReader_1, String[] vectorReader_2) {
        if(vectorReader_1.length != vectorReader_2.length) {
            throw new VectorsOfDifferentLengthException("\nLength of the first Vector is " + vectorReader_1.length + " and the second Vector has a length of " + vectorReader_2.length);
        }

        Vector<Integer> vector = new Vector<>();

        for(int i=0; i<vectorReader_1.length; i++) {
            vector.add(Integer.parseInt(vectorReader_1[i]) + Integer.parseInt(vectorReader_2[i]));
        }

        return vector;
    }
}