package bot.models.enums;

public enum WorkingPlaces {
  ENGELSA("Энгельса 36"),
  GORKOGO("Горького 67"),
  GOGOLYA("Гоголя 44"),
  KUYBISHEVA("Куйбышева 67");

  private final String address;

  WorkingPlaces(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return this.address;
  }
}
