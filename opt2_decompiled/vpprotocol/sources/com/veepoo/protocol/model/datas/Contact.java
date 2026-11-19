package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/Contact.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J;\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0016\"\u0004\b\u0019\u0010\u0018¨\u0006$"}, d2 = {"Lcom/veepoo/protocol/model/datas/Contact;", "", "contactID", "", "name", "", "phoneNumber", "isSettingSOS", "", "isSupportSOS", "<init>", "(ILjava/lang/String;Ljava/lang/String;ZZ)V", "getContactID", "()I", "setContactID", "(I)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPhoneNumber", "setPhoneNumber", "()Z", "setSettingSOS", "(Z)V", "setSupportSOS", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "vpprotocol_release"})
public final /* data */ class Contact {
    private int contactID;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;
    private boolean isSettingSOS;
    private boolean isSupportSOS;

    public Contact(int i, @NotNull String str, @NotNull String str2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "phoneNumber");
        this.contactID = i;
        this.name = str;
        this.phoneNumber = str2;
        this.isSettingSOS = z;
        this.isSupportSOS = z2;
    }

    public static /* synthetic */ Contact copy$default(Contact contact, int i, String str, String str2, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = contact.contactID;
        }
        if ((i2 & 2) != 0) {
            str = contact.name;
        }
        if ((i2 & 4) != 0) {
            str2 = contact.phoneNumber;
        }
        if ((i2 & 8) != 0) {
            z = contact.isSettingSOS;
        }
        if ((i2 & 16) != 0) {
            z2 = contact.isSupportSOS;
        }
        return contact.copy(i, str, str2, z, z2);
    }

    public final int getContactID() {
        return this.contactID;
    }

    public final void setContactID(int i) {
        this.contactID = i;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @NotNull
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final void setPhoneNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phoneNumber = str;
    }

    public final boolean isSettingSOS() {
        return this.isSettingSOS;
    }

    public final void setSettingSOS(boolean z) {
        this.isSettingSOS = z;
    }

    public final boolean isSupportSOS() {
        return this.isSupportSOS;
    }

    public final void setSupportSOS(boolean z) {
        this.isSupportSOS = z;
    }

    public final int component1() {
        return this.contactID;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.phoneNumber;
    }

    public final boolean component4() {
        return this.isSettingSOS;
    }

    public final boolean component5() {
        return this.isSupportSOS;
    }

    @NotNull
    public final Contact copy(int contactID, @NotNull String name, @NotNull String phoneNumber, boolean isSettingSOS, boolean isSupportSOS) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        return new Contact(contactID, name, phoneNumber, isSettingSOS, isSupportSOS);
    }

    @NotNull
    public String toString() {
        return "Contact(contactID=" + this.contactID + ", name=" + this.name + ", phoneNumber=" + this.phoneNumber + ", isSettingSOS=" + this.isSettingSOS + ", isSupportSOS=" + this.isSupportSOS + ")";
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.contactID) * 31) + this.name.hashCode()) * 31) + this.phoneNumber.hashCode()) * 31) + Boolean.hashCode(this.isSettingSOS)) * 31) + Boolean.hashCode(this.isSupportSOS);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) other;
        return this.contactID == contact.contactID && Intrinsics.areEqual(this.name, contact.name) && Intrinsics.areEqual(this.phoneNumber, contact.phoneNumber) && this.isSettingSOS == contact.isSettingSOS && this.isSupportSOS == contact.isSupportSOS;
    }

    public /* synthetic */ Contact(int i, String str, String str2, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2);
    }
}
