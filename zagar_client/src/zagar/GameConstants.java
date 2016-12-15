package zagar;

import org.jetbrains.annotations.NotNull;

/**
 * @author apomosov
 */
public interface GameConstants {
  @NotNull
  String DEFAULT_GAME_SERVER_HOST = "localhost";
  int DEFAULT_GAME_SERVER_PORT = 7000;
  @NotNull
  String DEFAULT_ACCOUNT_SERVER_HOST = "localhost";
  int DEFAULT_ACCOUNT_SERVER_PORT = 8080;
  @NotNull
  String DEFAULT_LOGIN = "zAgar";
  @NotNull
  String DEFAULT_PASSWORD = "pass";

  int FOOD_MASS = 314;
  int VIRUS_MASS = 100;
}
