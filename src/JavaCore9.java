import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaCore9 {

    public static void main(String[] args) {

        // Подготовка...
        List<Course> courses = prepareCourse();
        List<Student> students = prepareStudent(courses);

        List<String> uniqueCourses = getUniqueCourseNames(students);
        System.out.println("Course list " + uniqueCourses.toString());

        List<Student> top3Students = getTop3Inquisitive(students);
        System.out.println();
        top3Students.stream()
                .map(Student::getName)
                .forEach(System.out::println);

        List<Student> studentsOnCourse = getStudentsOnCourse(students, courses.get(2));
        System.out.println("\nStudent list");
        studentsOnCourse.stream()
                .map(Student::getName)
                .forEach(System.out::println);
    }

    // Подготовка курсов...
    static List<Course> prepareCourse() {
        List<Course> courses = new ArrayList<>();
        for(int i=0; i<=5; i++) {
            courses.add(new Course("Course " + i));
        }

        return courses;
    }

    // Подготовка студентов...
    static List<Student> prepareStudent(List<Course> courses) {
        List<Student> students = new ArrayList<>();


        Student _student = new Student("Sergey");
        _student.setCourse(courses.get(0));
        _student.setCourse(courses.get(1));
        _student.setCourse(courses.get(2));
        _student.setCourse(courses.get(3));
        _student.setCourse(courses.get(4));

        students.add(_student);


        _student = new Student("Petya");
        _student.setCourse(courses.get(0));
        _student.setCourse(courses.get(1));
        _student.setCourse(courses.get(2));
        _student.setCourse(courses.get(3));

        students.add(_student);


        _student = new Student("Matvei");
        _student.setCourse(courses.get(0));
        _student.setCourse(courses.get(1));
        _student.setCourse(courses.get(2));

        students.add(_student);


        _student = new Student("Vova");
        _student.setCourse(courses.get(0));
        _student.setCourse(courses.get(1));

        students.add(_student);

        return students;
    }

    // Получить список уникальных курсов...
    static List<String> getUniqueCourseNames(List<Student> students) {
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(Collection::stream)
                .map(CourseInterface::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    // Получить ТОП-3 любознательных...
    static List<Student> getTop3Inquisitive(List<Student> students) {
        return students.stream()
                .sorted((stud1, stud2) -> stud2.getAllCourses().size() - stud1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    // Получить список студентов на целевом курсе...
    static List<Student> getStudentsOnCourse(List<Student> students, Course course) {
        String courseName = course.getName();
        return students.stream()
                .filter(stud -> stud.getAllCourses().stream().anyMatch(c -> c.getName().equals(courseName)))
                .collect(Collectors.toList());
    }
}