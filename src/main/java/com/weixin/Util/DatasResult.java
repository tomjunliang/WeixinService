package com.weixin.Util;

import java.util.List;

public class DatasResult<T> {
    private String Status;
    private List<T> datas;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
