import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BrickArranger {
    public static List<Position> arrange(int stage) {
        List<Position> positions = new ArrayList<>();
        File stageFile = new File("./src/stages/" + stage + ".txt");
        Scanner in;

        try {
            in = new Scanner(stageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return positions;
        }

        String[] line;
        while (in.hasNextLine()) {
            line = in.nextLine().trim().split(" ");
            if (line.length == 1 && line[0].equals("")) continue;
            String brickType = line[0];     // currently unused
            positions.add(new Position(line[1], line[2]));
        }

        return positions;
    }

// for unit testing
//    public static void main(String[] args) {
//        List<Position> ret = BrickArranger.arrange(0);
//        ret.forEach(r -> System.out.println(r));
//    }

}
