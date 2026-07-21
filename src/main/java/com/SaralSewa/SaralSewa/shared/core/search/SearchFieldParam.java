package com.SaralSewa.SaralSewa.shared.core.search;


public class SearchFieldParam extends AbstractFieldParam {
    private String fieldCondition;
    private String fieldValue;
    public SearchFieldParam() {
        this.fieldKey = null;
        this.fieldOperator = null;
        this.fieldCondition = null;
        this.fieldValue = null;
    }
}
