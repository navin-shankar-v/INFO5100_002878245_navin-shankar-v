import java.util.*;

import static java.lang.System.out;

abstract class Student {

    String stdName;
    String stdType;
    List<Integer> quizScore;

    public Student(String stdName, String stdType, List<Integer> quizScore) {
        this.stdName = stdName;
        this.stdType = stdType;
        this.quizScore = quizScore;
    }


    public void addQuizScore(int score) {
        quizScore.add(score);
    }

    public String getStdName() {
        return stdName;
    }

    public List<Integer> getQuizScore() {
        return quizScore;
    }

    public void calculateAverageScore(){
        int totalScore = 0;
        for (Integer score : quizScore) {
            totalScore += score;
        }
        double averageScore = (double) totalScore / quizScore.size(); //Total score is cast to double
        out.println(stdName +"  "+stdType+"  : Average Quiz Score = " + averageScore+"\n");

    }

}

class partTimeStudent extends Student{
    public partTimeStudent(String stdName, String stdType, List<Integer> quizScore) {
        super(stdName, stdType, quizScore);
    }
}


class FullTimeStudents extends Student{
    double quizMark1;
    double quizMark2;
    public FullTimeStudents(String stdName, String stdType, List<Integer> quizScore,int quizMark1, int quizMark2){
        super(stdName, stdType, quizScore);
        this.quizMark1 = quizMark1;
        this.quizMark2 = quizMark2;
        addQuizScore(quizMark1);
        addQuizScore(quizMark2);
    }

    public void studentDetails(){
        out.println("Student Name "+stdName+":\n Quiz score 1: "+quizMark1+"\n Quiz score 2: "+quizMark2);
    }
}

class Session{
    List<Student> stds;
    public Session(){
        stds = new LinkedList<>();
    }
    public void addStudents(Student student){
        stds.add(student);
    }

    public void printStudentList() { //To print students in a Session part time and full time
        System.out.println("Student List in a Session:");
        for (Student student : stds) {
            System.out.println(student.getStdName()+" "+student.stdType+"\n");
        }
    }
    public void calAndPrintAverageScore(){
        for(Student student : stds){
            student.calculateAverageScore(); //Called the method from abstract class student
        }
    }

    public void printScoresAscending(){
        List<Integer> allQuizScores = new LinkedList<>();
        for (Student student : stds) {
            allQuizScores.addAll(student.quizScore);
            Collections.sort(allQuizScores);
        }
        out.println("Quiz scores in Ascending Order "+allQuizScores+"\n");
    }

    public void fullTimeStudentMark(){
        out.println("Marks of Full time Students");
        List<String> studentName = new LinkedList<>();
        for (Student student : stds) {
            if (student instanceof FullTimeStudents) {
                FullTimeStudents fullTimeStudent = (FullTimeStudents) student; // Cast to FullTimeStudents
                System.out.println(fullTimeStudent.getStdName() + " " + fullTimeStudent.stdType + " " + fullTimeStudent.getQuizScore()+" \n");
            }
        }
    }

    public void printPartTimeStudentNames() {
        System.out.println("Part-Time Students:");
        for (Student student : stds) {
            if (student instanceof partTimeStudent) {
                System.out.println(student.getStdName() + " " + student.stdType+"\n");
            }
        }
    }

}

public class exercise1 {
    public static void main(String[] args) {

        List<String> randomNames = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Ivan", "Julia", "Kevin", "Linda", "Michael", "Nina", "Oliver", "Pamela", "Quincy", "Rachel", "Samuel", "Tina");
        Session session = new Session();

        Random random = new Random();


        for (int i = 1; i <= 20; i++)
        {
            List<Integer> quizScores = new LinkedList<>();
            for (int j = 0; j < 15; j++) {
                quizScores.add((int) (Math.random() * 100));
            }
            if (i % 2 == 0) {
                String randomName = randomNames.get(random.nextInt(randomNames.size()));

                FullTimeStudents fullTimeStudent = new FullTimeStudents(randomName + i, "Full time", quizScores, (int) (Math.random() * 100), (int) (Math.random() * 100));

                session.addStudents(fullTimeStudent);
            } else {
                String randomName = randomNames.get(random.nextInt(randomNames.size()));

                partTimeStudent partTimeStudent = new partTimeStudent(randomName + i,"Part Time", quizScores);
                session.addStudents(partTimeStudent);
            }
        }

        session.printStudentList();
        session.fullTimeStudentMark();
        session.calAndPrintAverageScore();
        session.printScoresAscending();


        session.printPartTimeStudentNames();
    }
}



