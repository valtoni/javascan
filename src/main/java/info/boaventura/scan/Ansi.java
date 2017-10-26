package info.boaventura.scan;

public class Ansi {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_GRAY = "\u001B[37m";
  public static final String ANSI_WHITE = "\u001B[37;1m";

  public static class Builder {

    private String output = "";

    public Builder() {}

    public Builder reset(String string) {
      output += ANSI_RESET + string;
      return this;
    }

    public Builder black(String string) {
      output += ANSI_BLACK + string;
      return this;
    }

    public Builder red(String string) {
      output += ANSI_RED + string;
      return this;
    }

    public Builder green(String string) {
      output += ANSI_GREEN + string;
      return this;
    }

    public Builder yellow(String string) {
      output += ANSI_YELLOW + string;
      return this;
    }

    public Builder blue(String string) {
      output += ANSI_BLUE + string;
      return this;
    }

    public Builder purple(String string) {
      output += ANSI_PURPLE + string;
      return this;
    }

    public Builder cyan(String string) {
      output += ANSI_CYAN + string;
      return this;
    }

    public Builder gray(String string) {
      output += ANSI_GRAY + string;
      return this;
    }

    public Builder white(String string) {
      output += ANSI_WHITE + string;
      return this;
    }

    public String string() {
      return output;
    }

  }


}