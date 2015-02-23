import java.util.Arrays;

public class Util {

  private static int letterToIndex(char letter) {
    return letter - (int) 'A';
  }

  private static int numberToIndex(char number) {
    assert ((int)'0'<=(int)number && (int)number<=(int)'9'): 
    "number must be between 0-9";
    return (int) number - (int) '0';  
  }

  public static Coordinate parseCoordinate(String s) {
    //assert string length 2?
    int row    = letterToIndex(s.charAt(0));
    int column = numberToIndex(s.charAt(1));
    return new Coordinate(row,column);
  }

  public static Piece hideShip(Piece piece) {
    if(piece == Piece.SHIP){
      return Piece.WATER;
    }
    else{
      return piece;
    }
  }

  public static void hideShips(Piece[][] grid) {
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        grid[i][j] = hideShip(grid[i][j]);
      }
    }
  }

  public static Piece[][] deepClone(Piece[][] input) {
    Piece[][] output = new Piece[input.length][];
    for (int i = 0; i < input.length; i++) {
        output[i] = Arrays.copyOf(input[i], input[i].length);
    }
    return output;
  }
}
