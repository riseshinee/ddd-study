public class ValueObject {
  Height heightMetric = Height.metric(180);
  Height heightImperial = Height.imperial(5, 3);

  String string1 = heightMetric.toString();             // "180cm"
  String string2 = heightImperial.toString();           // "5 feet 3 inches"
  String string3 = heightMetric.toImperial().toString(); // "5 feet 11 inches"

  boolean firstIsHigher = heightMetric.isHigherThan(heightImperial); // true

  PhoneNumber phone = PhoneNumber.parse("+359877123503");
  String country = phone.getCountry();                        // "BG"
  String phoneType = phone.getPhoneType();                    // "MOBILE"
  boolean isValid = PhoneNumber.isValid("+972120266680");     // false

  Color red = Color.fromRGB(255, 0, 0);
  Color green = Color.getGreen();
  Color yellow = red.mixWith(green);
  String yellowString = yellow.toString();                    // "#FFFF00"
}
