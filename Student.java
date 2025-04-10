public class Student {
    private String name;
    private String id;
    private double[] scores;
    private double average;
    private char grade;
    private String recommendation;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.scores = new double[5]; // 5 subjects
    }

    // Setters and Getters
    public void setScores(double[] scores) { this.scores = scores; }
    public String getName() { return name; }
    public String getId() { return id; }
    public double[] getScores() { return scores; }
    public double getAverage() { return average; }
    public char getGrade() { return grade; }
    public String getRecommendation() { return recommendation; }

    // Calculate average score
    public void calculateAverage() {
        double sum = 0;
        for (double score : scores) sum += score;
        average = sum / scores.length;
    }

    // Determine grade and recommendation
    public void calculateGradeAndRecommendation() {
        if (average >= 70) {
            grade = 'A';
            recommendation = "Excellent";
        } else if (average >= 60) {
            grade = 'B';
            recommendation = "Good";
        } else if (average >= 50) {
            grade = 'C';
            recommendation = "Average";
        } else if (average >= 40) {
            grade = 'D';
            recommendation = "Pass";
        } else {
            grade = 'F';
            recommendation = "Fail";
        }
    }
}