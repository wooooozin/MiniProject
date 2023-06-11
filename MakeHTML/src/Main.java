import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static String setHeadBlockStyle() {
        return """
                <head>
                    <meta charset="UTF-8" />
                    <style>
                        table { border-collapse: collapse; width: 100%; }
                        th, td { border: solid 1px #000; }
                        tbody th { text-align: left; }
                    </style>
                </head>
                """;
    }

    public static String setBodyBlock() {
        StringBuilder tdData = new StringBuilder();
        for (Object k : System.getProperties().keySet()) {
            String key = "<tr><th>" + k.toString() + "</th>";
            String value = "<td>" + System.getProperty(k.toString()) + "</td></tr>";
            tdData.append(key).append(value);
        }

        return """
                 <body> 
                   <h1>자바 환경정보</h1>
                   <table> 
                       <thead>
                            <tr>
                                <th>키</th>
                                <td></td>
                            </tr>
                       </thead>
                       <tbody>
                """ + tdData.toString() +
                """
                        </tbody>
                   </table>
                </body>
                """;
    }

    public static void main(String[] args) {
        try {
            File file = new File("index.html");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(setHeadBlockStyle());
            writer.write(setBodyBlock());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}