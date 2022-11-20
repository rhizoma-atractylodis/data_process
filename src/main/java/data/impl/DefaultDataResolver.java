package data.impl;

import base.Constants;
import com.google.protobuf.InvalidProtocolBufferException;
import data.MeasurementDataResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.PingData;
import pojo.TraceData;
import transfer.TransferOuterClass;

import java.time.Instant;
import java.util.Base64;

public class DefaultDataResolver implements MeasurementDataResolver {
    private Logger logger = LoggerFactory.getLogger(DefaultDataResolver.class);

    @Override
    public Object resolveLineData(String line) {
        Object result = null;
        String[] tags = line.split(",");
        String dataType = tags[0];
        String[] fields = tags[2].split(" ");
//        long timestamp = Long.parseLong(fields[2]);
        String data = fields[1].split("=")[1];
        byte[] base64Data = Base64.getDecoder().decode(data);
        switch (dataType) {
            case Constants.PING_DATA -> {
                try {
                    TransferOuterClass.TransferPingRequest pingRequest = TransferOuterClass.TransferPingRequest.parseFrom(base64Data);
                    TransferOuterClass.MeasurementBaseInfo base = pingRequest.getMeasurementBaseResult();
                    PingData pingData = new PingData();
                    String dest = base.getDestination();
                    String[] netSeg = dest.split("\\.");
                    pingData.setHost(base.getHost());
                    pingData.setDestA(netSeg[0]);
                    pingData.setDestB(netSeg[1]);
                    pingData.setDestC(netSeg[2]);
                    pingData.setDestD(netSeg[3]);
                    pingData.setProtocol(base.getProtocol().name());
                    pingData.setRound(base.getRound());
                    pingData.setMeasurementPrefix(base.getMeasurementPrefix());
                    pingData.setRtt(pingRequest.getRttMillisecond());
                    pingData.setTime(Instant.ofEpochMilli(base.getRecvTimestampMillisecond()));
                    result = pingData;
                } catch (InvalidProtocolBufferException e) {
                    logger.error("parse ping data error: "+e.getMessage());
                }
            }
            case Constants.TRACE_DATA -> {
                try {
                    TransferOuterClass.TransferTraceRequest traceRequest = TransferOuterClass.TransferTraceRequest.parseFrom(base64Data);
                    TransferOuterClass.MeasurementBaseInfo base = traceRequest.getMeasurementBaseResult();
                    TraceData traceData = new TraceData();
                    String dest = base.getDestination();
                    String[] netSeg = dest.split("\\.");
                    traceData.setHost(base.getHost());
                    traceData.setDestA(netSeg[0]);
                    traceData.setDestB(netSeg[1]);
                    traceData.setDestC(netSeg[2]);
                    traceData.setDestD(netSeg[3]);
                    traceData.setProtocol(base.getProtocol().name());
                    traceData.setRound(base.getRound());
                    traceData.setMeasurementPrefix(base.getMeasurementPrefix());
                    traceData.setRtt(traceRequest.getRttMillisecond());
                    traceData.setTime(Instant.ofEpochMilli(base.getRecvTimestampMillisecond()));
                } catch (InvalidProtocolBufferException e) {
                    logger.error("parse trace data error: "+e.getMessage());
                }
            }
            default -> {}
        }
        return result;
    }
}
