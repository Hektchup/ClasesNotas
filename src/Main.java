import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static boolean classRegistered = false;

    private static final HashMap<String, Float> studensDic = new HashMap<>();

    private static String className;

    private static float maxGrade, minGrade, average, mode;

    public static void main(String[] args) {

        // Index

        System.out.println("Bienvenido, ¿Qué le gustaría hacer?");
        System.out.println();

        while (true) {
            printIndex();

            int indexNumber = in.nextInt();
            //javacosas
            in.nextLine();

            switch (indexNumber) {
                case 1:
                    registerClass();
                    break;
                case 2:
                    if (classRegistered) {
                        getStudentGrade();
                    }
                    break;
                case 3:
                    if (classRegistered) {
                        printClassStats();
                    }
                    break;
                case 4:
                    System.out.println("¡Hasta pronto!");
                    return;
                default:
                    System.out.println(ANSI_RED + "Por favor, introduzca un número válido." + ANSI_RESET);
                    System.out.println();
            }
        }
    }

    public static void printIndex() {
        System.out.println("Opciones");
        if (!classRegistered) {
            System.out.println("1. Registrar una nueva clase");
            System.out.println(ANSI_RED + "2. Revisar clase" + ANSI_RESET);
            System.out.println(ANSI_RED + "3. Comprobar estadísticas de la clase" + ANSI_RESET);

        } else {
            System.out.println("1. Registrar una nueva clase y sobreescribir la anterior");
            System.out.println("2. Revisar clase");
            System.out.println("3. Comprobar estadísticas de la clase");

        }
        System.out.println("4. Salir");
        System.out.println("Introduzca una opción a traves de los números");
    }

    public static void registerClass() {
        // Register students
        System.out.print("Introduzca el nombre de la clase: ");
        className = in.nextLine();

        System.out.print("Número de alumnos: ");
        int studentNumber = in.nextInt();
        in.nextLine();

        float[] grades = new float[studentNumber];

        maxGrade = Integer.MIN_VALUE;
        minGrade = Integer.MAX_VALUE;

        for (int i = 0; i < studentNumber; i++) {

            System.out.print("Nombre del alumno: ");
            String name = in.nextLine();

            System.out.print("Nota: ");
            float grade = in.nextInt();
            grades[i] = grade;
            in.nextLine();

            studensDic.put(name, grade);

            maxGrade = Math.max(grades[i], maxGrade);
            minGrade = Math.min(grades[i], minGrade);

        }

        // Average
        float sum = 0;
        for (float grade : grades) {
            sum += grade;
        }
        average = sum / studentNumber;

        // Mode
        HashMap<Float, Integer> repetitionsDic = new HashMap<>();
        int repetitionMode = Integer.MIN_VALUE;
        mode = 0;

        for (float grade : grades) {
            int repetitions = 1;
            if (repetitionsDic.containsKey(grade)) {
                repetitions = repetitionsDic.get(grade);
                ++repetitions;
            }
            repetitionsDic.put(grade, repetitions);
            if (repetitions > repetitionMode) {
                mode = grade;
                repetitionMode = repetitions;
            }
        }
        classRegistered = true;
    }

    public static void printClassStats() {// Results
        System.out.println(className + " resultados.");
        System.out.println();
        System.out.println("Nota más alta: " + maxGrade);
        System.out.println("Nota más baja: " + minGrade);
        System.out.println("Media de la clase: " + average);
        System.out.println("Moda: " + mode);
        System.out.println();
    }

    public static void getStudentGrade() {
        // Found grade from name
        System.out.println();
        System.out.println("Busque una nota por nombre: ");
        boolean wantLoocking = true;
        while (wantLoocking) {
            String whatName = in.nextLine();
            if (whatName.equals("salir")) {
                System.out.println("¿Dejar de buscar notas por nombre?");
                String exit = in.nextLine();
                if (exit.equals("si")) wantLoocking = false;
            } else {
                System.out.println(" " + studensDic.get(whatName));
            }
        }
    }
}
