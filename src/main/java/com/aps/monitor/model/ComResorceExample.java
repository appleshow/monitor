package com.aps.monitor.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ComResorceExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public ComResorceExample() {
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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andResourceIdIsNull() {
			addCriterion("RESOURCE_ID is null");
			return (Criteria) this;
		}

		public Criteria andResourceIdIsNotNull() {
			addCriterion("RESOURCE_ID is not null");
			return (Criteria) this;
		}

		public Criteria andResourceIdEqualTo(Integer value) {
			addCriterion("RESOURCE_ID =", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdNotEqualTo(Integer value) {
			addCriterion("RESOURCE_ID <>", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdGreaterThan(Integer value) {
			addCriterion("RESOURCE_ID >", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_ID >=", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdLessThan(Integer value) {
			addCriterion("RESOURCE_ID <", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdLessThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_ID <=", value, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdIn(List<Integer> values) {
			addCriterion("RESOURCE_ID in", values, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdNotIn(List<Integer> values) {
			addCriterion("RESOURCE_ID not in", values, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_ID between", value1, value2, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceIdNotBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_ID not between", value1, value2, "resourceId");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAIsNull() {
			addCriterion("RESOURCE_TYPE_A is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAIsNotNull() {
			addCriterion("RESOURCE_TYPE_A is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_A =", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeANotEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_A <>", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAGreaterThan(String value) {
			addCriterion("RESOURCE_TYPE_A >", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAGreaterThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_A >=", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeALessThan(String value) {
			addCriterion("RESOURCE_TYPE_A <", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeALessThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_A <=", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeALike(String value) {
			addCriterion("RESOURCE_TYPE_A like", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeANotLike(String value) {
			addCriterion("RESOURCE_TYPE_A not like", value, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeAIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_A in", values, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeANotIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_A not in", values, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeABetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_A between", value1, value2, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeANotBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_A not between", value1, value2, "resourceTypeA");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBIsNull() {
			addCriterion("RESOURCE_TYPE_B is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBIsNotNull() {
			addCriterion("RESOURCE_TYPE_B is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_B =", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBNotEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_B <>", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBGreaterThan(String value) {
			addCriterion("RESOURCE_TYPE_B >", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBGreaterThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_B >=", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBLessThan(String value) {
			addCriterion("RESOURCE_TYPE_B <", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBLessThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_B <=", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBLike(String value) {
			addCriterion("RESOURCE_TYPE_B like", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBNotLike(String value) {
			addCriterion("RESOURCE_TYPE_B not like", value, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_B in", values, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBNotIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_B not in", values, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_B between", value1, value2, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeBNotBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_B not between", value1, value2, "resourceTypeB");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCIsNull() {
			addCriterion("RESOURCE_TYPE_C is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCIsNotNull() {
			addCriterion("RESOURCE_TYPE_C is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_C =", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCNotEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_C <>", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCGreaterThan(String value) {
			addCriterion("RESOURCE_TYPE_C >", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCGreaterThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_C >=", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCLessThan(String value) {
			addCriterion("RESOURCE_TYPE_C <", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCLessThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_C <=", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCLike(String value) {
			addCriterion("RESOURCE_TYPE_C like", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCNotLike(String value) {
			addCriterion("RESOURCE_TYPE_C not like", value, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_C in", values, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCNotIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_C not in", values, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_C between", value1, value2, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeCNotBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_C not between", value1, value2, "resourceTypeC");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDIsNull() {
			addCriterion("RESOURCE_TYPE_D is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDIsNotNull() {
			addCriterion("RESOURCE_TYPE_D is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_D =", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDNotEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_D <>", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDGreaterThan(String value) {
			addCriterion("RESOURCE_TYPE_D >", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDGreaterThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_D >=", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDLessThan(String value) {
			addCriterion("RESOURCE_TYPE_D <", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDLessThanOrEqualTo(String value) {
			addCriterion("RESOURCE_TYPE_D <=", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDLike(String value) {
			addCriterion("RESOURCE_TYPE_D like", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDNotLike(String value) {
			addCriterion("RESOURCE_TYPE_D not like", value, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_D in", values, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDNotIn(List<String> values) {
			addCriterion("RESOURCE_TYPE_D not in", values, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_D between", value1, value2, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeDNotBetween(String value1, String value2) {
			addCriterion("RESOURCE_TYPE_D not between", value1, value2, "resourceTypeD");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEIsNull() {
			addCriterion("RESOURCE_TYPE_E is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEIsNotNull() {
			addCriterion("RESOURCE_TYPE_E is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_E =", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeENotEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_E <>", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEGreaterThan(Integer value) {
			addCriterion("RESOURCE_TYPE_E >", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEGreaterThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_E >=", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeELessThan(Integer value) {
			addCriterion("RESOURCE_TYPE_E <", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeELessThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_E <=", value, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_E in", values, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeENotIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_E not in", values, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeEBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_E between", value1, value2, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeENotBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_E not between", value1, value2, "resourceTypeE");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFIsNull() {
			addCriterion("RESOURCE_TYPE_F is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFIsNotNull() {
			addCriterion("RESOURCE_TYPE_F is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_F =", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFNotEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_F <>", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFGreaterThan(Integer value) {
			addCriterion("RESOURCE_TYPE_F >", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFGreaterThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_F >=", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFLessThan(Integer value) {
			addCriterion("RESOURCE_TYPE_F <", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFLessThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_F <=", value, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_F in", values, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFNotIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_F not in", values, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_F between", value1, value2, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeFNotBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_F not between", value1, value2, "resourceTypeF");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGIsNull() {
			addCriterion("RESOURCE_TYPE_G is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGIsNotNull() {
			addCriterion("RESOURCE_TYPE_G is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_G =", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGNotEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_G <>", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGGreaterThan(Integer value) {
			addCriterion("RESOURCE_TYPE_G >", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGGreaterThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_G >=", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGLessThan(Integer value) {
			addCriterion("RESOURCE_TYPE_G <", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGLessThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_G <=", value, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_G in", values, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGNotIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_G not in", values, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_G between", value1, value2, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeGNotBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_G not between", value1, value2, "resourceTypeG");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHIsNull() {
			addCriterion("RESOURCE_TYPE_H is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHIsNotNull() {
			addCriterion("RESOURCE_TYPE_H is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_H =", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHNotEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_H <>", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHGreaterThan(Integer value) {
			addCriterion("RESOURCE_TYPE_H >", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHGreaterThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_H >=", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHLessThan(Integer value) {
			addCriterion("RESOURCE_TYPE_H <", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHLessThanOrEqualTo(Integer value) {
			addCriterion("RESOURCE_TYPE_H <=", value, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_H in", values, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHNotIn(List<Integer> values) {
			addCriterion("RESOURCE_TYPE_H not in", values, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_H between", value1, value2, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeHNotBetween(Integer value1, Integer value2) {
			addCriterion("RESOURCE_TYPE_H not between", value1, value2, "resourceTypeH");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKIsNull() {
			addCriterion("RESOURCE_TYPE_K is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKIsNotNull() {
			addCriterion("RESOURCE_TYPE_K is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K =", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKNotEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K <>", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKGreaterThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K >", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K >=", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKLessThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K <", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K <=", value, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K in", values, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKNotIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K not in", values, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K between", value1, value2, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeKNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_K not between", value1, value2, "resourceTypeK");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLIsNull() {
			addCriterion("RESOURCE_TYPE_L is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLIsNotNull() {
			addCriterion("RESOURCE_TYPE_L is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L =", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLNotEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L <>", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLGreaterThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L >", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L >=", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLLessThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L <", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L <=", value, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L in", values, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLNotIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L not in", values, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L between", value1, value2, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeLNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_L not between", value1, value2, "resourceTypeL");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMIsNull() {
			addCriterion("RESOURCE_TYPE_M is null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMIsNotNull() {
			addCriterion("RESOURCE_TYPE_M is not null");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M =", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMNotEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M <>", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMGreaterThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M >", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M >=", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMLessThan(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M <", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M <=", value, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M in", values, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMNotIn(List<Date> values) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M not in", values, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M between", value1, value2, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andResourceTypeMNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("RESOURCE_TYPE_M not between", value1, value2, "resourceTypeM");
			return (Criteria) this;
		}

		public Criteria andPrflagIsNull() {
			addCriterion("PRFLAG is null");
			return (Criteria) this;
		}

		public Criteria andPrflagIsNotNull() {
			addCriterion("PRFLAG is not null");
			return (Criteria) this;
		}

		public Criteria andPrflagEqualTo(Integer value) {
			addCriterion("PRFLAG =", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagNotEqualTo(Integer value) {
			addCriterion("PRFLAG <>", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagGreaterThan(Integer value) {
			addCriterion("PRFLAG >", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagGreaterThanOrEqualTo(Integer value) {
			addCriterion("PRFLAG >=", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagLessThan(Integer value) {
			addCriterion("PRFLAG <", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagLessThanOrEqualTo(Integer value) {
			addCriterion("PRFLAG <=", value, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagIn(List<Integer> values) {
			addCriterion("PRFLAG in", values, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagNotIn(List<Integer> values) {
			addCriterion("PRFLAG not in", values, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagBetween(Integer value1, Integer value2) {
			addCriterion("PRFLAG between", value1, value2, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrflagNotBetween(Integer value1, Integer value2) {
			addCriterion("PRFLAG not between", value1, value2, "prflag");
			return (Criteria) this;
		}

		public Criteria andPrgroupIsNull() {
			addCriterion("PRGROUP is null");
			return (Criteria) this;
		}

		public Criteria andPrgroupIsNotNull() {
			addCriterion("PRGROUP is not null");
			return (Criteria) this;
		}

		public Criteria andPrgroupEqualTo(String value) {
			addCriterion("PRGROUP =", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupNotEqualTo(String value) {
			addCriterion("PRGROUP <>", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupGreaterThan(String value) {
			addCriterion("PRGROUP >", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupGreaterThanOrEqualTo(String value) {
			addCriterion("PRGROUP >=", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupLessThan(String value) {
			addCriterion("PRGROUP <", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupLessThanOrEqualTo(String value) {
			addCriterion("PRGROUP <=", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupLike(String value) {
			addCriterion("PRGROUP like", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupNotLike(String value) {
			addCriterion("PRGROUP not like", value, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupIn(List<String> values) {
			addCriterion("PRGROUP in", values, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupNotIn(List<String> values) {
			addCriterion("PRGROUP not in", values, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupBetween(String value1, String value2) {
			addCriterion("PRGROUP between", value1, value2, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrgroupNotBetween(String value1, String value2) {
			addCriterion("PRGROUP not between", value1, value2, "prgroup");
			return (Criteria) this;
		}

		public Criteria andPrtypeIsNull() {
			addCriterion("PRTYPE is null");
			return (Criteria) this;
		}

		public Criteria andPrtypeIsNotNull() {
			addCriterion("PRTYPE is not null");
			return (Criteria) this;
		}

		public Criteria andPrtypeEqualTo(String value) {
			addCriterion("PRTYPE =", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeNotEqualTo(String value) {
			addCriterion("PRTYPE <>", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeGreaterThan(String value) {
			addCriterion("PRTYPE >", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeGreaterThanOrEqualTo(String value) {
			addCriterion("PRTYPE >=", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeLessThan(String value) {
			addCriterion("PRTYPE <", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeLessThanOrEqualTo(String value) {
			addCriterion("PRTYPE <=", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeLike(String value) {
			addCriterion("PRTYPE like", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeNotLike(String value) {
			addCriterion("PRTYPE not like", value, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeIn(List<String> values) {
			addCriterion("PRTYPE in", values, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeNotIn(List<String> values) {
			addCriterion("PRTYPE not in", values, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeBetween(String value1, String value2) {
			addCriterion("PRTYPE between", value1, value2, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrtypeNotBetween(String value1, String value2) {
			addCriterion("PRTYPE not between", value1, value2, "prtype");
			return (Criteria) this;
		}

		public Criteria andPrclassIsNull() {
			addCriterion("PRCLASS is null");
			return (Criteria) this;
		}

		public Criteria andPrclassIsNotNull() {
			addCriterion("PRCLASS is not null");
			return (Criteria) this;
		}

		public Criteria andPrclassEqualTo(String value) {
			addCriterion("PRCLASS =", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassNotEqualTo(String value) {
			addCriterion("PRCLASS <>", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassGreaterThan(String value) {
			addCriterion("PRCLASS >", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassGreaterThanOrEqualTo(String value) {
			addCriterion("PRCLASS >=", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassLessThan(String value) {
			addCriterion("PRCLASS <", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassLessThanOrEqualTo(String value) {
			addCriterion("PRCLASS <=", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassLike(String value) {
			addCriterion("PRCLASS like", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassNotLike(String value) {
			addCriterion("PRCLASS not like", value, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassIn(List<String> values) {
			addCriterion("PRCLASS in", values, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassNotIn(List<String> values) {
			addCriterion("PRCLASS not in", values, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassBetween(String value1, String value2) {
			addCriterion("PRCLASS between", value1, value2, "prclass");
			return (Criteria) this;
		}

		public Criteria andPrclassNotBetween(String value1, String value2) {
			addCriterion("PRCLASS not between", value1, value2, "prclass");
			return (Criteria) this;
		}

		public Criteria andProperty0IsNull() {
			addCriterion("PROPERTY0 is null");
			return (Criteria) this;
		}

		public Criteria andProperty0IsNotNull() {
			addCriterion("PROPERTY0 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty0EqualTo(String value) {
			addCriterion("PROPERTY0 =", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0NotEqualTo(String value) {
			addCriterion("PROPERTY0 <>", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0GreaterThan(String value) {
			addCriterion("PROPERTY0 >", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY0 >=", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0LessThan(String value) {
			addCriterion("PROPERTY0 <", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY0 <=", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0Like(String value) {
			addCriterion("PROPERTY0 like", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0NotLike(String value) {
			addCriterion("PROPERTY0 not like", value, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0In(List<String> values) {
			addCriterion("PROPERTY0 in", values, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0NotIn(List<String> values) {
			addCriterion("PROPERTY0 not in", values, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0Between(String value1, String value2) {
			addCriterion("PROPERTY0 between", value1, value2, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty0NotBetween(String value1, String value2) {
			addCriterion("PROPERTY0 not between", value1, value2, "property0");
			return (Criteria) this;
		}

		public Criteria andProperty1IsNull() {
			addCriterion("PROPERTY1 is null");
			return (Criteria) this;
		}

		public Criteria andProperty1IsNotNull() {
			addCriterion("PROPERTY1 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty1EqualTo(String value) {
			addCriterion("PROPERTY1 =", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1NotEqualTo(String value) {
			addCriterion("PROPERTY1 <>", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1GreaterThan(String value) {
			addCriterion("PROPERTY1 >", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY1 >=", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1LessThan(String value) {
			addCriterion("PROPERTY1 <", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY1 <=", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1Like(String value) {
			addCriterion("PROPERTY1 like", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1NotLike(String value) {
			addCriterion("PROPERTY1 not like", value, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1In(List<String> values) {
			addCriterion("PROPERTY1 in", values, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1NotIn(List<String> values) {
			addCriterion("PROPERTY1 not in", values, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1Between(String value1, String value2) {
			addCriterion("PROPERTY1 between", value1, value2, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty1NotBetween(String value1, String value2) {
			addCriterion("PROPERTY1 not between", value1, value2, "property1");
			return (Criteria) this;
		}

		public Criteria andProperty2IsNull() {
			addCriterion("PROPERTY2 is null");
			return (Criteria) this;
		}

		public Criteria andProperty2IsNotNull() {
			addCriterion("PROPERTY2 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty2EqualTo(String value) {
			addCriterion("PROPERTY2 =", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2NotEqualTo(String value) {
			addCriterion("PROPERTY2 <>", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2GreaterThan(String value) {
			addCriterion("PROPERTY2 >", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY2 >=", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2LessThan(String value) {
			addCriterion("PROPERTY2 <", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY2 <=", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2Like(String value) {
			addCriterion("PROPERTY2 like", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2NotLike(String value) {
			addCriterion("PROPERTY2 not like", value, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2In(List<String> values) {
			addCriterion("PROPERTY2 in", values, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2NotIn(List<String> values) {
			addCriterion("PROPERTY2 not in", values, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2Between(String value1, String value2) {
			addCriterion("PROPERTY2 between", value1, value2, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty2NotBetween(String value1, String value2) {
			addCriterion("PROPERTY2 not between", value1, value2, "property2");
			return (Criteria) this;
		}

		public Criteria andProperty3IsNull() {
			addCriterion("PROPERTY3 is null");
			return (Criteria) this;
		}

		public Criteria andProperty3IsNotNull() {
			addCriterion("PROPERTY3 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty3EqualTo(String value) {
			addCriterion("PROPERTY3 =", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3NotEqualTo(String value) {
			addCriterion("PROPERTY3 <>", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3GreaterThan(String value) {
			addCriterion("PROPERTY3 >", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY3 >=", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3LessThan(String value) {
			addCriterion("PROPERTY3 <", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY3 <=", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3Like(String value) {
			addCriterion("PROPERTY3 like", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3NotLike(String value) {
			addCriterion("PROPERTY3 not like", value, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3In(List<String> values) {
			addCriterion("PROPERTY3 in", values, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3NotIn(List<String> values) {
			addCriterion("PROPERTY3 not in", values, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3Between(String value1, String value2) {
			addCriterion("PROPERTY3 between", value1, value2, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty3NotBetween(String value1, String value2) {
			addCriterion("PROPERTY3 not between", value1, value2, "property3");
			return (Criteria) this;
		}

		public Criteria andProperty4IsNull() {
			addCriterion("PROPERTY4 is null");
			return (Criteria) this;
		}

		public Criteria andProperty4IsNotNull() {
			addCriterion("PROPERTY4 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty4EqualTo(String value) {
			addCriterion("PROPERTY4 =", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4NotEqualTo(String value) {
			addCriterion("PROPERTY4 <>", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4GreaterThan(String value) {
			addCriterion("PROPERTY4 >", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY4 >=", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4LessThan(String value) {
			addCriterion("PROPERTY4 <", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY4 <=", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4Like(String value) {
			addCriterion("PROPERTY4 like", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4NotLike(String value) {
			addCriterion("PROPERTY4 not like", value, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4In(List<String> values) {
			addCriterion("PROPERTY4 in", values, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4NotIn(List<String> values) {
			addCriterion("PROPERTY4 not in", values, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4Between(String value1, String value2) {
			addCriterion("PROPERTY4 between", value1, value2, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty4NotBetween(String value1, String value2) {
			addCriterion("PROPERTY4 not between", value1, value2, "property4");
			return (Criteria) this;
		}

		public Criteria andProperty5IsNull() {
			addCriterion("PROPERTY5 is null");
			return (Criteria) this;
		}

		public Criteria andProperty5IsNotNull() {
			addCriterion("PROPERTY5 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty5EqualTo(String value) {
			addCriterion("PROPERTY5 =", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5NotEqualTo(String value) {
			addCriterion("PROPERTY5 <>", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5GreaterThan(String value) {
			addCriterion("PROPERTY5 >", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY5 >=", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5LessThan(String value) {
			addCriterion("PROPERTY5 <", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY5 <=", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5Like(String value) {
			addCriterion("PROPERTY5 like", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5NotLike(String value) {
			addCriterion("PROPERTY5 not like", value, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5In(List<String> values) {
			addCriterion("PROPERTY5 in", values, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5NotIn(List<String> values) {
			addCriterion("PROPERTY5 not in", values, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5Between(String value1, String value2) {
			addCriterion("PROPERTY5 between", value1, value2, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty5NotBetween(String value1, String value2) {
			addCriterion("PROPERTY5 not between", value1, value2, "property5");
			return (Criteria) this;
		}

		public Criteria andProperty6IsNull() {
			addCriterion("PROPERTY6 is null");
			return (Criteria) this;
		}

		public Criteria andProperty6IsNotNull() {
			addCriterion("PROPERTY6 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty6EqualTo(String value) {
			addCriterion("PROPERTY6 =", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6NotEqualTo(String value) {
			addCriterion("PROPERTY6 <>", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6GreaterThan(String value) {
			addCriterion("PROPERTY6 >", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY6 >=", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6LessThan(String value) {
			addCriterion("PROPERTY6 <", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY6 <=", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6Like(String value) {
			addCriterion("PROPERTY6 like", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6NotLike(String value) {
			addCriterion("PROPERTY6 not like", value, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6In(List<String> values) {
			addCriterion("PROPERTY6 in", values, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6NotIn(List<String> values) {
			addCriterion("PROPERTY6 not in", values, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6Between(String value1, String value2) {
			addCriterion("PROPERTY6 between", value1, value2, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty6NotBetween(String value1, String value2) {
			addCriterion("PROPERTY6 not between", value1, value2, "property6");
			return (Criteria) this;
		}

		public Criteria andProperty7IsNull() {
			addCriterion("PROPERTY7 is null");
			return (Criteria) this;
		}

		public Criteria andProperty7IsNotNull() {
			addCriterion("PROPERTY7 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty7EqualTo(String value) {
			addCriterion("PROPERTY7 =", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7NotEqualTo(String value) {
			addCriterion("PROPERTY7 <>", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7GreaterThan(String value) {
			addCriterion("PROPERTY7 >", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY7 >=", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7LessThan(String value) {
			addCriterion("PROPERTY7 <", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY7 <=", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7Like(String value) {
			addCriterion("PROPERTY7 like", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7NotLike(String value) {
			addCriterion("PROPERTY7 not like", value, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7In(List<String> values) {
			addCriterion("PROPERTY7 in", values, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7NotIn(List<String> values) {
			addCriterion("PROPERTY7 not in", values, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7Between(String value1, String value2) {
			addCriterion("PROPERTY7 between", value1, value2, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty7NotBetween(String value1, String value2) {
			addCriterion("PROPERTY7 not between", value1, value2, "property7");
			return (Criteria) this;
		}

		public Criteria andProperty8IsNull() {
			addCriterion("PROPERTY8 is null");
			return (Criteria) this;
		}

		public Criteria andProperty8IsNotNull() {
			addCriterion("PROPERTY8 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty8EqualTo(String value) {
			addCriterion("PROPERTY8 =", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8NotEqualTo(String value) {
			addCriterion("PROPERTY8 <>", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8GreaterThan(String value) {
			addCriterion("PROPERTY8 >", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY8 >=", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8LessThan(String value) {
			addCriterion("PROPERTY8 <", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY8 <=", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8Like(String value) {
			addCriterion("PROPERTY8 like", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8NotLike(String value) {
			addCriterion("PROPERTY8 not like", value, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8In(List<String> values) {
			addCriterion("PROPERTY8 in", values, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8NotIn(List<String> values) {
			addCriterion("PROPERTY8 not in", values, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8Between(String value1, String value2) {
			addCriterion("PROPERTY8 between", value1, value2, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty8NotBetween(String value1, String value2) {
			addCriterion("PROPERTY8 not between", value1, value2, "property8");
			return (Criteria) this;
		}

		public Criteria andProperty9IsNull() {
			addCriterion("PROPERTY9 is null");
			return (Criteria) this;
		}

		public Criteria andProperty9IsNotNull() {
			addCriterion("PROPERTY9 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty9EqualTo(String value) {
			addCriterion("PROPERTY9 =", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9NotEqualTo(String value) {
			addCriterion("PROPERTY9 <>", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9GreaterThan(String value) {
			addCriterion("PROPERTY9 >", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9GreaterThanOrEqualTo(String value) {
			addCriterion("PROPERTY9 >=", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9LessThan(String value) {
			addCriterion("PROPERTY9 <", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9LessThanOrEqualTo(String value) {
			addCriterion("PROPERTY9 <=", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9Like(String value) {
			addCriterion("PROPERTY9 like", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9NotLike(String value) {
			addCriterion("PROPERTY9 not like", value, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9In(List<String> values) {
			addCriterion("PROPERTY9 in", values, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9NotIn(List<String> values) {
			addCriterion("PROPERTY9 not in", values, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9Between(String value1, String value2) {
			addCriterion("PROPERTY9 between", value1, value2, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty9NotBetween(String value1, String value2) {
			addCriterion("PROPERTY9 not between", value1, value2, "property9");
			return (Criteria) this;
		}

		public Criteria andProperty10IsNull() {
			addCriterion("PROPERTY10 is null");
			return (Criteria) this;
		}

		public Criteria andProperty10IsNotNull() {
			addCriterion("PROPERTY10 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty10EqualTo(BigDecimal value) {
			addCriterion("PROPERTY10 =", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY10 <>", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY10 >", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY10 >=", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10LessThan(BigDecimal value) {
			addCriterion("PROPERTY10 <", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY10 <=", value, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10In(List<BigDecimal> values) {
			addCriterion("PROPERTY10 in", values, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY10 not in", values, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY10 between", value1, value2, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty10NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY10 not between", value1, value2, "property10");
			return (Criteria) this;
		}

		public Criteria andProperty11IsNull() {
			addCriterion("PROPERTY11 is null");
			return (Criteria) this;
		}

		public Criteria andProperty11IsNotNull() {
			addCriterion("PROPERTY11 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty11EqualTo(BigDecimal value) {
			addCriterion("PROPERTY11 =", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY11 <>", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY11 >", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY11 >=", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11LessThan(BigDecimal value) {
			addCriterion("PROPERTY11 <", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY11 <=", value, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11In(List<BigDecimal> values) {
			addCriterion("PROPERTY11 in", values, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY11 not in", values, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY11 between", value1, value2, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty11NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY11 not between", value1, value2, "property11");
			return (Criteria) this;
		}

		public Criteria andProperty12IsNull() {
			addCriterion("PROPERTY12 is null");
			return (Criteria) this;
		}

		public Criteria andProperty12IsNotNull() {
			addCriterion("PROPERTY12 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty12EqualTo(BigDecimal value) {
			addCriterion("PROPERTY12 =", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY12 <>", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY12 >", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY12 >=", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12LessThan(BigDecimal value) {
			addCriterion("PROPERTY12 <", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY12 <=", value, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12In(List<BigDecimal> values) {
			addCriterion("PROPERTY12 in", values, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY12 not in", values, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY12 between", value1, value2, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty12NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY12 not between", value1, value2, "property12");
			return (Criteria) this;
		}

		public Criteria andProperty13IsNull() {
			addCriterion("PROPERTY13 is null");
			return (Criteria) this;
		}

		public Criteria andProperty13IsNotNull() {
			addCriterion("PROPERTY13 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty13EqualTo(BigDecimal value) {
			addCriterion("PROPERTY13 =", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY13 <>", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY13 >", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY13 >=", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13LessThan(BigDecimal value) {
			addCriterion("PROPERTY13 <", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY13 <=", value, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13In(List<BigDecimal> values) {
			addCriterion("PROPERTY13 in", values, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY13 not in", values, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY13 between", value1, value2, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty13NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY13 not between", value1, value2, "property13");
			return (Criteria) this;
		}

		public Criteria andProperty14IsNull() {
			addCriterion("PROPERTY14 is null");
			return (Criteria) this;
		}

		public Criteria andProperty14IsNotNull() {
			addCriterion("PROPERTY14 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty14EqualTo(BigDecimal value) {
			addCriterion("PROPERTY14 =", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY14 <>", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY14 >", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY14 >=", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14LessThan(BigDecimal value) {
			addCriterion("PROPERTY14 <", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY14 <=", value, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14In(List<BigDecimal> values) {
			addCriterion("PROPERTY14 in", values, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY14 not in", values, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY14 between", value1, value2, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty14NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY14 not between", value1, value2, "property14");
			return (Criteria) this;
		}

		public Criteria andProperty15IsNull() {
			addCriterion("PROPERTY15 is null");
			return (Criteria) this;
		}

		public Criteria andProperty15IsNotNull() {
			addCriterion("PROPERTY15 is not null");
			return (Criteria) this;
		}

		public Criteria andProperty15EqualTo(BigDecimal value) {
			addCriterion("PROPERTY15 =", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15NotEqualTo(BigDecimal value) {
			addCriterion("PROPERTY15 <>", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15GreaterThan(BigDecimal value) {
			addCriterion("PROPERTY15 >", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15GreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY15 >=", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15LessThan(BigDecimal value) {
			addCriterion("PROPERTY15 <", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15LessThanOrEqualTo(BigDecimal value) {
			addCriterion("PROPERTY15 <=", value, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15In(List<BigDecimal> values) {
			addCriterion("PROPERTY15 in", values, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15NotIn(List<BigDecimal> values) {
			addCriterion("PROPERTY15 not in", values, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15Between(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY15 between", value1, value2, "property15");
			return (Criteria) this;
		}

		public Criteria andProperty15NotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("PROPERTY15 not between", value1, value2, "property15");
			return (Criteria) this;
		}

		public Criteria andPrinfIsNull() {
			addCriterion("PRINF is null");
			return (Criteria) this;
		}

		public Criteria andPrinfIsNotNull() {
			addCriterion("PRINF is not null");
			return (Criteria) this;
		}

		public Criteria andPrinfEqualTo(String value) {
			addCriterion("PRINF =", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfNotEqualTo(String value) {
			addCriterion("PRINF <>", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfGreaterThan(String value) {
			addCriterion("PRINF >", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfGreaterThanOrEqualTo(String value) {
			addCriterion("PRINF >=", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfLessThan(String value) {
			addCriterion("PRINF <", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfLessThanOrEqualTo(String value) {
			addCriterion("PRINF <=", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfLike(String value) {
			addCriterion("PRINF like", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfNotLike(String value) {
			addCriterion("PRINF not like", value, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfIn(List<String> values) {
			addCriterion("PRINF in", values, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfNotIn(List<String> values) {
			addCriterion("PRINF not in", values, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfBetween(String value1, String value2) {
			addCriterion("PRINF between", value1, value2, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrinfNotBetween(String value1, String value2) {
			addCriterion("PRINF not between", value1, value2, "prinf");
			return (Criteria) this;
		}

		public Criteria andPrexpIsNull() {
			addCriterion("PREXP is null");
			return (Criteria) this;
		}

		public Criteria andPrexpIsNotNull() {
			addCriterion("PREXP is not null");
			return (Criteria) this;
		}

		public Criteria andPrexpEqualTo(String value) {
			addCriterion("PREXP =", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpNotEqualTo(String value) {
			addCriterion("PREXP <>", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpGreaterThan(String value) {
			addCriterion("PREXP >", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpGreaterThanOrEqualTo(String value) {
			addCriterion("PREXP >=", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpLessThan(String value) {
			addCriterion("PREXP <", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpLessThanOrEqualTo(String value) {
			addCriterion("PREXP <=", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpLike(String value) {
			addCriterion("PREXP like", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpNotLike(String value) {
			addCriterion("PREXP not like", value, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpIn(List<String> values) {
			addCriterion("PREXP in", values, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpNotIn(List<String> values) {
			addCriterion("PREXP not in", values, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpBetween(String value1, String value2) {
			addCriterion("PREXP between", value1, value2, "prexp");
			return (Criteria) this;
		}

		public Criteria andPrexpNotBetween(String value1, String value2) {
			addCriterion("PREXP not between", value1, value2, "prexp");
			return (Criteria) this;
		}

		public Criteria andItimeIsNull() {
			addCriterion("ITIME is null");
			return (Criteria) this;
		}

		public Criteria andItimeIsNotNull() {
			addCriterion("ITIME is not null");
			return (Criteria) this;
		}

		public Criteria andItimeEqualTo(Date value) {
			addCriterion("ITIME =", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeNotEqualTo(Date value) {
			addCriterion("ITIME <>", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeGreaterThan(Date value) {
			addCriterion("ITIME >", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeGreaterThanOrEqualTo(Date value) {
			addCriterion("ITIME >=", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeLessThan(Date value) {
			addCriterion("ITIME <", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeLessThanOrEqualTo(Date value) {
			addCriterion("ITIME <=", value, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeIn(List<Date> values) {
			addCriterion("ITIME in", values, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeNotIn(List<Date> values) {
			addCriterion("ITIME not in", values, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeBetween(Date value1, Date value2) {
			addCriterion("ITIME between", value1, value2, "itime");
			return (Criteria) this;
		}

		public Criteria andItimeNotBetween(Date value1, Date value2) {
			addCriterion("ITIME not between", value1, value2, "itime");
			return (Criteria) this;
		}

		public Criteria andIpersonIsNull() {
			addCriterion("IPERSON is null");
			return (Criteria) this;
		}

		public Criteria andIpersonIsNotNull() {
			addCriterion("IPERSON is not null");
			return (Criteria) this;
		}

		public Criteria andIpersonEqualTo(Integer value) {
			addCriterion("IPERSON =", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonNotEqualTo(Integer value) {
			addCriterion("IPERSON <>", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonGreaterThan(Integer value) {
			addCriterion("IPERSON >", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonGreaterThanOrEqualTo(Integer value) {
			addCriterion("IPERSON >=", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonLessThan(Integer value) {
			addCriterion("IPERSON <", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonLessThanOrEqualTo(Integer value) {
			addCriterion("IPERSON <=", value, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonIn(List<Integer> values) {
			addCriterion("IPERSON in", values, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonNotIn(List<Integer> values) {
			addCriterion("IPERSON not in", values, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonBetween(Integer value1, Integer value2) {
			addCriterion("IPERSON between", value1, value2, "iperson");
			return (Criteria) this;
		}

		public Criteria andIpersonNotBetween(Integer value1, Integer value2) {
			addCriterion("IPERSON not between", value1, value2, "iperson");
			return (Criteria) this;
		}

		public Criteria andUtimeIsNull() {
			addCriterion("UTIME is null");
			return (Criteria) this;
		}

		public Criteria andUtimeIsNotNull() {
			addCriterion("UTIME is not null");
			return (Criteria) this;
		}

		public Criteria andUtimeEqualTo(Date value) {
			addCriterion("UTIME =", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeNotEqualTo(Date value) {
			addCriterion("UTIME <>", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeGreaterThan(Date value) {
			addCriterion("UTIME >", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("UTIME >=", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeLessThan(Date value) {
			addCriterion("UTIME <", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeLessThanOrEqualTo(Date value) {
			addCriterion("UTIME <=", value, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeIn(List<Date> values) {
			addCriterion("UTIME in", values, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeNotIn(List<Date> values) {
			addCriterion("UTIME not in", values, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeBetween(Date value1, Date value2) {
			addCriterion("UTIME between", value1, value2, "utime");
			return (Criteria) this;
		}

		public Criteria andUtimeNotBetween(Date value1, Date value2) {
			addCriterion("UTIME not between", value1, value2, "utime");
			return (Criteria) this;
		}

		public Criteria andUpersonIsNull() {
			addCriterion("UPERSON is null");
			return (Criteria) this;
		}

		public Criteria andUpersonIsNotNull() {
			addCriterion("UPERSON is not null");
			return (Criteria) this;
		}

		public Criteria andUpersonEqualTo(Integer value) {
			addCriterion("UPERSON =", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonNotEqualTo(Integer value) {
			addCriterion("UPERSON <>", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonGreaterThan(Integer value) {
			addCriterion("UPERSON >", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonGreaterThanOrEqualTo(Integer value) {
			addCriterion("UPERSON >=", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonLessThan(Integer value) {
			addCriterion("UPERSON <", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonLessThanOrEqualTo(Integer value) {
			addCriterion("UPERSON <=", value, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonIn(List<Integer> values) {
			addCriterion("UPERSON in", values, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonNotIn(List<Integer> values) {
			addCriterion("UPERSON not in", values, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonBetween(Integer value1, Integer value2) {
			addCriterion("UPERSON between", value1, value2, "uperson");
			return (Criteria) this;
		}

		public Criteria andUpersonNotBetween(Integer value1, Integer value2) {
			addCriterion("UPERSON not between", value1, value2, "uperson");
			return (Criteria) this;
		}

		public Criteria andDflagIsNull() {
			addCriterion("DFLAG is null");
			return (Criteria) this;
		}

		public Criteria andDflagIsNotNull() {
			addCriterion("DFLAG is not null");
			return (Criteria) this;
		}

		public Criteria andDflagEqualTo(String value) {
			addCriterion("DFLAG =", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagNotEqualTo(String value) {
			addCriterion("DFLAG <>", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagGreaterThan(String value) {
			addCriterion("DFLAG >", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagGreaterThanOrEqualTo(String value) {
			addCriterion("DFLAG >=", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagLessThan(String value) {
			addCriterion("DFLAG <", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagLessThanOrEqualTo(String value) {
			addCriterion("DFLAG <=", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagLike(String value) {
			addCriterion("DFLAG like", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagNotLike(String value) {
			addCriterion("DFLAG not like", value, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagIn(List<String> values) {
			addCriterion("DFLAG in", values, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagNotIn(List<String> values) {
			addCriterion("DFLAG not in", values, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagBetween(String value1, String value2) {
			addCriterion("DFLAG between", value1, value2, "dflag");
			return (Criteria) this;
		}

		public Criteria andDflagNotBetween(String value1, String value2) {
			addCriterion("DFLAG not between", value1, value2, "dflag");
			return (Criteria) this;
		}

		public Criteria andDtimeIsNull() {
			addCriterion("DTIME is null");
			return (Criteria) this;
		}

		public Criteria andDtimeIsNotNull() {
			addCriterion("DTIME is not null");
			return (Criteria) this;
		}

		public Criteria andDtimeEqualTo(Date value) {
			addCriterionForJDBCDate("DTIME =", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotEqualTo(Date value) {
			addCriterionForJDBCDate("DTIME <>", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeGreaterThan(Date value) {
			addCriterionForJDBCDate("DTIME >", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("DTIME >=", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeLessThan(Date value) {
			addCriterionForJDBCDate("DTIME <", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("DTIME <=", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeIn(List<Date> values) {
			addCriterionForJDBCDate("DTIME in", values, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotIn(List<Date> values) {
			addCriterionForJDBCDate("DTIME not in", values, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("DTIME between", value1, value2, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("DTIME not between", value1, value2, "dtime");
			return (Criteria) this;
		}

		public Criteria andDpersonIsNull() {
			addCriterion("DPERSON is null");
			return (Criteria) this;
		}

		public Criteria andDpersonIsNotNull() {
			addCriterion("DPERSON is not null");
			return (Criteria) this;
		}

		public Criteria andDpersonEqualTo(Integer value) {
			addCriterion("DPERSON =", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonNotEqualTo(Integer value) {
			addCriterion("DPERSON <>", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonGreaterThan(Integer value) {
			addCriterion("DPERSON >", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonGreaterThanOrEqualTo(Integer value) {
			addCriterion("DPERSON >=", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonLessThan(Integer value) {
			addCriterion("DPERSON <", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonLessThanOrEqualTo(Integer value) {
			addCriterion("DPERSON <=", value, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonIn(List<Integer> values) {
			addCriterion("DPERSON in", values, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonNotIn(List<Integer> values) {
			addCriterion("DPERSON not in", values, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonBetween(Integer value1, Integer value2) {
			addCriterion("DPERSON between", value1, value2, "dperson");
			return (Criteria) this;
		}

		public Criteria andDpersonNotBetween(Integer value1, Integer value2) {
			addCriterion("DPERSON not between", value1, value2, "dperson");
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