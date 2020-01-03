
public class Registro {
	
	private String CANDIDATE;
	private String CLIENT_ID;
	private String eventType;
	private String PROCESS_ID;
	private String DT_EVENT;
	private String JOB_TYPE;
	private double VALUE;
	private double LOST_VALUE;
	private String REFUND_REASON;
	private String BILLED_BY;
	private String PERIODO_COBRANCA;	
	 
	//Construtores	
	public Registro() {
		
	}	

	public Registro(String cANDIDATE, String cLIENT_ID, String eventType, String pROCESS_ID, String dT_EVENT,
			String jOB_TYPE, double vALUE, double lOST_VALUE, String rEFUND_REASON, String bILLED_BY,
			String pERIODO_COBRANCA) {
		super();
		CANDIDATE = cANDIDATE;
		CLIENT_ID = cLIENT_ID;
		this.eventType = eventType;
		PROCESS_ID = pROCESS_ID;
		DT_EVENT = dT_EVENT;
		JOB_TYPE = jOB_TYPE;
		VALUE = vALUE;
		LOST_VALUE = lOST_VALUE;
		REFUND_REASON = rEFUND_REASON;
		BILLED_BY = bILLED_BY;
		PERIODO_COBRANCA = pERIODO_COBRANCA;
	}



	//Getters and Setters
	public String getProcess_id() {
		return PROCESS_ID;
	}
	public void setProcess_id(String process_id) {
		this.PROCESS_ID = process_id;
	}
	public String getJob_type() {
		return JOB_TYPE;
	}
	public void setJob_type(String job_type) {
		this.JOB_TYPE = job_type;
	}
	public String getClient_id() {
		return CLIENT_ID;
	}
	public void setClient_id(String client_id) {
		this.CLIENT_ID = client_id;
	}
	public String getCandidate() {
		return CANDIDATE;
	}
	public void setCandidate(String candidate) {
		this.CANDIDATE = candidate;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getDt_event() {
		return DT_EVENT;
	}
	public void setDt_event(String dt_event) {
		this.DT_EVENT = dt_event;
	}
	public double getValue() {
		return VALUE;
	}
	public void setValue(double value) {
		this.VALUE = value;
	}
	public String getBilled_by() {
		return BILLED_BY;
	}
	public void setBilled_by(String billed_by) {
		this.BILLED_BY = billed_by;
	}
	public String getPeriodo_cobranca() {
		return PERIODO_COBRANCA;
	}
	public void setPeriodo_cobranca(String periodo_cobranca) {
		this.PERIODO_COBRANCA = periodo_cobranca;
	}
	public double getLost_value() {
		return LOST_VALUE;
	}
	public void setLost_value(double lost_value) {
		this.LOST_VALUE = lost_value;
	}
	public String getRefund_reason() {
		return REFUND_REASON;
	}
	public void setRefund_reason(String refund_reason) {
		this.REFUND_REASON = refund_reason;
	}

}
