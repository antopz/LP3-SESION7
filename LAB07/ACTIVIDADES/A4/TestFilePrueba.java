import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TestFilePrueba {

    public static void main(String[] args) {
        byte b[]=new byte[1024];

        try {
            FileInputStream file=new FileInputStream("TestFile.java");
            int bytesLeidos=file.read(b);

            String s=new String(b, 0, bytesLeidos);

            ViewFile view=new ViewFile(s);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(400, 150);
            view.setVisible(true);

            file.close();

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class ViewFile extends JFrame {

    private JTextArea areaTexto;

    public ViewFile(String s) {
        super("Mostrando el contenido de un archivo");
        areaTexto=new JTextArea(s, 5, 40);
        add(areaTexto);
    }
}
