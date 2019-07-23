package com.pinjhu.ad.mysql.listener;

import com.pinjhu.ad.mysql.dto.BinlogRowData;

public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
