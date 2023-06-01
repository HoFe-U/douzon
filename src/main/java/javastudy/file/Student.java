package javastudy.file;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class Student {

    public Student() {
    }

    private Integer studentNo;
    private String email;
    private Integer koreanScore;
    private Integer englishScore;
    private Integer mathScore;
    private Integer scienceScore;
    private Integer historyScore;
    private Integer totalScore;
    private String teacherCode;
    private String achievement;
    private String localeCode;

    static class Builder{
        private Integer studentNo;
        private String email;
        private Integer koreanScore;
        private Integer englishScore;
        private Integer mathScore;
        private Integer scienceScore;
        private Integer historyScore;
        private Integer totalScore;
        private String teacherCode;
        private String achievement;
        private String localeCode;

        public Builder studentNo(Integer studentNo) {
            this.studentNo = studentNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder koreanScore(Integer koreanScore) {
            this.koreanScore = koreanScore;
            return this;
        }

        public Builder englishScore(Integer englishScore) {
            this.englishScore = englishScore;
            return this;
        }

        public Builder mathScore(Integer mathScore) {
            this.mathScore = mathScore;
            return this;
        }

        public Builder scienceScore(Integer scienceScore) {
            this.scienceScore = scienceScore;
            return this;
        }

        public Builder nationalHistoryScore(Integer historyScore) {
            this.historyScore = historyScore;
            return this;
        }

        public Builder hotalScore(Integer totalScore) {
            this.totalScore = totalScore;
            return this;
        }

        public Builder teacherCode(String teacherCode) {
            this.teacherCode = teacherCode;
            return this;
        }

        public Builder achievement(String achievement) {
            this.achievement = achievement;
            return this;
        }

        public Builder localeCode(String localeCode) {
            this.localeCode = localeCode;
            return this;
        }
        public Student build(){
            return new Student(this);
        }
    }

    public Student(Builder builder) {
        this.studentNo = builder.studentNo;
        this.email = builder.email;
        this.koreanScore = builder.koreanScore;
        this.englishScore = builder.englishScore;
        this.mathScore = builder.mathScore;
        this.scienceScore = builder.scienceScore;
        this.historyScore = builder.historyScore;
        this.totalScore = builder.totalScore;
        this.teacherCode = builder.teacherCode;
        this.achievement = builder.achievement;
        this.localeCode = builder.localeCode;
    }

    @Override
    public String toString() {
        return
             studentNo +
            "," + email +
            "," + koreanScore +
            "," + englishScore +
            "," + mathScore +
            "," + scienceScore +
            "," + historyScore +
            "," + totalScore +
            "," + teacherCode +
            "," + achievement +
            "," + localeCode;
    }

    public Integer getStudentNo() {
        return studentNo;
    }

    public String getEmail() {
        return email;
    }

    public Integer getKoreanScore() {
        return koreanScore;
    }

    public Integer getEnglishScore() {
        return englishScore;
    }

    public Integer getMathScore() {
        return mathScore;
    }

    public Integer getScienceScore() {
        return scienceScore;
    }

    public Integer getHistoryScore() {
        return historyScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public String getAchievement() {
        return achievement;
    }

    public String getLocaleCode() {
        return localeCode;
    }
}
