public class Main {
    public static void main(String[] args) {

        double a, b, c;

        a = Double.parseDouble(args[0]);
        b = Double.parseDouble(args[1]);
        c = Double.parseDouble(args[2]);

        double delta = b * b - 4 * a * c;

        if(delta == 0) {
            double x0 = -b / (2 * a);
            System.out.println("The root is equal: " + x0);
        }
        else if(delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("The roots are equal: " + x1 + ", and: " + x2);
        }
        else {
            delta *= -1;

            double xr = (-b) / (2 * a);
            double xj = Math.sqrt(delta) / (2 * a);

            System.out.println("The roots are equal: " + xr + " + " + xj + "j, and: " + xr + " - " + xj + "j");
        }
    }
}