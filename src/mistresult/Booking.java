/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mistresult;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "booking", catalog = "OTB", schema = "")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id"),
    @NamedQuery(name = "Booking.findByFromm", query = "SELECT b FROM Booking b WHERE b.fromm = :fromm"),
    @NamedQuery(name = "Booking.findByToo", query = "SELECT b FROM Booking b WHERE b.too = :too"),
    @NamedQuery(name = "Booking.findByBus", query = "SELECT b FROM Booking b WHERE b.bus = :bus"),
    @NamedQuery(name = "Booking.findByDate", query = "SELECT b FROM Booking b WHERE b.date = :date"),
    @NamedQuery(name = "Booking.findByTime", query = "SELECT b FROM Booking b WHERE b.time = :time"),
    @NamedQuery(name = "Booking.findByA1", query = "SELECT b FROM Booking b WHERE b.a1 = :a1"),
    @NamedQuery(name = "Booking.findByA2", query = "SELECT b FROM Booking b WHERE b.a2 = :a2"),
    @NamedQuery(name = "Booking.findByA3", query = "SELECT b FROM Booking b WHERE b.a3 = :a3"),
    @NamedQuery(name = "Booking.findByA4", query = "SELECT b FROM Booking b WHERE b.a4 = :a4"),
    @NamedQuery(name = "Booking.findByB1", query = "SELECT b FROM Booking b WHERE b.b1 = :b1"),
    @NamedQuery(name = "Booking.findByB2", query = "SELECT b FROM Booking b WHERE b.b2 = :b2"),
    @NamedQuery(name = "Booking.findByB3", query = "SELECT b FROM Booking b WHERE b.b3 = :b3"),
    @NamedQuery(name = "Booking.findByB4", query = "SELECT b FROM Booking b WHERE b.b4 = :b4"),
    @NamedQuery(name = "Booking.findByC1", query = "SELECT b FROM Booking b WHERE b.c1 = :c1"),
    @NamedQuery(name = "Booking.findByC2", query = "SELECT b FROM Booking b WHERE b.c2 = :c2"),
    @NamedQuery(name = "Booking.findByC3", query = "SELECT b FROM Booking b WHERE b.c3 = :c3"),
    @NamedQuery(name = "Booking.findByC4", query = "SELECT b FROM Booking b WHERE b.c4 = :c4"),
    @NamedQuery(name = "Booking.findByD1", query = "SELECT b FROM Booking b WHERE b.d1 = :d1"),
    @NamedQuery(name = "Booking.findByD2", query = "SELECT b FROM Booking b WHERE b.d2 = :d2"),
    @NamedQuery(name = "Booking.findByD3", query = "SELECT b FROM Booking b WHERE b.d3 = :d3"),
    @NamedQuery(name = "Booking.findByD4", query = "SELECT b FROM Booking b WHERE b.d4 = :d4"),
    @NamedQuery(name = "Booking.findByE1", query = "SELECT b FROM Booking b WHERE b.e1 = :e1"),
    @NamedQuery(name = "Booking.findByE2", query = "SELECT b FROM Booking b WHERE b.e2 = :e2"),
    @NamedQuery(name = "Booking.findByE3", query = "SELECT b FROM Booking b WHERE b.e3 = :e3"),
    @NamedQuery(name = "Booking.findByE4", query = "SELECT b FROM Booking b WHERE b.e4 = :e4"),
    @NamedQuery(name = "Booking.findByF1", query = "SELECT b FROM Booking b WHERE b.f1 = :f1"),
    @NamedQuery(name = "Booking.findByF2", query = "SELECT b FROM Booking b WHERE b.f2 = :f2"),
    @NamedQuery(name = "Booking.findByF3", query = "SELECT b FROM Booking b WHERE b.f3 = :f3"),
    @NamedQuery(name = "Booking.findByF4", query = "SELECT b FROM Booking b WHERE b.f4 = :f4"),
    @NamedQuery(name = "Booking.findByG1", query = "SELECT b FROM Booking b WHERE b.g1 = :g1"),
    @NamedQuery(name = "Booking.findByG2", query = "SELECT b FROM Booking b WHERE b.g2 = :g2"),
    @NamedQuery(name = "Booking.findByG3", query = "SELECT b FROM Booking b WHERE b.g3 = :g3"),
    @NamedQuery(name = "Booking.findByG4", query = "SELECT b FROM Booking b WHERE b.g4 = :g4"),
    @NamedQuery(name = "Booking.findByH1", query = "SELECT b FROM Booking b WHERE b.h1 = :h1"),
    @NamedQuery(name = "Booking.findByH2", query = "SELECT b FROM Booking b WHERE b.h2 = :h2"),
    @NamedQuery(name = "Booking.findByH3", query = "SELECT b FROM Booking b WHERE b.h3 = :h3"),
    @NamedQuery(name = "Booking.findByH4", query = "SELECT b FROM Booking b WHERE b.h4 = :h4"),
    @NamedQuery(name = "Booking.findByI1", query = "SELECT b FROM Booking b WHERE b.i1 = :i1"),
    @NamedQuery(name = "Booking.findByI2", query = "SELECT b FROM Booking b WHERE b.i2 = :i2"),
    @NamedQuery(name = "Booking.findByI3", query = "SELECT b FROM Booking b WHERE b.i3 = :i3"),
    @NamedQuery(name = "Booking.findByI4", query = "SELECT b FROM Booking b WHERE b.i4 = :i4"),
    @NamedQuery(name = "Booking.findByJ1", query = "SELECT b FROM Booking b WHERE b.j1 = :j1"),
    @NamedQuery(name = "Booking.findByJ2", query = "SELECT b FROM Booking b WHERE b.j2 = :j2"),
    @NamedQuery(name = "Booking.findByJ3", query = "SELECT b FROM Booking b WHERE b.j3 = :j3"),
    @NamedQuery(name = "Booking.findByJ4", query = "SELECT b FROM Booking b WHERE b.j4 = :j4")})
