package com.jy.model.dcs;

public class DcsLineItem {
    private Long recId;

    private String lineId;

    private String lineName;

    private String nodeId;

    private String nodeName;

    private String deviceId;

    private String deviceName;

    private String partId;

    private String partName;

    private String itemId;

    private String itemName;

    private String itemValOrg;

    private String itemVal;

    private String dcsDttm;

    private String etlDttm;

    private Integer dcsYear;

    private Integer dcsMonth;

    private Integer dcsDay;

    private Integer dcsHour;

    private Integer dcsMin;

    private Integer dcsSec;

    private Integer dcsMs;

    private String sourceType;

    private String sourceId;

    private String privator;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemValOrg() {
        return itemValOrg;
    }

    public void setItemValOrg(String itemValOrg) {
        this.itemValOrg = itemValOrg == null ? null : itemValOrg.trim();
    }

    public String getItemVal() {
        return itemVal;
    }

    public void setItemVal(String itemVal) {
        this.itemVal = itemVal == null ? null : itemVal.trim();
    }

    public String getDcsDttm() {
        return dcsDttm;
    }

    public void setDcsDttm(String dcsDttm) {
        this.dcsDttm = dcsDttm == null ? null : dcsDttm.trim();
    }

    public String getEtlDttm() {
        return etlDttm;
    }

    public void setEtlDttm(String etlDttm) {
        this.etlDttm = etlDttm == null ? null : etlDttm.trim();
    }

    public Integer getDcsYear() {
        return dcsYear;
    }

    public void setDcsYear(Integer dcsYear) {
        this.dcsYear = dcsYear;
    }

    public Integer getDcsMonth() {
        return dcsMonth;
    }

    public void setDcsMonth(Integer dcsMonth) {
        this.dcsMonth = dcsMonth;
    }

    public Integer getDcsDay() {
        return dcsDay;
    }

    public void setDcsDay(Integer dcsDay) {
        this.dcsDay = dcsDay;
    }

    public Integer getDcsHour() {
        return dcsHour;
    }

    public void setDcsHour(Integer dcsHour) {
        this.dcsHour = dcsHour;
    }

    public Integer getDcsMin() {
        return dcsMin;
    }

    public void setDcsMin(Integer dcsMin) {
        this.dcsMin = dcsMin;
    }

    public Integer getDcsSec() {
        return dcsSec;
    }

    public void setDcsSec(Integer dcsSec) {
        this.dcsSec = dcsSec;
    }

    public Integer getDcsMs() {
        return dcsMs;
    }

    public void setDcsMs(Integer dcsMs) {
        this.dcsMs = dcsMs;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getPrivator() {
        return privator;
    }

    public void setPrivator(String privator) {
        this.privator = privator == null ? null : privator.trim();
    }
}