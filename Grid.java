public class Grid {

  private static final int WIDTH = 10;
  private static final int HEIGHT = 10;

  private final Piece[][] grid = new Piece[HEIGHT][WIDTH];

  public Grid() {
    for(int i = 0; i < this.WIDTH; i++){
      for(int j = 0; j < this.HEIGHT; j++){
        grid[i][j] = Piece.WATER;
      }
    }
  }

  private boolean isInGrid(Coordinate c){
    if(0 <= c.getRow() && c.getRow() <= HEIGHT
      && 0 <= c.getColumn() && c.getColumn() <= WIDTH){
      return true;
    }
    else{
      return false;
    }
  }

  public boolean canPlace(Coordinate c, int size, boolean isDown) {
    int row    = c.getRow(); 
    int column = c.getColumn();

    if(isInGrid(c) == false){
      return false;
    }

    if(isDown){
      Coordinate edge = new Coordinate(row+size,column);
      if(isInGrid(edge) == false){
        return false;
      }

      for(int i = 0; i < size; i++){
        if(grid[row+i][column] != Piece.WATER){
          return false;
        }
      }
      return true;
    }

    else{
      Coordinate edge = new Coordinate(row,column+size);
      if(isInGrid(edge)==false){
        return false;
      }

      for(int i = 0; i<size; i++){
        if(grid[row][column+i] != Piece.WATER){
          return false;
        }
      }
      return true;
    }
  }

  public void placeShip(Coordinate c, int size, boolean isDown) {
    assert (canPlace(c,size,isDown)):"this ship can't be placed here";
    int row = c.getRow();
    int column = c.getColumn();

    if(isDown){
      for(int i = 0; i < size; i++){
        grid[row+i][column] = Piece.SHIP;
      }
    }
    else{
      for(int i = 0; i < size; i++){
        grid[row][column+i] = Piece.SHIP;
      }
    }
  }

  public boolean wouldAttackSucceed(Coordinate c) {
    return grid[c.getRow()][c.getColumn()] == Piece.SHIP;
  }

  public void attackCell(Coordinate c) {
    int row = c.getRow();
    int column = c.getColumn();
    if(grid[row][column] == Piece.WATER){
      grid[row][column] = Piece.MISS;
    }
    else if(grid[row][column] == Piece.SHIP){
      grid[row][column] = Piece.DAMAGED_SHIP;
    }
  }

  public boolean areAllSunk() {
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[0].length; j++){
        if(grid[i][j] == Piece.SHIP){
          return false;
        }
      }
    }
    return true;
  }

  public String toPlayerString() {
    Piece[][] clone = Util.deepClone(grid);
    Util.hideShips(clone);
    return renderGrid(clone);  
  }

  public String toString() {
      return renderGrid(grid);
  }

  private static String renderGrid(Piece[][] grid) {
    StringBuilder sb = new StringBuilder();
    sb.append(" 0123456789\n");
    for (int i = 0; i < grid.length; i++) {
      sb.append((char) ('A' + i));
      for (int j = 0; j < grid[i].length; j++) {
          if (grid[i][j] == null) {
              return "!";
          }
          switch (grid[i][j]) {
          case SHIP:
              sb.append('#');
              break;
          case DAMAGED_SHIP:
              sb.append('*');
              break;
          case MISS:
              sb.append('o');
              break;
          case WATER:
              sb.append('.');
              break;
          }
        }
      sb.append('\n');
    }

      return sb.toString();
  }
}
