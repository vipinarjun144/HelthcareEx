package in.nareshIT.hc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointment_tab")
@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="app_doc_id_fk")
	private Doctor doctor;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="app_date_col")
	private Date date;
	
	@Column(name="app_slot_col")
	private Integer noOfSlot;
	
	@Column(name="app_dtl_col")
	private String details;
	
	@Column(name="app_amt_col")
	private Double fees;
	
	
	
	
	

}
