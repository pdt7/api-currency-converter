package br.com.paulodt.apicurrencyconverter.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;
    @NotBlank
    private String originCurrency;
    private long originValue;
    @NotBlank
    private String destinationCurrency;
    private double destinationValue;
    private double conversionRate;
    
    public Transaction(){

    }

    public Transaction(Long idUsuario, String originCurrency, long originValue, String destinationCurrency) {
        this.idUsuario = idUsuario;
        this.originCurrency = originCurrency;
        this.originValue = originValue;
        this.destinationCurrency = destinationCurrency;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getOriginCurrency() {
        return originCurrency;
    }
    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }
    public long getOriginValue() {
        return originValue;
    }
    public void setOriginValue(long originValue) {
        this.originValue = originValue;
    }
    public String getDestinationCurrency() {
        return destinationCurrency;
    }
    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
    public double getDestinationValue() {
        return destinationValue;
    }
    public void setDestinationValue(double destinationValue) {
        this.destinationValue = destinationValue;
    }
    public double getConversionRate() {
        return conversionRate;
    }
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;
    
}
