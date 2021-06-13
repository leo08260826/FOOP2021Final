import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BrickArranger {
    
    public static void arrange(int stage, List<GameObject> bricks, int theSameZ) {
        File stageFile = new File("./src/stages/" + stage + ".txt");
        Scanner in;

        try {
            in = new Scanner(stageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ;
        }

        String[] line;
        while (in.hasNextLine()) {
            line = in.nextLine().trim().split(" ");
            String brickType = line[0];     // currently unused
            if( brickType.equals("BasicBrick") ){
                bricks.add(new BasicBrick("BasicBrick", Integer.parseInt(line[1]), Integer.parseInt(line[2]), theSameZ, 30, 20, ""));
            }
            else if( brickType.equals("BlockBrick") ){
                bricks.add(new BlockBrick("BlockBrick", Integer.parseInt(line[1]), Integer.parseInt(line[2]), theSameZ, 30, 20, ""));
            }
            else if( brickType.equals("HardBrick") ){
                bricks.add(new HardBrick("HardBrick", Integer.parseInt(line[1]), Integer.parseInt(line[2]), theSameZ, 30, 20, ""));
            }
            else if( brickType.equals("RandomBrick") ){
                bricks.add(new RandomBrick("RandomBrick", Integer.parseInt(line[1]), Integer.parseInt(line[2]), theSameZ, 30, 20, ""));
            }
            
        }

        return ;
    }

// for unit testing
//    public static void main(String[] args) {
//        List<Position> ret = BrickArranger.arrange(0);
//        ret.forEach(r -> System.out.println(r));
//    }

}
