import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BrickArranger {
    
    public static void arrange(int stage, List<GameObject> bricks, int theSameZ) {
        File stageFile = new File("./src/stages/new/" + stage + ".txt");
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
            if (line.length == 1 && line[0].equals("")) continue;
            String brickType = line[0];
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[2]);
            int brickWidth = 40;
            if( brickType.equals("BasicBrick") ){
                bricks.add(new BasicBrick(brickType, x, y, theSameZ, brickWidth, 20, ""));
            }
            else if( brickType.equals("BlockBrick") ){
                bricks.add(new BlockBrick(brickType, x, y, theSameZ, brickWidth, 20, ""));
            }
            else if( brickType.equals("HardBrick") ){
                bricks.add(new HardBrick(brickType, x, y, theSameZ, brickWidth, 20, ""));
            }
            else if( brickType.equals("RandomBrick") ){
                bricks.add(new RandomBrick(brickType, x, y, theSameZ, brickWidth, 20, ""));
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
