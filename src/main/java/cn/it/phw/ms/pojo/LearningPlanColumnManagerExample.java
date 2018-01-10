package cn.it.phw.ms.pojo;

import java.util.ArrayList;
import java.util.List;

public class LearningPlanColumnManagerExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LearningPlanColumnManagerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdIsNull() {
            addCriterion("learning_plan_form_id is null");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdIsNotNull() {
            addCriterion("learning_plan_form_id is not null");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdEqualTo(Integer value) {
            addCriterion("learning_plan_form_id =", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdNotEqualTo(Integer value) {
            addCriterion("learning_plan_form_id <>", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdGreaterThan(Integer value) {
            addCriterion("learning_plan_form_id >", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("learning_plan_form_id >=", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdLessThan(Integer value) {
            addCriterion("learning_plan_form_id <", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdLessThanOrEqualTo(Integer value) {
            addCriterion("learning_plan_form_id <=", value, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdIn(List<Integer> values) {
            addCriterion("learning_plan_form_id in", values, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdNotIn(List<Integer> values) {
            addCriterion("learning_plan_form_id not in", values, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdBetween(Integer value1, Integer value2) {
            addCriterion("learning_plan_form_id between", value1, value2, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanFormIdNotBetween(Integer value1, Integer value2) {
            addCriterion("learning_plan_form_id not between", value1, value2, "learningPlanFormId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdIsNull() {
            addCriterion("learning_plan_column_id is null");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdIsNotNull() {
            addCriterion("learning_plan_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdEqualTo(Integer value) {
            addCriterion("learning_plan_column_id =", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdNotEqualTo(Integer value) {
            addCriterion("learning_plan_column_id <>", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdGreaterThan(Integer value) {
            addCriterion("learning_plan_column_id >", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("learning_plan_column_id >=", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdLessThan(Integer value) {
            addCriterion("learning_plan_column_id <", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("learning_plan_column_id <=", value, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdIn(List<Integer> values) {
            addCriterion("learning_plan_column_id in", values, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdNotIn(List<Integer> values) {
            addCriterion("learning_plan_column_id not in", values, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("learning_plan_column_id between", value1, value2, "learningPlanColumnId");
            return (Criteria) this;
        }

        public Criteria andLearningPlanColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("learning_plan_column_id not between", value1, value2, "learningPlanColumnId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}