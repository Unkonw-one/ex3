import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TexasTest {

    @Test
    public void shouldReturnWhitewins() {
        String Black = "2H,3D,5S,9C,KD";
        String White = "2C,3H,4S,8C,AH";
        Texas texas = new Texas();
        int expectedResult = texas.texasCompare(Black,White);
        Assert.assertEquals(2,expectedResult);
    }
    @Test
    public void shouldReturnWhitewins1(){
        String Black = "2H,3D,5S,9C,KD";
        String White = "2C,3H,4S,8C,AH";
        Texas texas = new Texas();
        int expectedResult = texas.texasCompare(Black,White);
        Assert.assertEquals(2,expectedResult);
    }

    @Test
    public void shouldReturnBlackswins(){
        String Black = "2H,3H,5H,9H,KH";
        String White = "2C,3D,4S,5C,6H";
        Texas texas = new Texas();
        int expectedResult = texas.texasCompare(Black,White);
        Assert.assertEquals(1,expectedResult);
    }
    @Test
    public void shouldReturnTie(){
        String Black = "2H,3D,5S,9C,KD";
        String White = "2D,3H,5C,9S,KH";
        Texas texas = new Texas();
        int expectedResult = texas.texasCompare(Black,White);
        Assert.assertEquals(0,expectedResult);
    }
    @Test
    public void shouldReturnError(){
        String Black = "2D,3D,5S,9C,KD";
        String White = "2D,3D,5C,9S,KH";
        Texas texas = new Texas();
        int expectedResult = texas.texasCompare(Black,White);
        Assert.assertEquals(-1,expectedResult);
    }
}