import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args) {
        ArrayList<Person> guys = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonData.txt");

        boolean done = false;

        // a.	ID (a String)
        // b.	FirstName
        // c.	LastName
        // d.	Title (a string like Mr., Mrs., Ms., Dr., etc.)
        // e.	YearOfBirth (an int)

        String ID = "";
        String FirstName = "";
        String LastName = "";
        String Title = "";
        int YearOfBirth = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits] ");
            FirstName = SafeInput.getNonZeroLenString(in, "Enter the first name ");
            LastName = SafeInput.getNonZeroLenString(in, "Enter the last name ");
            Title = SafeInput.getNonZeroLenString(in, "Enter the title ");
            YearOfBirth = SafeInput.getRangedInt(in, "Enter the year of birth", 1940, 2023);

            Person person = new Person(ID, FirstName, LastName, Title, YearOfBirth);
            guys.add(person);

            done = SafeInput.getYNConfirm(in, "Are you done ?");
        } while (!done);

        for (Person person: guys)
        { System.out.println(person.toCSVDataRecord()); }


        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person person : guys)
            {
                writer.write(person.toCSVDataRecord());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line
            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
