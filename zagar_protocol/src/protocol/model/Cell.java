package protocol.model;

/**
 * @author apomosov
 */
public  class Cell {
  private final int cellId;
  private final int playerId;
  private final float size;
  private int x;
  private int y;

  public Cell(int cellId, int playerId, float size, int x, int y) {
    this.cellId = cellId;
    this.playerId = playerId;
    this.size = size;
    this.x = x;
    this.y = y;
  }

  public int getPlayerId() {
    return playerId;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getCellId() {
    return cellId;
  }

  public float getSize() {
    return size;
  }
}
