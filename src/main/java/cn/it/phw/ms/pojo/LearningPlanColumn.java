package cn.it.phw.ms.pojo;

public class LearningPlanColumn extends BaseEntity {
    private Integer id;

    private String learningPlanColumnName;

    private String learningPlanColumnDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLearningPlanColumnName() {
        return learningPlanColumnName;
    }

    public void setLearningPlanColumnName(String learningPlanColumnName) {
        this.learningPlanColumnName = learningPlanColumnName == null ? null : learningPlanColumnName.trim();
    }

    public String getLearningPlanColumnDesc() {
        return learningPlanColumnDesc;
    }

    public void setLearningPlanColumnDesc(String learningPlanColumnDesc) {
        this.learningPlanColumnDesc = learningPlanColumnDesc == null ? null : learningPlanColumnDesc.trim();
    }
}