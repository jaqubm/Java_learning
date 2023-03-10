public class Main {
    public static void main(String[] args) {

        for(int i = Integer.parseInt(args[1]); i<=Integer.parseInt(args[2]); i++)
            System.out.print(args[0].charAt(i));
    }
}