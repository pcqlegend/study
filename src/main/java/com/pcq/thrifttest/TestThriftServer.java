package com.pcq.thrifttest;

import com.sankuai.zcm.coop.sdk.service.settleunit.SettleUnitRuleReqVO;
import com.sankuai.zcm.coop.sdk.service.settleunit.SettleUnitRuleResVO;
import com.sankuai.zcm.coop.sdk.service.settleunit.SettleUnitRuleThriftService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by pcq on 2017/5/15.
 */
public class TestThriftServer {
    public static void main(String[] args) throws TTransportException {
//IP为本机IP或服务器的云外网IP  不能为localhost或127.0.0.1
        TTransport tTransport = new TSocket("172.18.221.9", 1078);
        TFramedTransport transport = new TFramedTransport(tTransport);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
//这以上的代码都不用改
//为Service名称.Client
        SettleUnitRuleThriftService.Client client = new SettleUnitRuleThriftService.Client(protocol);
//这里就自由调用就可以了
        SettleUnitRuleReqVO settleUnitRuleReqVO=new SettleUnitRuleReqVO();
        settleUnitRuleReqVO.setShopId(1l);
        try {
          SettleUnitRuleResVO resVO = client.queryByShopId(settleUnitRuleReqVO);
            System.out.println(resVO.toString());
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
