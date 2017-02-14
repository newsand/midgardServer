package Valhalla.midgardServer.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class TemperatureRegister {
	private int m_hardwareId;
	private int m_sensorId;
	private String m_registerTime;
	private double m_temperature;

	public TemperatureRegister() {
	}

	public TemperatureRegister(OffsetDateTime p_registerTime, double p_temperature, int p_hardwareId, int p_sensorId) {
		if (p_registerTime == null)
			this.m_registerTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
		this.m_registerTime = p_registerTime.toString();
		this.m_temperature = p_temperature;
		this.m_hardwareId = p_hardwareId;
		this.m_sensorId = p_sensorId;
	}

	public TemperatureRegister(String p_registerTime, double p_temperature, int p_hardwareId, int p_sensorId) {
		if (p_registerTime == null)
			this.m_registerTime = OffsetDateTime.now(ZoneOffset.UTC).toString();
		this.m_registerTime = p_registerTime;
		this.m_temperature = p_temperature;
		this.m_hardwareId = p_hardwareId;
		this.m_sensorId = p_sensorId;
	}

	public int getHardwareId() {
		return m_hardwareId;
	}

	public void setHardwareId(int p_hardwareId) {
		this.m_hardwareId = p_hardwareId;
	}

	public int getSensorId() {
		return m_sensorId;
	}

	public void setSensorId(int p_sensorId) {
		this.m_sensorId = p_sensorId;
	}

	public String getRegisterTime() {
		return m_registerTime;
	}

	
	public void setRegisterTime(String p_registerTime) {
		this.m_registerTime = p_registerTime;
	}
	
	public void setRegisterTime(OffsetDateTime p_registerTime) {
		this.m_registerTime = p_registerTime.toString();
	}

	public double getTemperature() {
		return m_temperature;
	}

	public void setTemperature(double m_temperature) {
		this.m_temperature = m_temperature;
	}

	public Document toDocument() {
		Document v_converted = new Document();
		v_converted.append("RegisterTime", this.getRegisterTime().toString());
		v_converted.append("Temperature", this.getTemperature());
		v_converted.append("Hardware", this.getHardwareId());
		v_converted.append("Sensor", this.getSensorId());

		return v_converted;
	}

	@Override
	public String toString() {
		return "TemperatureRegister [m_hardwareId=" + m_hardwareId + ", m_sensorId=" + m_sensorId + ", m_registerTime="
				+ m_registerTime + ", m_temperature=" + m_temperature + "]";
	}

}
