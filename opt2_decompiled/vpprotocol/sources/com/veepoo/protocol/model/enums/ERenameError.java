package com.veepoo.protocol.model.enums;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ERenameError.class */
public enum ERenameError {
    LENGTH_0_ERROR("设备名不能为空"),
    LENGTH_1_8_ERROR("设备名长度异常,字节数不能超过8位"),
    LENGTH_1_18_ERROR("设备名长度异常，字节数不能超过18位"),
    DEVICE_MODIFY_ERROR("设备端回复修改失败");

    private String des;

    ERenameError(String str) {
        this.des = str;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String str) {
        this.des = str;
    }
}
