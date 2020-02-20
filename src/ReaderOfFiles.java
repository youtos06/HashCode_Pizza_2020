import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReaderOfFiles {
    static void reader(String filename) throws Exception {
        //String path = d
        String path  = "src/in/"+filename;
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder numberInString = new StringBuilder();

        while (true) {
            int i = br.read();
            if (i == -1) {
                if(!numberInString.toString().equals("")){
                    System.out.println(Integer.valueOf(numberInString.toString()));
                }

                break;
            } else if ((char) i == '\n') {
                System.out.println(Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder();
            } else if ((char) i == ' ') {
                System.out.println(Integer.valueOf(numberInString.toString()));
                numberInString = new StringBuilder("");
            }else{
                numberInString.append((char) i);
            }
        }
    }
}
