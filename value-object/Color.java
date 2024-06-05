public class Color {
  public final byte red;
  public final byte green;
  public final byte blue;

  public Color(byte r, byte g, byte b) {
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  public Color mixWith(Color other) {
    return new Color(
            (byte) Math.min(this.red + other.red, 255),
            (byte) Math.min(this.green + other.green, 255),
            (byte) Math.min(this.blue + other.blue, 255)
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Color other = (Color) obj;
    return red == other.red && green == other.green && blue == other.blue;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(red, green, blue);
  }

  // ...
}

  public static void main(String[] args) {
    Color red = new Color((byte) 255, (byte) 0, (byte) 0);
    Color green = new Color((byte) 0, (byte) 255, (byte) 0);
    Color yellow = red.mixWith(green);
    System.out.println(yellow);  // toString() 메서드를 정의하여 사용합니다.
  }
