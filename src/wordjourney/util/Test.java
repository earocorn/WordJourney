package wordjourney.util;

public class Test {

    public static int objectCount = 0;
    
    public static void printObject(Object object) {
        objectCount++;
        System.out.println("Instantiated: " + object.getClass() + ". Total Instantiated: " + objectCount);
    }
}
