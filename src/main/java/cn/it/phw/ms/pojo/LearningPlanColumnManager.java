package cn.it.phw.ms.pojo;

public class LearningPlanColumnManager extends BaseEntity {
    private Integer id;

    private Integer learningPlanFormId;

    private Integer learningPlanColumnId;

    private String learningPlanColumnName;

    private String learningPlanColumnContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLearningPlanFormId() {
        return learningPlanFormId;
    }

    public void setLearningPlanFormId(Integer learningPlanFormId) {
        this.learningPlanFormId = learningPlanFormId;
    }

    public Integer getLearningPlanColumnId() {
        return learningPlanColumnId;
    }

    public void setLearningPlanColumnId(Integer learningPlanColumnId) {
        this.learningPlanColumnId = learningPlanColumnId;
    }

    public String getLearningPlanColumnName() {
        return learningPlanColumnName;
    }

    public void setLearningPlanColumnName(String learningPlanColumnName) {
        this.learningPlanColumnName = learningPlanColumnName == null ? null : learningPlanColumnName.trim();
    }

    public String getLearningPlanColumnContent() {
        return learningPlanColumnContent;
    }

    public void setLearningPlanColumnContent(String learningPlanColumnContent) {
        this.learningPlanColumnContent = learningPlanColumnContent == null ? null : learningPlanColumnContent.trim();
    }
}