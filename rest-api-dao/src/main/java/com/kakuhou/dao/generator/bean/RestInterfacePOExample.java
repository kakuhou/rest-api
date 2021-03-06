package com.kakuhou.dao.generator.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestInterfacePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    protected String functionColumn = null;

    protected String function = null;

    public RestInterfacePOExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    public void setFunctionColumn(String functionColumn) {
        this.functionColumn=functionColumn;
    }

    public String getFunctionColumn() {
        return functionColumn;
    }

    public void setFunction(String function) {
        this.function=function;
    }

    public String getFunction() {
        return function;
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriIsNull() {
            addCriterion("interface_uri is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriIsNotNull() {
            addCriterion("interface_uri is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriEqualTo(String value) {
            addCriterion("interface_uri =", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriNotEqualTo(String value) {
            addCriterion("interface_uri <>", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriGreaterThan(String value) {
            addCriterion("interface_uri >", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriGreaterThanOrEqualTo(String value) {
            addCriterion("interface_uri >=", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriLessThan(String value) {
            addCriterion("interface_uri <", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriLessThanOrEqualTo(String value) {
            addCriterion("interface_uri <=", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriLike(String value) {
            addCriterion("interface_uri like", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriNotLike(String value) {
            addCriterion("interface_uri not like", value, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriIn(List<String> values) {
            addCriterion("interface_uri in", values, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriNotIn(List<String> values) {
            addCriterion("interface_uri not in", values, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriBetween(String value1, String value2) {
            addCriterion("interface_uri between", value1, value2, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andInterfaceUriNotBetween(String value1, String value2) {
            addCriterion("interface_uri not between", value1, value2, "interfaceUri");
            return (Criteria) this;
        }

        public Criteria andEncryptedIsNull() {
            addCriterion("encrypted is null");
            return (Criteria) this;
        }

        public Criteria andEncryptedIsNotNull() {
            addCriterion("encrypted is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptedEqualTo(Boolean value) {
            addCriterion("encrypted =", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotEqualTo(Boolean value) {
            addCriterion("encrypted <>", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedGreaterThan(Boolean value) {
            addCriterion("encrypted >", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("encrypted >=", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedLessThan(Boolean value) {
            addCriterion("encrypted <", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedLessThanOrEqualTo(Boolean value) {
            addCriterion("encrypted <=", value, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedIn(List<Boolean> values) {
            addCriterion("encrypted in", values, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotIn(List<Boolean> values) {
            addCriterion("encrypted not in", values, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedBetween(Boolean value1, Boolean value2) {
            addCriterion("encrypted between", value1, value2, "encrypted");
            return (Criteria) this;
        }

        public Criteria andEncryptedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("encrypted not between", value1, value2, "encrypted");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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