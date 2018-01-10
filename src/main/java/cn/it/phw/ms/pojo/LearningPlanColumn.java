package cn.it.phw.ms.pojo;

public class LearningPlanColumn extends BaseEntity {
    private Integer id;

    private String learningPlanColumnName;

    private String learningplancolumnDesc;

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

    public String getLearningplancolumnDesc() {
        return learningplancolumnDesc;
    }

    public void setLearningplancolumnDesc(String learningplancolumnDesc) {
        this.learningplancolumnDesc = learningplancolumnDesc == null ? null : learningplancolumnDesc.trim();
    }
}