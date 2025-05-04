package com.langbat.lang.model.bson.user;

import com.langbat.language.model.xnum.WorkHistoryMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class WorkHistory {
    private long id;
    private WorkHistoryMode mode;
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    private String description;
    private String responsibilities;
    public WorkHistory() {
        this.id = System.currentTimeMillis();
    }
}
