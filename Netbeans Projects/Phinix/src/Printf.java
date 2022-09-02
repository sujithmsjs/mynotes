
import java.util.Locale;

public class Printf {

    public static void main(String[] args) {

        // System.out.printf(format, arguments);
        int n = 65;
        // System.out.printf(" %d %c \n",n); MissingFormatArgumentException
        System.out.printf("%d %c %o %x \n", n, n, n, n, n); // 65 A 101 41 

        float f = 7.25f;
        System.out.printf("%f %g %e \n", f, f, f); // 7.250000 7.25000 7.250000e+00  

        double g = 7.12345f;
        System.out.printf("%f %g %e\n", g, g, g); // 7.123450 7.12345 7.123450e+00

        String str = "World";
        System.out.printf("Hello %s!%n", str); // Hello World!
        //  System.out.printf("Hello %c!%n", str); IllegalFormatConversionException

        /*
    s formats strings.
    d formats decimal integers.
    f formats floating-point numbers.
    t formats date/time values.
    b format boolean.
     
         */
        //Line Separator
        System.out.printf("baeldung%nline%nterminator%n");

        //Strings
        System.out.printf("'%s' %n", "baeldung");
        System.out.printf("'%S' %n", "baeldung");
        System.out.printf("'%15s' %n", "baeldung");
        System.out.printf("'%-10s' %n", "baeldung");
        System.out.printf("%2.2s%n", "Hi there!");
        System.out.printf("'%-10.4s'%n", "Hi there!");

        System.out.printf(Locale.US, "%,d %n", 10000);
        System.out.printf(Locale.ITALY, "%,d %n", 10000);

        System.out.printf("%f%n", 5.1473); //5.147300
        System.out.printf("'%5.2f'%n", 123.123456); // '123.12'
        System.out.printf("'%10.1f'%n", 1233.123456); // '    1233.1'
        System.out.printf("'%3.1f'%n", 12545.123456); // '12545.1'
        System.out.printf("'%3.1f'%n", 123345.123456); // '123345.1'
        System.out.printf("'%5.2e'%n", 5.1473); // '5.15e+00'

      
        
        
        
        
        
    }

}