public class Booking implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fromm")
    private String fromm;
    @Basic(optional = false)
    @Column(name = "too")
    private String too;
    @Basic(optional = false)
    @Column(name = "bus")
    private String bus;
    @Basic(optional = false)
    @Column(name = "date")
    private String date;
    @Basic(optional = false)
    @Column(name = "time")
    private String time;
    @Basic(optional = false)
    @Column(name = "A1")
    private int a1;
    @Basic(optional = false)
    @Column(name = "A2")
    private int a2;
    @Basic(optional = false)
    @Column(name = "A3")
    private int a3;
    @Basic(optional = false)
    @Column(name = "A4")
    private int a4;
    @Basic(optional = false)
    @Column(name = "B1")
    private int b1;
    @Basic(optional = false)
    @Column(name = "B2")
    private int b2;
    @Basic(optional = false)
    @Column(name = "B3")
    private int b3;
    @Basic(optional = false)
    @Column(name = "B4")
    private int b4;
    @Basic(optional = false)
    @Column(name = "C1")
    private int c1;
    @Basic(optional = false)
    @Column(name = "C2")
    private int c2;
    @Basic(optional = false)
    @Column(name = "C3")
    private int c3;
    @Basic(optional = false)
    @Column(name = "C4")
    private int c4;
    @Basic(optional = false)
    @Column(name = "D1")
    private int d1;
    @Basic(optional = false)
    @Column(name = "D2")
    private int d2;
    @Basic(optional = false)
    @Column(name = "D3")
    private int d3;
    @Basic(optional = false)
    @Column(name = "D4")
    private int d4;
    @Basic(optional = false)
    @Column(name = "E1")
    private int e1;
    @Basic(optional = false)
    @Column(name = "E2")
    private int e2;
    @Basic(optional = false)
    @Column(name = "E3")
    private int e3;
    @Basic(optional = false)
    @Column(name = "E4")
    private int e4;
    @Basic(optional = false)
    @Column(name = "F1")
    private int f1;
    @Basic(optional = false)
    @Column(name = "F2")
    private int f2;
    @Basic(optional = false)
    @Column(name = "F3")
    private int f3;
    @Basic(optional = false)
    @Column(name = "F4")
    private int f4;
    @Basic(optional = false)
    @Column(name = "G1")
    private int g1;
    @Basic(optional = false)
    @Column(name = "G2")
    private int g2;
    @Basic(optional = false)
    @Column(name = "G3")
    private int g3;
    @Basic(optional = false)
    @Column(name = "G4")
    private int g4;
    @Basic(optional = false)
    @Column(name = "H1")
    private int h1;
    @Basic(optional = false)
    @Column(name = "H2")
    private int h2;
    @Basic(optional = false)
    @Column(name = "H3")
    private int h3;
    @Basic(optional = false)
    @Column(name = "H4")
    private int h4;
    @Basic(optional = false)
    @Column(name = "I1")
    private int i1;
    @Basic(optional = false)
    @Column(name = "I2")
    private int i2;
    @Basic(optional = false)
    @Column(name = "I3")
    private int i3;
    @Basic(optional = false)
    @Column(name = "I4")
    private int i4;
    @Basic(optional = false)
    @Column(name = "J1")
    private int j1;
    @Basic(optional = false)
    @Column(name = "J2")
    private int j2;
    @Basic(optional = false)
    @Column(name = "J3")
    private int j3;
    @Basic(optional = false)
    @Column(name = "J4")
    private int j4;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, String fromm, String too, String bus, String date, String time, int a1, int a2, int a3, int a4, int b1, int b2, int b3, int b4, int c1, int c2, int c3, int c4, int d1, int d2, int d3, int d4, int e1, int e2, int e3, int e4, int f1, int f2, int f3, int f4, int g1, int g2, int g3, int g4, int h1, int h2, int h3, int h4, int i1, int i2, int i3, int i4, int j1, int j2, int j3, int j4) {
        this.id = id;
        this.fromm = fromm;
        this.too = too;
        this.bus = bus;
        this.date = date;
        this.time = time;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.g1 = g1;
        this.g2 = g2;
        this.g3 = g3;
        this.g4 = g4;
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.j1 = j1;
        this.j2 = j2;
        this.j3 = j3;
        this.j4 = j4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFromm() {
        return fromm;
    }

    public void setFromm(String fromm) {
        String oldFromm = this.fromm;
        this.fromm = fromm;
        changeSupport.firePropertyChange("fromm", oldFromm, fromm);
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        String oldToo = this.too;
        this.too = too;
        changeSupport.firePropertyChange("too", oldToo, too);
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        String oldBus = this.bus;
        this.bus = bus;
        changeSupport.firePropertyChange("bus", oldBus, bus);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String oldDate = this.date;
        this.date = date;
        changeSupport.firePropertyChange("date", oldDate, date);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        String oldTime = this.time;
        this.time = time;
        changeSupport.firePropertyChange("time", oldTime, time);
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        int oldA1 = this.a1;
        this.a1 = a1;
        changeSupport.firePropertyChange("A1", oldA1, a1);
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        int oldA2 = this.a2;
        this.a2 = a2;
        changeSupport.firePropertyChange("A2", oldA2, a2);
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        int oldA3 = this.a3;
        this.a3 = a3;
        changeSupport.firePropertyChange("A3", oldA3, a3);
    }

    public int getA4() {
        return a4;
    }

    public void setA4(int a4) {
        int oldA4 = this.a4;
        this.a4 = a4;
        changeSupport.firePropertyChange("A4", oldA4, a4);
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        int oldB1 = this.b1;
        this.b1 = b1;
        changeSupport.firePropertyChange("B1", oldB1, b1);
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        int oldB2 = this.b2;
        this.b2 = b2;
        changeSupport.firePropertyChange("B2", oldB2, b2);
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        int oldB3 = this.b3;
        this.b3 = b3;
        changeSupport.firePropertyChange("B3", oldB3, b3);
    }

    public int getB4() {
        return b4;
    }

    public void setB4(int b4) {
        int oldB4 = this.b4;
        this.b4 = b4;
        changeSupport.firePropertyChange("B4", oldB4, b4);
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        int oldC1 = this.c1;
        this.c1 = c1;
        changeSupport.firePropertyChange("C1", oldC1, c1);
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        int oldC2 = this.c2;
        this.c2 = c2;
        changeSupport.firePropertyChange("C2", oldC2, c2);
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        int oldC3 = this.c3;
        this.c3 = c3;
        changeSupport.firePropertyChange("C3", oldC3, c3);
    }

    public int getC4() {
        return c4;
    }

    public void setC4(int c4) {
        int oldC4 = this.c4;
        this.c4 = c4;
        changeSupport.firePropertyChange("C4", oldC4, c4);
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        int oldD1 = this.d1;
        this.d1 = d1;
        changeSupport.firePropertyChange("D1", oldD1, d1);
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        int oldD2 = this.d2;
        this.d2 = d2;
        changeSupport.firePropertyChange("D2", oldD2, d2);
    }

    public int getD3() {
        return d3;
    }

    public void setD3(int d3) {
        int oldD3 = this.d3;
        this.d3 = d3;
        changeSupport.firePropertyChange("D3", oldD3, d3);
    }

    public int getD4() {
        return d4;
    }

    public void setD4(int d4) {
        int oldD4 = this.d4;
        this.d4 = d4;
        changeSupport.firePropertyChange("D4", oldD4, d4);
    }

    public int getE1() {
        return e1;
    }

    public void setE1(int e1) {
        int oldE1 = this.e1;
        this.e1 = e1;
        changeSupport.firePropertyChange("E1", oldE1, e1);
    }

    public int getE2() {
        return e2;
    }

    public void setE2(int e2) {
        int oldE2 = this.e2;
        this.e2 = e2;
        changeSupport.firePropertyChange("E2", oldE2, e2);
    }

    public int getE3() {
        return e3;
    }

    public void setE3(int e3) {
        int oldE3 = this.e3;
        this.e3 = e3;
        changeSupport.firePropertyChange("E3", oldE3, e3);
    }

    public int getE4() {
        return e4;
    }

    public void setE4(int e4) {
        int oldE4 = this.e4;
        this.e4 = e4;
        changeSupport.firePropertyChange("E4", oldE4, e4);
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        int oldF1 = this.f1;
        this.f1 = f1;
        changeSupport.firePropertyChange("F1", oldF1, f1);
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        int oldF2 = this.f2;
        this.f2 = f2;
        changeSupport.firePropertyChange("F2", oldF2, f2);
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        int oldF3 = this.f3;
        this.f3 = f3;
        changeSupport.firePropertyChange("F3", oldF3, f3);
    }

    public int getF4() {
        return f4;
    }

    public void setF4(int f4) {
        int oldF4 = this.f4;
        this.f4 = f4;
        changeSupport.firePropertyChange("F4", oldF4, f4);
    }

    public int getG1() {
        return g1;
    }

    public void setG1(int g1) {
        int oldG1 = this.g1;
        this.g1 = g1;
        changeSupport.firePropertyChange("G1", oldG1, g1);
    }

    public int getG2() {
        return g2;
    }

    public void setG2(int g2) {
        int oldG2 = this.g2;
        this.g2 = g2;
        changeSupport.firePropertyChange("G2", oldG2, g2);
    }

    public int getG3() {
        return g3;
    }

    public void setG3(int g3) {
        int oldG3 = this.g3;
        this.g3 = g3;
        changeSupport.firePropertyChange("G3", oldG3, g3);
    }

    public int getG4() {
        return g4;
    }

    public void setG4(int g4) {
        int oldG4 = this.g4;
        this.g4 = g4;
        changeSupport.firePropertyChange("G4", oldG4, g4);
    }

    public int getH1() {
        return h1;
    }

    public void setH1(int h1) {
        int oldH1 = this.h1;
        this.h1 = h1;
        changeSupport.firePropertyChange("H1", oldH1, h1);
    }

    public int getH2() {
        return h2;
    }

    public void setH2(int h2) {
        int oldH2 = this.h2;
        this.h2 = h2;
        changeSupport.firePropertyChange("H2", oldH2, h2);
    }

    public int getH3() {
        return h3;
    }

    public void setH3(int h3) {
        int oldH3 = this.h3;
        this.h3 = h3;
        changeSupport.firePropertyChange("H3", oldH3, h3);
    }

    public int getH4() {
        return h4;
    }

    public void setH4(int h4) {
        int oldH4 = this.h4;
        this.h4 = h4;
        changeSupport.firePropertyChange("H4", oldH4, h4);
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        int oldI1 = this.i1;
        this.i1 = i1;
        changeSupport.firePropertyChange("I1", oldI1, i1);
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        int oldI2 = this.i2;
        this.i2 = i2;
        changeSupport.firePropertyChange("I2", oldI2, i2);
    }

    public int getI3() {
        return i3;
    }

    public void setI3(int i3) {
        int oldI3 = this.i3;
        this.i3 = i3;
        changeSupport.firePropertyChange("I3", oldI3, i3);
    }

    public int getI4() {
        return i4;
    }

    public void setI4(int i4) {
        int oldI4 = this.i4;
        this.i4 = i4;
        changeSupport.firePropertyChange("I4", oldI4, i4);
    }

    public int getJ1() {
        return j1;
    }

    public void setJ1(int j1) {
        int oldJ1 = this.j1;
        this.j1 = j1;
        changeSupport.firePropertyChange("J1", oldJ1, j1);
    }

    public int getJ2() {
        return j2;
    }

    public void setJ2(int j2) {
        int oldJ2 = this.j2;
        this.j2 = j2;
        changeSupport.firePropertyChange("J2", oldJ2, j2);
    }

    public int getJ3() {
        return j3;
    }

    public void setJ3(int j3) {
        int oldJ3 = this.j3;
        this.j3 = j3;
        changeSupport.firePropertyChange("J3", oldJ3, j3);
    }

    public int getJ4() {
        return j4;
    }

    public void setJ4(int j4) {
        int oldJ4 = this.j4;
        this.j4 = j4;
        changeSupport.firePropertyChange("J4", oldJ4, j4);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mistresult.Booking[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
