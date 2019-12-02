package lab;

public enum Operation {
    PLUS {double eval(double x, double y){return x + y;}},
    MINUS {double eval(double x, double y){return x - y;}},
    MULTIPLY {double eval(double x, double y){return x * y;}},
    DIVIDE {double eval(double x, double y){return x / y;}};
    abstract double eval(double x, double y);

    public static void main(String[] args) {
        int h1 = 90, h2 = 101,  w1 = 2, w2 = 9;
        for (Operation i: Operation.values()) {
            printTab(i, h1, h2, w1, w2);
        }
    }

    public static void printTab(Operation i, int h1, int h2, int w1, int w2) {
        int h = h2 - h1;
        int w = w2 - w1;
        if (i.ordinal() == 0)
            printUp("+", w+1, w1);
        if (i.ordinal() == 1)
            printUp("-", w+1, w1);
        if (i.ordinal() == 2)
            printUp("x", w+1, w1);
        if (i.ordinal() == 3)
            printUp("/", w+1, w1);
        for (int x=h1; x<h1+h+1; x++) {
            System.out.printf("║%3d", x);
            for (int y=w1; y<w1+w+1; y++) {
                System.out.printf("║%7.3f", i.eval(x,y));
            }
            System.out.print("║\n");
        }
        printDown(w+1);
    }

    private static Integer printUp(String s, int w, int w1) {
        System.out.print("╔═══");
        for (int n=0; n<w; n++) {
            System.out.print("╦═══════");
        }
        System.out.print("╗\n║ "+s+" ║");
        for (int n=w1; n<w+2; n++) {
            System.out.printf("%4d   ║", n);
        }
        System.out.print("\n╠═══");
        for (int n=0; n<w; n++) {
            System.out.print("╬═══════");
        }
        System.out.print("╣\n");
        return null;
    }

    private static void printDown(int w) {
        System.out.print("╚═══");
        for (int n=0; n<w; n++) {
            System.out.print("╩═══════");
        }
        System.out.print("╝\n");
    }
}