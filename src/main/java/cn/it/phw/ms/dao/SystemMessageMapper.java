package cn.it.phw.ms.dao;

import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.SystemMessage;

import java.util.List;

public interface SystemMessageMapper extends BaseMapper<SystemMessage> {

    /**
     *
     * @param example
     * @return
     */
    List<SystemMessage> selectByExampleWithBLOBs(BaseExample example);

}
