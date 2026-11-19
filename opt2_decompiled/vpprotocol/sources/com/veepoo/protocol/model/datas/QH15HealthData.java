package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.HealthStatus;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vpno.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QH15HealthData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0003\bÎ\u0001\n\u0002\u0010\u000b\n\u0002\bI\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B¹\u0005\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u0003\u0012\b\b\u0002\u0010$\u001a\u00020\u0003\u0012\b\b\u0002\u0010%\u001a\u00020\u0003\u0012\b\b\u0002\u0010&\u001a\u00020\u0007\u0012\b\b\u0002\u0010'\u001a\u00020\u0007\u0012\b\b\u0002\u0010(\u001a\u00020\u0007\u0012\b\b\u0002\u0010)\u001a\u00020\u0003\u0012\b\b\u0002\u0010*\u001a\u00020\u0003\u0012\b\b\u0002\u0010+\u001a\u00020\u0003\u0012\b\b\u0002\u0010,\u001a\u00020\u0003\u0012\b\b\u0002\u0010-\u001a\u00020\u0003\u0012\b\b\u0002\u0010.\u001a\u00020\u0003\u0012\b\b\u0002\u0010/\u001a\u00020\u0007\u0012\b\b\u0002\u00100\u001a\u00020\u0007\u0012\b\b\u0002\u00101\u001a\u00020\u0007\u0012\b\b\u0002\u00102\u001a\u00020\u0003\u0012\b\b\u0002\u00103\u001a\u00020\u0003\u0012\b\b\u0002\u00104\u001a\u00020\u0003\u0012\b\b\u0002\u00105\u001a\u00020\u0003\u0012\b\b\u0002\u00106\u001a\u00020\u0003\u0012\b\b\u0002\u00107\u001a\u00020\u0003\u0012\b\b\u0002\u00108\u001a\u00020\u0007\u0012\b\b\u0002\u00109\u001a\u00020\u0007\u0012\b\b\u0002\u0010:\u001a\u00020\u0007\u0012\b\b\u0002\u0010;\u001a\u00020\u0003\u0012\b\b\u0002\u0010<\u001a\u00020\u0003\u0012\b\b\u0002\u0010=\u001a\u00020\u0003\u0012\b\b\u0002\u0010>\u001a\u00020\u0003\u0012\b\b\u0002\u0010?\u001a\u00020\u0003\u0012\b\b\u0002\u0010@\u001a\u00020\u0003\u0012\b\b\u0002\u0010A\u001a\u00020\u0007\u0012\b\b\u0002\u0010B\u001a\u00020\u0007\u0012\b\b\u0002\u0010C\u001a\u00020\u0007\u0012\b\b\u0002\u0010D\u001a\u00020\u0003\u0012\b\b\u0002\u0010E\u001a\u00020\u0003\u0012\b\b\u0002\u0010F\u001a\u00020\u0003\u0012\b\b\u0002\u0010G\u001a\u00020\u0003\u0012\b\b\u0002\u0010H\u001a\u00020\u0003\u0012\b\b\u0002\u0010I\u001a\u00020\u0003¢\u0006\u0004\bJ\u0010KJ\u0016\u0010Ü\u0001\u001a\u00030Ý\u00012\t\u0010Þ\u0001\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010ß\u0001\u001a\u00020\u0003H\u0016J\n\u0010à\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010á\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010â\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ã\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010ä\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010å\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010æ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ç\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010è\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010é\u0001\u001a\u00020\u000eHÆ\u0003J\n\u0010ê\u0001\u001a\u00020\u000eHÆ\u0003J\n\u0010ë\u0001\u001a\u00020\u000eHÆ\u0003J\n\u0010ì\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010í\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010î\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ï\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ð\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ñ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ò\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010ó\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010ô\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010õ\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010ö\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010÷\u0001\u001a\u00020\u0007HÆ\u0003J\n\u0010ø\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ù\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ú\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010û\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ü\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ý\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010þ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ÿ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0080\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0081\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0082\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0083\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0084\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0085\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0086\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0087\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0088\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0089\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008a\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008b\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008c\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u008d\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008e\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008f\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0090\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0091\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0092\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0093\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0094\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0095\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u0096\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0097\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0098\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0099\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009a\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009b\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009c\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u009d\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u009e\u0002\u001a\u00020\u0007HÆ\u0003J\n\u0010\u009f\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010¡\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010¢\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010£\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010¤\u0002\u001a\u00020\u0003HÆ\u0003J¼\u0005\u0010¥\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00072\b\b\u0002\u0010'\u001a\u00020\u00072\b\b\u0002\u0010(\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00072\b\b\u0002\u00100\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020\u00072\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u00020\u00032\b\b\u0002\u00107\u001a\u00020\u00032\b\b\u0002\u00108\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020\u00072\b\b\u0002\u0010:\u001a\u00020\u00072\b\b\u0002\u0010;\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u00032\b\b\u0002\u0010@\u001a\u00020\u00032\b\b\u0002\u0010A\u001a\u00020\u00072\b\b\u0002\u0010B\u001a\u00020\u00072\b\b\u0002\u0010C\u001a\u00020\u00072\b\b\u0002\u0010D\u001a\u00020\u00032\b\b\u0002\u0010E\u001a\u00020\u00032\b\b\u0002\u0010F\u001a\u00020\u00032\b\b\u0002\u0010G\u001a\u00020\u00032\b\b\u0002\u0010H\u001a\u00020\u00032\b\b\u0002\u0010I\u001a\u00020\u0003HÆ\u0001J\u000b\u0010¦\u0002\u001a\u00030§\u0002HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010M\"\u0004\bQ\u0010OR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010M\"\u0004\bS\u0010OR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010U\"\u0004\bY\u0010WR\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010U\"\u0004\b[\u0010WR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010M\"\u0004\b]\u0010OR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010M\"\u0004\b_\u0010OR\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010M\"\u0004\ba\u0010OR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u001a\u0010\u000f\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010c\"\u0004\bg\u0010eR\u001a\u0010\u0010\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010c\"\u0004\bi\u0010eR\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010M\"\u0004\bk\u0010OR\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010M\"\u0004\bm\u0010OR\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010M\"\u0004\bo\u0010OR\u001a\u0010\u0014\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010M\"\u0004\bq\u0010OR\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010M\"\u0004\bs\u0010OR\u001a\u0010\u0016\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010M\"\u0004\bu\u0010OR\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010U\"\u0004\bw\u0010WR\u001a\u0010\u0018\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010U\"\u0004\by\u0010WR\u001a\u0010\u0019\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010U\"\u0004\b{\u0010WR\u001a\u0010\u001a\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010U\"\u0004\b}\u0010WR\u001a\u0010\u001b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010U\"\u0004\b\u007f\u0010WR\u001c\u0010\u001c\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010U\"\u0005\b\u0081\u0001\u0010WR\u001c\u0010\u001d\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010M\"\u0005\b\u0083\u0001\u0010OR\u001c\u0010\u001e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010M\"\u0005\b\u0085\u0001\u0010OR\u001c\u0010\u001f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u0010M\"\u0005\b\u0087\u0001\u0010OR\u001c\u0010 \u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010M\"\u0005\b\u0089\u0001\u0010OR\u001c\u0010!\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010M\"\u0005\b\u008b\u0001\u0010OR\u001c\u0010\"\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010M\"\u0005\b\u008d\u0001\u0010OR\u001c\u0010#\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010M\"\u0005\b\u008f\u0001\u0010OR\u001c\u0010$\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010M\"\u0005\b\u0091\u0001\u0010OR\u001c\u0010%\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010M\"\u0005\b\u0093\u0001\u0010OR\u001c\u0010&\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010U\"\u0005\b\u0095\u0001\u0010WR\u001c\u0010'\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0001\u0010U\"\u0005\b\u0097\u0001\u0010WR\u001c\u0010(\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0098\u0001\u0010U\"\u0005\b\u0099\u0001\u0010WR\u001c\u0010)\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010M\"\u0005\b\u009b\u0001\u0010OR\u001c\u0010*\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0001\u0010M\"\u0005\b\u009d\u0001\u0010OR\u001c\u0010+\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0001\u0010M\"\u0005\b\u009f\u0001\u0010OR\u001c\u0010,\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010M\"\u0005\b¡\u0001\u0010OR\u001c\u0010-\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0001\u0010M\"\u0005\b£\u0001\u0010OR\u001c\u0010.\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u0010M\"\u0005\b¥\u0001\u0010OR\u001c\u0010/\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¦\u0001\u0010U\"\u0005\b§\u0001\u0010WR\u001c\u00100\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¨\u0001\u0010U\"\u0005\b©\u0001\u0010WR\u001c\u00101\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bª\u0001\u0010U\"\u0005\b«\u0001\u0010WR\u001c\u00102\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¬\u0001\u0010M\"\u0005\b\u00ad\u0001\u0010OR\u001c\u00103\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b®\u0001\u0010M\"\u0005\b¯\u0001\u0010OR\u001c\u00104\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b°\u0001\u0010M\"\u0005\b±\u0001\u0010OR\u001c\u00105\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u0010M\"\u0005\b³\u0001\u0010OR\u001c\u00106\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b´\u0001\u0010M\"\u0005\bµ\u0001\u0010OR\u001c\u00107\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¶\u0001\u0010M\"\u0005\b·\u0001\u0010OR\u001c\u00108\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0001\u0010U\"\u0005\b¹\u0001\u0010WR\u001c\u00109\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bº\u0001\u0010U\"\u0005\b»\u0001\u0010WR\u001c\u0010:\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¼\u0001\u0010U\"\u0005\b½\u0001\u0010WR\u001c\u0010;\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¾\u0001\u0010M\"\u0005\b¿\u0001\u0010OR\u001c\u0010<\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÀ\u0001\u0010M\"\u0005\bÁ\u0001\u0010OR\u001c\u0010=\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÂ\u0001\u0010M\"\u0005\bÃ\u0001\u0010OR\u001c\u0010>\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÄ\u0001\u0010M\"\u0005\bÅ\u0001\u0010OR\u001c\u0010?\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÆ\u0001\u0010M\"\u0005\bÇ\u0001\u0010OR\u001c\u0010@\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÈ\u0001\u0010M\"\u0005\bÉ\u0001\u0010OR\u001c\u0010A\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÊ\u0001\u0010U\"\u0005\bË\u0001\u0010WR\u001c\u0010B\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÌ\u0001\u0010U\"\u0005\bÍ\u0001\u0010WR\u001c\u0010C\u001a\u00020\u0007X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÎ\u0001\u0010U\"\u0005\bÏ\u0001\u0010WR\u001c\u0010D\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÐ\u0001\u0010M\"\u0005\bÑ\u0001\u0010OR\u001c\u0010E\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÒ\u0001\u0010M\"\u0005\bÓ\u0001\u0010OR\u001c\u0010F\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÔ\u0001\u0010M\"\u0005\bÕ\u0001\u0010OR\u001c\u0010G\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÖ\u0001\u0010M\"\u0005\b×\u0001\u0010OR\u001c\u0010H\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bØ\u0001\u0010M\"\u0005\bÙ\u0001\u0010OR\u001c\u0010I\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÚ\u0001\u0010M\"\u0005\bÛ\u0001\u0010O¨\u0006¨\u0002"}, d2 = {"Lcom/veepoo/protocol/model/datas/QH15HealthData;", "", "biosAge", "", "heartAge", "fitnessAge", "biosAgeStatus", "Lcom/veepoo/protocol/model/enums/HealthStatus;", "heartAgeStatus", "fitnessAgeStatus", "biosAgeChange", "heartAgeChange", "fitnessAgeChange", "biosAge90Days", "", "heartAge90Days", "fitnessAge90Days", "biosAgeLastMonth", "heartAgeLastMonth", "fitnessAgeLastMonth", "biosAgeLastYear", "heartAgeLastYear", "fitnessAgeLastYear", "biosAgeLastMonthStatus", "heartAgeLastMonthStatus", "fitnessAgeLastMonthStatus", "biosAgeLastYearStatus", "heartAgeLastYearStatus", "fitnessAgeLastYearStatus", "biosAgeLastMonthChange", "heartAgeLastMonthChange", "fitnessAgeLastMonthChange", "biosAgeLastYearChange", "heartAgeLastYearChange", "fitnessAgeLastYearChange", "cardiovascularRisk", "dementiaRisk", "diabetesRisk", "cardiovascularRiskStatus", "dementiaRiskStatus", "diabetesRiskStatus", "cardiovascularRiskChange", "dementiaRiskChange", "diabetesRiskChange", "heartAttackRisk", "strokeRisk", "heartFailureRisk", "heartAttackRiskStatus", "strokeRiskStatus", "heartFailureRiskStatus", "heartAttackRiskChange", "strokeRiskChange", "heartFailureRiskChange", "memoryLossRisk", "fallInjuryRisk", "loseIndependentRisk", "memoryLossRiskStatus", "fallInjuryRiskStatus", "loseIndependentRiskStatus", "memoryLossRiskChange", "fallInjuryRiskChange", "loseIndependentRiskChange", "kidneyDiseaseRisk", "nerveDamageRisk", "visionLossRisk", "kidneyDiseaseRiskStatus", "nerveDamageRiskStatus", "visionLossRiskStatus", "kidneyDiseaseRiskChange", "nerveDamageRiskChange", "visionLossRiskChange", "nutritionStatus", "goalStatus", "timestamp", "<init>", "(IIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;III[I[I[IIIIIIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;IIIIIIIIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;IIIIIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;IIIIIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;IIIIIILcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;Lcom/veepoo/protocol/model/enums/HealthStatus;IIIIII)V", "getBiosAge", "()I", "setBiosAge", "(I)V", "getHeartAge", "setHeartAge", "getFitnessAge", "setFitnessAge", "getBiosAgeStatus", "()Lcom/veepoo/protocol/model/enums/HealthStatus;", "setBiosAgeStatus", "(Lcom/veepoo/protocol/model/enums/HealthStatus;)V", "getHeartAgeStatus", "setHeartAgeStatus", "getFitnessAgeStatus", "setFitnessAgeStatus", "getBiosAgeChange", "setBiosAgeChange", "getHeartAgeChange", "setHeartAgeChange", "getFitnessAgeChange", "setFitnessAgeChange", "getBiosAge90Days", "()[I", "setBiosAge90Days", "([I)V", "getHeartAge90Days", "setHeartAge90Days", "getFitnessAge90Days", "setFitnessAge90Days", "getBiosAgeLastMonth", "setBiosAgeLastMonth", "getHeartAgeLastMonth", "setHeartAgeLastMonth", "getFitnessAgeLastMonth", "setFitnessAgeLastMonth", "getBiosAgeLastYear", "setBiosAgeLastYear", "getHeartAgeLastYear", "setHeartAgeLastYear", "getFitnessAgeLastYear", "setFitnessAgeLastYear", "getBiosAgeLastMonthStatus", "setBiosAgeLastMonthStatus", "getHeartAgeLastMonthStatus", "setHeartAgeLastMonthStatus", "getFitnessAgeLastMonthStatus", "setFitnessAgeLastMonthStatus", "getBiosAgeLastYearStatus", "setBiosAgeLastYearStatus", "getHeartAgeLastYearStatus", "setHeartAgeLastYearStatus", "getFitnessAgeLastYearStatus", "setFitnessAgeLastYearStatus", "getBiosAgeLastMonthChange", "setBiosAgeLastMonthChange", "getHeartAgeLastMonthChange", "setHeartAgeLastMonthChange", "getFitnessAgeLastMonthChange", "setFitnessAgeLastMonthChange", "getBiosAgeLastYearChange", "setBiosAgeLastYearChange", "getHeartAgeLastYearChange", "setHeartAgeLastYearChange", "getFitnessAgeLastYearChange", "setFitnessAgeLastYearChange", "getCardiovascularRisk", "setCardiovascularRisk", "getDementiaRisk", "setDementiaRisk", "getDiabetesRisk", "setDiabetesRisk", "getCardiovascularRiskStatus", "setCardiovascularRiskStatus", "getDementiaRiskStatus", "setDementiaRiskStatus", "getDiabetesRiskStatus", "setDiabetesRiskStatus", "getCardiovascularRiskChange", "setCardiovascularRiskChange", "getDementiaRiskChange", "setDementiaRiskChange", "getDiabetesRiskChange", "setDiabetesRiskChange", "getHeartAttackRisk", "setHeartAttackRisk", "getStrokeRisk", "setStrokeRisk", "getHeartFailureRisk", "setHeartFailureRisk", "getHeartAttackRiskStatus", "setHeartAttackRiskStatus", "getStrokeRiskStatus", "setStrokeRiskStatus", "getHeartFailureRiskStatus", "setHeartFailureRiskStatus", "getHeartAttackRiskChange", "setHeartAttackRiskChange", "getStrokeRiskChange", "setStrokeRiskChange", "getHeartFailureRiskChange", "setHeartFailureRiskChange", "getMemoryLossRisk", "setMemoryLossRisk", "getFallInjuryRisk", "setFallInjuryRisk", "getLoseIndependentRisk", "setLoseIndependentRisk", "getMemoryLossRiskStatus", "setMemoryLossRiskStatus", "getFallInjuryRiskStatus", "setFallInjuryRiskStatus", "getLoseIndependentRiskStatus", "setLoseIndependentRiskStatus", "getMemoryLossRiskChange", "setMemoryLossRiskChange", "getFallInjuryRiskChange", "setFallInjuryRiskChange", "getLoseIndependentRiskChange", "setLoseIndependentRiskChange", "getKidneyDiseaseRisk", "setKidneyDiseaseRisk", "getNerveDamageRisk", "setNerveDamageRisk", "getVisionLossRisk", "setVisionLossRisk", "getKidneyDiseaseRiskStatus", "setKidneyDiseaseRiskStatus", "getNerveDamageRiskStatus", "setNerveDamageRiskStatus", "getVisionLossRiskStatus", "setVisionLossRiskStatus", "getKidneyDiseaseRiskChange", "setKidneyDiseaseRiskChange", "getNerveDamageRiskChange", "setNerveDamageRiskChange", "getVisionLossRiskChange", "setVisionLossRiskChange", "getNutritionStatus", "setNutritionStatus", "getGoalStatus", "setGoalStatus", "getTimestamp", "setTimestamp", "equals", "", "other", "hashCode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "copy", "toString", "", "vpprotocol_release"})
public final /* data */ class QH15HealthData {
    private int biosAge;
    private int heartAge;
    private int fitnessAge;

    @NotNull
    private HealthStatus biosAgeStatus;

    @NotNull
    private HealthStatus heartAgeStatus;

    @NotNull
    private HealthStatus fitnessAgeStatus;
    private int biosAgeChange;
    private int heartAgeChange;
    private int fitnessAgeChange;

    @NotNull
    private int[] biosAge90Days;

    @NotNull
    private int[] heartAge90Days;

    @NotNull
    private int[] fitnessAge90Days;
    private int biosAgeLastMonth;
    private int heartAgeLastMonth;
    private int fitnessAgeLastMonth;
    private int biosAgeLastYear;
    private int heartAgeLastYear;
    private int fitnessAgeLastYear;

    @NotNull
    private HealthStatus biosAgeLastMonthStatus;

    @NotNull
    private HealthStatus heartAgeLastMonthStatus;

    @NotNull
    private HealthStatus fitnessAgeLastMonthStatus;

    @NotNull
    private HealthStatus biosAgeLastYearStatus;

    @NotNull
    private HealthStatus heartAgeLastYearStatus;

    @NotNull
    private HealthStatus fitnessAgeLastYearStatus;
    private int biosAgeLastMonthChange;
    private int heartAgeLastMonthChange;
    private int fitnessAgeLastMonthChange;
    private int biosAgeLastYearChange;
    private int heartAgeLastYearChange;
    private int fitnessAgeLastYearChange;
    private int cardiovascularRisk;
    private int dementiaRisk;
    private int diabetesRisk;

    @NotNull
    private HealthStatus cardiovascularRiskStatus;

    @NotNull
    private HealthStatus dementiaRiskStatus;

    @NotNull
    private HealthStatus diabetesRiskStatus;
    private int cardiovascularRiskChange;
    private int dementiaRiskChange;
    private int diabetesRiskChange;
    private int heartAttackRisk;
    private int strokeRisk;
    private int heartFailureRisk;

    @NotNull
    private HealthStatus heartAttackRiskStatus;

    @NotNull
    private HealthStatus strokeRiskStatus;

    @NotNull
    private HealthStatus heartFailureRiskStatus;
    private int heartAttackRiskChange;
    private int strokeRiskChange;
    private int heartFailureRiskChange;
    private int memoryLossRisk;
    private int fallInjuryRisk;
    private int loseIndependentRisk;

    @NotNull
    private HealthStatus memoryLossRiskStatus;

    @NotNull
    private HealthStatus fallInjuryRiskStatus;

    @NotNull
    private HealthStatus loseIndependentRiskStatus;
    private int memoryLossRiskChange;
    private int fallInjuryRiskChange;
    private int loseIndependentRiskChange;
    private int kidneyDiseaseRisk;
    private int nerveDamageRisk;
    private int visionLossRisk;

    @NotNull
    private HealthStatus kidneyDiseaseRiskStatus;

    @NotNull
    private HealthStatus nerveDamageRiskStatus;

    @NotNull
    private HealthStatus visionLossRiskStatus;
    private int kidneyDiseaseRiskChange;
    private int nerveDamageRiskChange;
    private int visionLossRiskChange;
    private int nutritionStatus;
    private int goalStatus;
    private int timestamp;

    public static /* synthetic */ QH15HealthData copy$default(QH15HealthData qH15HealthData, int i, int i2, int i3, HealthStatus healthStatus, HealthStatus healthStatus2, HealthStatus healthStatus3, int i4, int i5, int i6, int[] iArr, int[] iArr2, int[] iArr3, int i7, int i8, int i9, int i10, int i11, int i12, HealthStatus healthStatus4, HealthStatus healthStatus5, HealthStatus healthStatus6, HealthStatus healthStatus7, HealthStatus healthStatus8, HealthStatus healthStatus9, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, HealthStatus healthStatus10, HealthStatus healthStatus11, HealthStatus healthStatus12, int i22, int i23, int i24, int i25, int i26, int i27, HealthStatus healthStatus13, HealthStatus healthStatus14, HealthStatus healthStatus15, int i28, int i29, int i30, int i31, int i32, int i33, HealthStatus healthStatus16, HealthStatus healthStatus17, HealthStatus healthStatus18, int i34, int i35, int i36, int i37, int i38, int i39, HealthStatus healthStatus19, HealthStatus healthStatus20, HealthStatus healthStatus21, int i40, int i41, int i42, int i43, int i44, int i45, int i46, int i47, int i48, Object obj) {
        if ((i46 & 1) != 0) {
            i = qH15HealthData.biosAge;
        }
        if ((i46 & 2) != 0) {
            i2 = qH15HealthData.heartAge;
        }
        if ((i46 & 4) != 0) {
            i3 = qH15HealthData.fitnessAge;
        }
        if ((i46 & 8) != 0) {
            healthStatus = qH15HealthData.biosAgeStatus;
        }
        if ((i46 & 16) != 0) {
            healthStatus2 = qH15HealthData.heartAgeStatus;
        }
        if ((i46 & 32) != 0) {
            healthStatus3 = qH15HealthData.fitnessAgeStatus;
        }
        if ((i46 & 64) != 0) {
            i4 = qH15HealthData.biosAgeChange;
        }
        if ((i46 & 128) != 0) {
            i5 = qH15HealthData.heartAgeChange;
        }
        if ((i46 & 256) != 0) {
            i6 = qH15HealthData.fitnessAgeChange;
        }
        if ((i46 & 512) != 0) {
            iArr = qH15HealthData.biosAge90Days;
        }
        if ((i46 & 1024) != 0) {
            iArr2 = qH15HealthData.heartAge90Days;
        }
        if ((i46 & 2048) != 0) {
            iArr3 = qH15HealthData.fitnessAge90Days;
        }
        if ((i46 & 4096) != 0) {
            i7 = qH15HealthData.biosAgeLastMonth;
        }
        if ((i46 & DfuBaseService.ERROR_REMOTE_MASK) != 0) {
            i8 = qH15HealthData.heartAgeLastMonth;
        }
        if ((i46 & DfuBaseService.ERROR_CONNECTION_MASK) != 0) {
            i9 = qH15HealthData.fitnessAgeLastMonth;
        }
        if ((i46 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0) {
            i10 = qH15HealthData.biosAgeLastYear;
        }
        if ((i46 & 65536) != 0) {
            i11 = qH15HealthData.heartAgeLastYear;
        }
        if ((i46 & 131072) != 0) {
            i12 = qH15HealthData.fitnessAgeLastYear;
        }
        if ((i46 & 262144) != 0) {
            healthStatus4 = qH15HealthData.biosAgeLastMonthStatus;
        }
        if ((i46 & 524288) != 0) {
            healthStatus5 = qH15HealthData.heartAgeLastMonthStatus;
        }
        if ((i46 & 1048576) != 0) {
            healthStatus6 = qH15HealthData.fitnessAgeLastMonthStatus;
        }
        if ((i46 & 2097152) != 0) {
            healthStatus7 = qH15HealthData.biosAgeLastYearStatus;
        }
        if ((i46 & 4194304) != 0) {
            healthStatus8 = qH15HealthData.heartAgeLastYearStatus;
        }
        if ((i46 & 8388608) != 0) {
            healthStatus9 = qH15HealthData.fitnessAgeLastYearStatus;
        }
        if ((i46 & 16777216) != 0) {
            i13 = qH15HealthData.biosAgeLastMonthChange;
        }
        if ((i46 & 33554432) != 0) {
            i14 = qH15HealthData.heartAgeLastMonthChange;
        }
        if ((i46 & 67108864) != 0) {
            i15 = qH15HealthData.fitnessAgeLastMonthChange;
        }
        if ((i46 & 134217728) != 0) {
            i16 = qH15HealthData.biosAgeLastYearChange;
        }
        if ((i46 & 268435456) != 0) {
            i17 = qH15HealthData.heartAgeLastYearChange;
        }
        if ((i46 & 536870912) != 0) {
            i18 = qH15HealthData.fitnessAgeLastYearChange;
        }
        if ((i46 & 1073741824) != 0) {
            i19 = qH15HealthData.cardiovascularRisk;
        }
        if ((i46 & Integer.MIN_VALUE) != 0) {
            i20 = qH15HealthData.dementiaRisk;
        }
        if ((i47 & 1) != 0) {
            i21 = qH15HealthData.diabetesRisk;
        }
        if ((i47 & 2) != 0) {
            healthStatus10 = qH15HealthData.cardiovascularRiskStatus;
        }
        if ((i47 & 4) != 0) {
            healthStatus11 = qH15HealthData.dementiaRiskStatus;
        }
        if ((i47 & 8) != 0) {
            healthStatus12 = qH15HealthData.diabetesRiskStatus;
        }
        if ((i47 & 16) != 0) {
            i22 = qH15HealthData.cardiovascularRiskChange;
        }
        if ((i47 & 32) != 0) {
            i23 = qH15HealthData.dementiaRiskChange;
        }
        if ((i47 & 64) != 0) {
            i24 = qH15HealthData.diabetesRiskChange;
        }
        if ((i47 & 128) != 0) {
            i25 = qH15HealthData.heartAttackRisk;
        }
        if ((i47 & 256) != 0) {
            i26 = qH15HealthData.strokeRisk;
        }
        if ((i47 & 512) != 0) {
            i27 = qH15HealthData.heartFailureRisk;
        }
        if ((i47 & 1024) != 0) {
            healthStatus13 = qH15HealthData.heartAttackRiskStatus;
        }
        if ((i47 & 2048) != 0) {
            healthStatus14 = qH15HealthData.strokeRiskStatus;
        }
        if ((i47 & 4096) != 0) {
            healthStatus15 = qH15HealthData.heartFailureRiskStatus;
        }
        if ((i47 & DfuBaseService.ERROR_REMOTE_MASK) != 0) {
            i28 = qH15HealthData.heartAttackRiskChange;
        }
        if ((i47 & DfuBaseService.ERROR_CONNECTION_MASK) != 0) {
            i29 = qH15HealthData.strokeRiskChange;
        }
        if ((i47 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0) {
            i30 = qH15HealthData.heartFailureRiskChange;
        }
        if ((i47 & 65536) != 0) {
            i31 = qH15HealthData.memoryLossRisk;
        }
        if ((i47 & 131072) != 0) {
            i32 = qH15HealthData.fallInjuryRisk;
        }
        if ((i47 & 262144) != 0) {
            i33 = qH15HealthData.loseIndependentRisk;
        }
        if ((i47 & 524288) != 0) {
            healthStatus16 = qH15HealthData.memoryLossRiskStatus;
        }
        if ((i47 & 1048576) != 0) {
            healthStatus17 = qH15HealthData.fallInjuryRiskStatus;
        }
        if ((i47 & 2097152) != 0) {
            healthStatus18 = qH15HealthData.loseIndependentRiskStatus;
        }
        if ((i47 & 4194304) != 0) {
            i34 = qH15HealthData.memoryLossRiskChange;
        }
        if ((i47 & 8388608) != 0) {
            i35 = qH15HealthData.fallInjuryRiskChange;
        }
        if ((i47 & 16777216) != 0) {
            i36 = qH15HealthData.loseIndependentRiskChange;
        }
        if ((i47 & 33554432) != 0) {
            i37 = qH15HealthData.kidneyDiseaseRisk;
        }
        if ((i47 & 67108864) != 0) {
            i38 = qH15HealthData.nerveDamageRisk;
        }
        if ((i47 & 134217728) != 0) {
            i39 = qH15HealthData.visionLossRisk;
        }
        if ((i47 & 268435456) != 0) {
            healthStatus19 = qH15HealthData.kidneyDiseaseRiskStatus;
        }
        if ((i47 & 536870912) != 0) {
            healthStatus20 = qH15HealthData.nerveDamageRiskStatus;
        }
        if ((i47 & 1073741824) != 0) {
            healthStatus21 = qH15HealthData.visionLossRiskStatus;
        }
        if ((i47 & Integer.MIN_VALUE) != 0) {
            i40 = qH15HealthData.kidneyDiseaseRiskChange;
        }
        if ((i48 & 1) != 0) {
            i41 = qH15HealthData.nerveDamageRiskChange;
        }
        if ((i48 & 2) != 0) {
            i42 = qH15HealthData.visionLossRiskChange;
        }
        if ((i48 & 4) != 0) {
            i43 = qH15HealthData.nutritionStatus;
        }
        if ((i48 & 8) != 0) {
            i44 = qH15HealthData.goalStatus;
        }
        if ((i48 & 16) != 0) {
            i45 = qH15HealthData.timestamp;
        }
        return qH15HealthData.copy(i, i2, i3, healthStatus, healthStatus2, healthStatus3, i4, i5, i6, iArr, iArr2, iArr3, i7, i8, i9, i10, i11, i12, healthStatus4, healthStatus5, healthStatus6, healthStatus7, healthStatus8, healthStatus9, i13, i14, i15, i16, i17, i18, i19, i20, i21, healthStatus10, healthStatus11, healthStatus12, i22, i23, i24, i25, i26, i27, healthStatus13, healthStatus14, healthStatus15, i28, i29, i30, i31, i32, i33, healthStatus16, healthStatus17, healthStatus18, i34, i35, i36, i37, i38, i39, healthStatus19, healthStatus20, healthStatus21, i40, i41, i42, i43, i44, i45);
    }

    public QH15HealthData() {
        this(0, 0, 0, null, null, null, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0, 0, 0, 0, -1, -1, 31, null);
    }

    public final int getBiosAge() {
        return this.biosAge;
    }

    public final void setBiosAge(int i) {
        this.biosAge = i;
    }

    public final int getHeartAge() {
        return this.heartAge;
    }

    public final void setHeartAge(int i) {
        this.heartAge = i;
    }

    public final int getFitnessAge() {
        return this.fitnessAge;
    }

    public final void setFitnessAge(int i) {
        this.fitnessAge = i;
    }

    @NotNull
    public final HealthStatus getBiosAgeStatus() {
        return this.biosAgeStatus;
    }

    public final void setBiosAgeStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.biosAgeStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getHeartAgeStatus() {
        return this.heartAgeStatus;
    }

    public final void setHeartAgeStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.heartAgeStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getFitnessAgeStatus() {
        return this.fitnessAgeStatus;
    }

    public final void setFitnessAgeStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.fitnessAgeStatus = healthStatus;
    }

    public final int getBiosAgeChange() {
        return this.biosAgeChange;
    }

    public final void setBiosAgeChange(int i) {
        this.biosAgeChange = i;
    }

    public final int getHeartAgeChange() {
        return this.heartAgeChange;
    }

    public final void setHeartAgeChange(int i) {
        this.heartAgeChange = i;
    }

    public final int getFitnessAgeChange() {
        return this.fitnessAgeChange;
    }

    public final void setFitnessAgeChange(int i) {
        this.fitnessAgeChange = i;
    }

    @NotNull
    public final int[] getBiosAge90Days() {
        return this.biosAge90Days;
    }

    public final void setBiosAge90Days(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.biosAge90Days = iArr;
    }

    @NotNull
    public final int[] getHeartAge90Days() {
        return this.heartAge90Days;
    }

    public final void setHeartAge90Days(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.heartAge90Days = iArr;
    }

    @NotNull
    public final int[] getFitnessAge90Days() {
        return this.fitnessAge90Days;
    }

    public final void setFitnessAge90Days(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.fitnessAge90Days = iArr;
    }

    public final int getBiosAgeLastMonth() {
        return this.biosAgeLastMonth;
    }

    public final void setBiosAgeLastMonth(int i) {
        this.biosAgeLastMonth = i;
    }

    public final int getHeartAgeLastMonth() {
        return this.heartAgeLastMonth;
    }

    public final void setHeartAgeLastMonth(int i) {
        this.heartAgeLastMonth = i;
    }

    public final int getFitnessAgeLastMonth() {
        return this.fitnessAgeLastMonth;
    }

    public final void setFitnessAgeLastMonth(int i) {
        this.fitnessAgeLastMonth = i;
    }

    public final int getBiosAgeLastYear() {
        return this.biosAgeLastYear;
    }

    public final void setBiosAgeLastYear(int i) {
        this.biosAgeLastYear = i;
    }

    public final int getHeartAgeLastYear() {
        return this.heartAgeLastYear;
    }

    public final void setHeartAgeLastYear(int i) {
        this.heartAgeLastYear = i;
    }

    public final int getFitnessAgeLastYear() {
        return this.fitnessAgeLastYear;
    }

    public final void setFitnessAgeLastYear(int i) {
        this.fitnessAgeLastYear = i;
    }

    @NotNull
    public final HealthStatus getBiosAgeLastMonthStatus() {
        return this.biosAgeLastMonthStatus;
    }

    public final void setBiosAgeLastMonthStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.biosAgeLastMonthStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getHeartAgeLastMonthStatus() {
        return this.heartAgeLastMonthStatus;
    }

    public final void setHeartAgeLastMonthStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.heartAgeLastMonthStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getFitnessAgeLastMonthStatus() {
        return this.fitnessAgeLastMonthStatus;
    }

    public final void setFitnessAgeLastMonthStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.fitnessAgeLastMonthStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getBiosAgeLastYearStatus() {
        return this.biosAgeLastYearStatus;
    }

    public final void setBiosAgeLastYearStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.biosAgeLastYearStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getHeartAgeLastYearStatus() {
        return this.heartAgeLastYearStatus;
    }

    public final void setHeartAgeLastYearStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.heartAgeLastYearStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getFitnessAgeLastYearStatus() {
        return this.fitnessAgeLastYearStatus;
    }

    public final void setFitnessAgeLastYearStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.fitnessAgeLastYearStatus = healthStatus;
    }

    public final int getBiosAgeLastMonthChange() {
        return this.biosAgeLastMonthChange;
    }

    public final void setBiosAgeLastMonthChange(int i) {
        this.biosAgeLastMonthChange = i;
    }

    public final int getHeartAgeLastMonthChange() {
        return this.heartAgeLastMonthChange;
    }

    public final void setHeartAgeLastMonthChange(int i) {
        this.heartAgeLastMonthChange = i;
    }

    public final int getFitnessAgeLastMonthChange() {
        return this.fitnessAgeLastMonthChange;
    }

    public final void setFitnessAgeLastMonthChange(int i) {
        this.fitnessAgeLastMonthChange = i;
    }

    public final int getBiosAgeLastYearChange() {
        return this.biosAgeLastYearChange;
    }

    public final void setBiosAgeLastYearChange(int i) {
        this.biosAgeLastYearChange = i;
    }

    public final int getHeartAgeLastYearChange() {
        return this.heartAgeLastYearChange;
    }

    public final void setHeartAgeLastYearChange(int i) {
        this.heartAgeLastYearChange = i;
    }

    public final int getFitnessAgeLastYearChange() {
        return this.fitnessAgeLastYearChange;
    }

    public final void setFitnessAgeLastYearChange(int i) {
        this.fitnessAgeLastYearChange = i;
    }

    public final int getCardiovascularRisk() {
        return this.cardiovascularRisk;
    }

    public final void setCardiovascularRisk(int i) {
        this.cardiovascularRisk = i;
    }

    public final int getDementiaRisk() {
        return this.dementiaRisk;
    }

    public final void setDementiaRisk(int i) {
        this.dementiaRisk = i;
    }

    public final int getDiabetesRisk() {
        return this.diabetesRisk;
    }

    public final void setDiabetesRisk(int i) {
        this.diabetesRisk = i;
    }

    @NotNull
    public final HealthStatus getCardiovascularRiskStatus() {
        return this.cardiovascularRiskStatus;
    }

    public final void setCardiovascularRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.cardiovascularRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getDementiaRiskStatus() {
        return this.dementiaRiskStatus;
    }

    public final void setDementiaRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.dementiaRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getDiabetesRiskStatus() {
        return this.diabetesRiskStatus;
    }

    public final void setDiabetesRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.diabetesRiskStatus = healthStatus;
    }

    public final int getCardiovascularRiskChange() {
        return this.cardiovascularRiskChange;
    }

    public final void setCardiovascularRiskChange(int i) {
        this.cardiovascularRiskChange = i;
    }

    public final int getDementiaRiskChange() {
        return this.dementiaRiskChange;
    }

    public final void setDementiaRiskChange(int i) {
        this.dementiaRiskChange = i;
    }

    public final int getDiabetesRiskChange() {
        return this.diabetesRiskChange;
    }

    public final void setDiabetesRiskChange(int i) {
        this.diabetesRiskChange = i;
    }

    public final int getHeartAttackRisk() {
        return this.heartAttackRisk;
    }

    public final void setHeartAttackRisk(int i) {
        this.heartAttackRisk = i;
    }

    public final int getStrokeRisk() {
        return this.strokeRisk;
    }

    public final void setStrokeRisk(int i) {
        this.strokeRisk = i;
    }

    public final int getHeartFailureRisk() {
        return this.heartFailureRisk;
    }

    public final void setHeartFailureRisk(int i) {
        this.heartFailureRisk = i;
    }

    @NotNull
    public final HealthStatus getHeartAttackRiskStatus() {
        return this.heartAttackRiskStatus;
    }

    public final void setHeartAttackRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.heartAttackRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getStrokeRiskStatus() {
        return this.strokeRiskStatus;
    }

    public final void setStrokeRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.strokeRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getHeartFailureRiskStatus() {
        return this.heartFailureRiskStatus;
    }

    public final void setHeartFailureRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.heartFailureRiskStatus = healthStatus;
    }

    public final int getHeartAttackRiskChange() {
        return this.heartAttackRiskChange;
    }

    public final void setHeartAttackRiskChange(int i) {
        this.heartAttackRiskChange = i;
    }

    public final int getStrokeRiskChange() {
        return this.strokeRiskChange;
    }

    public final void setStrokeRiskChange(int i) {
        this.strokeRiskChange = i;
    }

    public final int getHeartFailureRiskChange() {
        return this.heartFailureRiskChange;
    }

    public final void setHeartFailureRiskChange(int i) {
        this.heartFailureRiskChange = i;
    }

    public final int getMemoryLossRisk() {
        return this.memoryLossRisk;
    }

    public final void setMemoryLossRisk(int i) {
        this.memoryLossRisk = i;
    }

    public final int getFallInjuryRisk() {
        return this.fallInjuryRisk;
    }

    public final void setFallInjuryRisk(int i) {
        this.fallInjuryRisk = i;
    }

    public final int getLoseIndependentRisk() {
        return this.loseIndependentRisk;
    }

    public final void setLoseIndependentRisk(int i) {
        this.loseIndependentRisk = i;
    }

    @NotNull
    public final HealthStatus getMemoryLossRiskStatus() {
        return this.memoryLossRiskStatus;
    }

    public final void setMemoryLossRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.memoryLossRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getFallInjuryRiskStatus() {
        return this.fallInjuryRiskStatus;
    }

    public final void setFallInjuryRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.fallInjuryRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getLoseIndependentRiskStatus() {
        return this.loseIndependentRiskStatus;
    }

    public final void setLoseIndependentRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.loseIndependentRiskStatus = healthStatus;
    }

    public final int getMemoryLossRiskChange() {
        return this.memoryLossRiskChange;
    }

    public final void setMemoryLossRiskChange(int i) {
        this.memoryLossRiskChange = i;
    }

    public final int getFallInjuryRiskChange() {
        return this.fallInjuryRiskChange;
    }

    public final void setFallInjuryRiskChange(int i) {
        this.fallInjuryRiskChange = i;
    }

    public final int getLoseIndependentRiskChange() {
        return this.loseIndependentRiskChange;
    }

    public final void setLoseIndependentRiskChange(int i) {
        this.loseIndependentRiskChange = i;
    }

    public final int getKidneyDiseaseRisk() {
        return this.kidneyDiseaseRisk;
    }

    public final void setKidneyDiseaseRisk(int i) {
        this.kidneyDiseaseRisk = i;
    }

    public final int getNerveDamageRisk() {
        return this.nerveDamageRisk;
    }

    public final void setNerveDamageRisk(int i) {
        this.nerveDamageRisk = i;
    }

    public final int getVisionLossRisk() {
        return this.visionLossRisk;
    }

    public final void setVisionLossRisk(int i) {
        this.visionLossRisk = i;
    }

    @NotNull
    public final HealthStatus getKidneyDiseaseRiskStatus() {
        return this.kidneyDiseaseRiskStatus;
    }

    public final void setKidneyDiseaseRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.kidneyDiseaseRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getNerveDamageRiskStatus() {
        return this.nerveDamageRiskStatus;
    }

    public final void setNerveDamageRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.nerveDamageRiskStatus = healthStatus;
    }

    @NotNull
    public final HealthStatus getVisionLossRiskStatus() {
        return this.visionLossRiskStatus;
    }

    public final void setVisionLossRiskStatus(@NotNull HealthStatus healthStatus) {
        Intrinsics.checkNotNullParameter(healthStatus, "<set-?>");
        this.visionLossRiskStatus = healthStatus;
    }

    public final int getKidneyDiseaseRiskChange() {
        return this.kidneyDiseaseRiskChange;
    }

    public final void setKidneyDiseaseRiskChange(int i) {
        this.kidneyDiseaseRiskChange = i;
    }

    public final int getNerveDamageRiskChange() {
        return this.nerveDamageRiskChange;
    }

    public final void setNerveDamageRiskChange(int i) {
        this.nerveDamageRiskChange = i;
    }

    public final int getVisionLossRiskChange() {
        return this.visionLossRiskChange;
    }

    public final void setVisionLossRiskChange(int i) {
        this.visionLossRiskChange = i;
    }

    public final int getNutritionStatus() {
        return this.nutritionStatus;
    }

    public final void setNutritionStatus(int i) {
        this.nutritionStatus = i;
    }

    public final int getGoalStatus() {
        return this.goalStatus;
    }

    public final void setGoalStatus(int i) {
        this.goalStatus = i;
    }

    public final int getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(int i) {
        this.timestamp = i;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(QH15HealthData.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.veepoo.protocol.model.datas.QH15HealthData");
        QH15HealthData qH15HealthData = (QH15HealthData) other;
        return this.biosAge == qH15HealthData.biosAge && this.heartAge == qH15HealthData.heartAge && this.fitnessAge == qH15HealthData.fitnessAge && this.biosAgeStatus == qH15HealthData.biosAgeStatus && this.heartAgeStatus == qH15HealthData.heartAgeStatus && this.fitnessAgeStatus == qH15HealthData.fitnessAgeStatus && this.biosAgeChange == qH15HealthData.biosAgeChange && this.heartAgeChange == qH15HealthData.heartAgeChange && this.fitnessAgeChange == qH15HealthData.fitnessAgeChange && Arrays.equals(this.biosAge90Days, qH15HealthData.biosAge90Days) && Arrays.equals(this.heartAge90Days, qH15HealthData.heartAge90Days) && Arrays.equals(this.fitnessAge90Days, qH15HealthData.fitnessAge90Days) && this.biosAgeLastMonth == qH15HealthData.biosAgeLastMonth && this.heartAgeLastMonth == qH15HealthData.heartAgeLastMonth && this.fitnessAgeLastMonth == qH15HealthData.fitnessAgeLastMonth && this.biosAgeLastYear == qH15HealthData.biosAgeLastYear && this.heartAgeLastYear == qH15HealthData.heartAgeLastYear && this.fitnessAgeLastYear == qH15HealthData.fitnessAgeLastYear && this.biosAgeLastMonthStatus == qH15HealthData.biosAgeLastMonthStatus && this.heartAgeLastMonthStatus == qH15HealthData.heartAgeLastMonthStatus && this.fitnessAgeLastMonthStatus == qH15HealthData.fitnessAgeLastMonthStatus && this.biosAgeLastYearStatus == qH15HealthData.biosAgeLastYearStatus && this.heartAgeLastYearStatus == qH15HealthData.heartAgeLastYearStatus && this.fitnessAgeLastYearStatus == qH15HealthData.fitnessAgeLastYearStatus && this.biosAgeLastMonthChange == qH15HealthData.biosAgeLastMonthChange && this.heartAgeLastMonthChange == qH15HealthData.heartAgeLastMonthChange && this.fitnessAgeLastMonthChange == qH15HealthData.fitnessAgeLastMonthChange && this.biosAgeLastYearChange == qH15HealthData.biosAgeLastYearChange && this.heartAgeLastYearChange == qH15HealthData.heartAgeLastYearChange && this.fitnessAgeLastYearChange == qH15HealthData.fitnessAgeLastYearChange && this.cardiovascularRisk == qH15HealthData.cardiovascularRisk && this.dementiaRisk == qH15HealthData.dementiaRisk && this.diabetesRisk == qH15HealthData.diabetesRisk && this.cardiovascularRiskStatus == qH15HealthData.cardiovascularRiskStatus && this.dementiaRiskStatus == qH15HealthData.dementiaRiskStatus && this.diabetesRiskStatus == qH15HealthData.diabetesRiskStatus && this.cardiovascularRiskChange == qH15HealthData.cardiovascularRiskChange && this.dementiaRiskChange == qH15HealthData.dementiaRiskChange && this.diabetesRiskChange == qH15HealthData.diabetesRiskChange && this.heartAttackRisk == qH15HealthData.heartAttackRisk && this.strokeRisk == qH15HealthData.strokeRisk && this.heartFailureRisk == qH15HealthData.heartFailureRisk && this.heartAttackRiskStatus == qH15HealthData.heartAttackRiskStatus && this.strokeRiskStatus == qH15HealthData.strokeRiskStatus && this.heartFailureRiskStatus == qH15HealthData.heartFailureRiskStatus && this.heartAttackRiskChange == qH15HealthData.heartAttackRiskChange && this.strokeRiskChange == qH15HealthData.strokeRiskChange && this.heartFailureRiskChange == qH15HealthData.heartFailureRiskChange && this.memoryLossRisk == qH15HealthData.memoryLossRisk && this.fallInjuryRisk == qH15HealthData.fallInjuryRisk && this.loseIndependentRisk == qH15HealthData.loseIndependentRisk && this.memoryLossRiskStatus == qH15HealthData.memoryLossRiskStatus && this.fallInjuryRiskStatus == qH15HealthData.fallInjuryRiskStatus && this.loseIndependentRiskStatus == qH15HealthData.loseIndependentRiskStatus && this.memoryLossRiskChange == qH15HealthData.memoryLossRiskChange && this.fallInjuryRiskChange == qH15HealthData.fallInjuryRiskChange && this.loseIndependentRiskChange == qH15HealthData.loseIndependentRiskChange && this.kidneyDiseaseRisk == qH15HealthData.kidneyDiseaseRisk && this.nerveDamageRisk == qH15HealthData.nerveDamageRisk && this.visionLossRisk == qH15HealthData.visionLossRisk && this.kidneyDiseaseRiskStatus == qH15HealthData.kidneyDiseaseRiskStatus && this.nerveDamageRiskStatus == qH15HealthData.nerveDamageRiskStatus && this.visionLossRiskStatus == qH15HealthData.visionLossRiskStatus && this.kidneyDiseaseRiskChange == qH15HealthData.kidneyDiseaseRiskChange && this.nerveDamageRiskChange == qH15HealthData.nerveDamageRiskChange && this.visionLossRiskChange == qH15HealthData.visionLossRiskChange && this.nutritionStatus == qH15HealthData.nutritionStatus && this.goalStatus == qH15HealthData.goalStatus && this.timestamp == qH15HealthData.timestamp;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.biosAge * 31) + this.heartAge) * 31) + this.fitnessAge) * 31) + this.biosAgeStatus.hashCode()) * 31) + this.heartAgeStatus.hashCode()) * 31) + this.fitnessAgeStatus.hashCode()) * 31) + this.biosAgeChange) * 31) + this.heartAgeChange) * 31) + this.fitnessAgeChange) * 31) + Arrays.hashCode(this.biosAge90Days)) * 31) + Arrays.hashCode(this.heartAge90Days)) * 31) + Arrays.hashCode(this.fitnessAge90Days)) * 31) + this.biosAgeLastMonth) * 31) + this.heartAgeLastMonth) * 31) + this.fitnessAgeLastMonth) * 31) + this.biosAgeLastYear) * 31) + this.heartAgeLastYear) * 31) + this.fitnessAgeLastYear) * 31) + this.biosAgeLastMonthStatus.hashCode()) * 31) + this.heartAgeLastMonthStatus.hashCode()) * 31) + this.fitnessAgeLastMonthStatus.hashCode()) * 31) + this.biosAgeLastYearStatus.hashCode()) * 31) + this.heartAgeLastYearStatus.hashCode()) * 31) + this.fitnessAgeLastYearStatus.hashCode()) * 31) + this.biosAgeLastMonthChange) * 31) + this.heartAgeLastMonthChange) * 31) + this.fitnessAgeLastMonthChange) * 31) + this.biosAgeLastYearChange) * 31) + this.heartAgeLastYearChange) * 31) + this.fitnessAgeLastYearChange) * 31) + this.cardiovascularRisk) * 31) + this.dementiaRisk) * 31) + this.diabetesRisk) * 31) + this.cardiovascularRiskStatus.hashCode()) * 31) + this.dementiaRiskStatus.hashCode()) * 31) + this.diabetesRiskStatus.hashCode()) * 31) + this.cardiovascularRiskChange) * 31) + this.dementiaRiskChange) * 31) + this.diabetesRiskChange) * 31) + this.heartAttackRisk) * 31) + this.strokeRisk) * 31) + this.heartFailureRisk) * 31) + this.heartAttackRiskStatus.hashCode()) * 31) + this.strokeRiskStatus.hashCode()) * 31) + this.heartFailureRiskStatus.hashCode()) * 31) + this.heartAttackRiskChange) * 31) + this.strokeRiskChange) * 31) + this.heartFailureRiskChange) * 31) + this.memoryLossRisk) * 31) + this.fallInjuryRisk) * 31) + this.loseIndependentRisk) * 31) + this.memoryLossRiskStatus.hashCode()) * 31) + this.fallInjuryRiskStatus.hashCode()) * 31) + this.loseIndependentRiskStatus.hashCode()) * 31) + this.memoryLossRiskChange) * 31) + this.fallInjuryRiskChange) * 31) + this.loseIndependentRiskChange) * 31) + this.kidneyDiseaseRisk) * 31) + this.nerveDamageRisk) * 31) + this.visionLossRisk) * 31) + this.kidneyDiseaseRiskStatus.hashCode()) * 31) + this.nerveDamageRiskStatus.hashCode()) * 31) + this.visionLossRiskStatus.hashCode()) * 31) + this.kidneyDiseaseRiskChange) * 31) + this.nerveDamageRiskChange) * 31) + this.visionLossRiskChange) * 31) + this.nutritionStatus) * 31) + this.goalStatus) * 31) + this.timestamp;
    }

    public final int component1() {
        return this.biosAge;
    }

    public final int component2() {
        return this.heartAge;
    }

    public final int component3() {
        return this.fitnessAge;
    }

    @NotNull
    public final HealthStatus component4() {
        return this.biosAgeStatus;
    }

    @NotNull
    public final HealthStatus component5() {
        return this.heartAgeStatus;
    }

    @NotNull
    public final HealthStatus component6() {
        return this.fitnessAgeStatus;
    }

    public final int component7() {
        return this.biosAgeChange;
    }

    public final int component8() {
        return this.heartAgeChange;
    }

    public final int component9() {
        return this.fitnessAgeChange;
    }

    @NotNull
    public final int[] component10() {
        return this.biosAge90Days;
    }

    @NotNull
    public final int[] component11() {
        return this.heartAge90Days;
    }

    @NotNull
    public final int[] component12() {
        return this.fitnessAge90Days;
    }

    public final int component13() {
        return this.biosAgeLastMonth;
    }

    public final int component14() {
        return this.heartAgeLastMonth;
    }

    public final int component15() {
        return this.fitnessAgeLastMonth;
    }

    public final int component16() {
        return this.biosAgeLastYear;
    }

    public final int component17() {
        return this.heartAgeLastYear;
    }

    public final int component18() {
        return this.fitnessAgeLastYear;
    }

    @NotNull
    public final HealthStatus component19() {
        return this.biosAgeLastMonthStatus;
    }

    @NotNull
    public final HealthStatus component20() {
        return this.heartAgeLastMonthStatus;
    }

    @NotNull
    public final HealthStatus component21() {
        return this.fitnessAgeLastMonthStatus;
    }

    @NotNull
    public final HealthStatus component22() {
        return this.biosAgeLastYearStatus;
    }

    @NotNull
    public final HealthStatus component23() {
        return this.heartAgeLastYearStatus;
    }

    @NotNull
    public final HealthStatus component24() {
        return this.fitnessAgeLastYearStatus;
    }

    public final int component25() {
        return this.biosAgeLastMonthChange;
    }

    public final int component26() {
        return this.heartAgeLastMonthChange;
    }

    public final int component27() {
        return this.fitnessAgeLastMonthChange;
    }

    public final int component28() {
        return this.biosAgeLastYearChange;
    }

    public final int component29() {
        return this.heartAgeLastYearChange;
    }

    public final int component30() {
        return this.fitnessAgeLastYearChange;
    }

    public final int component31() {
        return this.cardiovascularRisk;
    }

    public final int component32() {
        return this.dementiaRisk;
    }

    public final int component33() {
        return this.diabetesRisk;
    }

    @NotNull
    public final HealthStatus component34() {
        return this.cardiovascularRiskStatus;
    }

    @NotNull
    public final HealthStatus component35() {
        return this.dementiaRiskStatus;
    }

    @NotNull
    public final HealthStatus component36() {
        return this.diabetesRiskStatus;
    }

    public final int component37() {
        return this.cardiovascularRiskChange;
    }

    public final int component38() {
        return this.dementiaRiskChange;
    }

    public final int component39() {
        return this.diabetesRiskChange;
    }

    public final int component40() {
        return this.heartAttackRisk;
    }

    public final int component41() {
        return this.strokeRisk;
    }

    public final int component42() {
        return this.heartFailureRisk;
    }

    @NotNull
    public final HealthStatus component43() {
        return this.heartAttackRiskStatus;
    }

    @NotNull
    public final HealthStatus component44() {
        return this.strokeRiskStatus;
    }

    @NotNull
    public final HealthStatus component45() {
        return this.heartFailureRiskStatus;
    }

    public final int component46() {
        return this.heartAttackRiskChange;
    }

    public final int component47() {
        return this.strokeRiskChange;
    }

    public final int component48() {
        return this.heartFailureRiskChange;
    }

    public final int component49() {
        return this.memoryLossRisk;
    }

    public final int component50() {
        return this.fallInjuryRisk;
    }

    public final int component51() {
        return this.loseIndependentRisk;
    }

    @NotNull
    public final HealthStatus component52() {
        return this.memoryLossRiskStatus;
    }

    @NotNull
    public final HealthStatus component53() {
        return this.fallInjuryRiskStatus;
    }

    @NotNull
    public final HealthStatus component54() {
        return this.loseIndependentRiskStatus;
    }

    public final int component55() {
        return this.memoryLossRiskChange;
    }

    public final int component56() {
        return this.fallInjuryRiskChange;
    }

    public final int component57() {
        return this.loseIndependentRiskChange;
    }

    public final int component58() {
        return this.kidneyDiseaseRisk;
    }

    public final int component59() {
        return this.nerveDamageRisk;
    }

    public final int component60() {
        return this.visionLossRisk;
    }

    @NotNull
    public final HealthStatus component61() {
        return this.kidneyDiseaseRiskStatus;
    }

    @NotNull
    public final HealthStatus component62() {
        return this.nerveDamageRiskStatus;
    }

    @NotNull
    public final HealthStatus component63() {
        return this.visionLossRiskStatus;
    }

    public final int component64() {
        return this.kidneyDiseaseRiskChange;
    }

    public final int component65() {
        return this.nerveDamageRiskChange;
    }

    public final int component66() {
        return this.visionLossRiskChange;
    }

    public final int component67() {
        return this.nutritionStatus;
    }

    public final int component68() {
        return this.goalStatus;
    }

    public final int component69() {
        return this.timestamp;
    }

    @NotNull
    public final QH15HealthData copy(int biosAge, int heartAge, int fitnessAge, @NotNull HealthStatus biosAgeStatus, @NotNull HealthStatus heartAgeStatus, @NotNull HealthStatus fitnessAgeStatus, int biosAgeChange, int heartAgeChange, int fitnessAgeChange, @NotNull int[] biosAge90Days, @NotNull int[] heartAge90Days, @NotNull int[] fitnessAge90Days, int biosAgeLastMonth, int heartAgeLastMonth, int fitnessAgeLastMonth, int biosAgeLastYear, int heartAgeLastYear, int fitnessAgeLastYear, @NotNull HealthStatus biosAgeLastMonthStatus, @NotNull HealthStatus heartAgeLastMonthStatus, @NotNull HealthStatus fitnessAgeLastMonthStatus, @NotNull HealthStatus biosAgeLastYearStatus, @NotNull HealthStatus heartAgeLastYearStatus, @NotNull HealthStatus fitnessAgeLastYearStatus, int biosAgeLastMonthChange, int heartAgeLastMonthChange, int fitnessAgeLastMonthChange, int biosAgeLastYearChange, int heartAgeLastYearChange, int fitnessAgeLastYearChange, int cardiovascularRisk, int dementiaRisk, int diabetesRisk, @NotNull HealthStatus cardiovascularRiskStatus, @NotNull HealthStatus dementiaRiskStatus, @NotNull HealthStatus diabetesRiskStatus, int cardiovascularRiskChange, int dementiaRiskChange, int diabetesRiskChange, int heartAttackRisk, int strokeRisk, int heartFailureRisk, @NotNull HealthStatus heartAttackRiskStatus, @NotNull HealthStatus strokeRiskStatus, @NotNull HealthStatus heartFailureRiskStatus, int heartAttackRiskChange, int strokeRiskChange, int heartFailureRiskChange, int memoryLossRisk, int fallInjuryRisk, int loseIndependentRisk, @NotNull HealthStatus memoryLossRiskStatus, @NotNull HealthStatus fallInjuryRiskStatus, @NotNull HealthStatus loseIndependentRiskStatus, int memoryLossRiskChange, int fallInjuryRiskChange, int loseIndependentRiskChange, int kidneyDiseaseRisk, int nerveDamageRisk, int visionLossRisk, @NotNull HealthStatus kidneyDiseaseRiskStatus, @NotNull HealthStatus nerveDamageRiskStatus, @NotNull HealthStatus visionLossRiskStatus, int kidneyDiseaseRiskChange, int nerveDamageRiskChange, int visionLossRiskChange, int nutritionStatus, int goalStatus, int timestamp) {
        Intrinsics.checkNotNullParameter(biosAgeStatus, "biosAgeStatus");
        Intrinsics.checkNotNullParameter(heartAgeStatus, "heartAgeStatus");
        Intrinsics.checkNotNullParameter(fitnessAgeStatus, "fitnessAgeStatus");
        Intrinsics.checkNotNullParameter(biosAge90Days, "biosAge90Days");
        Intrinsics.checkNotNullParameter(heartAge90Days, "heartAge90Days");
        Intrinsics.checkNotNullParameter(fitnessAge90Days, "fitnessAge90Days");
        Intrinsics.checkNotNullParameter(biosAgeLastMonthStatus, "biosAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(heartAgeLastMonthStatus, "heartAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(fitnessAgeLastMonthStatus, "fitnessAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(biosAgeLastYearStatus, "biosAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(heartAgeLastYearStatus, "heartAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(fitnessAgeLastYearStatus, "fitnessAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(cardiovascularRiskStatus, "cardiovascularRiskStatus");
        Intrinsics.checkNotNullParameter(dementiaRiskStatus, "dementiaRiskStatus");
        Intrinsics.checkNotNullParameter(diabetesRiskStatus, "diabetesRiskStatus");
        Intrinsics.checkNotNullParameter(heartAttackRiskStatus, "heartAttackRiskStatus");
        Intrinsics.checkNotNullParameter(strokeRiskStatus, "strokeRiskStatus");
        Intrinsics.checkNotNullParameter(heartFailureRiskStatus, "heartFailureRiskStatus");
        Intrinsics.checkNotNullParameter(memoryLossRiskStatus, "memoryLossRiskStatus");
        Intrinsics.checkNotNullParameter(fallInjuryRiskStatus, "fallInjuryRiskStatus");
        Intrinsics.checkNotNullParameter(loseIndependentRiskStatus, "loseIndependentRiskStatus");
        Intrinsics.checkNotNullParameter(kidneyDiseaseRiskStatus, "kidneyDiseaseRiskStatus");
        Intrinsics.checkNotNullParameter(nerveDamageRiskStatus, "nerveDamageRiskStatus");
        Intrinsics.checkNotNullParameter(visionLossRiskStatus, "visionLossRiskStatus");
        return new QH15HealthData(biosAge, heartAge, fitnessAge, biosAgeStatus, heartAgeStatus, fitnessAgeStatus, biosAgeChange, heartAgeChange, fitnessAgeChange, biosAge90Days, heartAge90Days, fitnessAge90Days, biosAgeLastMonth, heartAgeLastMonth, fitnessAgeLastMonth, biosAgeLastYear, heartAgeLastYear, fitnessAgeLastYear, biosAgeLastMonthStatus, heartAgeLastMonthStatus, fitnessAgeLastMonthStatus, biosAgeLastYearStatus, heartAgeLastYearStatus, fitnessAgeLastYearStatus, biosAgeLastMonthChange, heartAgeLastMonthChange, fitnessAgeLastMonthChange, biosAgeLastYearChange, heartAgeLastYearChange, fitnessAgeLastYearChange, cardiovascularRisk, dementiaRisk, diabetesRisk, cardiovascularRiskStatus, dementiaRiskStatus, diabetesRiskStatus, cardiovascularRiskChange, dementiaRiskChange, diabetesRiskChange, heartAttackRisk, strokeRisk, heartFailureRisk, heartAttackRiskStatus, strokeRiskStatus, heartFailureRiskStatus, heartAttackRiskChange, strokeRiskChange, heartFailureRiskChange, memoryLossRisk, fallInjuryRisk, loseIndependentRisk, memoryLossRiskStatus, fallInjuryRiskStatus, loseIndependentRiskStatus, memoryLossRiskChange, fallInjuryRiskChange, loseIndependentRiskChange, kidneyDiseaseRisk, nerveDamageRisk, visionLossRisk, kidneyDiseaseRiskStatus, nerveDamageRiskStatus, visionLossRiskStatus, kidneyDiseaseRiskChange, nerveDamageRiskChange, visionLossRiskChange, nutritionStatus, goalStatus, timestamp);
    }

    @NotNull
    public String toString() {
        return "QH15HealthData(biosAge=" + this.biosAge + ", heartAge=" + this.heartAge + ", fitnessAge=" + this.fitnessAge + ", biosAgeStatus=" + this.biosAgeStatus + ", heartAgeStatus=" + this.heartAgeStatus + ", fitnessAgeStatus=" + this.fitnessAgeStatus + ", biosAgeChange=" + this.biosAgeChange + ", heartAgeChange=" + this.heartAgeChange + ", fitnessAgeChange=" + this.fitnessAgeChange + ", biosAge90Days=" + Arrays.toString(this.biosAge90Days) + ", heartAge90Days=" + Arrays.toString(this.heartAge90Days) + ", fitnessAge90Days=" + Arrays.toString(this.fitnessAge90Days) + ", biosAgeLastMonth=" + this.biosAgeLastMonth + ", heartAgeLastMonth=" + this.heartAgeLastMonth + ", fitnessAgeLastMonth=" + this.fitnessAgeLastMonth + ", biosAgeLastYear=" + this.biosAgeLastYear + ", heartAgeLastYear=" + this.heartAgeLastYear + ", fitnessAgeLastYear=" + this.fitnessAgeLastYear + ", biosAgeLastMonthStatus=" + this.biosAgeLastMonthStatus + ", heartAgeLastMonthStatus=" + this.heartAgeLastMonthStatus + ", fitnessAgeLastMonthStatus=" + this.fitnessAgeLastMonthStatus + ", biosAgeLastYearStatus=" + this.biosAgeLastYearStatus + ", heartAgeLastYearStatus=" + this.heartAgeLastYearStatus + ", fitnessAgeLastYearStatus=" + this.fitnessAgeLastYearStatus + ", biosAgeLastMonthChange=" + this.biosAgeLastMonthChange + ", heartAgeLastMonthChange=" + this.heartAgeLastMonthChange + ", fitnessAgeLastMonthChange=" + this.fitnessAgeLastMonthChange + ", biosAgeLastYearChange=" + this.biosAgeLastYearChange + ", heartAgeLastYearChange=" + this.heartAgeLastYearChange + ", fitnessAgeLastYearChange=" + this.fitnessAgeLastYearChange + ", cardiovascularRisk=" + this.cardiovascularRisk + ", dementiaRisk=" + this.dementiaRisk + ", diabetesRisk=" + this.diabetesRisk + ", cardiovascularRiskStatus=" + this.cardiovascularRiskStatus + ", dementiaRiskStatus=" + this.dementiaRiskStatus + ", diabetesRiskStatus=" + this.diabetesRiskStatus + ", cardiovascularRiskChange=" + this.cardiovascularRiskChange + ", dementiaRiskChange=" + this.dementiaRiskChange + ", diabetesRiskChange=" + this.diabetesRiskChange + ", heartAttackRisk=" + this.heartAttackRisk + ", strokeRisk=" + this.strokeRisk + ", heartFailureRisk=" + this.heartFailureRisk + ", heartAttackRiskStatus=" + this.heartAttackRiskStatus + ", strokeRiskStatus=" + this.strokeRiskStatus + ", heartFailureRiskStatus=" + this.heartFailureRiskStatus + ", heartAttackRiskChange=" + this.heartAttackRiskChange + ", strokeRiskChange=" + this.strokeRiskChange + ", heartFailureRiskChange=" + this.heartFailureRiskChange + ", memoryLossRisk=" + this.memoryLossRisk + ", fallInjuryRisk=" + this.fallInjuryRisk + ", loseIndependentRisk=" + this.loseIndependentRisk + ", memoryLossRiskStatus=" + this.memoryLossRiskStatus + ", fallInjuryRiskStatus=" + this.fallInjuryRiskStatus + ", loseIndependentRiskStatus=" + this.loseIndependentRiskStatus + ", memoryLossRiskChange=" + this.memoryLossRiskChange + ", fallInjuryRiskChange=" + this.fallInjuryRiskChange + ", loseIndependentRiskChange=" + this.loseIndependentRiskChange + ", kidneyDiseaseRisk=" + this.kidneyDiseaseRisk + ", nerveDamageRisk=" + this.nerveDamageRisk + ", visionLossRisk=" + this.visionLossRisk + ", kidneyDiseaseRiskStatus=" + this.kidneyDiseaseRiskStatus + ", nerveDamageRiskStatus=" + this.nerveDamageRiskStatus + ", visionLossRiskStatus=" + this.visionLossRiskStatus + ", kidneyDiseaseRiskChange=" + this.kidneyDiseaseRiskChange + ", nerveDamageRiskChange=" + this.nerveDamageRiskChange + ", visionLossRiskChange=" + this.visionLossRiskChange + ", nutritionStatus=" + this.nutritionStatus + ", goalStatus=" + this.goalStatus + ", timestamp=" + this.timestamp + ")";
    }

    public QH15HealthData(int i, int i2, int i3, @NotNull HealthStatus healthStatus, @NotNull HealthStatus healthStatus2, @NotNull HealthStatus healthStatus3, int i4, int i5, int i6, @NotNull int[] iArr, @NotNull int[] iArr2, @NotNull int[] iArr3, int i7, int i8, int i9, int i10, int i11, int i12, @NotNull HealthStatus healthStatus4, @NotNull HealthStatus healthStatus5, @NotNull HealthStatus healthStatus6, @NotNull HealthStatus healthStatus7, @NotNull HealthStatus healthStatus8, @NotNull HealthStatus healthStatus9, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, @NotNull HealthStatus healthStatus10, @NotNull HealthStatus healthStatus11, @NotNull HealthStatus healthStatus12, int i22, int i23, int i24, int i25, int i26, int i27, @NotNull HealthStatus healthStatus13, @NotNull HealthStatus healthStatus14, @NotNull HealthStatus healthStatus15, int i28, int i29, int i30, int i31, int i32, int i33, @NotNull HealthStatus healthStatus16, @NotNull HealthStatus healthStatus17, @NotNull HealthStatus healthStatus18, int i34, int i35, int i36, int i37, int i38, int i39, @NotNull HealthStatus healthStatus19, @NotNull HealthStatus healthStatus20, @NotNull HealthStatus healthStatus21, int i40, int i41, int i42, int i43, int i44, int i45) {
        Intrinsics.checkNotNullParameter(healthStatus, "biosAgeStatus");
        Intrinsics.checkNotNullParameter(healthStatus2, "heartAgeStatus");
        Intrinsics.checkNotNullParameter(healthStatus3, "fitnessAgeStatus");
        Intrinsics.checkNotNullParameter(iArr, "biosAge90Days");
        Intrinsics.checkNotNullParameter(iArr2, "heartAge90Days");
        Intrinsics.checkNotNullParameter(iArr3, "fitnessAge90Days");
        Intrinsics.checkNotNullParameter(healthStatus4, "biosAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(healthStatus5, "heartAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(healthStatus6, "fitnessAgeLastMonthStatus");
        Intrinsics.checkNotNullParameter(healthStatus7, "biosAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(healthStatus8, "heartAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(healthStatus9, "fitnessAgeLastYearStatus");
        Intrinsics.checkNotNullParameter(healthStatus10, "cardiovascularRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus11, "dementiaRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus12, "diabetesRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus13, "heartAttackRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus14, "strokeRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus15, "heartFailureRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus16, "memoryLossRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus17, "fallInjuryRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus18, "loseIndependentRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus19, "kidneyDiseaseRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus20, "nerveDamageRiskStatus");
        Intrinsics.checkNotNullParameter(healthStatus21, "visionLossRiskStatus");
        this.biosAge = i;
        this.heartAge = i2;
        this.fitnessAge = i3;
        this.biosAgeStatus = healthStatus;
        this.heartAgeStatus = healthStatus2;
        this.fitnessAgeStatus = healthStatus3;
        this.biosAgeChange = i4;
        this.heartAgeChange = i5;
        this.fitnessAgeChange = i6;
        this.biosAge90Days = iArr;
        this.heartAge90Days = iArr2;
        this.fitnessAge90Days = iArr3;
        this.biosAgeLastMonth = i7;
        this.heartAgeLastMonth = i8;
        this.fitnessAgeLastMonth = i9;
        this.biosAgeLastYear = i10;
        this.heartAgeLastYear = i11;
        this.fitnessAgeLastYear = i12;
        this.biosAgeLastMonthStatus = healthStatus4;
        this.heartAgeLastMonthStatus = healthStatus5;
        this.fitnessAgeLastMonthStatus = healthStatus6;
        this.biosAgeLastYearStatus = healthStatus7;
        this.heartAgeLastYearStatus = healthStatus8;
        this.fitnessAgeLastYearStatus = healthStatus9;
        this.biosAgeLastMonthChange = i13;
        this.heartAgeLastMonthChange = i14;
        this.fitnessAgeLastMonthChange = i15;
        this.biosAgeLastYearChange = i16;
        this.heartAgeLastYearChange = i17;
        this.fitnessAgeLastYearChange = i18;
        this.cardiovascularRisk = i19;
        this.dementiaRisk = i20;
        this.diabetesRisk = i21;
        this.cardiovascularRiskStatus = healthStatus10;
        this.dementiaRiskStatus = healthStatus11;
        this.diabetesRiskStatus = healthStatus12;
        this.cardiovascularRiskChange = i22;
        this.dementiaRiskChange = i23;
        this.diabetesRiskChange = i24;
        this.heartAttackRisk = i25;
        this.strokeRisk = i26;
        this.heartFailureRisk = i27;
        this.heartAttackRiskStatus = healthStatus13;
        this.strokeRiskStatus = healthStatus14;
        this.heartFailureRiskStatus = healthStatus15;
        this.heartAttackRiskChange = i28;
        this.strokeRiskChange = i29;
        this.heartFailureRiskChange = i30;
        this.memoryLossRisk = i31;
        this.fallInjuryRisk = i32;
        this.loseIndependentRisk = i33;
        this.memoryLossRiskStatus = healthStatus16;
        this.fallInjuryRiskStatus = healthStatus17;
        this.loseIndependentRiskStatus = healthStatus18;
        this.memoryLossRiskChange = i34;
        this.fallInjuryRiskChange = i35;
        this.loseIndependentRiskChange = i36;
        this.kidneyDiseaseRisk = i37;
        this.nerveDamageRisk = i38;
        this.visionLossRisk = i39;
        this.kidneyDiseaseRiskStatus = healthStatus19;
        this.nerveDamageRiskStatus = healthStatus20;
        this.visionLossRiskStatus = healthStatus21;
        this.kidneyDiseaseRiskChange = i40;
        this.nerveDamageRiskChange = i41;
        this.visionLossRiskChange = i42;
        this.nutritionStatus = i43;
        this.goalStatus = i44;
        this.timestamp = i45;
    }

    public /* synthetic */ QH15HealthData(int i, int i2, int i3, HealthStatus healthStatus, HealthStatus healthStatus2, HealthStatus healthStatus3, int i4, int i5, int i6, int[] iArr, int[] iArr2, int[] iArr3, int i7, int i8, int i9, int i10, int i11, int i12, HealthStatus healthStatus4, HealthStatus healthStatus5, HealthStatus healthStatus6, HealthStatus healthStatus7, HealthStatus healthStatus8, HealthStatus healthStatus9, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, HealthStatus healthStatus10, HealthStatus healthStatus11, HealthStatus healthStatus12, int i22, int i23, int i24, int i25, int i26, int i27, HealthStatus healthStatus13, HealthStatus healthStatus14, HealthStatus healthStatus15, int i28, int i29, int i30, int i31, int i32, int i33, HealthStatus healthStatus16, HealthStatus healthStatus17, HealthStatus healthStatus18, int i34, int i35, int i36, int i37, int i38, int i39, HealthStatus healthStatus19, HealthStatus healthStatus20, HealthStatus healthStatus21, int i40, int i41, int i42, int i43, int i44, int i45, int i46, int i47, int i48, DefaultConstructorMarker defaultConstructorMarker) {
        this((i46 & 1) != 0 ? 0 : i, (i46 & 2) != 0 ? 0 : i2, (i46 & 4) != 0 ? 0 : i3, (i46 & 8) != 0 ? HealthStatus.STABLE : healthStatus, (i46 & 16) != 0 ? HealthStatus.STABLE : healthStatus2, (i46 & 32) != 0 ? HealthStatus.STABLE : healthStatus3, (i46 & 64) != 0 ? 0 : i4, (i46 & 128) != 0 ? 0 : i5, (i46 & 256) != 0 ? 0 : i6, (i46 & 512) != 0 ? new int[90] : iArr, (i46 & 1024) != 0 ? new int[90] : iArr2, (i46 & 2048) != 0 ? new int[90] : iArr3, (i46 & 4096) != 0 ? 0 : i7, (i46 & DfuBaseService.ERROR_REMOTE_MASK) != 0 ? 0 : i8, (i46 & DfuBaseService.ERROR_CONNECTION_MASK) != 0 ? 0 : i9, (i46 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0 ? 0 : i10, (i46 & 65536) != 0 ? 0 : i11, (i46 & 131072) != 0 ? 0 : i12, (i46 & 262144) != 0 ? HealthStatus.STABLE : healthStatus4, (i46 & 524288) != 0 ? HealthStatus.STABLE : healthStatus5, (i46 & 1048576) != 0 ? HealthStatus.STABLE : healthStatus6, (i46 & 2097152) != 0 ? HealthStatus.STABLE : healthStatus7, (i46 & 4194304) != 0 ? HealthStatus.STABLE : healthStatus8, (i46 & 8388608) != 0 ? HealthStatus.STABLE : healthStatus9, (i46 & 16777216) != 0 ? 0 : i13, (i46 & 33554432) != 0 ? 0 : i14, (i46 & 67108864) != 0 ? 0 : i15, (i46 & 134217728) != 0 ? 0 : i16, (i46 & 268435456) != 0 ? 0 : i17, (i46 & 536870912) != 0 ? 0 : i18, (i46 & 1073741824) != 0 ? 0 : i19, (i46 & Integer.MIN_VALUE) != 0 ? 0 : i20, (i47 & 1) != 0 ? 0 : i21, (i47 & 2) != 0 ? HealthStatus.STABLE : healthStatus10, (i47 & 4) != 0 ? HealthStatus.STABLE : healthStatus11, (i47 & 8) != 0 ? HealthStatus.STABLE : healthStatus12, (i47 & 16) != 0 ? 0 : i22, (i47 & 32) != 0 ? 0 : i23, (i47 & 64) != 0 ? 0 : i24, (i47 & 128) != 0 ? 0 : i25, (i47 & 256) != 0 ? 0 : i26, (i47 & 512) != 0 ? 0 : i27, (i47 & 1024) != 0 ? HealthStatus.STABLE : healthStatus13, (i47 & 2048) != 0 ? HealthStatus.STABLE : healthStatus14, (i47 & 4096) != 0 ? HealthStatus.STABLE : healthStatus15, (i47 & DfuBaseService.ERROR_REMOTE_MASK) != 0 ? 0 : i28, (i47 & DfuBaseService.ERROR_CONNECTION_MASK) != 0 ? 0 : i29, (i47 & DfuBaseService.ERROR_CONNECTION_STATE_MASK) != 0 ? 0 : i30, (i47 & 65536) != 0 ? 0 : i31, (i47 & 131072) != 0 ? 0 : i32, (i47 & 262144) != 0 ? 0 : i33, (i47 & 524288) != 0 ? HealthStatus.STABLE : healthStatus16, (i47 & 1048576) != 0 ? HealthStatus.STABLE : healthStatus17, (i47 & 2097152) != 0 ? HealthStatus.STABLE : healthStatus18, (i47 & 4194304) != 0 ? 0 : i34, (i47 & 8388608) != 0 ? 0 : i35, (i47 & 16777216) != 0 ? 0 : i36, (i47 & 33554432) != 0 ? 0 : i37, (i47 & 67108864) != 0 ? 0 : i38, (i47 & 134217728) != 0 ? 0 : i39, (i47 & 268435456) != 0 ? HealthStatus.STABLE : healthStatus19, (i47 & 536870912) != 0 ? HealthStatus.STABLE : healthStatus20, (i47 & 1073741824) != 0 ? HealthStatus.STABLE : healthStatus21, (i47 & Integer.MIN_VALUE) != 0 ? 0 : i40, (i48 & 1) != 0 ? 0 : i41, (i48 & 2) != 0 ? 0 : i42, (i48 & 4) != 0 ? 0 : i43, (i48 & 8) != 0 ? 0 : i44, (i48 & 16) != 0 ? 0 : i45);
    }
}
