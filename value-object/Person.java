/**
 * Value Object: 유효성 검사 로직이 밸류 오브젝트 안에 있음, 비즈니스 로직을 응집시키는 데 기여
 * 유비쿼터스 언어를 사용하여 코드에서 비즈니스 도메인의 개념을 표현
 *
 */
class Person
{
  private PersonId     id;
  private Name         name;
  private PhoneNumber  landline;
  private PhoneNumber  mobile;
  private EmailAddress email;
  private Height       height;
  private CountryCode  country;

  public Person(PersonId id, Name name, PhoneNumber landline, PhoneNumber mobile, EmailAddress email, Height height, CountryCode country) {
    this.id = id;
    this.name = name;
    this.landline = landline;
    this.mobile = mobile;
    this.email = email;
    this.height = height;
    this.country = country;
  }
}

public class Main {
  public static void main(String[] args) {
    Person dave = new Person(
            new PersonId(30217),
            new Name("Dave", "Ancelovici"),
            PhoneNumber.parse("023745001"),
            PhoneNumber.parse("0873712503"),
            EmailAddress.parse("dave@learning-ddd.com"),
            Height.fromMetric(180),
            CountryCode.parse("BG")
    );
  }
}
