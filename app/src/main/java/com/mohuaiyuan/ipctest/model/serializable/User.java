package com.mohuaiyuan.ipctest.model.serializable;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 314047784759242388L;

    private int userI;
    private String userName;
    private boolean isMale;


    public int getUserI() {
        return userI;
    }

    public void setUserI(int userI) {
        this.userI = userI;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
