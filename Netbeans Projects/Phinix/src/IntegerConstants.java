
public class IntegerConstants {

    public static void main(String[] args) {
        
        int bin = 0b1001_1001; //153
        // int bin = 0b1001_1001;
       // int bin2 = 0b_1001_1001;
        int oct = 0_123; // 83
        int oct2 = 0123; // 83
        int dec = 123_456; //123456
        int hex = 0x123; // 291
        int hex2 = 0xFFFFFF; // 16777215
       // int hex2 = 0x_123;
        
        char ch = 300; //ASCII CODE Ĭ
        int n = 90; //90
      // char ch2 = n; RE:Possible lossy conversation Error!
        char ch2 = (char) n;// Z; ASCII Code
        char ch3 = 0b1111_1111_1111_1111; // 65535
        int n2 = ch3; // 65535
        // char ch4 = 65536; RE:possible lossy conversion from int to char
        
        char ch4 = '*';
        int n4 = ch4; // OP: 42
        // char ch5 = n4; RE:Possible lossy conversation Error!
        char ch5 = (char) n4; // OP: *
        char u1 = '\u0C10'; // UNICODE Format
        
       // float f = 2.5; RE:possible lossy conversion from double to float
        float f = 2.5F; // 2.5
        float f2 = 12345e-2f; // 123.45
        float f3 = 123456e-3f; // 123.456
        
        double d = 2.5; // 2.5
        double d2 = 123456e-3; // 123.456
    }

}
