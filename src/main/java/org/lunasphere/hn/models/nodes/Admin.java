package org.lunasphere.hn.models.nodes;

public class Admin {
    String type;
    boolean resetPassword;
    boolean isSuper;

    public Admin(String type, boolean resetPassword, boolean isSuper) {
        this.type = type;
        this.resetPassword = resetPassword;
        this.isSuper = isSuper;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }
}
