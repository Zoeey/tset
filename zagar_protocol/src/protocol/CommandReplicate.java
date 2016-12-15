package protocol;

import org.jetbrains.annotations.NotNull;
import protocol.model.Cell;
import protocol.model.pFood;
import protocol.model.pVirus;

/**
 * @author apomosov
 */
public final class CommandReplicate extends Command {
  @NotNull
  public static final String NAME = "cells";
  @NotNull
  private final pFood[] food;
  @NotNull
  private final Cell[] cells;
  @NotNull
  private final pVirus[] virus;

  public CommandReplicate(@NotNull pFood[] foods,@NotNull Cell[] cells, @NotNull pVirus[] viruss) {
    super(NAME);
    this.food = foods;
    this.cells = cells;
    this.virus = viruss;
  }

  @NotNull
  public protocol.model.Cell[] getCells() {
    return cells;
  }

  @NotNull
  public pFood[] getFood() {
    return food;
  }
  @NotNull
  public pVirus[] getVirus() { return virus;}
}
