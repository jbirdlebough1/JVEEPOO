package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.INetServer4gConfigListener;
import com.veepoo.protocol.model.datas.NetworkSever4gConfigInfo;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bq.class */
public final class vp_bq extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private INetServer4gConfigListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bq$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final NetworkSever4gConfigInfo vp_b(byte[] bArr) {
        NetworkSever4gConfigInfo networkSever4gConfigInfo = new NetworkSever4gConfigInfo();
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            int i2 = i + 1;
            if (b != -96) {
                int i3 = i + 2;
                if (i3 > bArr.length) {
                    break;
                }
                int i4 = UByte.constructor-impl(bArr[i2]) & 255;
                int i5 = i4;
                int i6 = i3 + i4;
                if (i6 > bArr.length) {
                    break;
                }
                byte[] bArrSliceArray = ArraysKt.sliceArray(bArr, RangesKt.until(i3, i6));
                if (b != -95 && b != -93 && b != -92) {
                    if (b != -94) {
                        if (b != -91 && b != -87) {
                            if (b != -90 && b != -89) {
                                if (b != -88) {
                                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                    String str = String.format("0x%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                                    System.out.println((Object) ("Warning: Unknown Flag " + str + " at offset " + i3 + ". Stopping parse."));
                                    break;
                                }
                                if (i5 != 1) {
                                    System.out.println((Object) ("Error: Interval length mismatch (" + i5 + " != 1)"));
                                    break;
                                }
                                networkSever4gConfigInfo.setReportInterval(Integer.valueOf(UByte.constructor-impl(bArrSliceArray[0]) & 255));
                            } else {
                                if (i5 != 1) {
                                    System.out.println((Object) ("Error: Switch length mismatch (" + i5 + " != 1)"));
                                    break;
                                }
                                boolean z = bArrSliceArray[0] == 1;
                                if (b == -90) {
                                    networkSever4gConfigInfo.setSwitch4g(Boolean.valueOf(z));
                                } else if (b == -89) {
                                    networkSever4gConfigInfo.setSwitchUpload(Boolean.valueOf(z));
                                }
                            }
                        } else {
                            if (i5 != 4) {
                                System.out.println((Object) ("Error: Timestamp length mismatch (" + i5 + " != 4)"));
                                break;
                            }
                            long j = ((long) UInt.constructor-impl(ByteBuffer.wrap(bArrSliceArray).order(ByteOrder.LITTLE_ENDIAN).getInt())) & 4294967295L;
                            if (b == -91) {
                                networkSever4gConfigInfo.setSyncTime(Long.valueOf(j));
                            } else if (b == -87) {
                                networkSever4gConfigInfo.setRecoveryTime(Long.valueOf(j));
                            }
                        }
                    } else {
                        if (i5 != 2) {
                            System.out.println((Object) ("Error: Port length mismatch (" + i5 + " != 2)"));
                            break;
                        }
                        networkSever4gConfigInfo.setPort(Integer.valueOf(UShort.constructor-impl(ByteBuffer.wrap(bArrSliceArray).order(ByteOrder.LITTLE_ENDIAN).getShort()) & 65535));
                    }
                } else {
                    while (i5 > 0 && bArrSliceArray[i5 - 1] == 0) {
                        i5--;
                    }
                    String str2 = new String(ArraysKt.sliceArray(bArrSliceArray, RangesKt.until(0, i5)), Charsets.UTF_8);
                    if (b == -95) {
                        networkSever4gConfigInfo.setIpAddress(str2);
                    } else if (b == -93) {
                        networkSever4gConfigInfo.setUserName(str2);
                    } else if (b == -92) {
                        networkSever4gConfigInfo.setPassword(str2);
                    }
                }
                i = i6;
            } else {
                int i7 = i + 3;
                i = i7;
                if (i7 > bArr.length) {
                    break;
                }
                System.out.println((Object) "Total length flag (0xA0) parsed and skipped.");
            }
        }
        return networkSever4gConfigInfo;
    }

    public void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        send(new byte[]{-69, 4, 2, 0}, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_a(bluetoothClient, str, bleWriteResponse);
        send(new byte[]{-69, 4, 1, 1, 1, 0, -96, 6, 0, -86, 1, 0}, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "responseBytes");
        super.handler(bArr);
        if (bArr.length < 4) {
            System.out.println((Object) "Error: Response length is too short.");
            return;
        }
        if (bArr[0] != -69 || bArr[1] != 4) {
            System.out.println((Object) "Error: Invalid protocol header, type, or CON.");
            return;
        }
        byte b = bArr[2];
        if (b == 2 || b == 4) {
            NetworkSever4gConfigInfo networkSever4gConfigInfoVp_b = vp_b(ArraysKt.sliceArray(bArr, RangesKt.until(6, bArr.length)));
            INetServer4gConfigListener iNetServer4gConfigListener = this.vp_c;
            if (iNetServer4gConfigListener != null) {
                iNetServer4gConfigListener.onReadSuccess(networkSever4gConfigInfoVp_b);
                return;
            }
            return;
        }
        if (b == 1) {
            if (bArr[3] == 1) {
                INetServer4gConfigListener iNetServer4gConfigListener2 = this.vp_c;
                if (iNetServer4gConfigListener2 != null) {
                    iNetServer4gConfigListener2.onSettingSuccess();
                    return;
                }
                return;
            }
            INetServer4gConfigListener iNetServer4gConfigListener3 = this.vp_c;
            if (iNetServer4gConfigListener3 != null) {
                iNetServer4gConfigListener3.onSettingFailed();
            }
        }
    }

    private final List vp_a(NetworkSever4gConfigInfo networkSever4gConfigInfo) {
        byte[] bArrVp_b = vp_b(networkSever4gConfigInfo);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 1;
        int mtuValue = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue();
        int i3 = mtuValue;
        boolean zIsZKDevice = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isZKDevice();
        if (mtuValue == -1 || zIsZKDevice) {
            i3 = 20;
        }
        int length = ((bArrVp_b.length + i3) - 1) / i3;
        while (i < bArrVp_b.length) {
            int i4 = i;
            int iMin = i4 + Math.min(bArrVp_b.length - i, i3);
            i = iMin;
            arrayList.add(ArraysKt.plus(new byte[]{-69, 4, 1, (byte) i2, (byte) length, 0}, ArraysKt.sliceArray(bArrVp_b, RangesKt.until(i4, iMin))));
            i2++;
        }
        return arrayList;
    }

    private final byte[] vp_b(NetworkSever4gConfigInfo networkSever4gConfigInfo) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        String ipAddress = networkSever4gConfigInfo.getIpAddress();
        if (ipAddress != null) {
            byte[] bytes = ipAddress.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            if ((!(bytes.length == 0)) && bytes.length + 1 <= 255) {
                arrayList.add((byte) -95);
                arrayList.add(Byte.valueOf((byte) (bytes.length + 1)));
                arrayList.addAll(ArraysKt.toList(bytes));
                arrayList.add((byte) 0);
            }
        }
        Integer port = networkSever4gConfigInfo.getPort();
        if (port != null) {
            int iIntValue = port.intValue();
            arrayList.add((byte) -94);
            arrayList.add((byte) 2);
            byteBufferOrder.clear();
            byteBufferOrder.putShort((short) iIntValue);
            byte[] bArrArray = byteBufferOrder.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
            arrayList.addAll(ArraysKt.slice(bArrArray, RangesKt.until(0, 2)));
        }
        String userName = networkSever4gConfigInfo.getUserName();
        if (userName != null) {
            byte[] bytes2 = userName.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            if ((!(bytes2.length == 0)) && bytes2.length + 1 <= 255) {
                arrayList.add((byte) -93);
                arrayList.add(Byte.valueOf((byte) (bytes2.length + 1)));
                arrayList.addAll(ArraysKt.toList(bytes2));
                arrayList.add((byte) 0);
            }
        }
        String password = networkSever4gConfigInfo.getPassword();
        if (password != null) {
            byte[] bytes3 = password.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
            if ((!(bytes3.length == 0)) && bytes3.length + 1 <= 255) {
                arrayList.add((byte) -92);
                arrayList.add(Byte.valueOf((byte) (bytes3.length + 1)));
                arrayList.addAll(ArraysKt.toList(bytes3));
                arrayList.add((byte) 0);
            }
        }
        Long syncTime = networkSever4gConfigInfo.getSyncTime();
        if (syncTime != null) {
            long jLongValue = syncTime.longValue();
            arrayList.add((byte) -91);
            arrayList.add((byte) 4);
            byteBufferOrder.clear();
            byteBufferOrder.putInt((int) jLongValue);
            byte[] bArrArray2 = byteBufferOrder.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray2, "array(...)");
            arrayList.addAll(ArraysKt.slice(bArrArray2, RangesKt.until(0, 4)));
        }
        Boolean switch4g = networkSever4gConfigInfo.getSwitch4g();
        if (switch4g != null) {
            boolean zBooleanValue = switch4g.booleanValue();
            arrayList.add((byte) -90);
            arrayList.add((byte) 1);
            arrayList.add(Byte.valueOf(zBooleanValue ? (byte) 1 : (byte) 0));
        }
        Boolean switchUpload = networkSever4gConfigInfo.getSwitchUpload();
        if (switchUpload != null) {
            boolean zBooleanValue2 = switchUpload.booleanValue();
            arrayList.add((byte) -89);
            arrayList.add((byte) 1);
            arrayList.add(Byte.valueOf(zBooleanValue2 ? (byte) 1 : (byte) 0));
        }
        Integer reportInterval = networkSever4gConfigInfo.getReportInterval();
        if (reportInterval != null) {
            int iIntValue2 = reportInterval.intValue();
            arrayList.add((byte) -88);
            arrayList.add((byte) 1);
            arrayList.add(Byte.valueOf((byte) iIntValue2));
        }
        Long recoveryTime = networkSever4gConfigInfo.getRecoveryTime();
        if (recoveryTime != null) {
            long jLongValue2 = recoveryTime.longValue();
            arrayList.add((byte) -87);
            arrayList.add((byte) 4);
            byteBufferOrder.clear();
            byteBufferOrder.putInt((int) jLongValue2);
            byte[] bArrArray3 = byteBufferOrder.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray3, "array(...)");
            arrayList.addAll(ArraysKt.slice(bArrArray3, RangesKt.until(0, 4)));
        }
        int size = arrayList.size() + 3;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add((byte) -96);
        byteBufferOrder.clear();
        byteBufferOrder.putShort((short) size);
        byte[] bArrArray4 = byteBufferOrder.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray4, "array(...)");
        arrayList2.addAll(ArraysKt.slice(bArrArray4, RangesKt.until(0, 2)));
        arrayList2.addAll(arrayList);
        return CollectionsKt.toByteArray(arrayList2);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, NetworkSever4gConfigInfo networkSever4gConfigInfo) {
        Intrinsics.checkNotNullParameter(networkSever4gConfigInfo, "configInfo");
        super.vp_a(bluetoothClient, str, bleWriteResponse, networkSever4gConfigInfo);
        List listVp_a = vp_a(networkSever4gConfigInfo);
        int i = 0;
        for (Object obj : listVp_a) {
            int i2 = i;
            i++;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            send((byte[]) obj, bluetoothClient, str, bleWriteResponse);
        }
    }

    public final void vp_a(INetServer4gConfigListener iNetServer4gConfigListener) {
        this.vp_c = iNetServer4gConfigListener;
    }
}
