package chap03;

public class ExceptionHandleExam2 {
    static class AException extends RuntimeException{}
    static class BException extends RuntimeException{}

    public static void main(String[] args) {
        try {

        } catch (AException | BException e) {

        }
    }
}

