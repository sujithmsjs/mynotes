package more.pdf;


import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;


class SimpleStripper extends PDFTextStripper {

    private String str;
    private float lastY = 0;

    public SimpleStripper() throws IOException {
    }

    @Override
    protected void writeString(String string, List<TextPosition> tp) throws IOException {

        TextPosition get = tp.get(0);

        if ((int) get.getXDirAdj() == 72) {
            str += "\n";
        }
        if ((int) get.getXDirAdj() == 93) {
            str += "\n>";
        }

        for (TextPosition text : tp) {
            if (get.getUnicode().equals("\t")) {
                str += " ";
            } else {
                str += text.getUnicode();
            }
        }
    }

    public String getText() {
        return str;
    }
}
