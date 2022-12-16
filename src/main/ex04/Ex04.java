package ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ex04 {

    public ArrayList<String> ex04(Program program){

        return program.courses.stream()
               .flatMap(Course -> Course.students.values().stream().distinct())
               .map(Student ->
                       Student.firstName.charAt(0) +
                       Student.lastName.charAt(0) +
                       Student.studentId.toString().charAt(2) +
                       Student.studentId.toString().charAt(3) +
                       "@hk.no")
               .collect(Collectors.toCollection(ArrayList::new));
    }

    /*
    * Flatmap for convertin course to student and .distinct for streaming the same student only once
    * map from student to string with wished criteria.
    * Using .toCollection(ArrayList::new) to return wished list type.
    *
    * */
}
