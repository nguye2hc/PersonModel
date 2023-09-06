public class PersonTest

{  public static void main(String[] args)
{

    Person Huy = new Person( "123456", "Huy", "Nguyen","Student", 2004);

    Huy.setFirstName("Huy");

    System.out.println(Huy.getFirstName());

    System.out.println(Huy);

    System.out.println(Huy.toCSVDataRecord());

    System.out.println(Huy.getFullName());

    System.out.println(Huy.getFormalName());

    System.out.println(Huy.getYear());

    System.out.println(Huy.getAge());
}
}
