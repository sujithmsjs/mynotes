package coll.pri;

public class AC { //Access control.

    String default_string = "";
    private String private_string = "";
    public String public_string = "";
    protected String protected_string = "";

    static class IN {

        String default_in_string = "";
        private String private_in_string = "";
        public String public_in_string = "";
        protected String protected_in_string = "";

        public void showOuter(){
            show();
        }

    }

    public static void main(String[] args) {
        IN in = new IN();
        in.showOuter();
        
    }

    protected static void show() {

    }

    static void outerMethod1() {

    }

}
