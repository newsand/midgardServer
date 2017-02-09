package Valhalla.midgardServer.model;

import java.time.OffsetDateTime;

public class TemperatureResgister {
	private OffsetDateTime m_registerTime;
	private float m_temperature;
	
	public TemperatureResgister(OffsetDateTime p_registerTime, float p_temperature) {
		super();
		this.m_registerTime = p_registerTime;
		this.m_temperature = p_temperature;
	}
	public OffsetDateTime getRegisterTime() {
		return m_registerTime;
	}
	public void setRegisterTime(OffsetDateTime m_registerTime) {
		this.m_registerTime = m_registerTime;
	}
	public float getTemperature() {
		return m_temperature;
	}
	public void setTemperature(float m_temperature) {
		this.m_temperature = m_temperature;
	}

}
