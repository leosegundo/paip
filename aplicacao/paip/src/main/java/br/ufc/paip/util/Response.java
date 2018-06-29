package br.ufc.paip.util;

public class Response {
	private String status;
	private Object data;
	
	public Response(){
		this.status = "FAIL";
		this.data = null;
	}
	
	public Response(String status, Object data){
		this.status = status;
		this.data = data;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public Object getData() {
		return data;
	}
 
	public void setData(Object data) {
		this.data = data;
	}
}