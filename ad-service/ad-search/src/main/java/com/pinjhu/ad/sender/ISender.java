package com.pinjhu.ad.sender;

import com.pinjhu.ad.mysql.dto.MySqlRowData;

public interface ISender {

    void sender(MySqlRowData rowData);
}
